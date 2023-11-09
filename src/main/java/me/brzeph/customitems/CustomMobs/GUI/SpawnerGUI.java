package me.brzeph.customitems.CustomMobs.GUI;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTTileEntity;
import me.brzeph.customitems.CustomMobs.CustomMobsListEnum;
import me.brzeph.customitems.CustomMobs.SpawnerRegistry;
import me.brzeph.customitems.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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

import static me.brzeph.customitems.CustomItemList.CustomArmor.GeneratingArmor.CustomArmorBoots.createT1Boots;
import static me.brzeph.customitems.CustomItemList.CustomArmor.UpdatingArmorLore.upgradingArmorLore;
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
        player.openInventory(inventory);
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
                Block block = SharedData.callingBlock.get(player);
                Location location = block.getLocation();
                Block block1 = SpawnerRegistry.spawnerList.get(location);
                NBTTileEntity nbtTileEntity = new NBTTileEntity(block1.getState());
                int tier = nbtTileEntity.getPersistentDataContainer().getInteger("tier");
                int spawnTime = nbtTileEntity.getPersistentDataContainer().getInteger("respawnRate");
                int mobCap = nbtTileEntity.getPersistentDataContainer().getInteger("maxAmountOfMobs");
                int size = nbtTileEntity.getPersistentDataContainer().getInteger("size");
                spawnMobs(size, mobCap, spawnTime, tier, location);
                player.closeInventory();
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Stop the spawner")){
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on stop spawner");
                stoppingTask();
                player.closeInventory();
            }
            event.setCancelled(true);
        }
    }
    public BukkitTask task;
    public Map<Entity, CustomMobsListEnum> entities = new HashMap<>();
    public void spawnMobs(int size, int mobCap, int spawnTime, int tier, Location spawnerLocation){
        CustomMobsListEnum[] mobTypes = CustomMobsListEnum.values();
        task = new BukkitRunnable(){
            Set<Entity> spawned = entities.keySet();
            List<Entity> removal = new ArrayList<>();
            @Override
            public void run() { //spawning algorithm
                for (Entity entity : spawned){
                    if (!entity.isValid() || entity.isDead()) removal.add(entity);
                } //this handles tracking dead mobs for the mob cap
                spawned.removeAll(removal);
//TODO: make the spawner require the input of ''block'', then get all of the other information from the block and put them here, after the run()
                //TODO: create a hashmap that tracks taskID and block spawner
                //TODO: make everything related to mob spawning be based of the block spawner that will be grabbed from taskID maybe put a uniqueID on the block
                int missingMobs = mobCap - entities.size();
                if (missingMobs <= 0) return;
                int spawnAmount = (int) (Math.random()*(missingMobs + 1)), count = 0;
                getServer().getConsoleSender().sendMessage("[DEBUG]: spawn amount value: " + spawnAmount);
                while (count <= spawnAmount){
                    count++;
                    int randomX = (int) (getRandomWithNegative(size) + spawnerLocation.getX());
                    int randomZ = (int) (getRandomWithNegative(size) + spawnerLocation.getZ());
                    Block block = Main.getInstance().world.getHighestBlockAt(randomX, randomZ);
                    double xOffset = randomOffset();
                    double zOffset = randomOffset();
                    Location location = block.getLocation().clone().add(xOffset,1,zOffset);
                    if (!isSpawnable(location)) continue;
                    double random = Math.random()*101, previous = 0;
                    CustomMobsListEnum typeToSpawn = mobTypes[0];
                    for (CustomMobsListEnum type : mobTypes){
                        previous += type.getSpawnChance();
                        if (random <= previous){ //48:10 on the video
                            typeToSpawn = type;
                            break;
                        }
                    }
                    entities.put(typeToSpawn.spawn(location), typeToSpawn); //this handles the mob cap
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, spawnTime); //every [spawnTime] seconds will spawn 0 ~ [mobCap] mobs in random amounts
    }
    public void stoppingTask(){
        task.cancel();
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!entities.containsKey(event.getEntity())) return;
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
