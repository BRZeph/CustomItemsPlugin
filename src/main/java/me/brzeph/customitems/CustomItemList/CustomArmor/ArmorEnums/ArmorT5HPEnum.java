package me.brzeph.customitems.CustomItemList.CustomArmor.ArmorEnums;

import java.util.Random;

public enum ArmorT5HPEnum {
    common(2100,2699),
    uncommon(2700,3199),
    rare(3200,3699),
    epic(3700,3899),
    legendary(3900,4000);

    ArmorT5HPEnum(int min, int max) {
        this.min = min;
        this.max = max;
    }

    private final int min;
    private final int max;
    public static int getRandomValueByRarity(int rarity) {
        Random random = new Random();

        if (rarity == 1) {
            return random.nextInt(common.max - common.min + 1) + common.min;
        } else if (rarity == 2) {
            return random.nextInt(uncommon.max - uncommon.min + 1) + uncommon.min;
        } else if (rarity == 3) {
            return random.nextInt(rare.max - rare.min + 1) + rare.min;
        } else if (rarity == 4) {
            return random.nextInt(epic.max - epic.min + 1) + epic.min;
        } else if (rarity == 5) {
            return random.nextInt(legendary.max - legendary.min + 1) + legendary.min;
        }
        throw new RuntimeException();
    }
}
