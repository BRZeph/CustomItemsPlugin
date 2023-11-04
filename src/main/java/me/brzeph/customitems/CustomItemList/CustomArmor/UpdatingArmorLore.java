package me.brzeph.customitems.CustomItemList.CustomArmor;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class UpdatingArmorLore {
    public static ItemStack upgradingArmorLore(ItemStack itemStack){
        NBTItem nbtItem = new NBTItem(itemStack);
        int tier = nbtItem.getInteger("tier");
        int bonusHealth = nbtItem.getInteger("bonusHealth");
        int bonusArmor  = nbtItem.getInteger("bonusArmor");
        int armorStatsVitality = nbtItem.getInteger("armorStatsVitality");
        int armorStatsDexterity = nbtItem.getInteger("armorStatsDexterity");
        int armorStatsStrength = nbtItem.getInteger("armorStatsStrength");
        int armorStatsAgility = nbtItem.getInteger("armorStatsAgility");
        int armorStatsDodge = nbtItem.getInteger("armorStatsDodge");
        int armorStatsBlock = nbtItem.getInteger("armorStatsBlock");
        int armorStatsReflect = nbtItem.getInteger("armorStatsReflect");

        ItemStack itemStack1 = new ItemStack(nbtItem.getItem());
        ItemMeta itemMeta = itemStack1.getItemMeta();
        itemMeta.setDisplayName("§fCustom T" + tier + " helmet");
        List<String> lore = itemMeta.getLore();
        lore.add(".");
        lore.add(".");
        lore.add(".");

        int i;
        int startLine = 0;
        for (i = lore.size() - 1; i >= startLine; i--){
            lore.remove(i);
        }
        itemMeta.setLore(lore);
        itemStack1.setItemMeta(itemMeta);

        List<String> lore2 = new ArrayList<>();
        NBTItem nbti = new NBTItem(itemStack1);
        if (nbti.hasKey("tier")) {
            if (tier > 0) {
                lore2.add("§cTier: " + tier);
            }
        }
        if (nbti.hasKey("bonusHealth")) {
            if (bonusHealth > 0) {
                lore2.add("§cHealth: " + bonusHealth);
            }
        }
        if (nbti.hasKey("bonusArmor")) {
            if (bonusArmor > 0) {
                lore2.add("§cArmor: " + bonusArmor);
            }
        }
        lore2.add("");
        if (nbti.hasKey("armorStatsVitality")) {
            if (armorStatsVitality > 0) {
                lore2.add("§cVitality: " + armorStatsVitality);
            }
        }
        if (nbti.hasKey("armorStatsDexterity")) {
            if (armorStatsDexterity > 0) {
                lore2.add("§cDexterity: " + armorStatsDexterity);
            }
        }
        if (nbti.hasKey("armorStatsStrength")) {
            if (armorStatsStrength > 0) {
                lore2.add("§cStrength: " + armorStatsStrength);
            }
        }
        if (nbti.hasKey("armorStatsAgility")) {
            if (armorStatsAgility > 0) {
                lore2.add("§cAgility: " + armorStatsAgility);
            }
        }
        if (nbti.hasKey("armorStatsDodge")) {
            if (armorStatsDodge > 0) {
                lore2.add("§cDodge: " + armorStatsDodge);
            }
        }
        if (nbti.hasKey("armorStatsBlock")) {
            if (armorStatsBlock > 0) {
                lore2.add("§cBlock: " + armorStatsBlock);
            }
        }
        if (nbti.hasKey("armorStatsReflect")) {
            if (armorStatsReflect > 0) {
                lore2.add("§cReflect: " + armorStatsReflect);
            }
        }
        ItemStack itemStack2 = new ItemStack(nbti.getItem());
        ItemMeta itemMeta1 = itemStack2.getItemMeta();
        itemMeta1.setLore(lore2);
        itemStack2.setItemMeta(itemMeta1);
        return itemStack2;
    }
}
