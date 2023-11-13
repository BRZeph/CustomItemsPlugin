package me.brzeph.customitems.CustomItemList.CustomCombatItems.ComatItemsEnums;

import java.util.Random;

public enum ArmorTXHPEnum {
    T1Common(10,39), T1Uncommon(40,59), T1Rare(60,84), T1Epic(85,99), T1Legendary(100,110),
    T2Common(80,149), T2Uncommon(150,239), T2Rare(240,299), T2Epic(300,349), T2Legendary(350,380),
    T3Common(370,459), T3Uncommon(460,549), T3Rare(550,649), T3Epic(650,729), T3Legendary(730,750),
    T4Common(750,1149), T4Uncommon(1150,1399), T4Rare(1400,1699), T4Epic(1700,1899), T4Legendary(1900,2000),
    T5Common(2100,2699), T5Uncommon(2700,3199), T5Rare(3200,3699), T5Epic(3700,3899), T5Legendary(3900,4000),
    T1CommonHPS(10,20), T1UncommonHPS(10,30), T1RareHPS(20,40), T1EpicHPS(30,50), T1LegendaryHPS(40,60),
    T2CommonHPS(60,120), T2UncommonHPS(60,180), T2RareHPS(120,240), T2EpicHPS(180,300), T2LegendaryHPS(240,360),
    T3CommonHPS(204,407), T3UncommonHPS(204,611), T3RareHPS(407,814), T3EpicHPS(611,1018), T3LegendaryHPS(814,1221),
    T4CommonHPS(263,525), T4UncommonHPS(263,787), T4RareHPS(525,1050), T4EpicHPS(788,1313), T4LegendaryHPS(1050,1575),
    T5CommonHPS(315,630), T5UncommonHPS(315,945), T5RareHPS(630,1260), T5EpicHPS(945,1575), T5LegendaryHPS(1260,1890),
    T1CommonEnergy(1,1), T1UncommonEnergy(1,2), T1RareEnergy(1,3), T1EpicEnergy(1,4), T1LegendaryEnergy(1,5),
    T2CommonEnergy(2,2), T2UncommonEnergy(2,3), T2RareEnergy(2,4), T2EpicEnergy(2,5), T2LegendaryEnergy(2,6),
    T3CommonEnergy(3,3), T3UncommonEnergy(3,4), T3RareEnergy(3,5), T3EpicEnergy(3,6), T3LegendaryEnergy(3,7),
    T4CommonEnergy(4,4), T4UncommonEnergy(4,5), T4RareEnergy(4,6), T4EpicEnergy(4,7), T4LegendaryEnergy(4,8),
    T5CommonEnergy(5,5), T5UncommonEnergy(5,6), T5RareEnergy(5,7), T5EpicEnergy(5,8), T5LegendaryEnergy(5,9);
    ArmorTXHPEnum(int min, int max) {
        this.min = min;
        this.max = max;
    }
    public static int getMaxHealthValueTier(int tier){
        if (tier <= 0 || tier > 5){
            throw new IllegalArgumentException("Tier must be between 1 and 5 (inclusive).");
        }
        if (tier == 1){
            return T1Legendary.max;
        } else if (tier == 2){
            return T2Legendary.max;
        } else if (tier == 3){
            return T3Legendary.max;
        } else if (tier == 4){
            return T4Legendary.max;
        } else {
            return T5Legendary.max;
        }
    }
    public static int getMinHealthValueTier(int tier){
        if (tier <= 0 || tier > 5){
            throw new IllegalArgumentException("Tier must be between 1 and 5 (inclusive).");
        }
        if (tier == 1){
            return T1Common.min;
        } else if (tier == 2){
            return T2Common.min;
        } else if (tier == 3){
            return T3Common.min;
        } else if (tier == 4){
            return T4Common.min;
        } else {
            return T5Common.min;
        }
    }

    private final int min;
    private final int max;
    public static int getArmorHP(int tier, int rarityRoll) {
        Random random = new Random();

        switch (tier){
            case 1:
                switch (rarityRoll){
                    case 1:
                        return random.nextInt(T1Common.max- T1Common.min + 1) + T1Common.min;
                    case 2:
                        return random.nextInt(T1Uncommon.max- T1Uncommon.min + 1) + T1Uncommon.min;
                    case 3:
                        return random.nextInt(T1Rare.max- T1Rare.min + 1) + T1Rare.min;
                    case 4:
                        return random.nextInt(T1Epic.max- T1Epic.min + 1) + T1Epic.min;
                    case 5:
                        return random.nextInt(T1Legendary.max- T1Legendary.min + 1) + T1Legendary.min;
                }
                throw new RuntimeException();
            case 2:
                switch (rarityRoll) {
                    case 1:
                        return random.nextInt(T2Common.max - T2Common.min + 1) + T2Common.min;
                    case 2:
                        return random.nextInt(T2Uncommon.max - T2Uncommon.min + 1) + T2Uncommon.min;
                    case 3:
                        return random.nextInt(T2Rare.max - T2Rare.min + 1) + T2Rare.min;
                    case 4:
                        return random.nextInt(T2Epic.max - T2Epic.min + 1) + T2Epic.min;
                    case 5:
                        return random.nextInt(T2Legendary.max - T2Legendary.min + 1) + T2Legendary.min;
                }
                throw new RuntimeException();
            case 3:
                switch (rarityRoll) {
                    case 1:
                        return random.nextInt(T3Common.max - T3Common.min + 1) + T3Common.min;
                    case 2:
                        return random.nextInt(T3Uncommon.max - T3Uncommon.min + 1) + T3Uncommon.min;
                    case 3:
                        return random.nextInt(T3Rare.max - T3Rare.min + 1) + T3Rare.min;
                    case 4:
                        return random.nextInt(T3Epic.max - T3Epic.min + 1) + T3Epic.min;
                    case 5:
                        return random.nextInt(T3Legendary.max - T3Legendary.min + 1) + T3Legendary.min;
                }
                throw new RuntimeException();
            case 4:
                switch (rarityRoll) {
                    case 1:
                        return random.nextInt(T4Common.max - T4Common.min + 1) + T4Common.min;
                    case 2:
                        return random.nextInt(T4Uncommon.max - T4Uncommon.min + 1) + T4Uncommon.min;
                    case 3:
                        return random.nextInt(T4Rare.max - T4Rare.min + 1) + T4Rare.min;
                    case 4:
                        return random.nextInt(T4Epic.max - T4Epic.min + 1) + T4Epic.min;
                    case 5:
                        return random.nextInt(T4Legendary.max - T4Legendary.min + 1) + T4Legendary.min;
                }
                throw new RuntimeException();
            case 5:
                switch (rarityRoll) {
                    case 1:
                        return random.nextInt(T5Common.max - T5Common.min + 1) + T5Common.min;
                    case 2:
                        return random.nextInt(T5Uncommon.max - T5Uncommon.min + 1) + T5Uncommon.min;
                    case 3:
                        return random.nextInt(T5Rare.max - T5Rare.min + 1) + T5Rare.min;
                    case 4:
                        return random.nextInt(T5Epic.max - T5Epic.min + 1) + T5Epic.min;
                    case 5:
                        return random.nextInt(T5Legendary.max - T5Legendary.min + 1) + T5Legendary.min;
                }
                throw new RuntimeException();
        }
        throw new RuntimeException();
    }
    public static int getArmorHPS(int tier, int rarityRoll) {
        Random random = new Random();

        switch (tier){
            case 1:
                switch (rarityRoll){
                    case 1:
                        return random.nextInt(T1CommonHPS.max- T1CommonHPS.min + 1) + T1CommonHPS.min;
                    case 2:
                        return random.nextInt(T1UncommonHPS.max- T1UncommonHPS.min + 1) + T1UncommonHPS.min;
                    case 3:
                        return random.nextInt(T1RareHPS.max- T1RareHPS.min + 1) + T1RareHPS.min;
                    case 4:
                        return random.nextInt(T1EpicHPS.max- T1EpicHPS.min + 1) + T1EpicHPS.min;
                    case 5:
                        return random.nextInt(T1LegendaryHPS.max- T1LegendaryHPS.min + 1) + T1LegendaryHPS.min;
                }
                throw new RuntimeException();
            case 2:
                switch (rarityRoll) {
                    case 1:
                        return random.nextInt(T2CommonHPS.max - T2CommonHPS.min + 1) + T2CommonHPS.min;
                    case 2:
                        return random.nextInt(T2UncommonHPS.max - T2UncommonHPS.min + 1) + T2UncommonHPS.min;
                    case 3:
                        return random.nextInt(T2RareHPS.max - T2RareHPS.min + 1) + T2RareHPS.min;
                    case 4:
                        return random.nextInt(T2EpicHPS.max - T2EpicHPS.min + 1) + T2EpicHPS.min;
                    case 5:
                        return random.nextInt(T2LegendaryHPS.max - T2LegendaryHPS.min + 1) + T2LegendaryHPS.min;
                }
                throw new RuntimeException();
            case 3:
                switch (rarityRoll) {
                    case 1:
                        return random.nextInt(T3CommonHPS.max - T3CommonHPS.min + 1) + T3CommonHPS.min;
                    case 2:
                        return random.nextInt(T3UncommonHPS.max - T3UncommonHPS.min + 1) + T3UncommonHPS.min;
                    case 3:
                        return random.nextInt(T3RareHPS.max - T3RareHPS.min + 1) + T3RareHPS.min;
                    case 4:
                        return random.nextInt(T3EpicHPS.max - T3EpicHPS.min + 1) + T3EpicHPS.min;
                    case 5:
                        return random.nextInt(T3LegendaryHPS.max - T3LegendaryHPS.min + 1) + T3LegendaryHPS.min;
                }
                throw new RuntimeException();
            case 4:
                switch (rarityRoll) {
                    case 1:
                        return random.nextInt(T4CommonHPS.max - T4CommonHPS.min + 1) + T4CommonHPS.min;
                    case 2:
                        return random.nextInt(T4UncommonHPS.max - T4UncommonHPS.min + 1) + T4UncommonHPS.min;
                    case 3:
                        return random.nextInt(T4RareHPS.max - T4RareHPS.min + 1) + T4RareHPS.min;
                    case 4:
                        return random.nextInt(T4EpicHPS.max - T4EpicHPS.min + 1) + T4EpicHPS.min;
                    case 5:
                        return random.nextInt(T4LegendaryHPS.max - T4LegendaryHPS.min + 1) + T4LegendaryHPS.min;
                }
                throw new RuntimeException();
            case 5:
                switch (rarityRoll) {
                    case 1:
                        return random.nextInt(T5CommonHPS.max - T5CommonHPS.min + 1) + T5CommonHPS.min;
                    case 2:
                        return random.nextInt(T5UncommonHPS.max - T5UncommonHPS.min + 1) + T5UncommonHPS.min;
                    case 3:
                        return random.nextInt(T5RareHPS.max - T5RareHPS.min + 1) + T5RareHPS.min;
                    case 4:
                        return random.nextInt(T5EpicHPS.max - T5EpicHPS.min + 1) + T5EpicHPS.min;
                    case 5:
                        return random.nextInt(T5LegendaryHPS.max - T5LegendaryHPS.min + 1) + T5LegendaryHPS.min;
                }
                throw new RuntimeException();
        }
        throw new RuntimeException();
    }
    public static int getArmorEnergy(int tier, int rarityRoll) {
        Random random = new Random();

        switch (tier){
            case 1:
                switch (rarityRoll){
                    case 1:
                        return random.nextInt(T1CommonEnergy.max- T1CommonEnergy.min + 1) + T1CommonEnergy.min;
                    case 2:
                        return random.nextInt(T1UncommonEnergy.max- T1UncommonEnergy.min + 1) + T1UncommonEnergy.min;
                    case 3:
                        return random.nextInt(T1RareEnergy.max- T1RareEnergy.min + 1) + T1RareEnergy.min;
                    case 4:
                        return random.nextInt(T1EpicEnergy.max- T1EpicEnergy.min + 1) + T1EpicEnergy.min;
                    case 5:
                        return random.nextInt(T1LegendaryEnergy.max- T1LegendaryEnergy.min + 1) + T1LegendaryEnergy.min;
                }
                throw new RuntimeException();
            case 2:
                switch (rarityRoll) {
                    case 1:
                        return random.nextInt(T2CommonEnergy.max - T2CommonEnergy.min + 1) + T2CommonEnergy.min;
                    case 2:
                        return random.nextInt(T2UncommonEnergy.max - T2UncommonEnergy.min + 1) + T2UncommonEnergy.min;
                    case 3:
                        return random.nextInt(T2RareEnergy.max - T2RareEnergy.min + 1) + T2RareEnergy.min;
                    case 4:
                        return random.nextInt(T2EpicEnergy.max - T2EpicEnergy.min + 1) + T2Epic.min;
                    case 5:
                        return random.nextInt(T2LegendaryEnergy.max - T2LegendaryEnergy.min + 1) + T2LegendaryEnergy.min;
                }
                throw new RuntimeException();
            case 3:
                switch (rarityRoll) {
                    case 1:
                        return random.nextInt(T3CommonEnergy.max - T3CommonEnergy.min + 1) + T3CommonEnergy.min;
                    case 2:
                        return random.nextInt(T3UncommonEnergy.max - T3UncommonEnergy.min + 1) + T3UncommonEnergy.min;
                    case 3:
                        return random.nextInt(T3RareEnergy.max - T3RareEnergy.min + 1) + T3RareEnergy.min;
                    case 4:
                        return random.nextInt(T3EpicEnergy.max - T3EpicEnergy.min + 1) + T3EpicEnergy.min;
                    case 5:
                        return random.nextInt(T3LegendaryEnergy.max - T3LegendaryEnergy.min + 1) + T3LegendaryEnergy.min;
                }
                throw new RuntimeException();
            case 4:
                switch (rarityRoll) {
                    case 1:
                        return random.nextInt(T4CommonEnergy.max - T4CommonEnergy.min + 1) + T4CommonEnergy.min;
                    case 2:
                        return random.nextInt(T4UncommonEnergy.max - T4UncommonEnergy.min + 1) + T4UncommonEnergy.min;
                    case 3:
                        return random.nextInt(T4RareEnergy.max - T4RareEnergy.min + 1) + T4RareEnergy.min;
                    case 4:
                        return random.nextInt(T4EpicEnergy.max - T4EpicEnergy.min + 1) + T4EpicEnergy.min;
                    case 5:
                        return random.nextInt(T4LegendaryEnergy.max - T4LegendaryEnergy.min + 1) + T4LegendaryEnergy.min;
                }
                throw new RuntimeException();
            case 5:
                switch (rarityRoll) {
                    case 1:
                        return random.nextInt(T5CommonEnergy.max - T5CommonEnergy.min + 1) + T5CommonEnergy.min;
                    case 2:
                        return random.nextInt(T5UncommonEnergy.max - T5UncommonEnergy.min + 1) + T5UncommonEnergy.min;
                    case 3:
                        return random.nextInt(T5RareEnergy.max - T5RareEnergy.min + 1) + T5RareEnergy.min;
                    case 4:
                        return random.nextInt(T5EpicEnergy.max - T5EpicEnergy.min + 1) + T5EpicEnergy.min;
                    case 5:
                        return random.nextInt(T5LegendaryEnergy.max - T5LegendaryEnergy.min + 1) + T5LegendaryEnergy.min;
                }
                throw new RuntimeException();
        }
        throw new RuntimeException();
    }
}
