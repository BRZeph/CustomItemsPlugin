package me.brzeph.customitems.CombatMechanics.CombatSystem;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems.CreateTXArmor;
import me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems.CreateTXWeapon;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems.CreateTXWeapon.*;
import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.UpgradingArmorLore.upgradingArmorLore;
import static org.bukkit.Bukkit.getServer;

public enum CustomMobsListEnum2 {
    T1BasicMobLevel1(1,"Leather wearer Bandit", 1, 1,1, true, "axe", mobWeaponAxe(1)
            , createArmorSet(1, 1), false),
    T1BasicMobLevel4(2,"Leather wearer Bandit", 1, 4, 2, true, "sword", mobWeaponSword(1)
            , createArmorSet(1, 4), false),
    T1BasicMobLevel7(3,"Leather wearer Bandit", 1, 7, 1, true, "shovel", mobWeaponShovel(1)
            , createArmorSet(1, 7), false),
    T1BasicMobLevel10(4,"Leather wearer Bandit", 1, 10, 1, true, "hoe", mobWeaponHoe(1)
            , createArmorSet(1, 10), false),
    T1BasicMobLevel13(5,"Leather wearer Bandit", 1, 13, 2, true, "random", mobWeaponRandomType(1)
            , createArmorSet(1, 13), false),
    T1BasicMobLevel16(6,"Leather wearer thief", 1, 16, 1, true, "random", mobWeaponRandomType(1)
            , createArmorSet(1, 16), false),
    T1BasicMobLevel19(11,"Leather wearer thief", 1, 19, 3, true, "random", mobWeaponRandomType(1)
            , createArmorSet(1, 19), true),
    T2BasicMobLevel21(7,"Chain wearer Bandit", 2, 21,1, true, "axe", mobWeaponAxe(2)
            , createArmorSet(2, 21), false),
    T2BasicMobLevel24(8,"Chain wearer Bandit", 2, 24, 2, true, "sword", mobWeaponSword(2)
            , createArmorSet(2, 24), false),
    T2BasicMobLevel27(9,"Chain wearer Bandit", 2, 27, 1, true, "shovel", mobWeaponShovel(2)
            , createArmorSet(2, 27), false),
    T2BasicMobLevel30(10,"Chain wearer Bandit", 2, 30, 1, true, "hoe", mobWeaponHoe(2)
            , createArmorSet(2, 30), false),
    T2BasicMobLevel33(11,"Chain wearer Bandit", 2, 33, 2, true, "random", mobWeaponRandomType(2)
            , createArmorSet(2, 33), false),
    T2BasicMobLevel36(12,"Chain wearer thief", 2, 36, 1, true, "random", mobWeaponRandomType(2)
            , createArmorSet(2, 36), false),
    T2BasicMobLevel39(13,"Chain wearer thief", 2, 39, 3, true, "random", mobWeaponRandomType(2)
            , createArmorSet(2, 39), true),


    T2BanditZombieRandomWeapon(14,"Chain wearer Bandit", 2, 39, 1, true, "random", mobWeaponRandomType(2)
            , createArmorSet(2, 39), false),
    T3BanditZombieRandomWeapon(15,"Iron wearer Bandit", 3, 59, 1, true, "random", mobWeaponRandomType(3)
            , createArmorSet(3, 59), false),
    T4BanditZombieRandomWeapon(16,"Diamond wearer Bandit", 4, 79, 1, true, "random", mobWeaponRandomType(4)
            , createArmorSet(4, 79), false),
    T5BanditZombieRandomWeapon(17,"Gold wearer Bandit", 5, 99, 1, true, "random", mobWeaponRandomType(5)
            , createArmorSet(5, 99), false);

    private static ItemStack mobWeaponAxe(int tier) {
        return CreateTXWeapon.createTXWeaponAxe(tier);
    }
    private static ItemStack mobWeaponSword(int tier) {
        return CreateTXWeapon.createTXWeaponSword(tier);
    }
    private static ItemStack mobWeaponShovel(int tier) {
        return CreateTXWeapon.createTXWeaponShovel(tier);
    }
    private static ItemStack mobWeaponHoe(int tier) {
        return CreateTXWeapon.createTXWeaponHoe(tier);
    }
    private static ItemStack mobWeaponRandomType(int tier) {
        return CreateTXWeapon.createTXWeaponRandomType(tier);
    }


    private int mobLevel;
    private String name;
    private int tier;
    private int mobType; //mobType{1,2,...} == {zombie,skeleton,...}
    private boolean melee;
    private ItemStack itemMainHand;
    private ItemStack[] armor;
    private String itemMainHandMaterial;
    private int uniqueMobID;
    private boolean babyVersion;


    CustomMobsListEnum2(int uniqueMobID, String name, int tier, int mobLevel, int mobType, boolean melee, String itemMainHandMaterial, ItemStack itemMainHand, ItemStack[] armor,
                        boolean babyVersion){
        this.name = name;
        this.tier = tier;
        this.mobLevel = mobLevel;
        this.mobType = mobType;
        this.melee = melee;
        this.itemMainHand = itemMainHand;
        this.armor = armor;
        this.itemMainHandMaterial = itemMainHandMaterial;
        this.uniqueMobID = uniqueMobID;
        this.babyVersion = babyVersion;
    }


    public int getMobLevel() {
        return mobLevel;
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

    public boolean isBabyVersion() {
        return babyVersion;
    }
    public static ItemStack[] createArmorSet(int tier, int level){
        ItemStack[] armor = new ItemStack[4];
        armor[3] = upgradingArmorLore(CreateTXArmor.createTXHelmet(tier, level));
        armor[2] = upgradingArmorLore(CreateTXArmor.createTXChestPlate(tier, level));
        armor[1] = upgradingArmorLore(CreateTXArmor.createTXLeggings(tier, level));
        armor[0] = upgradingArmorLore(CreateTXArmor.createTXBoots(tier, level));
        return armor;
    }

    public float getMobHPLevelMultiplier(){
        int level = getMobLevel();
        int baseTierLevel = 20*(getTier()-1);
        if (level >= baseTierLevel + 20 || level < baseTierLevel){throw new RuntimeException("Illegal level compared to tier");}
        float levelHPMultiplier = 1;
        float i;
        for (i = 1; i <= baseTierLevel + 21; i = i + 3) {
            if (baseTierLevel + i <= level && level < baseTierLevel + i + 3) {
                levelHPMultiplier = 0.6F + i/30;
            }
        }
        return levelHPMultiplier;
    }


    public LivingEntity spawnWithRandomStats(Location location) {
        while (true) {
            LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, returnMobTypeToEntityType(getMobType()));
            EntityEquipment inv = entity.getEquipment();
            inv.clear();
            inv.setArmorContents(createArmorSet(tier, getMobLevel()));
            inv.setItemInMainHand(itemInMainHand(tier, getItemMainHandMaterial()));
            inv.setHelmetDropChance(0F);
            inv.setChestplateDropChance(0F);
            inv.setLeggingsDropChance(0F);
            inv.setBootsDropChance(0F);
            inv.setItemInMainHandDropChance(0F);
            int helmetArmor = new NBTItem(inv.getHelmet()).getInteger("bonusArmor");
            int chestplateArmor = new NBTItem(inv.getChestplate()).getInteger("bonusArmor");
            int leggingsArmor = new NBTItem(inv.getLeggings()).getInteger("bonusArmor");
            int bootsArmor = new NBTItem(inv.getBoots()).getInteger("bonusArmor");
            int totalArmor = helmetArmor + chestplateArmor + leggingsArmor + bootsArmor;

            // Check if it's a baby version and rerun the loop if it is
            if (babyVersion){
                if (isBabyVersion(returnMobTypeToEntityType(getMobType()), entity)) {
                    entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                    entity.setMaxHealth(10);
                    entity.setHealth(10);
                    entity.setCustomNameVisible(true);
                    entity.setCustomName("Level " + mobLevel + " " + name + " ♥ " + returnEntityMaxHP(entity) + "/" + returnEntityMaxHP(entity));
                    NBTEntity nbtEntity = new NBTEntity(entity);
                    NBTCompound entityData = nbtEntity.getPersistentDataContainer();
                    entityData.setInteger("maxHP", returnEntityMaxHP(entity));
                    entityData.setInteger("currentHP", returnEntityMaxHP(entity));
                    entityData.setInteger("totalArmor", totalArmor);
                    entityData.setInteger("mobTier", tier);
                    entityData.setInteger("mobLevel", mobLevel);
                    entityData.setBoolean("sunlightResistant", true);
                    nbtEntity.mergeCompound(entityData);

                    return entity;
                } else {
                    entity.remove();
                }
            } else if (!isBabyVersion(returnMobTypeToEntityType(getMobType()), entity)) {
                entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                entity.setMaxHealth(10);
                entity.setHealth(10);
                entity.setCustomNameVisible(true);
                entity.setCustomName("Level " + mobLevel + " " + name + " ♥ " + returnEntityMaxHP(entity) + "/" + returnEntityMaxHP(entity));
                NBTEntity nbtEntity = new NBTEntity(entity);
                NBTCompound entityData = nbtEntity.getPersistentDataContainer();
                entityData.setInteger("maxHP", returnEntityMaxHP(entity));
                entityData.setInteger("currentHP", returnEntityMaxHP(entity));
                entityData.setInteger("totalArmor", totalArmor);
                entityData.setInteger("mobTier", tier);
                entityData.setInteger("mobLevel", mobLevel);
                entityData.setBoolean("sunlightResistant", true);
                nbtEntity.mergeCompound(entityData);

                return entity;
            } else {
                entity.remove();
            }
        }
    }
    private boolean isBabyVersion(EntityType entityType, LivingEntity entity) {
        if (entityType == EntityType.ZOMBIE && entity instanceof Zombie) {
            Zombie zombie = (Zombie) entity;
            return zombie.isBaby();
        }
        // Add similar checks for other entities with baby versions if needed
        return false;
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

    public static int returnEntityMaxHP(LivingEntity entity){
        EntityEquipment inv = entity.getEquipment();
        int helmetHP = 0;
        int chestPlateHP = 0;
        int leggingsHP = 0;
        int bootsHP = 0;
        int baseHealth = 0;
        if (entity.getEquipment().getHelmet() != null){
            helmetHP = new NBTItem(inv.getHelmet()).getInteger("bonusHealth");
        }
        if (entity.getEquipment().getChestplate() != null){
            chestPlateHP = new NBTItem(inv.getChestplate()).getInteger("bonusHealth");
        }
        if (entity.getEquipment().getLeggings() != null){
            leggingsHP = new NBTItem(inv.getLeggings()).getInteger("bonusHealth");
        }
        if (entity.getEquipment().getBoots() != null){
            bootsHP = new NBTItem(inv.getBoots()).getInteger("bonusHealth");
        }
        if (entity instanceof Player){
            NBTEntity nbtEntity = new NBTEntity(entity);
            baseHealth = nbtEntity.getInteger("baseHealth");
        }
        return helmetHP + chestPlateHP + leggingsHP + bootsHP + baseHealth;
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
