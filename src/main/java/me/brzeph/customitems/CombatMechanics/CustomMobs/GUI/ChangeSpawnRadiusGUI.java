package me.brzeph.customitems.CombatMechanics.CustomMobs.GUI;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTTileEntity;
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

public class ChangeSpawnRadiusGUI implements Listener {
    public static void changeSpawnerRadiusOpenGUI(Player player){
        Block block = SharedData.callingBlock.get(player);
        Inventory inventory = Bukkit.createInventory(player, 18, "§0Choose spawner radius GUI");
        inventory.setItem(1, decreaseRadiusAmountBy_i(5, block));
        inventory.setItem(2, decreaseRadiusAmountBy_i(3, block));
        inventory.setItem(3, decreaseRadiusAmountBy_i(1, block));
        inventory.setItem(4, glass());
        inventory.setItem(5, increaseRadiusAmountBy_i(1, block));
        inventory.setItem(6, increaseRadiusAmountBy_i(3, block));
        inventory.setItem(7, increaseRadiusAmountBy_i(5, block));

        inventory.setItem(11, setRadiusValueTo(1, block));
        inventory.setItem(12, setRadiusValueTo(5, block));
        inventory.setItem(13, setRadiusValueTo(10, block));
        inventory.setItem(14, setRadiusValueTo(15, block));
        inventory.setItem(15, setRadiusValueTo(20, block));
        player.openInventory(inventory);
    }
    private static ItemStack glass() {
        ItemStack itemStack = new ItemStack(Material.GLASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(" ");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack decreaseRadiusAmountBy_i(int i, Block block) {
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        int size = nbtTileEntity.getPersistentDataContainer().getInteger("size");
        ItemStack itemStack = new ItemStack(Material.RED_WOOL, i);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Decrease amount by " + i);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§8Set value to: §a" + (size - i));
        lore.add("§8Current value: §c" + size);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setInteger("changeValueBy", -i);
        return nbtItem.getItem();
    }

    private static ItemStack increaseRadiusAmountBy_i(int i, Block block) {
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        int radius = nbtTileEntity.getPersistentDataContainer().getInteger("size");
        ItemStack itemStack = new ItemStack(Material.GREEN_WOOL, i);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Increase amount by " + i);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§8Set value to: §a" + (radius + i));
        lore.add("§8Current value: §c" + radius);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setInteger("changeValueBy", i);
        return nbtItem.getItem();
    }
    private static ItemStack setRadiusValueTo(int i, Block block) {
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        int size = nbtTileEntity.getPersistentDataContainer().getInteger("size");
        ItemStack itemStack = new ItemStack(Material.WHITE_WOOL, i);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Set amount to " + i);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§8Set value to: §a" + i);
        lore.add("§8Current value: §c" + size);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setInteger("setValueTo", i);
        return nbtItem.getItem();
    }
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase("§0Choose spawner radius GUI")) {
            if (event.getCurrentItem() != null){
                if (event.getCurrentItem().getType() == Material.RED_WOOL || event.getCurrentItem().getType() == Material.GREEN_WOOL){
                    ItemStack clickedItem = event.getCurrentItem();
                    NBTItem nbtItem = new NBTItem(clickedItem);
                    int changeValueBy = nbtItem.getInteger("changeValueBy");
                    changeRadiusValueTo(changeValueBy, player);
                }
                if (event.getCurrentItem().getType() == Material.WHITE_WOOL){
                    ItemStack clickedItem = event.getCurrentItem();
                    NBTItem nbtItem = new NBTItem(clickedItem);
                    int setValueTo = nbtItem.getInteger("setValueTo");
                    setRadiusValueTo(setValueTo, player);
                }
                event.setCancelled(true);
            }
            event.setCancelled(true);
        }
    }

    private void setRadiusValueTo(int setValueTo, Player player) {
        Block block = SharedData.callingBlock.get(player);
        NBT.modifyPersistentData(block.getState(), nbt -> {
            nbt.setInteger("size", setValueTo);
        });
        player.closeInventory();
        player.sendMessage("§aUpdated spawner radius to §c" + setValueTo);
    }

    private void changeRadiusValueTo(int changeValueBy, Player player) {
        Block block = SharedData.callingBlock.get(player);
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        int newValue = nbtTileEntity.getPersistentDataContainer().getInteger("size") + changeValueBy;
        NBT.modifyPersistentData(block.getState(), nbt -> {
            nbt.setInteger("size", newValue);
        });
        player.closeInventory();
        player.sendMessage("§aUpdated spawner radius to §c" + newValue);
    }
}
