package me.brzeph.customitems.MiningEvents.Methods;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.MiningEvents.enchantmentEnums.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.brzeph.customitems.MiningEvents.RandomValueGenerators.randomlyChoosingNewEnchantment;

public class rollPickaxeEnchantOnTierChange {
    public static void rollPickaxeEnchantmentOnLevelUp (Player player){
        ItemStack itemHeld = new ItemStack(player.getInventory().getItemInMainHand());
        NBTItem nbtItem = new NBTItem(itemHeld);
        int currentTier = nbtItem.getInteger("tier");
        int currentDoubleOreValue = nbtItem.getInteger("enchantmentDoubleOre");
        int currentTripleOreValue = nbtItem.getInteger("enchantmentTripleOre");
        int currentMiningSuccessValue = nbtItem.getInteger("enchantmentMiningSuccess");
        int currentGemFindValue = nbtItem.getInteger("enchantmentGemFind");
        int currentTreasureFindValue = nbtItem.getInteger("enchantmentTreasureFind");
        int currentDurabilityValue = nbtItem.getInteger("enchantmentDurability");


        boolean definedNewEnchantment = false;
        while (!definedNewEnchantment){
            int choosingEnchantment = randomlyChoosingNewEnchantment();

            if (choosingEnchantment == 1){ //double ore
                int rollValue = pickDoubleOreEnum.getRandomValueByTier(currentTier);
                player.sendMessage("rolled: double ore");
                if (rollValue > currentDoubleOreValue) {
                    nbtItem.setInteger("enchantmentDoubleOre", rollValue);
                    player.getInventory().setItemInMainHand(nbtItem.getItem());
                    definedNewEnchantment = true;
                }

            }if (choosingEnchantment == 2){ //triple ore
                int rollValue = pickTripleOreEnum.getRandomValueByTier(currentTier);
                player.sendMessage("rolled: triple ore");
                if (rollValue > currentTripleOreValue){
                    nbtItem.setInteger("enchantmentTripleOre", rollValue);
                    player.getInventory().setItemInMainHand(nbtItem.getItem());
                    definedNewEnchantment = true;
                }

            }if (choosingEnchantment == 3){ //mining success
                int rollValue = pickMiningSuccessEnum.getRandomValueByTier(currentTier);
                player.sendMessage("rolled: mining success");
                if (rollValue > currentMiningSuccessValue){
                    nbtItem.setInteger("enchantmentMiningSuccess", rollValue);
                    player.getInventory().setItemInMainHand(nbtItem.getItem());
                    definedNewEnchantment = true;
                }

            }if (choosingEnchantment == 4){ //gem find
                int rollValue = pickGemFindEnum.getRandomValueByTier(currentTier);
                player.sendMessage("rolled: gem find");
                if (rollValue > currentGemFindValue){
                    nbtItem.setInteger("enchantmentGemFind", rollValue);
                    player.getInventory().setItemInMainHand(nbtItem.getItem());
                    definedNewEnchantment = true;
                }

            }if (choosingEnchantment == 5){ //treasure find
                if (currentTier >= 3) {
                    int rollValue = pickTreasureFindEnum.getRandomValueByTier(currentTier);
                    player.sendMessage("rolled: treasure find");
                    if (rollValue > currentTreasureFindValue) {
                        nbtItem.setInteger("enchantmentTreasureFind", rollValue);
                        player.getInventory().setItemInMainHand(nbtItem.getItem());
                        definedNewEnchantment = true;
                    }
                }

            }if (choosingEnchantment == 6){ //durability
                int rollValue = pickDurabilityEnum.getRandomValueByTier(currentTier);
                player.sendMessage("rolled: durability");
                if (rollValue > currentDurabilityValue){
                    nbtItem.setInteger("enchantmentDurability", rollValue);
                    player.getInventory().setItemInMainHand(nbtItem.getItem());
                    definedNewEnchantment = true;
                }

            }
        }

    }
}
