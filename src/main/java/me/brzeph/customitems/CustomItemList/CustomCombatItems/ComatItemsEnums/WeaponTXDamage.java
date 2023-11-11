package me.brzeph.customitems.CustomItemList.CustomCombatItems.ComatItemsEnums;

import java.util.Random;

public enum WeaponTXDamage {
    T1Common(4,7), T1Uncommon(8,11), T1Rare(12,15), T1Epic(16,18), T1Legendary(19,22),
    T2Common(23,30), T2Uncommon(31,40), T2Rare(41,45), T2Epic(46,48), T2Legendary(49,55),
    T3Common(56,70), T3Uncommon(71,80), T3Rare(81,85), T3Epic(86,90), T3Legendary(90,100),
    T4Common(101,130), T4Uncommon(131,150), T4Rare(151,170), T4Epic(171,185), T4Legendary(186,220),
    T5Common(221,280), T5Uncommon(281,330), T5Rare(331,350), T5Epic(351,370), T5Legendary(371,390);
    WeaponTXDamage(int min, int max) {
        this.min = min;
        this.max = max;
    }
    private final int min;
    private final int max;
    public static int getWeaponDamage(int tier, int rarityRoll) {
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
