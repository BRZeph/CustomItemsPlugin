package me.brzeph.customitems.MiningMechanics.MiningEvents;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

public class MiningXPLevelsTable {
    public static int XPToLevelUpRequiredMethod (ItemStack itemHeld){
        NBTItem nbt = new NBTItem(itemHeld);
        return nbt.getInteger("currentLevel") * 300;
    }
}
