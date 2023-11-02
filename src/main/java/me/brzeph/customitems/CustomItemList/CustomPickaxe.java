package me.brzeph.customitems.CustomItemList;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.MiningEvents.BaseEnchantmentValues;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomPickaxe {
    public static ItemStack create_dr_wooden_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.WOODEN_PICKAXE, 1));
        String uniqueItemID = UUID.randomUUID().toString();
        int currentXP = 0;
        int currentLevel = 1;
        nbtItem.setInteger("tier", 1);
        nbtItem.setInteger("currentXP", currentXP);
        nbtItem.setInteger("currentLevel", currentLevel);
        nbtItem.setString("uniqueItemID", uniqueItemID);
        Field[] fields = BaseEnchantmentValues.class.getDeclaredFields();
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
        itemMeta.setDisplayName("Custom T1 pickaxe");

        List<String> lore = new ArrayList<>();
        lore.add("§7Level: §6" + currentLevel);
        lore.add("§7XP: §6" + currentXP + "/" + currentLevel*300);
        lore.add("§7::::::::::::::::::::::::::::::::::::::::");
        lore.add("");
        lore.add("§6Pickaxe tier §f1");
        lore.add("§6Can only break the following ores: coal");
        lore.add("");
        lore.add("§cMining success: 5");
        //sets a random id to each created item [helps to locate duping]

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }

    public static ItemStack create_dr_stone_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.STONE_PICKAXE, 1));
        String uniqueItemID = UUID.randomUUID().toString();
        int currentXP = 0;
        int currentLevel = 20;
        nbtItem.setInteger("tier", 2);
        nbtItem.setInteger("currentXP", currentXP);
        nbtItem.setInteger("currentLevel", currentLevel);
        nbtItem.setString("uniqueItemID", uniqueItemID);
        Field[] fields = BaseEnchantmentValues.class.getDeclaredFields();
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
        itemMeta.setDisplayName("Custom T2 pickaxe");

        List<String> lore = new ArrayList<>();
        lore.add("§7Level: §6" + currentLevel);
        lore.add("§7XP: §6" + currentXP + "/" + currentLevel*300);
        lore.add("§7::::::::::::::::::::::::::::::::::::::::");
        lore.add("");
        lore.add("§6Pickaxe tier §f2");
        lore.add("§6Can only break the following ores: coal and emerald");
        lore.add("");
        lore.add("§cMining success: 5");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
    public static ItemStack create_dr_iron_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.IRON_PICKAXE, 1));
        String uniqueItemID = UUID.randomUUID().toString();
        int currentXP = 0;
        int currentLevel = 40;
        nbtItem.setInteger("tier", 3);
        nbtItem.setInteger("currentXP", currentXP);
        nbtItem.setInteger("currentLevel", currentLevel);
        nbtItem.setString("uniqueItemID", uniqueItemID);
        Field[] fields = BaseEnchantmentValues.class.getDeclaredFields();
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
        itemMeta.setDisplayName("Custom T3 pickaxe");

        List<String> lore = new ArrayList<>();
        lore.add("§7Level: §6" + currentLevel);
        lore.add("§7XP: §6" + currentXP + "/" + currentLevel*300);
        lore.add("§7::::::::::::::::::::::::::::::::::::::::");
        lore.add("");
        lore.add("§6Pickaxe tier §f3");
        lore.add("§6Can only break the following ores: coal, emerald and iron");
        lore.add("");
        lore.add("§cMining success: 5");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
    public static ItemStack create_dr_diamond_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.DIAMOND_PICKAXE, 1));
        String uniqueItemID = UUID.randomUUID().toString();
        int currentXP = 0;
        int currentLevel = 60;
        nbtItem.setInteger("tier", 4);
        nbtItem.setInteger("currentXP", currentXP);
        nbtItem.setInteger("currentLevel", currentLevel);
        nbtItem.setString("uniqueItemID", uniqueItemID);
        Field[] fields = BaseEnchantmentValues.class.getDeclaredFields();
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
        itemMeta.setDisplayName("Custom T4 pickaxe");

        List<String> lore = new ArrayList<>();
        lore.add("§7Level: §6" + currentLevel);
        lore.add("§7XP: §6" + currentXP + "/" + currentLevel*300);
        lore.add("§7::::::::::::::::::::::::::::::::::::::::");
        lore.add("");
        lore.add("§6Pickaxe tier §f4");
        lore.add("§6Can only break the following ores: coal, emerald, iron and diamond");
        lore.add("");
        lore.add("§cMining success: 5");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
    public static ItemStack create_dr_gold_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.GOLDEN_PICKAXE, 1));
        String uniqueItemID = UUID.randomUUID().toString();
        int currentXP = 0;
        int currentLevel = 80;
        nbtItem.setInteger("tier", 5);
        nbtItem.setInteger("currentXP", currentXP);
        nbtItem.setInteger("currentLevel", currentLevel);
        nbtItem.setString("uniqueItemID", uniqueItemID);
        Field[] fields = BaseEnchantmentValues.class.getDeclaredFields();
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
        itemMeta.setDisplayName("Custom T5 pickaxe");

        List<String> lore = new ArrayList<>();
        lore.add("§7Level: §6" + currentLevel);
        lore.add("§7XP: §6" + currentXP + "/" + currentLevel*300);
        lore.add("§7::::::::::::::::::::::::::::::::::::::::");
        lore.add("");
        lore.add("§6Pickaxe tier §f5");
        lore.add("§6Can only break the following ores: coal, emerald, iron, diamond and gold");
        lore.add("");
        lore.add("§cMining success: 5");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
}