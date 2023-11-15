package me.brzeph.customitems.CombatMechanics.CustomMobs.GUI;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ChangeRespawnRateGUI implements Listener {
    public static void changeRespawnRateOpenGUIPage1(Player player){
        Inventory inventory = Bukkit.createInventory(player, 54, "§0Choose respawn rate GUI page 1");
        int i;
        inventory.setItem(0, changeRespawnRateTo_1());
        for (i = 1; i <= 52; i++) {
            inventory.setItem(i, changeRespawnRateTo(i));
        }
        inventory.setItem(53, goToSecondPage());
        player.openInventory(inventory);
    }

    private static ItemStack changeRespawnRateTo_1() {
        ItemStack itemStack = new ItemStack(Material.CLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        double m =(double)1/12;
        String mFormatted = String.format("%.2f", m);
        itemMeta.setDisplayName("Change the respawn Rate to §c1§f seconds");
        List<String> lore = new ArrayList<>();
        lore.add("§fTime in minutes: §c" + mFormatted);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack changeRespawnRateTo(int i) {
        ItemStack itemStack = new ItemStack(Material.CLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        double m =(double)i/12;
        String mFormatted = String.format("%.2f", m);
        itemMeta.setDisplayName("Change the respawn Rate to §c" + 5*i + "§f seconds");
        List<String> lore = new ArrayList<>();
        lore.add("§fTime in minutes: §c" + mFormatted);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack goToSecondPage() {
        ItemStack itemStack = new ItemStack(Material.ARROW);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Go to second page");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    @EventHandler
    public void onRespawnRateChangePage1MenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase("§0Choose respawn rate GUI page 1")) {
            int i;
            if (event.getCurrentItem() != null) {
                if (event.getCurrentItem().getType() == Material.CLOCK) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Change the respawn Rate to §c1§f seconds")) {
                        spawnerChangeRespawnRateTo_1(player);
                    }
                    for (i = 1; i <= 52; i++) {
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Change the respawn Rate to §c" + 5 * i + "§f seconds")) {
                            spawnerChangeRespawnRate(i, player);
                        }
                        player.closeInventory();
                        event.setCancelled(true);
                    }
                }else if (event.getCurrentItem().getType() == Material.ARROW){
                    changeRespawnRateOpenGUIPage2(player);
                    event.setCancelled(true);
                }
            }
            event.setCancelled(true);
        }
    }

    private void spawnerChangeRespawnRateTo_1(Player player) {
        Block block = SharedData.callingBlock.get(player);
        NBT.modifyPersistentData(block.getState(), nbt -> {
            nbt.setInteger("respawnRate", 1);
        });
        player.closeInventory();
        player.sendMessage("§aUpdated respawn rate to §c" + 1 + "§a seconds");
    }

    private void spawnerChangeRespawnRate(int i, Player player) {
        Block block = SharedData.callingBlock.get(player);
        NBT.modifyPersistentData(block.getState(), nbt -> {
            nbt.setInteger("respawnRate", 5*i);
        });
        player.closeInventory();
        player.sendMessage("§aUpdated respawn rate to §c" + 5 * i + "§a seconds");
    }
    private void changeRespawnRateOpenGUIPage2(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, "§0Choose respawn rate GUI page 2");
        int i;
        for (i = 0; i <= 53; i++) {
            inventory.setItem(i, changeRespawnRateTo(i + 53));
        }
        player.openInventory(inventory);
    }
    @EventHandler
    public void onRespawnRateChangePage2MenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase("§0Choose respawn rate GUI page 2")) {
            int i;
            if (event.getCurrentItem() != null) {
                if (event.getCurrentItem().getType() == Material.CLOCK) {
                    for (i = 0; i <= 53; i++) {
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Change the respawn Rate to §c" + 5 * (i + 53) + "§f seconds")) {
                            spawnerChangeRespawnRate((i + 53), player);
                        }
                        event.setCancelled(true);
                    }
                }
            }
            event.setCancelled(true);
        }
    }
}
