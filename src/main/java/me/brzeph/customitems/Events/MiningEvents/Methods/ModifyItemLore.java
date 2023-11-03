package me.brzeph.customitems.Events.MiningEvents.Methods;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ModifyItemLore {
    public static void modifyItemLore(Player player, int lineIndex, String newLore){
        ItemStack itemHeld = player.getInventory().getItemInMainHand();

        if (itemHeld != null){
            ItemMeta itemMeta = itemHeld.getItemMeta();

            if (itemMeta != null && itemMeta.hasLore()){
                List<String> lore = itemMeta.getLore();

                if (lineIndex >= 0 && lineIndex < lore.size()){
                    lore.set(lineIndex, newLore);
                    itemMeta.setLore(lore);
                    itemHeld.setItemMeta(itemMeta);
                    player.getInventory().setItemInMainHand(itemHeld);
                }
            }
        }
    }
}
