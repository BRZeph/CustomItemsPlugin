package me.brzeph.customitems.Events.MiningEvents.Methods;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.brzeph.customitems.Events.MiningEvents.Methods.ModifyItemLore.modifyItemLore;
import static me.brzeph.customitems.Events.MiningEvents.Methods.UpdateProgressBar.updateProgressBar;

public class UpdateLoreEnchantment {
    public static void updateLoreForNewEnchantment(Player player){
        ItemStack itemHeld = player.getInventory().getItemInMainHand();
        int i;
        int startLine = 7;
        ItemMeta itemMeta = itemHeld.getItemMeta();
        List<String> lore = itemMeta.getLore();
        lore.add(".");

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
        NBTItem nbtItem = new NBTItem(itemHeld2);
        int currentLevel = nbtItem.getInteger("currentLevel");
        int currentXP = nbtItem.getInteger("currentXP");
        modifyItemLore(player, 0, "§7Level: §6" + currentLevel);
        modifyItemLore(player, 1, "§7XP: §6" + currentXP + "/" + currentLevel*300);
        updateProgressBar(player);
    }
    public static ItemStack updateLoreForNewEnchantmentItemStack(ItemStack itemStack){
        int i;
        int startLine = 7;
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();

        for (i = lore.size() - 1; i >= startLine; i--){
            lore.remove(i);
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        ItemStack itemStack1 = new ItemStack(itemStack);
        List<String> lore2 = new ArrayList<>(itemStack1.getItemMeta().getLore());
        NBTItem nbti = new NBTItem(itemStack1);

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

        ItemMeta itemMeta2 = itemStack1.getItemMeta();
        itemMeta2.setLore(lore2);
        itemStack1.setItemMeta(itemMeta2);
        return itemStack1;
    }
}
