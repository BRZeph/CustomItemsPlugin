package me.brzeph.customitems.CombatMechanics.CustomMobs;

import de.tr7zw.nbtapi.*;
import me.brzeph.customitems.CombatMechanics.CombatSystem.CustomMobsListEnum2;
import me.brzeph.customitems.Main;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

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
    public static int getRandomValue(int maxValue, int minValue) {
        return new Random().nextInt(maxValue - minValue + 1) + minValue;
    }
    @EventHandler
    public void onServerStart(ServerLoadEvent event){
        spawnMobs();
    }
}