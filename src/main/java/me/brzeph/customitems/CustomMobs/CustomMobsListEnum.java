package me.brzeph.customitems.CustomMobs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.brzeph.customitems.CustomItemList.CustomArmor.GeneratingArmor.CustomArmorBoots.createT1Boots;
import static me.brzeph.customitems.CustomItemList.CustomArmor.UpdatingArmorLore.upgradingArmorLore;

public enum CustomMobsListEnum { //IMPORTANT: all the mobs spawn chance summed must add up to 100
    //TODO: remove the drops from the ''firstMob'' and put on entityDeathEvent
    FirstMob("First Mob", 80, 100, EntityType.ZOMBIE, new ItemStack(Material.WOODEN_SWORD),
            makeArmorSet(new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.LEATHER_LEGGINGS),
                    new ItemStack(Material.LEATHER_BOOTS)), new LootDrops(upgradingArmorLore(createT1Boots()), 1, 1, 99));
    private String name;
    private double maxHealth, spawnChance;
    private EntityType type;
    private ItemStack itemMainHand;
    private ItemStack[] armor;
    private List<LootDrops> lootTable = new ArrayList<>();

    CustomMobsListEnum(String name, double maxHealth, double spawnChance, EntityType type,
                       ItemStack itemMainHand, ItemStack[] armor, LootDrops... lootDrops) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.spawnChance = spawnChance;
        this.type = type;
        this.itemMainHand = itemMainHand;
        this.armor = armor;
        lootTable = Arrays.asList(lootDrops);
    }
    public LivingEntity spawn(Location location){
        LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, type);
        entity.setCustomNameVisible(true);
        entity.setCustomName(name + (int) maxHealth + "/" + (int) maxHealth + "♥");
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        entity.setHealth(maxHealth);
        EntityEquipment inv = entity.getEquipment();
        if (armor != null) inv.setArmorContents(armor);
        inv.setHelmetDropChance(0F);
        inv.setChestplateDropChance(0F);
        inv.setLeggingsDropChance(0F);
        inv.setBootsDropChance(0F);
        inv.setItemInMainHand(itemMainHand);
        inv.setItemInMainHandDropChance(0F);
        return entity;
    }
    public void tryDropLoot(Location location){
        for (LootDrops drop : lootTable){
            drop.tryDropItem(location);
        }
    }
    public String getName() {
        return name;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getSpawnChance() {
        return spawnChance;
    }
    public static ItemStack[] makeArmorSet(ItemStack helmet, ItemStack chestPlate, ItemStack leggings, ItemStack boots){
        ItemStack[] armor = new ItemStack[4];
        armor[3] = helmet;
        armor[2] = chestPlate;
        armor[1] = leggings;
        armor[0] = boots;
        return armor;
    }
}