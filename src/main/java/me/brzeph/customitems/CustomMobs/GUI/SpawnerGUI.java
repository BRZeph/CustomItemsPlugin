package me.brzeph.customitems.CustomMobs.GUI;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTTileEntity;
import me.brzeph.customitems.CustomMobs.CustomMobsListEnum;
import me.brzeph.customitems.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

import static me.brzeph.customitems.CustomMobs.GUI.ChangeMaxAmountOfMobsGUI.changeMaxAmountOfMobsOpenGui;
import static me.brzeph.customitems.CustomMobs.GUI.ChangeMobTypeGUI.changeMobTypeOpenGUI;
import static me.brzeph.customitems.CustomMobs.GUI.ChangeRespawnRateGUI.changeRespawnRateOpenGUIPage1;
import static me.brzeph.customitems.CustomMobs.GUI.ChangeTierGUI.changeTierOpenGUI;
import static org.bukkit.Bukkit.getServer;

public class SpawnerGUI implements Listener {
    public static void spawnerOpenGUI(Player player){
        Inventory inventory = Bukkit.createInventory(player, 9, "§0Mob Spawner GUI");

        inventory.setItem(0, changeTier());
        inventory.setItem(1, changeMobType());
        inventory.setItem(2, changeRespawnRate());
        inventory.setItem(3, changeMaxAmountOfMobs());
        inventory.setItem(5, initializedSpawner());
        inventory.setItem(6, stopSpawner());
        inventory.setItem(8, registerspawner());
        player.openInventory(inventory);
    }

    private static ItemStack registerspawner() {
        ItemStack itemStack = new ItemStack(Material.CYAN_WOOL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("register spawner");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack stopSpawner() {
        ItemStack itemStack = new ItemStack(Material.RED_WOOL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Stop the spawner");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack initializedSpawner() {
        ItemStack itemStack = new ItemStack(Material.GREEN_WOOL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Initialize the spawner");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack changeTier() {
        ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Change spawner Tier");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§4values range between 1 and 5");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private static ItemStack changeMobType() {
        ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Change mob type");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§4values range between 1 and 2"); //1 = zombie, 2 = skeleton
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private static ItemStack changeRespawnRate() {
        ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Change respawn rate");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§4No upper limit yet");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack changeMaxAmountOfMobs() {
        ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Change max amount of mobs");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§4values range between 1 and does not have an upper limit yet");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    @EventHandler
    public void onMenuTierClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Block block = SharedData.callingBlock.get(player);
        if (event.getView().getTitle().equalsIgnoreCase("§0Mob Spawner GUI")) {
            if (event.getCurrentItem() == null) {
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on null");
                return;
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Change spawner Tier")){
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on change tier");
                changeTierOpenGUI(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Change mob type")){
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on change mob type");
                changeMobTypeOpenGUI(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Change respawn rate")){
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on change respawn rate");
                changeRespawnRateOpenGUIPage1(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Change max amount of mobs")){
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on change max amount of mobs");
                changeMaxAmountOfMobsOpenGui(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Initialize the spawner")){
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on change initialize spawner");
                spawnMobs();
                player.closeInventory();
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Stop the spawner")){
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on stop spawner");




                player.closeInventory();
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("register spawner")){
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on register spawner");
                registerSpawner(block);
                player.closeInventory();

            }




            event.setCancelled(true);
        }
    }
    public Map<Entity, CustomMobsListEnum> entities = new HashMap<>();
    public HashMap<Location, Block> spawnerList = new HashMap<>();
    public Map<Location, Set<Entity>> entitiesMap = new HashMap<>();
    public Map<Location, List<Entity>> removalMap = new HashMap<>();
    public Map<Location, Integer> tickCount = new HashMap<>();
    BukkitTask task;
    public void registerSpawner(Block spawner){
        spawnerList.put(spawner.getLocation(), spawner);
        entitiesMap.put(spawner.getLocation(), new HashSet<>());
        removalMap.put(spawner.getLocation(), new ArrayList<>());
        tickCount.put(spawner.getLocation(), 1);
    }

    public void spawnMobs() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Location spawnerLocation : spawnerList.keySet()) {
                    Set<Entity> spawned = entitiesMap.get(spawnerLocation);
                    List<Entity> removal = removalMap.get(spawnerLocation);
                    NBTTileEntity nbtTileEntity = new NBTTileEntity(spawnerLocation.getBlock().getState());
                    UUID uuid = nbtTileEntity.getPersistentDataContainer().getUUID("randomID");
                    getServer().getConsoleSender().sendMessage("[DEBUG]: spawner uuid-> " + uuid);
                    int mobCap = nbtTileEntity.getPersistentDataContainer().getInteger("maxAmountOfMobs");
                    int size = nbtTileEntity.getPersistentDataContainer().getInteger("size");
                    int i = nbtTileEntity.getPersistentDataContainer().getInteger("respawnRate");

                    getServer().getConsoleSender().sendMessage("[DEBUG1]: " + tickCount.get(spawnerLocation));

                    CustomMobsListEnum[] mobTypes = CustomMobsListEnum.values();

                    // Handle tracking dead mobs for the mob cap
                    List<Entity> deadEntities = new ArrayList<>();
                    for (Entity entity : spawned) {
                        if (!entity.isValid() || entity.isDead()) {
                            removal.add(entity);
                            deadEntities.add(entity);
                        } //this seems to be useless
                    }
                    spawned.removeAll(deadEntities);
                    int missingMobs = mobCap - spawned.size();
                    if (missingMobs <= 0) continue;

                    if (tickCount.get(spawnerLocation) < i){
                        tickCount.put(spawnerLocation, tickCount.get(spawnerLocation) + 1);
                        return;
                    }

                    getServer().getConsoleSender().sendMessage("[DEBUG2]");

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
                        getServer().getConsoleSender().sendMessage("[DEBUG3]");

                        double random = Math.random() * 101, previous = 0;
                        CustomMobsListEnum typeToSpawn = mobTypes[0];

                        for (CustomMobsListEnum type : mobTypes) {
                            previous += type.getSpawnChance();
                            if (random <= previous) {
                                typeToSpawn = type;
                                break;
                            }
                        }
                        getServer().getConsoleSender().sendMessage("[DEBUG4]");
                        // Spawn the mob and add it to the set of spawned entities
                        Entity spawnedEntity = typeToSpawn.spawn(location1);
                        NBT.modifyPersistentData(spawnedEntity, nbt -> {
                            nbt.setUUID("randomID", uuid);
                        });
                        spawned.add(spawnedEntity);

                        // This handles the mob cap
                        if (spawned.size() >= mobCap) break;
                    }
                    getServer().getConsoleSender().sendMessage("[DEBUG5]");
                    tickCount.put(spawnerLocation, 1);
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20);
    }

    public void stoppingTask(){
            getServer().getConsoleSender().sendMessage("[DEBUG]: if there's an error here, update code");
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
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
}
