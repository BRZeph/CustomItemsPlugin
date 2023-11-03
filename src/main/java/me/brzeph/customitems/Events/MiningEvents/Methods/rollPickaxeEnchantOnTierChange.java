package me.brzeph.customitems.Events.MiningEvents.Methods;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.Events.MiningEvents.Enums.enchantmentEnums.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.brzeph.customitems.Events.MiningEvents.RandomValueGenerators.randomlyChoosingNewEnchantment;

public class rollPickaxeEnchantOnTierChange {
    public static void rollPickaxeEnchantmentOnLevelUp (Player player){
        ItemStack itemHeld = new ItemStack(player.getInventory().getItemInMainHand());
        NBTItem nbtItem = new NBTItem(itemHeld);
        int currentTier = nbtItem.getInteger("tier") - 1; //prevents upgrading to t5 without rolling enchants that you can only get on level 100
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
                if (rollValue > currentDoubleOreValue) {
                    nbtItem.setInteger("enchantmentDoubleOre", rollValue);
                    player.getInventory().setItemInMainHand(nbtItem.getItem());
                    player.sendMessage("§aNew enchantment: double ore " + rollValue);
                    definedNewEnchantment = true;
                }

            }if (choosingEnchantment == 2){ //triple ore
                int rollValue = pickTripleOreEnum.getRandomValueByTier(currentTier);
                if (rollValue > currentTripleOreValue){
                    nbtItem.setInteger("enchantmentTripleOre", rollValue);
                    player.getInventory().setItemInMainHand(nbtItem.getItem());
                    player.sendMessage("§aNew enchantment: triple ore " + rollValue);
                    definedNewEnchantment = true;
                }

            }if (choosingEnchantment == 3){ //mining success
                int rollValue = pickMiningSuccessEnum.getRandomValueByTier(currentTier);
                if (rollValue > currentMiningSuccessValue){
                    nbtItem.setInteger("enchantmentMiningSuccess", rollValue);
                    player.getInventory().setItemInMainHand(nbtItem.getItem());
                    player.sendMessage("§aNew enchantment: mining success " + rollValue);
                    definedNewEnchantment = true;
                }

            }if (choosingEnchantment == 4){ //gem find
                int rollValue = pickGemFindEnum.getRandomValueByTier(currentTier);
                if (rollValue > currentGemFindValue){
                    nbtItem.setInteger("enchantmentGemFind", rollValue);
                    player.getInventory().setItemInMainHand(nbtItem.getItem());
                    player.sendMessage("§aNew enchantment: gem find " + rollValue);
                    definedNewEnchantment = true;
                }

            }if (choosingEnchantment == 5){ //treasure find
                if (currentTier >= 3) {
                    int rollValue = pickTreasureFindEnum.getRandomValueByTier(currentTier);
                    if (rollValue > currentTreasureFindValue) {
                        nbtItem.setInteger("enchantmentTreasureFind", rollValue);
                        player.getInventory().setItemInMainHand(nbtItem.getItem());
                        player.sendMessage("§aNew enchantment: treasure find " + rollValue);
                        definedNewEnchantment = true;
                    }
                }

            }if (choosingEnchantment == 6){ //durability
                int rollValue = pickDurabilityEnum.getRandomValueByTier(currentTier);
                if (rollValue > currentDurabilityValue){
                    nbtItem.setInteger("enchantmentDurability", rollValue);
                    player.getInventory().setItemInMainHand(nbtItem.getItem());
                    player.sendMessage("§aNew enchantment: durability " + rollValue);
                    definedNewEnchantment = true;
                }
            }
        }
    }
}
