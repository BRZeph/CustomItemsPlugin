package me.brzeph.customitems.Events.MiningEvents.Methods;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.brzeph.customitems.Events.MiningEvents.Methods.ChangeItemName.changeHoldingItemName;
import static me.brzeph.customitems.Events.MiningEvents.Methods.ModifyItemLore.modifyItemLore;
import static me.brzeph.customitems.Events.MiningEvents.Methods.rollPickaxeEnchantOnTierChange.rollPickaxeEnchantmentOnLevelUp;

public class PickaxeReachedLevel100 {
    public static void pickaxeReachedLevel100 (Player player) {
        rollPickaxeEnchantmentOnLevelUp(player);
        ItemStack itemHeld = new ItemStack(player.getInventory().getItemInMainHand());
        NBTItem nbtItem = new NBTItem(itemHeld);
        int currentTier = nbtItem.getInteger("tier");
        int newTier;
        if (currentTier == 5) {
            newTier = 1;
            String newTierString = Integer.toString(newTier);

            nbtItem.setInteger("tier", newTier);
            player.getInventory().setItemInMainHand(nbtItem.getItem());

            modifyItemLore(player, 4, "§6Pickaxe tier §f" + newTierString);
            modifyItemLore(player, 5, "§6Can only break the following ores: coal");

            ItemStack upgradingVisual = new ItemStack(player.getInventory().getItemInMainHand());
            upgradingVisual.setType(Material.WOODEN_PICKAXE);
            player.getInventory().setItemInMainHand(upgradingVisual);

            changeHoldingItemName(player, "Custom T1 pickaxe");
        }
    }
}
