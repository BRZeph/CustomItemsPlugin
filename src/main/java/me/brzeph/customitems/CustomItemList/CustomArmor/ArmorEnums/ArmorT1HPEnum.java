package me.brzeph.customitems.CustomItemList.CustomArmor.ArmorEnums;

import java.util.Random;

public enum ArmorT1HPEnum {
    common(10,39),
    uncommon(40,59),
    rare(60,84),
    epic(85,99),
    legendary(100,110);

    ArmorT1HPEnum(int min, int max) {
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
