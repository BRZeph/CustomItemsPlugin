package me.brzeph.customitems.CustomMobs.GUI;

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

public class ChangeMaxAmountOfMobsGUI implements Listener {
    public static void changeMaxAmountOfMobsOpenGui(Player player){
        Block block = SharedData.callingBlock.get(player);
        Inventory inventory = Bukkit.createInventory(player, 18, "§0Choose max amount of mobs GUI");
        inventory.setItem(1, decreaseAmountBy_i(5, block));
        inventory.setItem(2, decreaseAmountBy_i(3, block));
        inventory.setItem(3, decreaseAmountBy_i(1, block));
        inventory.setItem(4, glass());
        inventory.setItem(5, increaseAmountBy_i(1, block));
        inventory.setItem(6, increaseAmountBy_i(3, block));
        inventory.setItem(7, increaseAmountBy_i(5, block));

        inventory.setItem(11, setValueTo(1, block));
        inventory.setItem(12, setValueTo(5, block));
        inventory.setItem(13, setValueTo(10, block));
        inventory.setItem(14, setValueTo(15, block));
        inventory.setItem(15, setValueTo(20, block));
        player.openInventory(inventory);
    }
    private static ItemStack glass() {
        ItemStack itemStack = new ItemStack(Material.GLASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(" ");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static ItemStack decreaseAmountBy_i(int i, Block block) {
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        int currentAmountMobs = nbtTileEntity.getPersistentDataContainer().getInteger("maxAmountOfMobs");
        ItemStack itemStack = new ItemStack(Material.RED_WOOL, i);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Decrease amount by " + i);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§8Set value to: §a" + (currentAmountMobs - i));
        lore.add("§8Current value: §c" + currentAmountMobs);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setInteger("changeValueBy", -i);
        return nbtItem.getItem();
    }

    private static ItemStack increaseAmountBy_i(int i, Block block) {
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        int currentAmountMobs = nbtTileEntity.getPersistentDataContainer().getInteger("maxAmountOfMobs");
        ItemStack itemStack = new ItemStack(Material.GREEN_WOOL, i);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Increase amount by " + i);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§8Set value to: §a" + (currentAmountMobs + i));
        lore.add("§8Current value: §c" + currentAmountMobs);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setInteger("changeValueBy", i);
        return nbtItem.getItem();
    }
    private static ItemStack setValueTo(int i, Block block) {
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        int currentAmountMobs = nbtTileEntity.getPersistentDataContainer().getInteger("maxAmountOfMobs");
        ItemStack itemStack = new ItemStack(Material.WHITE_WOOL, i);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Set amount to " + i);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§8Set value to: §a" + i);
        lore.add("§8Current value: §c" + currentAmountMobs);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setInteger("setValueTo", i);
        return nbtItem.getItem();
    }
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase("§0Choose max amount of mobs GUI")) {
            if (event.getCurrentItem() != null){
                if (event.getCurrentItem().getType() == Material.RED_WOOL || event.getCurrentItem().getType() == Material.GREEN_WOOL){
                    ItemStack clickedItem = event.getCurrentItem();
                    NBTItem nbtItem = new NBTItem(clickedItem);
                    int changeValueBy = nbtItem.getInteger("changeValueBy");
                    changeMaxAmountOfMobsTo(changeValueBy, player);
                }
                if (event.getCurrentItem().getType() == Material.WHITE_WOOL){
                    ItemStack clickedItem = event.getCurrentItem();
                    NBTItem nbtItem = new NBTItem(clickedItem);
                    int setValueTo = nbtItem.getInteger("setValueTo");
                    setMaxAmountOfMobsTo(setValueTo, player);
                }
            }
            event.setCancelled(true);
        }
    }

    private void setMaxAmountOfMobsTo(int setValueTo, Player player) {
        Block block = SharedData.callingBlock.get(player);
        NBT.modifyPersistentData(block.getState(), nbt -> {
            nbt.setInteger("maxAmountOfMobs", setValueTo);
        });
        player.closeInventory();
        player.sendMessage("§aUpdated max amount of mobs to §c" + setValueTo);
    }

    private void changeMaxAmountOfMobsTo(int changeValueBy, Player player) {
        Block block = SharedData.callingBlock.get(player);
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        int newValue = nbtTileEntity.getPersistentDataContainer().getInteger("maxAmountOfMobs") + changeValueBy;
        NBT.modifyPersistentData(block.getState(), nbt -> {
            nbt.setInteger("maxAmountOfMobs", newValue);
        });
        player.closeInventory();
        player.sendMessage("§aUpdated max amount of mobs to §c" + newValue);
    }
}
