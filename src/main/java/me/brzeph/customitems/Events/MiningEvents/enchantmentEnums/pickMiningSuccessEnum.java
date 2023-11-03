package me.brzeph.customitems.Events.MiningEvents.enchantmentEnums;

import java.util.Random;

public enum pickMiningSuccessEnum {
    T1(5,8),
    T2(7,10),
    T3(8,13),
    T4(10,18),
    T5(13,22);

    pickMiningSuccessEnum(int min, int max) {
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
