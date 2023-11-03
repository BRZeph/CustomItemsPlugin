package me.brzeph.customitems.Events.MiningEvents.Enums.enchantmentEnums;

import java.util.Random;

public enum pickGemFindEnum {
    T1(5,8),
    T2(5,10),
    T3(5,13),
    T4(5,18),
    T5(8,22);

    pickGemFindEnum(int min, int max) {
        this.min = min;
        this.max = max;
    }

    private final int min;
    private final int max;
    public static int getRandomValueByTier(int tier) {
        Random random = new Random();

        if (tier == 1) {
            return random.nextInt(T1.max - T1.min + 1) + T1.min;
        } else if (tier == 2) {
            return random.nextInt(T2.max - T2.min + 1) + T2.min;
        } else if (tier == 3) {
            return random.nextInt(T3.max - T3.min + 1) + T3.min;
        } else if (tier == 4) {
            return random.nextInt(T4.max - T4.min + 1) + T4.min;
        } else if (tier == 5) {
            return random.nextInt(T5.max - T5.min + 1) + T5.min;
        }
        throw new RuntimeException();
    }

}
