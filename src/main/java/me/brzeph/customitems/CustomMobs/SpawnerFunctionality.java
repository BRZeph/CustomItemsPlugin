package me.brzeph.customitems.CustomMobs;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTTileEntity;
import me.brzeph.customitems.Main;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

import static me.brzeph.customitems.CustomItemList.CustomArmor.GeneratingArmor.CustomArmorBoots.createT1Boots;
import static me.brzeph.customitems.CustomItemList.CustomArmor.UpdatingArmorLore.upgradingArmorLore;
import static org.bukkit.Bukkit.getServer;

public class SpawnerFunctionality implements Listener {
    public static SpawnerFunctionality getInstance() {return instance;}
    public static SpawnerFunctionality instance;
    public Map<Entity, CustomMobsListEnum> entities = new HashMap<>();
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
                getServer().getConsoleSender().sendMessage("[DEBUG] list size: " + spawnerList.size());
                for (Location spawnerLocation : spawnerList.keySet()) {
                    Set<Entity> spawned = entitiesMap.get(spawnerLocation);
                    NBTTileEntity nbtTileEntity = new NBTTileEntity(spawnerLocation.getBlock().getState());
                    UUID uuid = nbtTileEntity.getPersistentDataContainer().getUUID("randomID");
                    int mobCap = nbtTileEntity.getPersistentDataContainer().getInteger("maxAmountOfMobs");
                    int size = nbtTileEntity.getPersistentDataContainer().getInteger("size");
                    int i = nbtTileEntity.getPersistentDataContainer().getInteger("respawnRate");

                    getServer().getConsoleSender().sendMessage("[DEBUG1]: " + tickCount.get(spawnerLocation) + "/" + i);

                    CustomMobsListEnum[] mobTypes = CustomMobsListEnum.values();

                    // Handle tracking dead mobs for the mob cap
                    List<Entity> deadEntities = new ArrayList<>();
                    for (Entity entity : spawned) {
                        if (!entity.isValid() || entity.isDead()) {
                            deadEntities.add(entity);
                        } //this seems to be useless
                    }
                    deadEntities.forEach(spawned::remove);
                    int missingMobs = mobCap - spawned.size();
                    if (missingMobs <= 0) continue;

                    if (tickCount.get(spawnerLocation) >= i){
                        tickCount.put(spawnerLocation, 1);

                        int count = 0;

                        while (count < missingMobs) {
                            count++;
                            int randomX = (int) (getRandomWithNegative(size) + spawnerLocation.getX());
                            int randomZ = (int) (getRandomWithNegative(size) + spawnerLocation.getZ());
                            Block block = Main.getInstance().world.getHighestBlockAt(randomX, randomZ);
                            double xOffset = randomOffset();
                            double zOffset = randomOffset();
                            Location location1 = block.getLocation().clone().add(xOffset,1,zOffset);
                            if (!isSpawnable(location1)) continue;

                            double random = Math.random() * 101, previous = 0;
                            CustomMobsListEnum typeToSpawn = mobTypes[0];

                            for (CustomMobsListEnum type : mobTypes) {
                                previous += type.getSpawnChance();
                                if (random <= previous) {
                                    typeToSpawn = type;
                                    break;
                                }
                            }
                            // Spawn the mob and add it to the set of spawned entities
                            Entity spawnedEntity = typeToSpawn.spawn(location1);
                            NBT.modifyPersistentData(spawnedEntity, nbt -> {
                                nbt.setUUID("randomID", uuid);
                            });
                            spawned.add(spawnedEntity);

                            // This handles the mob cap
                            if (spawned.size() >= mobCap) break;
                        }
                    } else {
                        tickCount.put(spawnerLocation, tickCount.get(spawnerLocation) + 1);
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20);
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
        entities.remove(event.getEntity());
        event.getDrops().add(upgradingArmorLore(createT1Boots()));
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        Entity rawEntity = event.getEntity();
        if (!entities.containsKey(rawEntity)) return;
        CustomMobsListEnum mob = entities.get(rawEntity);
        LivingEntity entity = (LivingEntity) rawEntity;
        double damage = event.getFinalDamage();
        double health = entity.getHealth() + entity.getAbsorptionAmount();
        if (health > damage){ //checks if the entity survived
            health -= damage;
            entity.setCustomName(mob.getName() + " " + (int) health + "/" + (int) mob.getMaxHealth() + "♥");
        }
        Location loc = entity.getLocation().clone().add(randomOffset(), 1, randomOffset());
        Main.getInstance().world.spawn(loc, ArmorStand.class, armorStand -> {
            armorStand.setMarker(true);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName("&c" + Main.getInstance().formatter.format(damage));
            Main.getInstance().indicators.put(armorStand, 20*2); //armorStand will last 2 seconds
        });

    }
}