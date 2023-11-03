package me.brzeph.customitems.Events.MiningEvents.Methods;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class UpdateLoreEnchantment {
    public static void updateLoreForNewEnchantment(Player player){
        ItemStack itemHeld = player.getInventory().getItemInMainHand();
        int i;
        int startLine = 7;
        ItemMeta itemMeta = itemHeld.getItemMeta();
        List<String> lore = itemMeta.getLore();

        for (i = lore.size() - 1; i >= startLine; i--){
            lore.remove(i);
        }
        itemMeta.setLore(lore);
        itemHeld.setItemMeta(itemMeta);
        player.getInventory().setItemInMainHand(itemHeld);

        ItemStack itemHeld2 = player.getInventory().getItemInMainHand();
        List<String> lore2 = new ArrayList<>(itemHeld2.getItemMeta().getLore());
        NBTItem nbti = new NBTItem(itemHeld2);

        // Check and update enchantments
        if (nbti.hasKey("enchantmentDoubleOre")) {
            int doubleOreValue = nbti.getInteger("enchantmentDoubleOre");
            if (doubleOreValue > 0) {
                lore2.add("§cDouble ore: " + doubleOreValue);
            }
        }if (nbti.hasKey("enchantmentTripleOre")) {
            int tripleOreValue = nbti.getInteger("enchantmentTripleOre");
            if (tripleOreValue > 0) {
                lore2.add("§cTriple ore: " + tripleOreValue);
            }
        }if (nbti.hasKey("enchantmentMiningSuccess")) {
            int miningSuccessValue = nbti.getInteger("enchantmentMiningSuccess");
            if (miningSuccessValue > 0) {
                lore2.add("§cMining success: " + miningSuccessValue);
            }
        }if (nbti.hasKey("enchantmentGemFind")) {
            int gemFind = nbti.getInteger("enchantmentGemFind");
            if (gemFind > 0) {
                lore2.add("§cGem find: " + gemFind);
            }
        }if (nbti.hasKey("enchantmentTreasureFind")) {
            int treasureFind = nbti.getInteger("enchantmentTreasureFind");
            if (treasureFind > 0) {
                lore2.add("§cTreasure find: " + treasureFind);
            }
        }if (nbti.hasKey("enchantmentDurability")) {
            int durability = nbti.getInteger("enchantmentDurability");
            if (durability > 0) {
                lore2.add("§cDurability: " + durability);
            }
        }

        ItemMeta itemMeta2 = itemHeld2.getItemMeta();
        itemMeta2.setLore(lore2);
        itemHeld2.setItemMeta(itemMeta2);

        player.getInventory().setItemInMainHand(itemHeld2);
    }
}
