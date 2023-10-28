package me.brzeph.customitems.CustomItemList;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomOres {
    public static ItemStack create_dr_coal_ore(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.COAL_ORE, 1));

        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "1");
        itemCompound.setString("customOre", "customCoal");
        itemCompound.setString("BreakableByT1Pick", "true");
        itemCompound.setString("BreakableByT2Pick", "true");
        itemCompound.setString("BreakableByT3Pick", "true");
        itemCompound.setString("BreakableByT4Pick", "true");
        itemCompound.setString("BreakableByT5Pick", "true");

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Custom T1 ore");

        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR ore");
        lore.add("This ore is tier 1");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
    public static ItemStack create_dr_emerald_ore(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.EMERALD_ORE, 1));

        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "2");
        itemCompound.setString("customOre", "customEmerald");
        itemCompound.setString("BreakableByT1Pick", "false");
        itemCompound.setString("BreakableByT2Pick", "true");
        itemCompound.setString("BreakableByT3Pick", "true");
        itemCompound.setString("BreakableByT4Pick", "true");
        itemCompound.setString("BreakableByT5Pick", "true");

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Custom T2 ore");

        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR ore");
        lore.add("This ore is tier 2");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);


        return nbtItem.getItem();
    }
    public static ItemStack create_dr_iron_ore(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.IRON_ORE, 1));

        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "3");
        itemCompound.setString("customOre", "customIron");
        itemCompound.setString("BreakableByT1Pick", "false");
        itemCompound.setString("BreakableByT2Pick", "false");
        itemCompound.setString("BreakableByT3Pick", "true");
        itemCompound.setString("BreakableByT4Pick", "true");
        itemCompound.setString("BreakableByT5Pick", "true");

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Custom T3 ore");

        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR ore");
        lore.add("This ore is tier 3");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);


        return nbtItem.getItem();
    }
    public static ItemStack create_dr_diamond_ore(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.DIAMOND_ORE, 1));

        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "4");
        itemCompound.setString("customOre", "customDiamond");
        itemCompound.setString("BreakableByT1Pick", "false");
        itemCompound.setString("BreakableByT2Pick", "false");
        itemCompound.setString("BreakableByT3Pick", "false");
        itemCompound.setString("BreakableByT4Pick", "true");
        itemCompound.setString("BreakableByT5Pick", "true");

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Custom T4 ore");

        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR ore");
        lore.add("This ore is tier 4");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);


        return nbtItem.getItem();
    }
    public static ItemStack create_dr_gold_ore(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.GOLD_ORE, 1));

        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "5");
        itemCompound.setString("customOre", "customGold");
        itemCompound.setString("BreakableByT1Pick", "false");
        itemCompound.setString("BreakableByT2Pick", "false");
        itemCompound.setString("BreakableByT3Pick", "false");
        itemCompound.setString("BreakableByT4Pick", "false");
        itemCompound.setString("BreakableByT5Pick", "true");

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Custom T5 ore");

        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR ore");
        lore.add("This ore is tier 5");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);


        return nbtItem.getItem();
    }
}
