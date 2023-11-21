package me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.CombatMechanics.CustomCombatItems.ComatItemsEnums.WeaponEnchantments;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.ComatItemsEnums.WeaponEnchantments.*;
import static me.brzeph.customitems.Utils.Utils.*;
import static org.bukkit.Bukkit.getServer;

public class GeneratingWeaponOrb {
    public static ItemStack orbWeapon(ItemStack itemStack) {
        String[] enchantmentsToBeReset = {
                "VSPlayer", "VSMonster", "Fire", "Ice", "Pure", "Poison",
                "CriticalHit", "LifeSteal", "Accuracy", "Shatter", "Crushing", "Cleave"
        };
        setItemNBTTags(itemStack, enchantmentsToBeReset, 0);
        String[] enchantmentsLoreToBeRemoved = {
                "§cVS Player", "§cVS Monster", "§cFire damage", "§cIce damage", "§cPure damage", "§cPoison damage",
                "§cCritical Hit", "§cLife steal", "§cAccuracy", "§cShatter", "§cCrushing", "§cCleave"
        };
        removeItemLoreStartingWith(itemStack, enchantmentsLoreToBeRemoved);
        List<String> enchantmentsToBeAdded = new ArrayList<>();
        int enchantmentMultiplicityRoll = getRandomValue(100, 1);
        int enchantmentMultiplicityDifficulty = 10;
        boolean increaseDifficulty = true;

        outerLoop: while (enchantmentMultiplicityRoll > enchantmentMultiplicityDifficulty) {
            if (increaseDifficulty) {
                enchantmentMultiplicityDifficulty *= 2;
            }
            increaseDifficulty = false;
            String newEnchantment = rollEnchantment();

            for (String enchantmentToBeAdded : enchantmentsToBeAdded) {
                if (enchantmentToBeAdded.equals(newEnchantment)){
                    continue outerLoop;
                }
            } //prevents repeating enchants

            if (newEnchantment.equals(ChanceFire.name()) || newEnchantment.equals(ChanceIce.name()) || //checks for elemental on new orb
                    newEnchantment.equals(ChancePoison.name()) || newEnchantment.equals(ChancePure.name())) {
                for (String enchantmentToBeAdded : enchantmentsToBeAdded) {

                    if (enchantmentToBeAdded.equals(ChanceFire.name()) || enchantmentToBeAdded.equals(ChanceIce.name()) || //checks for double elemental
                            enchantmentToBeAdded.equals(ChancePoison.name()) || enchantmentToBeAdded.equals(ChancePure.name())) {
                        if (!(newEnchantment.equals(ChancePure.name()) && new NBTItem(itemStack).getBoolean("doubleElemental"))) {
                            continue outerLoop;
                        }
                    }
                }
            }
            if (newEnchantment.equals(ChanceVSPlayer.name())) {
                for (String enchantmentToBeAdded : enchantmentsToBeAdded) {
                    if (enchantmentToBeAdded.equals(ChanceVSMonster.name())) {
                        continue outerLoop;
                    }
                }
            }   //prevents a weapon having VSPlayer and VSMonster at the same time
            if (newEnchantment.equals(ChanceVSMonster.name())) {
                for (String enchantmentToBeAdded : enchantmentsToBeAdded) {
                    if (enchantmentToBeAdded.equals(ChanceVSPlayer.name())) {
                        continue outerLoop;
                    }
                }
            }
            enchantmentsToBeAdded.add(newEnchantment);
            increaseDifficulty = true;
        }
        int tier = new NBTItem(itemStack).getInteger("tier");
        int[] enchantmentValue = new int[enchantmentsToBeAdded.size()];
        int i = 0;
        for (String enchantmentName : enchantmentsToBeAdded) {
            int j = rollEnchantmentValue(enchantmentName, tier);
            enchantmentValue[i] = j;
            i++;
        }
        String[] enchantmentsToBeAddedToString = enchantmentsToBeAdded.toArray(new String[0]);
        return addWeaponEnchantment(itemStack, enchantmentsToBeAddedToString, enchantmentValue);
    }

    private static int rollEnchantmentValue(String enchantmentName, int tier) {
        for (WeaponEnchantments enchantment : WeaponEnchantments.values()) {
            if (enchantment.name().equals(ChanceVSPlayer.name())) {
                switch (tier){
                    case 1: return enchantmentValueByTier(T1VSPlayer);
                    case 2: return enchantmentValueByTier(T2VSPlayer);
                    case 3: return enchantmentValueByTier(T3VSPlayer);
                    case 4: return enchantmentValueByTier(T4VSPlayer);
                    case 5: return enchantmentValueByTier(T5VSPlayer);
                }
                throw new RuntimeException("Error rolling enchantment value");
            }
            if (enchantment.name().equals(ChanceVSMonster.name())) {
                switch (tier){
                    case 1: return enchantmentValueByTier(T1VSMonster);
                    case 2: return enchantmentValueByTier(T2VSMonster);
                    case 3: return enchantmentValueByTier(T3VSMonster);
                    case 4: return enchantmentValueByTier(T4VSMonster);
                    case 5: return enchantmentValueByTier(T5VSMonster);
                }
                throw new RuntimeException("Error rolling enchantment value");
            }
            if (enchantment.name().equals(ChanceFire.name())) {
                switch (tier){
                    case 1: return enchantmentValueByTier(T1Fire);
                    case 2: return enchantmentValueByTier(T2Fire);
                    case 3: return enchantmentValueByTier(T3Fire);
                    case 4: return enchantmentValueByTier(T4Fire);
                    case 5: return enchantmentValueByTier(T5Fire);
                }
                throw new RuntimeException("Error rolling enchantment value");
            }
            if (enchantment.name().equals(ChanceIce.name())) {
                switch (tier){
                    case 1: return enchantmentValueByTier(T1Ice);
                    case 2: return enchantmentValueByTier(T2Ice);
                    case 3: return enchantmentValueByTier(T3Ice);
                    case 4: return enchantmentValueByTier(T4Ice);
                    case 5: return enchantmentValueByTier(T5Ice);
                }
                throw new RuntimeException("Error rolling enchantment value");
            }
            if (enchantment.name().equals(ChancePure.name())) {
                switch (tier){
                    case 1: return enchantmentValueByTier(T1Pure);
                    case 2: return enchantmentValueByTier(T2Pure);
                    case 3: return enchantmentValueByTier(T3Pure);
                    case 4: return enchantmentValueByTier(T4Pure);
                    case 5: return enchantmentValueByTier(T5Pure);
                }
                throw new RuntimeException("Error rolling enchantment value");
            }
            if (enchantment.name().equals(ChancePoison.name())) {
                switch (tier){
                    case 1: return enchantmentValueByTier(T1Poison);
                    case 2: return enchantmentValueByTier(T2Poison);
                    case 3: return enchantmentValueByTier(T3Poison);
                    case 4: return enchantmentValueByTier(T4Poison);
                    case 5: return enchantmentValueByTier(T5Poison);
                }
                throw new RuntimeException("Error rolling enchantment value");
            }
            if (enchantment.name().equals(ChanceCriticalHit.name())) {
                switch (tier){
                    case 1: return enchantmentValueByTier(T1CriticalHit);
                    case 2: return enchantmentValueByTier(T2CriticalHit);
                    case 3: return enchantmentValueByTier(T3CriticalHit);
                    case 4: return enchantmentValueByTier(T4CriticalHit);
                    case 5: return enchantmentValueByTier(T5CriticalHit);
                }
                throw new RuntimeException("Error rolling enchantment value");
            }
            if (enchantment.name().equals(ChanceLifeSteal.name())) {
                switch (tier){
                    case 1: return enchantmentValueByTier(T1LifeSteal);
                    case 2: return enchantmentValueByTier(T2LifeSteal);
                    case 3: return enchantmentValueByTier(T3LifeSteal);
                    case 4: return enchantmentValueByTier(T4LifeSteal);
                    case 5: return enchantmentValueByTier(T5LifeSteal);
                }
                throw new RuntimeException("Error rolling enchantment value");
            }
            if (enchantment.name().equals(ChanceAccuracy.name())) {
                switch (tier){
                    case 1: return enchantmentValueByTier(T1Accuracy);
                    case 2: return enchantmentValueByTier(T2Accuracy);
                    case 3: return enchantmentValueByTier(T3Accuracy);
                    case 4: return enchantmentValueByTier(T4Accuracy);
                    case 5: return enchantmentValueByTier(T5Accuracy);
                }
                throw new RuntimeException("Error rolling enchantment value");
            }
            if (enchantment.name().equals(ChanceShatter.name())) {
                switch (tier){
                    case 1: return enchantmentValueByTier(T1Shatter);
                    case 2: return enchantmentValueByTier(T2Shatter);
                    case 3: return enchantmentValueByTier(T3Shatter);
                    case 4: return enchantmentValueByTier(T4Shatter);
                    case 5: return enchantmentValueByTier(T5Shatter);
                }
                throw new RuntimeException("Error rolling enchantment value");
            }
            if (enchantment.name().equals(ChanceCleave.name())) {
                switch (tier){
                    case 1: return enchantmentValueByTier(T1Cleave);
                    case 2: return enchantmentValueByTier(T2Cleave);
                    case 3: return enchantmentValueByTier(T3Cleave);
                    case 4: return enchantmentValueByTier(T4Cleave);
                    case 5: return enchantmentValueByTier(T5Cleave);
                }
                throw new RuntimeException("Error rolling enchantment value");
            }
            if (enchantment.name().equals(ChanceCrushing.name())) {
                switch (tier){
                    case 1: return enchantmentValueByTier(T1Crushing);
                    case 2: return enchantmentValueByTier(T2Crushing);
                    case 3: return enchantmentValueByTier(T3Crushing);
                    case 4: return enchantmentValueByTier(T4Crushing);
                    case 5: return enchantmentValueByTier(T5Crushing);
                }
                throw new RuntimeException("Error rolling enchantment value");
            }
        }
        throw new IllegalArgumentException("Enchantment not found: " + enchantmentName);
    }

    public static ItemStack addWeaponEnchantment(ItemStack itemStack, String[] enchantmentName, int[] enchantmentValue){
        String[] rarity = new String[5];
        rarity[0] = "§fCommon";
        rarity[1] = "§aUncommon";
        rarity[2] = "§9Rare";
        rarity[3] = "§5Epic";
        rarity[4] = "§eLegendary";
        removeItemLoreStartingWith(itemStack, rarity);
        List<String> lore0 = itemStack.getLore();
        lore0.remove(2);
        NBTItem nbtItem = new NBTItem(itemStack);
        for (int i = 0; i < enchantmentName.length; i++) {
            nbtItem.setInteger(WeaponEnchantments.enchantmentNameToNBTTag(WeaponEnchantments.valueOf(enchantmentName[i])), enchantmentValue[i]);
        }
        ItemMeta itemMeta = nbtItem.getItem().getItemMeta();
        List<String> lore = new ArrayList<>(lore0);
        for (int i = 0; i < enchantmentName.length; i++){
            lore.add(WeaponEnchantments.enchantmentNameToLore(WeaponEnchantments.valueOf(enchantmentName[i])) + ": §c" + enchantmentValue[i]);
        }
        lore.add("");
        lore.add(nbtItem.getString("rarity"));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static String rollEnchantment() {
        final WeaponEnchantments[] selectedEnchantments = {
                WeaponEnchantments.ChanceVSPlayer, WeaponEnchantments.ChanceVSMonster,
                WeaponEnchantments.ChanceFire, WeaponEnchantments.ChanceIce,
                WeaponEnchantments.ChancePure, WeaponEnchantments.ChancePoison,
                WeaponEnchantments.ChanceCriticalHit, WeaponEnchantments.ChanceLifeSteal,
                WeaponEnchantments.ChanceAccuracy, WeaponEnchantments.ChanceShatter,
                WeaponEnchantments.ChanceCrushing, WeaponEnchantments.ChanceCleave
        };
        int totalPercentage = 0;
        for (WeaponEnchantments enchantment : selectedEnchantments) {
            totalPercentage += enchantment.getPercentage();
        }

        int randomRoll = new Random().nextInt(totalPercentage) + 1;
        int cumulativePercentage = 0;

        for (WeaponEnchantments enchantment : selectedEnchantments) {
            cumulativePercentage += enchantment.getPercentage();
            if (randomRoll <= cumulativePercentage) {
                return enchantment.name();
            }
        }
        throw new RuntimeException("Error generating weapon orb, values do not add up to 100");
    }
}


