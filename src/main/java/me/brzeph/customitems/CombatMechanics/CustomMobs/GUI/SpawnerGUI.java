package me.brzeph.customitems.CombatMechanics.CustomMobs.GUI;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTTileEntity;
import me.brzeph.customitems.CombatMechanics.CombatSystem.CustomMobsListEnum2;
import me.brzeph.customitems.CombatMechanics.CustomMobs.SpawnerFunctionality;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static me.brzeph.customitems.CombatMechanics.CustomMobs.GUI.ChangeMaxAmountOfMobsGUI.changeMaxAmountOfMobsOpenGui;
import static me.brzeph.customitems.CombatMechanics.CustomMobs.GUI.ChangeMobTypeGUI.changeMobTypeOpenGUI;
import static me.brzeph.customitems.CombatMechanics.CustomMobs.GUI.ChangeRespawnRateGUI.changeRespawnRateOpenGUIPage1;
import static me.brzeph.customitems.CombatMechanics.CustomMobs.GUI.ChangeSpawnRadiusGUI.changeSpawnerRadiusOpenGUI;
import static me.brzeph.customitems.CombatMechanics.CustomMobs.GUI.ChangeTierGUI.changeTierOpenGUI;

public class SpawnerGUI implements Listener {
    public static void spawnerOpenGUI(Player player){
        Inventory inventory = Bukkit.createInventory(player, 18, "§0Mob Spawner GUI");

        inventory.setItem(0, changeTier());                  //working
        inventory.setItem(1, changeMobType());               //working
        inventory.setItem(2, changeRespawnRate());           //working
        inventory.setItem(3, changeMaxAmountOfMobs());       //working
        inventory.setItem(4, changeSpawnRadius());           //working
        inventory.setItem(7, spawnerInfo(player));           //working
        inventory.setItem(8, deleteCurrentSpawnerData());    //working
        inventory.setItem(16, registerSpawner());            //working
        inventory.setItem(17, unRegisterSpawner());          //working
        player.openInventory(inventory);
    }

    private static ItemStack deleteCurrentSpawnerData() {
        ItemStack itemStack = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§aRemove current spawner data");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§cSpawner needs to be unregistered before deleting the data");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack changeSpawnRadius() {
        ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Change spawner spawn radius");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§4No value limits");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack spawnerInfo(Player player) {
        Block block = SharedData.callingBlock.get(player);
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        int tier = nbtTileEntity.getPersistentDataContainer().getInteger("tier");
        int respawnRate = nbtTileEntity.getPersistentDataContainer().getInteger("respawnRate");
        int maxAmountOfMobs = nbtTileEntity.getPersistentDataContainer().getInteger("maxAmountOfMobs");
        int size = nbtTileEntity.getPersistentDataContainer().getInteger("size");

        ItemStack itemStack = new ItemStack(Material.WHITE_WOOL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Spawner info");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§fSpawner tier: " + tier);
        lore.add("§fSpawner cooldown to spawn: " + respawnRate + " seconds");
        lore.add("§fSpawner mob cap: " + maxAmountOfMobs);
        lore.add("§fSpawner radius: " + size);
        lore.add("");
        lore.add("§6Spawner mob list: ");

        int i = 1;
        for (i = 1; i <= CustomMobsListEnum2.values().length; i++){
            int k = nbtTileEntity.getPersistentDataContainer().getInteger(String.valueOf(i));
            if (k != 0){
                for (CustomMobsListEnum2 mob : CustomMobsListEnum2.values()) {
                    if (mob.getUniqueMobID() == i) {
                        lore.add(mob.getName() + " " + k + "%");
                    }
                }
            }
        }

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack registerSpawner() {
        ItemStack itemStack = new ItemStack(Material.GREEN_WOOL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Register spawner");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack unRegisterSpawner() {
        ItemStack itemStack = new ItemStack(Material.RED_WOOL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Unregister the spawner");
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
        ItemStack itemStack = new ItemStack(Material.CREEPER_HEAD);
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
                return;
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Change spawner Tier")){
                changeTierOpenGUI(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Change mob type")){
                changeMobTypeOpenGUI(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Change respawn rate")){
                changeRespawnRateOpenGUIPage1(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Change max amount of mobs")){
                changeMaxAmountOfMobsOpenGui(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Unregister the spawner")){
                SpawnerFunctionality.getInstance().unRegisterSpawner(block);
                player.closeInventory();
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Register spawner")){
                SpawnerFunctionality.getInstance().registerSpawner(block);
                player.closeInventory();
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Change spawner spawn radius")){
                changeSpawnerRadiusOpenGUI(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aRemove current spawner data")){
                deleteSpawnerData(block);
                player.closeInventory();
            }
            event.setCancelled(true);
        }
    }

    private void deleteSpawnerData(Block block) {
        NBT.modifyPersistentData(block.getState(), nbt ->{
            nbt.setInteger("tier", 1);
            nbt.setInteger("mobType", 1);
            nbt.setInteger("respawnRate", 5);
            nbt.setInteger("maxAmountOfMobs", 5);
            nbt.setInteger("size", 1);
        });
        resetMobsToSpawn(block);
    }
    public void resetMobsToSpawn(Block block){
        NBT.modifyPersistentData(block.getState(), nbt ->{
            int i;
            for (i = 1; i <= CustomMobsListEnum2.values().length; i++){
                if (nbt.getInteger("" + i) != 0){
                    nbt.setInteger("" + i, 0);
                }
            }
        });

    }
}
