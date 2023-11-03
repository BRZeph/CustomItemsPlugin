package me.brzeph.customitems.Events.MiningEvents.Methods;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.Events.MiningEvents.enchantmentEnums.*;
import org.bukkit.inventory.ItemStack;

import static me.brzeph.customitems.Events.MiningEvents.RandomValueGenerators.randomlyChoosingNewEnchantment;

public class RollPickaxeEnchantOnCreation {
    public static NBTItem RollPickaxeEnchantmentOnCreation (NBTItem nbtItem){

        NBTItem nbtItem1 = new NBTItem(nbtItem.getItem());
        int currentTier = nbtItem1.getInteger("tier");
        int currentDoubleOreValue = nbtItem1.getInteger("enchantmentDoubleOre");
        int currentTripleOreValue = nbtItem1.getInteger("enchantmentTripleOre");
        int currentMiningSuccessValue = nbtItem1.getInteger("enchantmentMiningSuccess");
        int currentGemFindValue = nbtItem1.getInteger("enchantmentGemFind");
        int currentTreasureFindValue = nbtItem1.getInteger("enchantmentTreasureFind");
        int currentDurabilityValue = nbtItem1.getInteger("enchantmentDurability");

        boolean definedNewEnchantment = false;
        while (!definedNewEnchantment){
            int choosingEnchantment = randomlyChoosingNewEnchantment();

            if (choosingEnchantment == 1){ //double ore
                int rollValue = pickDoubleOreEnum.getRandomValueByTier(currentTier);
                if (rollValue > currentDoubleOreValue) {
                    nbtItem1.setInteger("enchantmentDoubleOre", rollValue);
                    definedNewEnchantment = true;
                }

            }if (choosingEnchantment == 2){ //triple ore
                int rollValue = pickTripleOreEnum.getRandomValueByTier(currentTier);
                if (rollValue > currentTripleOreValue){
                    nbtItem1.setInteger("enchantmentTripleOre", rollValue);
                    definedNewEnchantment = true;
                }

            }if (choosingEnchantment == 3){ //mining success
                int rollValue = pickMiningSuccessEnum.getRandomValueByTier(currentTier);
                if (rollValue > currentMiningSuccessValue){
                    nbtItem1.setInteger("enchantmentMiningSuccess", rollValue);
                    definedNewEnchantment = true;
                }

            }if (choosingEnchantment == 4){ //gem find
                int rollValue = pickGemFindEnum.getRandomValueByTier(currentTier);
                if (rollValue > currentGemFindValue){
                    nbtItem1.setInteger("enchantmentGemFind", rollValue);
                    definedNewEnchantment = true;
                }

            }if (choosingEnchantment == 5){ //treasure find
                if (currentTier >= 3) {
                    int rollValue = pickTreasureFindEnum.getRandomValueByTier(currentTier);
                    if (rollValue > currentTreasureFindValue) {
                        nbtItem1.setInteger("enchantmentTreasureFind", rollValue);
                        definedNewEnchantment = true;
                    }
                }

            }if (choosingEnchantment == 6){ //durability
                int rollValue = pickDurabilityEnum.getRandomValueByTier(currentTier);
                if (rollValue > currentDurabilityValue){
                    nbtItem1.setInteger("enchantmentDurability", rollValue);
                    definedNewEnchantment = true;
                }
            }
        }
        return nbtItem1;
    }
}
