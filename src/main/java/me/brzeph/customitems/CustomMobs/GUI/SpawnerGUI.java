package me.brzeph.customitems.CustomMobs.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

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
        player.openInventory(inventory);
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
            event.setCancelled(true);
        }
    }
}
