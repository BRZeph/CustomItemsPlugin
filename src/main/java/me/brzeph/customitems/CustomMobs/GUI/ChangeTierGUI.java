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

public class ChangeTierGUI implements Listener {
    public static void changeTierOpenGUI(Player player){
        Inventory inventory = Bukkit.createInventory(player, 9, "§0Choose tier GUI");

        inventory.setItem(0, changeSpawnerTierToTier1());
        inventory.setItem(1, changeSpawnerTierToTier2());
        inventory.setItem(2, changeSpawnerTierToTier3());
        inventory.setItem(3, changeSpawnerTierToTier4());
        inventory.setItem(4, changeSpawnerTierToTier5());
        player.openInventory(inventory);
    }
    private static ItemStack changeSpawnerTierToTier1() {
        ItemStack itemStack = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Tier 1");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private static ItemStack changeSpawnerTierToTier2() {
        ItemStack itemStack = new ItemStack(Material.STONE_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Tier 2");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private static ItemStack changeSpawnerTierToTier3() {
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Tier 3");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private static ItemStack changeSpawnerTierToTier4() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Tier 4");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    private static ItemStack changeSpawnerTierToTier5() {
        ItemStack itemStack = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Tier 5");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase("§0Choose tier GUI")) {
            if (event.getCurrentItem() == null) {
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on null");
                return;
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Tier 1")){
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on tier 1");
                int tier = 1;
                spawnerChangeTier(tier, player);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Tier 2")) {
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on tier 2");
                int tier = 2;
                spawnerChangeTier(tier, player);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Tier 3")) {
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on tier 3");
                int tier = 3;
                spawnerChangeTier(tier, player);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Tier 4")) {
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on tier 4");
                int tier = 4;
                spawnerChangeTier(tier, player);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Tier 5")) {
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on tier 5");
                int tier = 5;
                spawnerChangeTier(tier, player);
            }
            event.setCancelled(true);
        }
    }
    public void spawnerChangeTier(int tier, Player player) {
        Block block = SharedData.callingBlock.get(player);
        NBT.modifyPersistentData(block.getState(), nbt -> {
            nbt.setInteger("tier", tier);
        });
        player.closeInventory();
        player.sendMessage("§aUpdated the tier to §c" + tier);
        SharedData.callingBlock.clear();
    }
}
