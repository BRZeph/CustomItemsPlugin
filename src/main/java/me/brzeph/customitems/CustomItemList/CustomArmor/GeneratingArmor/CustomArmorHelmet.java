package me.brzeph.customitems.CustomItemList.CustomArmor.GeneratingArmor;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.CustomItemList.CustomArmor.ArmorEnums.ArmorArmorEnum;
import me.brzeph.customitems.CustomItemList.CustomArmor.ArmorEnums.ArmorHPEnum;
import me.brzeph.customitems.CustomItemList.CustomArmor.BaseArmorStats;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CustomArmorHelmet {
    public static ItemStack createT1Helmet(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.LEATHER_HELMET, 1));
        nbtItem.setString("armorType", "helmet");
        nbtItem.setInteger("tier", 1);
        nbtItem.setInteger("bonusHealth", ArmorHPEnum.getRandomValueByTier(1));
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
        itemMeta.setDisplayName("Custom T1 helmet");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
    public static ItemStack createT2Helmet(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.CHAINMAIL_HELMET, 1));
        nbtItem.setString("armorType", "helmet");
        nbtItem.setInteger("tier", 2);
        nbtItem.setInteger("bonusHealth", ArmorHPEnum.getRandomValueByTier(2));
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
        itemMeta.setDisplayName("Custom T2 helmet");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
    public static ItemStack createT3Helmet(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.IRON_HELMET, 1));
        nbtItem.setString("armorType", "helmet");
        nbtItem.setInteger("tier", 3);
        nbtItem.setInteger("bonusHealth", ArmorHPEnum.getRandomValueByTier(3));
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
        itemMeta.setDisplayName("Custom T3 helmet");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
    public static ItemStack createT4Helmet(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.DIAMOND_HELMET, 1));
        nbtItem.setString("armorType", "helmet");
        nbtItem.setInteger("tier", 4);
        nbtItem.setInteger("bonusHealth", ArmorHPEnum.getRandomValueByTier(4));
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
        itemMeta.setDisplayName("Custom T4 helmet");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
    public static ItemStack createT5Helmet(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.GOLDEN_HELMET, 1));
        nbtItem.setString("armorType", "helmet");
        nbtItem.setInteger("tier", 5);
        nbtItem.setInteger("bonusHealth", ArmorHPEnum.getRandomValueByTier(5));
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
        itemMeta.setDisplayName("Custom T5 helmet");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
}
