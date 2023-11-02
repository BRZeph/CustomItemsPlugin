package me.brzeph.customitems.MiningEvents.enchantmentEnums;

public enum OreRespawnCooldown {
    T1(5),     //numbers in seconds please, not in ticks
    T2(10),
    T3(15),
    T4(20),
    T5(25);

    OreRespawnCooldown(int respawnTime) {
        this.respawnTime = respawnTime;
    }
    private final int respawnTime;

    public static int getOreRespawnTime(int tier){
        return switch (tier) {
            case 1 -> T1.respawnTime;
            case 2 -> T2.respawnTime;
            case 3 -> T3.respawnTime;
            case 4 -> T4.respawnTime;
            case 5 -> T5.respawnTime;
            default -> throw new RuntimeException("Invalid tier provided: " + tier);
        };
    }
}
