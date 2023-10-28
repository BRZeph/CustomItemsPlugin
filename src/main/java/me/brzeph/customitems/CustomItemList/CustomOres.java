package me.brzeph.customitems.CustomItemList;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomOres {
    public static ItemStack create_dr_coal_ore(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.COAL_ORE, 1));

        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "1");
        itemCompound.setString("customOre", "customCoal");

        return nbtItem.getItem();
    }
    public static ItemStack create_dr_emerald_ore(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.EMERALD_ORE, 1));

        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "2");
        itemCompound.setString("customOre", "customEmerald");

        return nbtItem.getItem();
    }
}
