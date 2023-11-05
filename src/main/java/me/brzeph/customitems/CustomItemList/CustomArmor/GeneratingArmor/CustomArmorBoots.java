package me.brzeph.customitems.CustomItemList.CustomArmor.GeneratingArmor;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.CustomItemList.CustomArmor.ArmorEnums.*;
import me.brzeph.customitems.CustomItemList.CustomArmor.BaseArmorStats;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static me.brzeph.customitems.CustomItemList.CustomArmor.GeneratingArmor.CalculatingRarity.calculatingRarity;
import static me.brzeph.customitems.CustomItemList.CustomArmor.GeneratingArmor.CalculatingRarity.rarityToString;

public class CustomArmorBoots {
    public static ItemStack createT1Boots(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.LEATHER_BOOTS, 1));
        int rarity = calculatingRarity();
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("armorType", "boots");
        nbtItem.setInteger("tier", 1);
        nbtItem.setInteger("bonusHealth", ArmorT1HPEnum.getRandomValueByRarity(rarity));
        nbtItem.setInteger("bonusArmor", ArmorArmorEnum.getRandomValueByTier(1));
        Field[] fields = BaseArmorStats.class.getDeclaredFields();
        for (Field field : fields){
            if (field.getType() == int.class){
                try {
                    int value = field.getInt(null);
                    nbtItem.setInteger(field.getName(), value);
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T1 boots");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
    public static ItemStack createT2Boots(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.CHAINMAIL_BOOTS, 1));
        int rarity = calculatingRarity();
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("armorType", "boots");
        nbtItem.setInteger("tier", 2);
        nbtItem.setInteger("bonusHealth", ArmorT2HPEnum.getRandomValueByRarity(rarity));
        nbtItem.setInteger("bonusArmor", ArmorArmorEnum.getRandomValueByTier(2));
        Field[] fields = BaseArmorStats.class.getDeclaredFields();
        for (Field field : fields){
            if (field.getType() == int.class){
                try {
                    int value = field.getInt(null);
                    nbtItem.setInteger(field.getName(), value);
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T2 boots");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
    public static ItemStack createT3Boots(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.IRON_BOOTS, 1));
        int rarity = calculatingRarity();
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("armorType", "boots");
        nbtItem.setInteger("tier", 3);
        nbtItem.setInteger("bonusHealth", ArmorT3HPEnum.getRandomValueByRarity(rarity));
        nbtItem.setInteger("bonusArmor", ArmorArmorEnum.getRandomValueByTier(3));
        Field[] fields = BaseArmorStats.class.getDeclaredFields();
        for (Field field : fields){
            if (field.getType() == int.class){
                try {
                    int value = field.getInt(null);
                    nbtItem.setInteger(field.getName(), value);
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T3 boots");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
    public static ItemStack createT4Boots(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.DIAMOND_BOOTS, 1));
        int rarity = calculatingRarity();
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("armorType", "boots");
        nbtItem.setInteger("tier", 4);
        nbtItem.setInteger("bonusHealth", ArmorT4HPEnum.getRandomValueByRarity(rarity));
        nbtItem.setInteger("bonusArmor", ArmorArmorEnum.getRandomValueByTier(4));
        Field[] fields = BaseArmorStats.class.getDeclaredFields();
        for (Field field : fields){
            if (field.getType() == int.class){
                try {
                    int value = field.getInt(null);
                    nbtItem.setInteger(field.getName(), value);
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T4 boots");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
    public static ItemStack createT5Boots(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.GOLDEN_BOOTS, 1));
        int rarity = calculatingRarity();
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("armorType", "boots");
        nbtItem.setInteger("tier", 5);
        nbtItem.setInteger("bonusHealth", ArmorT5HPEnum.getRandomValueByRarity(rarity));
        nbtItem.setInteger("bonusArmor", ArmorArmorEnum.getRandomValueByTier(5));
        Field[] fields = BaseArmorStats.class.getDeclaredFields();
        for (Field field : fields){
            if (field.getType() == int.class){
                try {
                    int value = field.getInt(null);
                    nbtItem.setInteger(field.getName(), value);
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T5 boots");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
}
