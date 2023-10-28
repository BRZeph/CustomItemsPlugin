package me.brzeph.customitems.CustomItemList;

import de.tr7zw.nbtapi.NBTCompound;
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

        //sets a random id to each created item [helps to locate duping]
        String uniqueItemID = UUID.randomUUID().toString();
        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "1");
        itemCompound.setString("CanBreakT1", "true");
        itemCompound.setString("CanBreakT2", "false");
        itemCompound.setString("CanBreakT3", "false");
        itemCompound.setString("CanBreakT4", "false");
        itemCompound.setString("CanBreakT5", "false");

        // Add a unique "ItemIdentifications" tag
        itemCompound.setString("ItemIdentifications", uniqueItemID);

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Custom T1 pickaxe");

        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR pickaxe");
        lore.add("This pickaxe is tier 1");
        lore.add("Can only break custom coal");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }

    public static ItemStack create_dr_stone_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.STONE_PICKAXE, 1));

        //sets a random id to each created item [helps to locate duping]
        String uniqueItemID = UUID.randomUUID().toString();
        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "2");
        itemCompound.setString("CanBreakT1", "true");
        itemCompound.setString("CanBreakT2", "true");
        itemCompound.setString("CanBreakT3", "false");
        itemCompound.setString("CanBreakT4", "false");
        itemCompound.setString("CanBreakT5", "false");

        // Add a unique "ItemIdentifications" tag
        itemCompound.setString("ItemIdentifications", uniqueItemID);

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Custom T2 pickaxe");

        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR pickaxe");
        lore.add("This pickaxe is tier 2");
        lore.add("Can only break custom coal and emerald");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
    public static ItemStack create_dr_iron_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.IRON_PICKAXE, 1));

        //sets a random id to each created item [helps to locate duping]
        String uniqueItemID = UUID.randomUUID().toString();
        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "3");
        itemCompound.setString("CanBreakT1", "true");
        itemCompound.setString("CanBreakT2", "true");
        itemCompound.setString("CanBreakT3", "true");
        itemCompound.setString("CanBreakT4", "false");
        itemCompound.setString("CanBreakT5", "false");

        // Add a unique "ItemIdentifications" tag
        itemCompound.setString("ItemIdentifications", uniqueItemID);

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Custom T3 pickaxe");

        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR pickaxe");
        lore.add("This pickaxe is tier 3");
        lore.add("Can only break custom coal, emerald and iron");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
    public static ItemStack create_dr_diamond_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.DIAMOND_PICKAXE, 1));

        //sets a random id to each created item [helps to locate duping]
        String uniqueItemID = UUID.randomUUID().toString();
        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "4");
        itemCompound.setString("CanBreakT1", "true");
        itemCompound.setString("CanBreakT2", "true");
        itemCompound.setString("CanBreakT3", "true");
        itemCompound.setString("CanBreakT4", "true");
        itemCompound.setString("CanBreakT5", "false");

        // Add a unique "ItemIdentifications" tag
        itemCompound.setString("ItemIdentifications", uniqueItemID);

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Custom T4 pickaxe");

        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR pickaxe");
        lore.add("This pickaxe is tier 4");
        lore.add("Can only break custom coal, emerald, iron and diamond");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
    public static ItemStack create_dr_gold_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.GOLDEN_PICKAXE, 1));

        //sets a random id to each created item [helps to locate duping]
        String uniqueItemID = UUID.randomUUID().toString();
        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "5");
        itemCompound.setString("CanBreakT1", "true");
        itemCompound.setString("CanBreakT2", "true");
        itemCompound.setString("CanBreakT3", "true");
        itemCompound.setString("CanBreakT4", "true");
        itemCompound.setString("CanBreakT5", "true");

        // Add a unique "ItemIdentifications" tag
        itemCompound.setString("ItemIdentifications", uniqueItemID);

        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Custom T5 pickaxe");

        List<String> lore = new ArrayList<>();
        lore.add("This is a custom DR pickaxe");
        lore.add("This pickaxe is tier 5");
        lore.add("Can only break custom coal, emerald, iron, diamond and gold");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return nbtItem.getItem();
    }
}
