package me.brzeph.customitems.CustomMobs;

import de.tr7zw.nbtapi.*;
import me.brzeph.customitems.CustomItemList.CustomCombatItems.GeneratingCombatItems.CreateTXArmor;
import me.brzeph.customitems.Main;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

import static me.brzeph.customitems.CustomItemList.CustomCombatItems.UpdatingArmorLore.upgradingArmorLore;
import static me.brzeph.customitems.CustomMobs.CustomMobsListEnum2.returnEntityMaxHP;
import static org.bukkit.Bukkit.getServer;

public class SpawnerFunctionality implements Listener {
    public static SpawnerFunctionality getInstance() {return instance;}
    public static SpawnerFunctionality instance;
    public Map<Location, Set<Entity>> entitiesMap = new HashMap<>();
    public Map<Location, Integer> tickCount = new HashMap<>();
    public HashMap<Location, Block> spawnerList = new HashMap<>();
    public void registerSpawner(Block spawner){
        spawnerList.put(spawner.getLocation(), spawner);
        entitiesMap.put(spawner.getLocation(), new HashSet<>());
        tickCount.put(spawner.getLocation(), 1);
        getServer().getConsoleSender().sendMessage("[DEBUG] spawner uuid: " + new NBTTileEntity(spawner.getState()).getPersistentDataContainer()
                .getUUID("randomID"));
    }
    public void unRegisterSpawner(Block block) {
        spawnerList.remove(block.getLocation(), block);
    }
    BukkitTask task;
    public void spawnMobs() {
        instance = this;
        task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Location spawnerLocation : spawnerList.keySet()) {
                    Set<Entity> spawned = entitiesMap.get(spawnerLocation);
                    NBTTileEntity nbtTileEntity = new NBTTileEntity(spawnerLocation.getBlock().getState());
                    int respawnRate = nbtTileEntity.getPersistentDataContainer().getInteger("respawnRate");
                    int mobCap = nbtTileEntity.getPersistentDataContainer().getInteger("maxAmountOfMobs");
                    int size = nbtTileEntity.getPersistentDataContainer().getInteger("size");
                    UUID uuid = nbtTileEntity.getPersistentDataContainer().getUUID("randomID");

                    getServer().getConsoleSender().sendMessage("[DEBUG1]: " + tickCount.get(spawnerLocation) + "/" + respawnRate);

                    // Handle tracking dead mobs for the mob cap
                    List<Entity> deadEntities = new ArrayList<>();
                    for (Entity entity : spawned) {
                        if (!entity.isValid() || entity.isDead()) {
                            deadEntities.add(entity);
                        }
                    }
                    deadEntities.forEach(spawned::remove);
                    int missingMobs = mobCap - spawned.size();
                    if (missingMobs <= 0) continue;

                    if (tickCount.get(spawnerLocation) >= respawnRate){
                        tickCount.put(spawnerLocation, 1);

                        int count = 0;

                        while (count < missingMobs) {
                            count++;
                            int randomX = (int) (getRandomWithNegative(size) + spawnerLocation.getX());
                            int randomZ = (int) (getRandomWithNegative(size) + spawnerLocation.getZ());
                            Block block = Main.getInstance().world.getHighestBlockAt(randomX, randomZ);
                            double xOffset = randomOffset();
                            double zOffset = randomOffset();
                            Location location1 = block.getLocation().clone().add(xOffset, 1, zOffset);
                            if (!isSpawnable(location1)) continue;

                            CustomMobsListEnum2 checkForFail = choseMobToSpawn(spawnerLocation.getBlock());
                            if (checkForFail == null) {
                                throw new IllegalStateException("Could not find a mob.");
                            } else {
                                Entity spawnedEntity = checkForFail.spawnWithRandomStats(location1);
                                NBT.modifyPersistentData(spawnedEntity, nbt -> {
                                    nbt.setUUID("randomID", uuid);
                                    nbt.setString("customMob", "yes");
                                });
                                spawned.add(spawnedEntity);

                                // This handles the mob cap
                                if (spawned.size() >= mobCap) break;
                            }
                        }
                    } else {
                        tickCount.put(spawnerLocation, tickCount.get(spawnerLocation) + 1);
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20);
    }

    private CustomMobsListEnum2 choseMobToSpawn(Block block) {
        HashMap<Integer, Integer> mobToSpawnList = new HashMap<>();
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        int i;
        for ( i = 1; i <= CustomMobsListEnum2.values().length; i++){
            if (nbtTileEntity.getPersistentDataContainer().getInteger("" + i) != 0){
                mobToSpawnList.put(i, nbtTileEntity.getPersistentDataContainer().getInteger("" + i));
            }
        }
        int selectedKey = selectKey(mobToSpawnList, getRandomValue(100, 1));
        for (CustomMobsListEnum2 mob : CustomMobsListEnum2.values()){
            if (mob.getUniqueMobID() == selectedKey){
                return mob;
            }
        }
        return null;
    }

    private static int selectKey(Map<Integer, Integer> map, int randomValue) {
        int cumulative = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            cumulative += entry.getValue();
            if (randomValue <= cumulative) {
                return entry.getKey();
            }
        }
        throw new IllegalStateException("The mob spawn percentage values do not add to 100%.");
    }

    private boolean isSpawnable(Location location){
        Block feetBlock = location.getBlock();
        Block headBlock = location.clone().add(0,2,0).getBlock();
        Block middleBlock = location.clone().add(0,1,0).getBlock();
        return feetBlock.isPassable() && headBlock.isPassable() && middleBlock.isPassable() &&
                !feetBlock.isLiquid() && !headBlock.isLiquid() && !middleBlock.isLiquid();
    }
    private int getRandomWithNegative(int size){
        int random = (int) (Math.random()*(size + 1));
        if (Math.random() > 0.5 ) random *= -1;
        return random;
    }
    private double randomOffset(){
        double random =Math.random();
        if (Math.random() > 0.5 ) random *= -1;
        return random;
    }
    private static int getRandomValue(int maxValue, int minValue) {
        return new Random().nextInt(maxValue - minValue + 1) + minValue;
    }
    @EventHandler
    public void onServerStart(ServerLoadEvent event){
        spawnMobs();
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity.customName() == null) return;
        event.setDroppedExp(0);
        event.getDrops().clear();
        event.getDrops().add(upgradingArmorLore(CreateTXArmor.createTXBoots(1)));
    }
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        event.setDamage(0);
        Entity entityHitting = event.getDamager();
        Entity entityHitted = event.getEntity();
        if (new NBTEntity(entityHitted).getPersistentDataContainer().getString("customMob").equals("yes")) {
            //player hitting mob situation
            Player player = (Player) entityHitting;
            NBTItem nbtItemPlayer = new NBTItem(player.getItemInHand());
            int damageFromWeapon;
            if (nbtItemPlayer == null){
                damageFromWeapon = 1;
            } else {
                damageFromWeapon = getRandomValue(nbtItemPlayer.getInteger("maxDamage"), nbtItemPlayer.getInteger("minDamage"));
            }
            NBTEntity nbtEntity = new NBTEntity(entityHitted);
            int maxHP = nbtEntity.getPersistentDataContainer().getInteger("maxHP");
            int HPBeforeHit = nbtEntity.getPersistentDataContainer().getInteger("currentHP");
            nbtEntity.getPersistentDataContainer().setInteger("currentHP", HPBeforeHit - damageFromWeapon);
            int HPAfterHit = HPBeforeHit - damageFromWeapon;
//TODO: improve this to better check who's hitting who, implement the situation of PVP
            String name = entityHitted.getName();
            String nameWithoutHealth = name.split(" ♥ ")[0].trim();
            String[] parts = entityHitted.getName().split(" ♥ ");
            String wordsBeforeHeart = parts[0].trim();
            player.sendMessage("§c" + damageFromWeapon + " DMG -> §f " + wordsBeforeHeart + " [" + HPAfterHit + " HP]");
            if (HPBeforeHit > damageFromWeapon) {
                event.setDamage(0);
                entityHitted.setCustomName(nameWithoutHealth + " ♥ " + Main.getInstance().formatter.format(HPAfterHit) + "/" + maxHP + " ♥");
            } else{
                event.setDamage(50); //hard coding to kill the mob to prevent math issues due to the damage being INT and the health being FLOAT -> INT
            }
            Location loc = entityHitted.getLocation().clone().add(randomOffset(), 1, randomOffset());
            Main.getInstance().world.spawn(loc, ArmorStand.class, armorStand -> {
                armorStand.setMarker(true);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setSmall(true);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName("&c" + Main.getInstance().formatter.format(damageFromWeapon));
                Main.getInstance().indicators.put(armorStand, 20 * 2); //armorStand will last 2 seconds
                });
        }
        if (entityHitted instanceof Player){
            getServer().getConsoleSender().sendMessage("[DEBUG]: running mob hitting player event");
            //mob hitting player situation
            Player player = (Player) entityHitted;
            LivingEntity mob = (LivingEntity) entityHitting;
            NBTItem nbtItemMob = new NBTItem(mob.getEquipment().getItemInMainHand());
            int damageFromWeapon;
            if (nbtItemMob == null){
                damageFromWeapon = 1;
            } else {
                damageFromWeapon = getRandomValue(nbtItemMob.getInteger("maxDamage"), nbtItemMob.getInteger("minDamage"));
            }
            NBTEntity nbtEntityPlayer = new NBTEntity(player);
            NBTCompound playerData = nbtEntityPlayer.getPersistentDataContainer();
            float maxHP = playerData.getFloat("currentMaxHealth");
            float HPBeforeHit = playerData.getFloat("currentHP");
            float HPAfterHit = HPBeforeHit - damageFromWeapon;
            playerData.setFloat("currentHP", HPAfterHit);
            nbtEntityPlayer.mergeCompound(playerData);
            float damageOnHealth = 10*damageFromWeapon/maxHP;

            String[] parts = mob.getName().split(" ♥ ");
            String wordsBeforeHeart = parts[0].trim();
            player.sendMessage("§c-" + damageFromWeapon + "HP (" + wordsBeforeHeart + ")" + " §a[" + HPAfterHit + " HP]");
            if (HPAfterHit <= 0){
                event.setDamage(50);
            } else {
                player.damage(damageOnHealth);
            }
        }
    }
}