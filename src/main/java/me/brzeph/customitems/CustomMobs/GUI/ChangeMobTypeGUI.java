package me.brzeph.customitems.CustomMobs.GUI;

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

import static org.bukkit.Bukkit.getServer;

public class ChangeMobTypeGUI implements Listener {
    public static void changeMobTypeOpenGUI(Player player){
        Inventory inventory = Bukkit.createInventory(player, 9, "§0Choose mob type GUI");

        inventory.setItem(0, changeSpawnerMobTypeToZombie());
        inventory.setItem(1, changeSpawnerMobTypeToSkeleton());
        player.openInventory(inventory);
    }
    private static ItemStack changeSpawnerMobTypeToZombie() {
        ItemStack itemStack = new ItemStack(Material.ZOMBIE_HEAD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Zombie");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private static ItemStack changeSpawnerMobTypeToSkeleton() {
        ItemStack itemStack = new ItemStack(Material.SKELETON_SKULL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Skeleton");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase("§0Choose mob type GUI")) {
            if (event.getCurrentItem() == null) {
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on null");
                return;
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Zombie")) {
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on Zombie");
                int mobType = 1; //mobType{1,2,...} == {zombie,skeleton,...}
                spawnerChangeMobType(mobType, player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Skeleton")) {
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on Skeleton");
                int mobType = 2; //mobType{1,2,...} == {zombie,skeleton,...}
                spawnerChangeMobType(mobType, player);
            }
            event.setCancelled(true);
        }
    }

    private void spawnerChangeMobType(int mobType, Player player) {
        Block block = SharedData.callingBlock.get(player);
        NBT.modifyPersistentData(block.getState(), nbt -> {
            nbt.setInteger("mobType", mobType);
        });
        player.closeInventory();
        String mob;
        String z = new String("Zombie");
        String s = new String("Skeleton");
        if (mobType == 1){
            mob = z;
        } else {
            mob = s;
        }
        player.sendMessage("§aUpdated the mob type to §c" + mob);
    }
}
