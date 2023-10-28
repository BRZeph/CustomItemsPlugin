package me.brzeph.customitems.CustomItemList;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CustomPickaxe {
    public static ItemStack create_dr_wooden_pickaxe() {
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.WOODEN_PICKAXE, 1));

        //sets a random id to each created item [helps to locate duping]
        String uniqueItemID = UUID.randomUUID().toString();


        // Set custom NBT data
        NBTCompound itemCompound = nbtItem.addCompound("customData");
        itemCompound.setString("tier", "1");
        itemCompound.setString("descriptionIfBreakT1", "This pickaxe can break custom coal ore");
        itemCompound.setString("descriptionIfBreakT2", "This pickaxe cannot break custom emerald ore");
        itemCompound.setString("descriptionIfBreakT3", "This pickaxe cannot break custom iron ore");
        itemCompound.setString("descriptionIfBreakT4", "This pickaxe cannot break custom diamond ore");
        itemCompound.setString("descriptionIfBreakT5", "This pickaxe cannot break custom gold ore");

        // Add a unique "ItemIdentifications" tag
        itemCompound.setString("ItemIdentifications", uniqueItemID);

        return nbtItem.getItem();
    }

}
