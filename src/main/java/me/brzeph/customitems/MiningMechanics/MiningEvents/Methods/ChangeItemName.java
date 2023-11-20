package me.brzeph.customitems.MiningMechanics.MiningEvents.Methods;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChangeItemName {
    public static void changeHoldingItemName (Player player, String newName){
        ItemStack itemStack = new ItemStack(player.getInventory().getItemInMainHand());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(newName);
        itemStack.setItemMeta(itemMeta);
        player.getInventory().setItemInMainHand(itemStack);
    }
}
