package me.brzeph.customitems.CustomItemList.CustomCombatItems.ComatItemsEnums;

import java.util.Random;

public enum ArmorTXHPEnum {
    T1Common(10,39), T1Uncommon(40,59), T1Rare(60,84), T1Epic(85,99), T1Legendary(100,110),
    T2Common(80,149), T2Uncommon(150,239), T2Rare(240,299), T2Epic(300,349), T2Legendary(350,380),
    T3Common(370,459), T3Uncommon(460,549), T3Rare(550,649), T3Epic(650,729), T3Legendary(730,750),
    T4Common(750,1149), T4Uncommon(1150,1399), T4Rare(1400,1699), T4Epic(1700,1899), T4Legendary(1900,2000),
    T5Common(2100,2699), T5Uncommon(2700,3199), T5Rare(3200,3699), T5Epic(3700,3899), T5Legendary(3900,4000);
    ArmorTXHPEnum(int min, int max) {
        this.min = min;
        this.max = max;
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
                        return random.nextInt(T2Common.max - T1Common.min + 1) + T1Common.min;
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
}
