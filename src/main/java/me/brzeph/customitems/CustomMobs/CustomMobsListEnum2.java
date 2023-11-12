package me.brzeph.customitems.CustomMobs;

import me.brzeph.customitems.CustomItemList.CustomCombatItems.GeneratingCombatItems.CreateTXArmor;
import me.brzeph.customitems.CustomItemList.CustomCombatItems.GeneratingCombatItems.CreateTXWeapon;
import org.bukkit.inventory.ItemStack;

public enum CustomMobsListEnum2 {
    T1BanditZombieAxe(1,"Leather wearer Bandit1", 1, 1, true, "axe", mobWeaponAxe(1)
            , createArmorSet(1)),
    T1BanditZombieSword(2,"Leather wearer Bandit2", 1, 3, true, "sword", mobWeaponSword(1)
            , createArmorSet(1)),
    T1BanditZombieShovel(3,"Leather wearer Bandit3", 1, 1, true, "shovel", mobWeaponShovel(1)
            , createArmorSet(1)),
    T1BanditZombieHoe(4,"Leather wearer Bandit4", 1, 1, true, "hoe", mobWeaponHoe(1)
            , createArmorSet(1)),
    T1BanditSkeletonRandomWeapon(5,"Leather wearer Bandit5", 1, 2, true, "random", mobWeaponRandomType(1)
            , createArmorSet(1)),
    T1ThiefZombieRandomWeapon(6,"Leather wearer thief6", 1, 1, true, "random", mobWeaponRandomType(1)
            , createArmorSet(1)),


    T2BanditZombieRandomWeapon(7,"Chain wearer Bandit", 2, 1, true, "random", mobWeaponRandomType(2)
            , createArmorSet(2)),
    T3BanditZombieRandomWeapon(8,"Iron wearer Bandit", 3, 1, true, "random", mobWeaponRandomType(3)
            , createArmorSet(3)),
    T4BanditZombieRandomWeapon(9,"Diamond wearer Bandit", 4, 1, true, "random", mobWeaponRandomType(4)
            , createArmorSet(4)),
    T5BanditZombieRandomWeapon(10,"Gold wearer Bandit", 5, 1, true, "random", mobWeaponRandomType(5)
            , createArmorSet(5));

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
    private String itemMainHandMaterial;
    private int uniqueMobID;


    CustomMobsListEnum2(int uniqueMobID, String name, int tier, int mobType, boolean melee, String itemMainHandMaterial, ItemStack itemMainHand, ItemStack[] armor){
        this.name = name;
        this.tier = tier;
        this.mobType = mobType;
        this.melee = melee;
        this.itemMainHand = itemMainHand;
        this.armor = armor;
        this.itemMainHandMaterial = itemMainHandMaterial;
        this.uniqueMobID = uniqueMobID;
    }

    public int getUniqueMobID() {
        return uniqueMobID;
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

    public ItemStack getItemMainHand() {
        return itemMainHand;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public String getItemMainHandMaterial() {
        return itemMainHandMaterial;
    }
}
