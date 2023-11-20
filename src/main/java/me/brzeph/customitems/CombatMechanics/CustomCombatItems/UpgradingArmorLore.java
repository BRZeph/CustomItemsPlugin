package me.brzeph.customitems.CombatMechanics.CustomCombatItems;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class UpgradingArmorLore {
    public static ItemStack upgradingArmorLore(ItemStack itemStack){
        NBTItem nbtItem = new NBTItem(itemStack);
        String armorType = nbtItem.getString("armorType");
        String rarity = nbtItem.getString("rarity");
        int tier = nbtItem.getInteger("tier");
        int level = nbtItem.getInteger("level");
        int bonusHealth = nbtItem.getInteger("bonusHealth");
        int bonusArmor  = nbtItem.getInteger("bonusArmor");
        int armorStatsVitality = nbtItem.getInteger("armorStatsVitality");
        int armorStatsDexterity = nbtItem.getInteger("armorStatsDexterity");
        int armorStatsStrength = nbtItem.getInteger("armorStatsStrength");
        int armorStatsAgility = nbtItem.getInteger("armorStatsAgility");
        int armorStatsDodge = nbtItem.getInteger("armorStatsDodge");
        int armorStatsBlock = nbtItem.getInteger("armorStatsBlock");
        int armorStatsReflect = nbtItem.getInteger("armorStatsReflect");
        float armorHPS = nbtItem.getFloat("armorHPS");
        float armorEnergy = nbtItem.getFloat("armorEnergy");

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§fCustom T" + tier + " " + armorType);
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
        itemStack.setItemMeta(itemMeta);

        List<String> lore1 = new ArrayList<>();
        NBTItem nbti = new NBTItem(itemStack);
        if (nbti.hasKey("tier")) {
            if (tier > 0) {
                lore1.add("§cTier: " + tier);
            }
        }
        if (nbti.hasKey("level")) {
            if (level > 0) {
                lore1.add("§cLevel: " + level);
            }
        }
        lore1.add("");
        if (nbti.hasKey("bonusHealth")) {
            if (bonusHealth > 0) {
                lore1.add("§cHealth: " + bonusHealth);
            }
        }
        if (nbti.hasKey("armorHPS")){
            if (armorHPS > 0){
                lore1.add("§cHealth regeneration/s: " + (int)armorHPS);
            }
        }
        if (nbti.hasKey("armorEnergy")){
            if (armorEnergy > 0){
                lore1.add("§cEnergy/s: " + (int)armorEnergy);
            }
        }
        if (nbti.hasKey("bonusArmor")) {
            if (bonusArmor > 0) {
                lore1.add("§cArmor: " + (int)bonusArmor);
            }
        }
        lore1.add("");
        if (nbti.hasKey("armorStatsVitality")) {
            if (armorStatsVitality > 0) {
                lore1.add("§cVitality: " + armorStatsVitality);
            }
        }
        if (nbti.hasKey("armorStatsDexterity")) {
            if (armorStatsDexterity > 0) {
                lore1.add("§cDexterity: " + armorStatsDexterity);
            }
        }
        if (nbti.hasKey("armorStatsStrength")) {
            if (armorStatsStrength > 0) {
                lore1.add("§cStrength: " + armorStatsStrength);
            }
        }
        if (nbti.hasKey("armorStatsAgility")) {
            if (armorStatsAgility > 0) {
                lore1.add("§cAgility: " + armorStatsAgility);
            }
        }
        if (nbti.hasKey("armorStatsDodge")) {
            if (armorStatsDodge > 0) {
                lore1.add("§cDodge: " + armorStatsDodge);
            }
        }
        if (nbti.hasKey("armorStatsBlock")) {
            if (armorStatsBlock > 0) {
                lore1.add("§cBlock: " + armorStatsBlock);
            }
        }
        if (nbti.hasKey("armorStatsReflect")) {
            if (armorStatsReflect > 0) {
                lore1.add("§cReflect: " + armorStatsReflect);
            }
        }
        if (nbti.hasKey("rarity")){
            if (rarity != null){
                lore1.add("");
                lore1.add(rarity);
            }
        }
        ItemStack itemStack1 = new ItemStack(nbti.getItem());
        ItemMeta itemMeta1 = itemStack1.getItemMeta();
        itemMeta1.setLore(lore1);
        itemStack1.setItemMeta(itemMeta1);
        return itemStack1;
    }
}
