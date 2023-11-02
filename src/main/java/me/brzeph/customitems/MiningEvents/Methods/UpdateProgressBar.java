package me.brzeph.customitems.MiningEvents.Methods;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static me.brzeph.customitems.MiningEvents.Methods.ModifyItemLore.modifyItemLore;

public class UpdateProgressBar {
    public static void updateProgressBar(Player player){
        ItemStack itemHeld = new ItemStack(player.getInventory().getItemInMainHand());
        NBTItem nbtItem = new NBTItem(itemHeld);
        int currentXP = nbtItem.getInteger("currentXP");
        int currentLevel = nbtItem.getInteger("currentLevel");
        double ratio = (double) currentXP/(currentLevel*300);
        int barLength = 40;
        int greenBlocks = (int) (barLength*ratio);

        List<String> lore = new ArrayList<>();

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
