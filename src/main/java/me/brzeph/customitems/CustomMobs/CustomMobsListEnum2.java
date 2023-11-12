package me.brzeph.customitems.CustomMobs;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.CustomItemList.CustomCombatItems.GeneratingCombatItems.CreateTXArmor;
import me.brzeph.customitems.CustomItemList.CustomCombatItems.GeneratingCombatItems.CreateTXWeapon;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import static me.brzeph.customitems.CustomItemList.CustomCombatItems.GeneratingCombatItems.CreateTXWeapon.*;

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
    public LivingEntity spawnWithRandomStats(Location location){

        LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, returnMobTypeToEntityType(getMobType()));
        EntityEquipment inv = entity.getEquipment();
        inv.clear();
        inv.setArmorContents(createArmorSet(tier));
        inv.setItemInMainHand(itemInMainHand(tier, getItemMainHandMaterial()));
        inv.setHelmetDropChance(0F);
        inv.setChestplateDropChance(0F);
        inv.setLeggingsDropChance(0F);
        inv.setBootsDropChance(0F);
        inv.setItemInMainHandDropChance(0F);

        entity.setCustomNameVisible(true);
        entity.setCustomName(name + " ♥ " + returnMobMaxHP(entity) + "/" + returnMobMaxHP(entity) + "♥");
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(returnMobMaxHP(entity));
        entity.setHealth(returnMobMaxHP(entity));
        return entity;
    }

    private ItemStack itemInMainHand(int tier, String weaponMaterial) {
        ItemStack itemStack;
        if (weaponMaterial.equalsIgnoreCase("axe")) return createTXWeaponAxe(tier);
        if (weaponMaterial.equalsIgnoreCase("sword")) return createTXWeaponSword(tier);
        if (weaponMaterial.equalsIgnoreCase("shovel")) return createTXWeaponShovel(tier);
        if (weaponMaterial.equalsIgnoreCase("hoe")) return createTXWeaponHoe(tier);
        if (weaponMaterial.equalsIgnoreCase("random")) return createTXWeaponRandomType(tier);
        itemStack = new ItemStack(Material.REDSTONE_BLOCK);
        if (itemStack.getType() == Material.REDSTONE_BLOCK){
            throw new IllegalStateException("Incorrectly defined weapon type.");
        }
        return null;
    }

    public int returnMobMaxHP(LivingEntity entity){
        EntityEquipment inv = entity.getEquipment();
        int helmetHP = 0;
        int chestPlateHP = 0;
        int leggingsHP = 0;
        int bootsHP = 0;
        if (armor != null){
            helmetHP = new NBTItem(inv.getHelmet()).getInteger("bonusHealth");
            chestPlateHP = new NBTItem(inv.getChestplate()).getInteger("bonusHealth");
            leggingsHP = new NBTItem(inv.getLeggings()).getInteger("bonusHealth");
            bootsHP = new NBTItem(inv.getBoots()).getInteger("bonusHealth");
        }
        int totalArmorHP = helmetHP + chestPlateHP + leggingsHP + bootsHP;
        return totalArmorHP;
    }
    public EntityType returnMobTypeToEntityType(int mobType){
        EntityType mobTypeToEntityType = EntityType.MINECART_MOB_SPAWNER;
        return switch (mobType) {
            case 1 -> mobTypeToEntityType = EntityType.ZOMBIE;
            case 2 -> mobTypeToEntityType = EntityType.SKELETON;
            default -> throw new IllegalArgumentException("Unregistered mobType: " + mobType);
        };
    }
}
