package me.brzeph.customitems.MiningEvents.Methods;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.brzeph.customitems.MiningEvents.Methods.ChangeItemName.changeHoldingItemName;
import static me.brzeph.customitems.MiningEvents.Methods.ModifyItemLore.modifyItemLore;
import static me.brzeph.customitems.MiningEvents.Methods.PickaxeReachedLevel100.pickaxeReachedLevel100;
import static me.brzeph.customitems.MiningEvents.Methods.rollPickaxeEnchantOnTierChange.rollPickaxeEnchantmentOnLevelUp;

public class UpgradeTier {
    public static void upgradeTier (Player player){
        ItemStack itemHeld = new ItemStack(player.getInventory().getItemInMainHand());
        NBTItem nbtItem = new NBTItem(itemHeld);
        int currentTier = nbtItem.getInteger("tier");
        int newTier = 0;
        if (currentTier == 1){
            newTier = 2;
            String newTierString = Integer.toString(newTier);

            nbtItem.setInteger("tier", newTier);
            player.getInventory().setItemInMainHand(nbtItem.getItem());

            modifyItemLore(player, 4, "§6Pickaxe tier §f" + newTierString);
            modifyItemLore(player, 5, "§6Can only break the following ores: coal and emerald");

            ItemStack upgradingVisual = new ItemStack(player.getInventory().getItemInMainHand());
            upgradingVisual.setType(Material.STONE_PICKAXE);
            player.getInventory().setItemInMainHand(upgradingVisual);

            rollPickaxeEnchantmentOnLevelUp(player);
            changeHoldingItemName(player, "Custom T2 pickaxe");


        }if (currentTier == 2){
            newTier = 3;
            String newTierString = Integer.toString(newTier);

            nbtItem.setInteger("tier", newTier);
            player.getInventory().setItemInMainHand(nbtItem.getItem());

            modifyItemLore(player, 4, "§6Pickaxe tier §f" + newTierString);
            modifyItemLore(player, 5, "§6Can only break the following ores: coal, emerald and iron");

            ItemStack upgradingTier = new ItemStack(player.getInventory().getItemInMainHand());
            upgradingTier.setType(Material.IRON_PICKAXE);
            player.getInventory().setItemInMainHand(upgradingTier);

            rollPickaxeEnchantmentOnLevelUp(player);
            changeHoldingItemName(player, "Custom T3 pickaxe");

        }if (currentTier == 3){
            newTier = 4;
            String newTierString = Integer.toString(newTier);

            nbtItem.setInteger("tier", newTier);
            player.getInventory().setItemInMainHand(nbtItem.getItem());

            modifyItemLore(player, 4, "§6Pickaxe tier §f" + newTierString);
            modifyItemLore(player, 5, "§6Can only break the following ores: coal, emerald, iron and diamond");

            ItemStack upgradingTier = new ItemStack(player.getInventory().getItemInMainHand());
            upgradingTier.setType(Material.DIAMOND_PICKAXE);
            player.getInventory().setItemInMainHand(upgradingTier);

            rollPickaxeEnchantmentOnLevelUp(player);
            changeHoldingItemName(player, "Custom T4 pickaxe");

        }if (currentTier == 4){
            newTier = 5;
            String newTierString = Integer.toString(newTier);

            nbtItem.setInteger("tier", newTier);
            player.getInventory().setItemInMainHand(nbtItem.getItem());

            modifyItemLore(player, 4, "§6Pickaxe tier §f" + newTierString);
            modifyItemLore(player, 5, "§6Can only break the following ores: coal, emerald, iron, diamond and gold");

            ItemStack upgradingTier = new ItemStack(player.getInventory().getItemInMainHand());
            upgradingTier.setType(Material.GOLDEN_PICKAXE);
            player.getInventory().setItemInMainHand(upgradingTier);

            rollPickaxeEnchantmentOnLevelUp(player);
            changeHoldingItemName(player, "Custom T5 pickaxe");

        }if (currentTier == 5){
            pickaxeReachedLevel100(player);
        }
    }
}
