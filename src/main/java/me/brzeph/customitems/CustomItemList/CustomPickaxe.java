package me.brzeph.customitems.CustomItemList;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomPickaxe {
    public static ItemStack create_dr_wooden_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.WOODEN_PICKAXE, 1));
        nbtItem.setInteger("tier", 1);

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T1 pickaxe");

        String uniqueItemID = UUID.randomUUID().toString();
        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR pickaxe");
        lore.add("This pickaxe is tier 1");
        lore.add("Can only break custom coal, emerald and iron");
        lore.add("Unique identifier: " + uniqueItemID);
        //sets a random id to each created item [helps to locate duping]

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }

    public static ItemStack create_dr_stone_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.STONE_PICKAXE, 1));
        nbtItem.setInteger("tier", 2);

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T2 pickaxe");

        String uniqueItemID = UUID.randomUUID().toString();
        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR pickaxe");
        lore.add("This pickaxe is tier 2");
        lore.add("Can only break custom coal, emerald and iron");
        lore.add("Unique identifier: " + uniqueItemID);
        //sets a random id to each created item [helps to locate duping]

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
    public static ItemStack create_dr_iron_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.IRON_PICKAXE, 1));
        nbtItem.setInteger("tier", 3);

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T3 pickaxe");

        String uniqueItemID = UUID.randomUUID().toString();
        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR pickaxe");
        lore.add("This pickaxe is tier 3");
        lore.add("Can only break custom coal, emerald and iron");
        lore.add("Unique identifier: " + uniqueItemID);
        //sets a random id to each created item [helps to locate duping]

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
    public static ItemStack create_dr_diamond_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.DIAMOND_PICKAXE, 1));
        nbtItem.setInteger("tier", 4);

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T4 pickaxe");

        String uniqueItemID = UUID.randomUUID().toString();
        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR pickaxe");
        lore.add("This pickaxe is tier 4");
        lore.add("Can only break custom coal, emerald, iron and diamond");
        lore.add("Unique identifier: " + uniqueItemID);
        //sets a random id to each created item [helps to locate duping]

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
    public static ItemStack create_dr_gold_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.GOLDEN_PICKAXE, 1));
        nbtItem.setInteger("tier", 5);

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T5 pickaxe");

        String uniqueItemID = UUID.randomUUID().toString();
        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR pickaxe");
        lore.add("This pickaxe is tier 5");
        lore.add("Can only break coal, emerald, iron, diamond and gold");
        lore.add("Unique identifier: " + uniqueItemID);
        //sets a random id to each created item [helps to locate duping]

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
}