package me.brzeph.customitems.CombatMechanics.CustomCombatItems.ComatItemsEnums;

import static me.brzeph.customitems.Utils.Utils.getRandomValue;

public enum WeaponEnchantments {
    T1VSPlayer(1,20),T1VSMonster(1,20),T1Fire(1,20),T1Ice(1,20),T1Pure(1,20),T1Poison(1,20),T1CriticalHit(1,20),T1LifeSteal(1,20),T1Accuracy(1,20),T1Shatter(1,20),T1Cleave(1,20),T1Crushing(1,20),
    T2VSPlayer(1,20),T2VSMonster(1,20),T2Fire(1,20),T2Ice(1,20),T2Pure(1,20),T2Poison(1,20),T2CriticalHit(1,20),T2LifeSteal(1,20),T2Accuracy(1,20),T2Shatter(1,20),T2Cleave(1,20),T2Crushing(1,20),
    T3VSPlayer(1,20),T3VSMonster(1,20),T3Fire(1,20),T3Ice(1,20),T3Pure(1,20),T3Poison(1,20),T3CriticalHit(1,20),T3LifeSteal(1,20),T3Accuracy(1,20),T3Shatter(1,20),T3Cleave(1,20),T3Crushing(1,20),
    T4VSPlayer(1,20),T4VSMonster(1,20),T4Fire(1,20),T4Ice(1,20),T4Pure(1,20),T4Poison(1,20),T4CriticalHit(1,20),T4LifeSteal(1,20),T4Accuracy(1,20),T4Shatter(1,20),T4Cleave(1,20),T4Crushing(1,20),
    T5VSPlayer(1,20),T5VSMonster(1,20),T5Fire(1,20),T5Ice(1,20),T5Pure(1,20),T5Poison(1,20),T5CriticalHit(1,20),T5LifeSteal(1,20),T5Accuracy(1,20),T5Shatter(1,20),T5Cleave(1,20),T5Crushing(1,20),
    ChanceVSPlayer(10),ChanceVSMonster(10),ChanceFire(8),ChanceIce(8),ChancePure(8),ChancePoison(8),ChanceCriticalHit(10),ChanceLifeSteal(10),ChanceAccuracy(7),ChanceShatter(7),ChanceCleave(7),ChanceCrushing(7);
    WeaponEnchantments(int min, int max) {
        this.min = min;
        this.max = max;
    }
    WeaponEnchantments(int percentage){
        this.percentage = percentage;
    }
    private int percentage;
    private int min;
    private int max;
    public int getPercentage(WeaponEnchantments weaponEnchantments){
        return weaponEnchantments.getPercentage();
    }
    public int getMin(WeaponEnchantments weaponEnchantments) {
        return weaponEnchantments.min;
    }
    public int getMax(WeaponEnchantments weaponEnchantments) {
        return weaponEnchantments.max;
    }
    public static int getRandomOrbValue(WeaponEnchantments weaponEnchantments){
        return getRandomValue(weaponEnchantments.max, weaponEnchantments.min);
    }
    public static int enchantmentValueByTier(WeaponEnchantments weaponEnchantments){
        return getRandomValue(weaponEnchantments.max, weaponEnchantments.min);
    }
    public static String enchantmentNameToLore(WeaponEnchantments weaponEnchantments){
        switch (weaponEnchantments){
            case ChanceVSPlayer: return "§cVS Player";
            case ChanceVSMonster: return "§cVS Monster";
            case ChanceFire: return "§cFire damage";
            case ChanceIce: return "§cIce damage";
            case ChancePure: return "§cPure damage";
            case ChancePoison: return "§cPoison damage";
            case ChanceCriticalHit: return "§cCritical Hit";
            case ChanceLifeSteal: return "§cLife steal";
            case ChanceAccuracy: return "§cAccuracy";
            case ChanceShatter: return "§cShatter";
            case ChanceCleave: return "§cCrushing";
            case ChanceCrushing: return "§cCleave";
        }
        throw new RuntimeException("Wrong usage of ''enchantmentNameToLore'' method, case not found");
    }
    public static String enchantmentNameToNBTTag(WeaponEnchantments weaponEnchantments){
        switch (weaponEnchantments){
            case ChanceVSPlayer: return "VSPlayer";
            case ChanceVSMonster: return "VSMonster";
            case ChanceFire: return "Fire";
            case ChanceIce: return "Ice";
            case ChancePure: return "Pure";
            case ChancePoison: return "Poison";
            case ChanceCriticalHit: return "CriticalHit";
            case ChanceLifeSteal: return "LifeSteal";
            case ChanceAccuracy: return "Accuracy";
            case ChanceShatter: return "Shatter";
            case ChanceCleave: return "Crushing";
            case ChanceCrushing: return "Cleave";
        }
        throw new RuntimeException("Wrong usage of ''enchantmentNameToLore'' method, case not found");
    }
    public int getPercentage() {
        return percentage;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
