package me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems;

import me.brzeph.customitems.CombatMechanics.CustomCombatItems.ComatItemsEnums.WeaponEnchantments;

import java.util.Random;

import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.ComatItemsEnums.WeaponEnchantments.T5VSMonster;
import static org.bukkit.util.Vector.getRandom;

public class CalculatingRarity {
    public static int calculatingRarity(){
        int choosingRarity = randomRarityInt();
        int common = 50;
        int uncommon = 80;
        int rare = 90;
        int epic = 98;
        int legendary = 100;
        int rarity = 0;
        if (0 <= choosingRarity && choosingRarity <= common){
            rarity = 1;
        }
        if (common < choosingRarity && choosingRarity <= uncommon){
            rarity = 2;
        }
        if (uncommon < choosingRarity && choosingRarity <= rare){
            rarity = 3;
        }
        if (rare < choosingRarity && choosingRarity <= epic){
            rarity = 4;
        }
        if (epic < choosingRarity && choosingRarity <= legendary){
            rarity = 5;
        }
        return rarity;
    }
    public static int randomRarityInt(){
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
    public static String rarityToString(int i){
        String rarity = "";
        if (i == 1){
            rarity = "§fCommon";
        }
        if (i == 2){
            rarity = "§aUncommon";
        }
        if (i == 3){
            rarity = "§9Rare";
        }
        if (i == 4){
            rarity = "§5Epic";
        }
        if (i == 5){
            rarity = "§eLegendary";
        }
        return rarity;
    }
}
