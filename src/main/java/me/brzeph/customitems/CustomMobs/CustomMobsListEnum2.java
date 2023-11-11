package me.brzeph.customitems.CustomMobs;

import me.brzeph.customitems.CustomItemList.CustomCombatItems.GeneratingCombatItems.CreateTXArmor;
import me.brzeph.customitems.CustomItemList.CustomCombatItems.GeneratingCombatItems.CreateTXWeapon;
import org.bukkit.inventory.ItemStack;

public enum CustomMobsListEnum2 {
    T1BanditZombieAxe("Leather wearer Bandit", 1, 1, true, mobWeaponAxe(1), createArmorSet(1)),
    T1BanditZombieSword("Leather wearer Bandit", 1, 1, true, mobWeaponSword(1), createArmorSet(1)),
    T1BanditZombieShovel("Leather wearer Bandit", 1, 1, true, mobWeaponShovel(1), createArmorSet(1)),
    T1BanditZombieHoe("Leather wearer Bandit", 1, 1, true, mobWeaponHoe(1), createArmorSet(1)),
    T1BanditSkeletonRandomWeapon("Leather wearer Bandit", 1, 2, true, mobWeaponRandomType(1), createArmorSet(1)),
    T1ThiefZombieRandomWeapon("Leather wearer thief", 1, 1, true, mobWeaponRandomType(1), createArmorSet(1)),
    T1ThiefSkeletonRandomWeapon("Leather wearer thief", 1, 2, true, mobWeaponRandomType(1), createArmorSet(1)),


    T2BanditZombieRandomWeapon("Chain wearer Bandit", 2, 1, true, mobWeaponRandomType(2), createArmorSet(2)),
    T3BanditZombieRandomWeapon("Iron wearer Bandit", 3, 1, true, mobWeaponRandomType(3), createArmorSet(3)),
    T4BanditZombieRandomWeapon("Diamond wearer Bandit", 4, 1, true, mobWeaponRandomType(4), createArmorSet(4)),
    T5BanditZombieRandomWeapon("Gold wearer Bandit", 5, 1, true, mobWeaponRandomType(5), createArmorSet(5));

    private static ItemStack mobWeaponAxe(int i) {
        return CreateTXWeapon.createTXWeaponAxe(i);
    }
    private static ItemStack mobWeaponSword(int i) {
        return CreateTXWeapon.createTXWeaponSword(i);
    }
    private static ItemStack mobWeaponShovel(int i) {
        return CreateTXWeapon.createTXWeaponShovel(i);
    }
    private static ItemStack mobWeaponHoe(int i) {
        return CreateTXWeapon.createTXWeaponHoe(i);
    }
    private static ItemStack mobWeaponRandomType(int i) {
        return CreateTXWeapon.createTXWeaponRandomType(i);
    }

    public static ItemStack[] createArmorSet(int tier){
        ItemStack[] armor = new ItemStack[4];
        armor[3] = CreateTXArmor.createTXHelmet(tier);
        armor[2] = CreateTXArmor.createTXChestPlate(tier);
        armor[1] = CreateTXArmor.createTXLeggings(tier);
        armor[0] = CreateTXArmor.createTXBoots(tier);
        return armor;
    }

    private String name;
    private int tier;
    private int mobType; //mobType{1,2,...} == {zombie,skeleton,...}
    private boolean melee;
    private ItemStack itemMainHand;
    private ItemStack[] armor;


    CustomMobsListEnum2(String name, int tier, int mobType, boolean melee, ItemStack itemMainHand, ItemStack[] armor){
        this.name = name;
        this.tier = tier;
        this.mobType = mobType;
        this.melee = melee;
        this.itemMainHand = itemMainHand;
        this.armor = armor;
    }
    public String getName() {
        return name;
    }
    public int getTier() {
        return tier;
    }
    public int getMobType() {
        return mobType;
    }
    public boolean isMelee() {
        return melee;
    }
}
