package me.brzeph.customitems.MiningMechanics.MiningEvents.Methods;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.brzeph.customitems.MiningMechanics.MiningEvents.Methods.ModifyItemLore.modifyItemLore;
import static me.brzeph.customitems.MiningMechanics.MiningEvents.MiningXPLevelsTable.XPToLevelUpRequiredMethod;

public class UpdateProgressBar {
    public static void updateProgressBar(Player player){
        ItemStack itemHeld = new ItemStack(player.getInventory().getItemInMainHand());
        NBTItem nbtItem = new NBTItem(itemHeld);
        int currentXP = nbtItem.getInteger("currentXP");
        double ratio = (double) currentXP/(XPToLevelUpRequiredMethod(player.getInventory().getItemInMainHand()));
        int barLength = 40;
        int greenBlocks = (int) (barLength*ratio);

        StringBuilder newLore = new StringBuilder();
        for (int i = 0; i < barLength; i++){
            if (i<greenBlocks){
                newLore.append("§a:");
            }else {
                newLore.append("§7:");
            }
        }
        modifyItemLore(player, 2, newLore.toString());

    }
}
