package me.brzeph.customitems.CombatMechanics.CustomMobs;

import de.tr7zw.nbtapi.*;
import me.brzeph.customitems.CombatMechanics.CombatSystem.CustomMobsListEnum2;
import me.brzeph.customitems.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

import static me.brzeph.customitems.Utils.Utils.getRandomValue;
import static org.bukkit.Bukkit.getServer;

public class SpawnerFunctionality implements Listener {
    public static synchronized SpawnerFunctionality getInstance() {
        if (instance == null) {
            instance = new SpawnerFunctionality();
        }
        return instance;
    }
    public static SpawnerFunctionality instance = new SpawnerFunctionality();
    public Map<Location, Set<Entity>> entitiesMap = new HashMap<>();
    public Map<Location, Integer> tickCount = new HashMap<>();
    public HashMap<Location, Block> spawnerList = new HashMap<>();
    public void registerSpawner(Block spawner){
        spawnerList.put(spawner.getLocation(), spawner);
        entitiesMap.put(spawner.getLocation(), new HashSet<>());
        tickCount.put(spawner.getLocation(), 1000);
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
                    int mobRangeLimit = nbtTileEntity.getPersistentDataContainer().getInteger("mobRangeLimit");
                    int playerDistanceToSpawn = nbtTileEntity.getPersistentDataContainer().getInteger("playerDistanceToSpawn");
                    UUID uuid = nbtTileEntity.getPersistentDataContainer().getUUID("randomID");
                    boolean playerNearby = false;
                    List<Entity> nearbyEntities = new ArrayList<>(spawnerLocation.getWorld().getNearbyLivingEntities(spawnerLocation, playerDistanceToSpawn, 4, playerDistanceToSpawn));

                    for (Entity entity : spawned) {
                        NBTEntity nbtEntity = new NBTEntity(entity);
                        World flatworld = entity.getWorld();
                        int XPos = nbtEntity.getPersistentDataContainer().getInteger("XPos");
                        int YPos = nbtEntity.getPersistentDataContainer().getInteger("YPos");
                        int ZPos = nbtEntity.getPersistentDataContainer().getInteger("ZPos");
                        Location mobLocation = entity.getLocation();
                        Location spawnerLocationFromMob = new Location(flatworld, XPos, YPos, ZPos);
                        double distance = mobLocation.distance(spawnerLocationFromMob);
                        if (distance >= mobRangeLimit) {
                            entity.teleport(spawnerLocationFromMob.add(size*randomOffset(), 0, size*randomOffset()));
                        }
                    }
                    for (Entity entity: nearbyEntities){
                        if (entity instanceof Player) {
                            playerNearby = true;
                            break;
                        }
                    }
                    if (playerNearby) {
                        List<Entity> deadEntities = new ArrayList<>();
                        for (Entity entity : spawned) {
                            if (!entity.isValid() || entity.isDead()) {
                                deadEntities.add(entity);
                            }
                        }
                        deadEntities.forEach(spawned::remove);
                        int missingMobs = mobCap - spawned.size();
                        if (missingMobs <= 0) continue;
                        if (tickCount.get(spawnerLocation) >= respawnRate) {
                            tickCount.put(spawnerLocation, 1);

                            int count = 0;

                            while (count < missingMobs) {
                                count++;
                                int randomX = (int) (getRandomWithNegative(size) + spawnerLocation.getX());
                                int randomZ = (int) (getRandomWithNegative(size) + spawnerLocation.getZ());
                                Block block = Main.getInstance().flatworld.getHighestBlockAt(randomX, randomZ);
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
                                        nbt.setInteger("XPos", spawnerLocation.getBlockX());
                                        nbt.setInteger("YPos", spawnerLocation.getBlockY());
                                        nbt.setInteger("ZPos", spawnerLocation.getBlockZ());
                                    });
                                    spawned.add(spawnedEntity);

                                    if (spawned.size() >= mobCap) break;
                                }
                            }
                        } else {
                            if (tickCount.get(spawnerLocation) > respawnRate){
                                tickCount.put(spawnerLocation, respawnRate + 1);

                            } else {
                                tickCount.put(spawnerLocation, tickCount.get(spawnerLocation) + 1);
                            }
                        }
                    } else {
                        if (tickCount.get(spawnerLocation) > respawnRate){
                            tickCount.put(spawnerLocation, respawnRate + 1);
                        } else {
                            tickCount.put(spawnerLocation, tickCount.get(spawnerLocation) + 1);
                        }
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
    public int getRandomWithNegative(int size){
        int random = (int) (Math.random()*(size + 1));
        if (Math.random() > 0.5 ) random *= -1;
        return random;
    }
    public static double randomOffset(){
        double random =Math.random();
        if (Math.random() > 0.5 ) random *= -1;
        return random;
    }
    @EventHandler
    public void onServerStart(ServerLoadEvent event){
        spawnMobs();
        for (Location location : Main.getInstance().loadedLocations) {
            registerSpawner(location.getBlock());
        }
    }
}