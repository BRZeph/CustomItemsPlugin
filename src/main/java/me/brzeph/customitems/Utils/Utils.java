package me.brzeph.customitems.Utils;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTTileEntity;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class Utils {
    public static int getRandomValue(int maxValue, int minValue) {
        return new Random().nextInt(maxValue - minValue + 1) + minValue;
    }



    public static void setBlockNBTTags(Block block, String[] keys, int[] values) {
        for (int i = 0; i < keys.length; i++) {
            int finalI = i;
            NBT.modifyPersistentData(block.getState(), nbt -> {
                nbt.setInteger(keys[finalI], values[finalI]);
            });
        }
    }
    public static int getBlockNBTTagsInt(Block block, String key) {
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        if (nbtTileEntity.getPersistentDataContainer().getInteger(key) != 0) {
            return nbtTileEntity.getPersistentDataContainer().getInteger(key);
        }
        throw new RuntimeException("Invalid getter key");
    }
    public static void setBlockNBTTags(Block block, String[] keys, String[] values) {
        for (int i = 0; i < keys.length; i++) {
            int finalI = i;
            NBT.modifyPersistentData(block.getState(), nbt -> {
                nbt.setString(keys[finalI], values[finalI]);
            });
        }
    }
    public static String getBlockNBTTagsString(Block block, String key) {
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        if (nbtTileEntity.getPersistentDataContainer().getString(key) != null) {
            return nbtTileEntity.getPersistentDataContainer().getString(key);
        }
        throw new RuntimeException("Invalid getter key");
    }
    public static void setBlockNBTTags(Block block, String[] keys, Boolean[] values) {
        for (int i = 0; i < keys.length; i++) {
            int finalI = i;
            NBT.modifyPersistentData(block.getState(), nbt -> {
                nbt.setBoolean(keys[finalI], values[finalI]);
            });
        }
    }
    public static boolean getBlockNBTTagsBoolean(Block block, String key) {
        NBTTileEntity nbtTileEntity = new NBTTileEntity(block.getState());
        if (nbtTileEntity.getPersistentDataContainer().getBoolean(key) != null) {
            return nbtTileEntity.getPersistentDataContainer().getBoolean(key);
        }
        throw new RuntimeException("Invalid getter key");
    }



    public static void setItemNBTTags(ItemStack item, String[] keys, int[] values) {
        NBT.modify(item, nbt -> {
            for (int i = 0; i < keys.length; i++) {
                nbt.setInteger(keys[i], values[i]);
            }
        });
    }
    public static void setItemNBTTags(ItemStack item, String[] keys, int value) {
        NBT.modify(item, nbt -> {
            for (int i = 0; i < keys.length; i++) {
                nbt.setInteger(keys[i], value);
            }
        });
    }
    public static int getItemNBTTagsInt(ItemStack item, String key) {
        NBTItem nbtItem = new NBTItem(item);
        if (nbtItem.hasKey(key)) {
            return nbtItem.getInteger(key);
        }
        throw new RuntimeException("Invalid getter key");
    }
    public static void setItemNBTTags(ItemStack item, String[] keys, String[] values) {
        NBT.modify(item, nbt -> {
            for (int i = 0; i < keys.length; i++) {
                nbt.setString(keys[i], values[i]);
            }
        });
    }
    public static String getItemNBTTagsString(ItemStack item, String key) {
        NBTItem nbtItem = new NBTItem(item);
        if (nbtItem.hasKey(key)) {
            return nbtItem.getString(key);
        }
        throw new RuntimeException("Invalid getter key");
    }
    public static void setItemNBTTags(ItemStack item, String[] keys, Boolean[] values) {
        NBT.modify(item, nbt -> {
            for (int i = 0; i < keys.length; i++) {
                nbt.setBoolean(keys[i], values[i]);
            }
        });
    }
    public static boolean getItemNBTTagsBoolean(ItemStack item, String key) {
        NBTItem nbtItem = new NBTItem(item);
        if (nbtItem.hasKey(key)) {
            return nbtItem.getBoolean(key);
        }
        throw new RuntimeException("Invalid getter key");
    }


    public static void removeItemLoreStartingWith(ItemStack itemStack, String[] loreToBeRemoved) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return;
        }
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        Iterator<String> iterator = lore.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            for (String prefix : loreToBeRemoved) {
                if (line.startsWith(prefix)) {
                    iterator.remove();
                    break;
                }
            }
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }
}
