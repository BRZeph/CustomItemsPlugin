package me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.CombatMechanics.CustomCombatItems.ComatItemsEnums.WeaponTXDamage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems.CalculatingRarity.calculatingRarity;
import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems.CalculatingRarity.rarityToString;

public class CreateTXWeapon {
    public static ItemStack createTXWeaponAxe(int tier){
        NBTItem nbtItem = new NBTItem(new ItemStack(getAxeMaterial(tier), 1));
        int rarity = calculatingRarity();
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("weaponType", "axe");
        nbtItem.setInteger("tier", tier);
        int minDamage = 0;
        int maxDamage = 1;
        int roll1 = WeaponTXDamage.getWeaponDamage(tier, rarity);
        int roll2 = WeaponTXDamage.getWeaponDamage(tier, rarity);
        if (roll1>roll2){
            minDamage = roll2;
            maxDamage = roll1;
        }else{
            minDamage = roll1;
            maxDamage = roll2;
        }
        nbtItem.setInteger("minDamage", minDamage);
        nbtItem.setInteger("maxDamage", maxDamage);
        ItemStack itemStack = new ItemStack(nbtItem.getItem());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T" + tier + " Axe");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§cDamage: " + minDamage + " -> " + maxDamage);
        lore.add("");
        lore.add(rarityToString(rarity));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    public static ItemStack createTXWeaponSword(int tier){
        NBTItem nbtItem = new NBTItem(new ItemStack(getSwordMaterial(tier), 1));
        int rarity = calculatingRarity();
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("weaponType", "sword");
        nbtItem.setInteger("tier", tier);
        int minDamage = 0;
        int maxDamage = 1;
        int roll1 = WeaponTXDamage.getWeaponDamage(tier, rarity);
        int roll2 = WeaponTXDamage.getWeaponDamage(tier, rarity);
        if (roll1>roll2){
            minDamage = roll2;
            maxDamage = roll1;
        }else{
            minDamage = roll1;
            maxDamage = roll2;
        }
        nbtItem.setInteger("minDamage", minDamage);
        nbtItem.setInteger("maxDamage", maxDamage);
        ItemStack itemStack = new ItemStack(nbtItem.getItem());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T" + tier + " sword");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§cDamage: " + minDamage + " -> " + maxDamage);
        lore.add("");
        lore.add(rarityToString(rarity));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    public static ItemStack createTXWeaponShovel(int tier){
        NBTItem nbtItem = new NBTItem(new ItemStack(getShovelMaterial(tier), 1));
        int rarity = calculatingRarity();
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("weaponType", "shovel");
        nbtItem.setInteger("tier", tier);
        int minDamage = 0;
        int maxDamage = 1;
        int roll1 = WeaponTXDamage.getWeaponDamage(tier, rarity);
        int roll2 = WeaponTXDamage.getWeaponDamage(tier, rarity);
        if (roll1>roll2){
            minDamage = roll2;
            maxDamage = roll1;
        }else{
            minDamage = roll1;
            maxDamage = roll2;
        }
        nbtItem.setInteger("minDamage", minDamage);
        nbtItem.setInteger("maxDamage", maxDamage);
        ItemStack itemStack = new ItemStack(nbtItem.getItem());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T" + tier + " shovel");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§cDamage: " + minDamage + " -> " + maxDamage);
        lore.add("");
        lore.add(rarityToString(rarity));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    public static ItemStack createTXWeaponHoe(int tier){
        NBTItem nbtItem = new NBTItem(new ItemStack(getHoeMaterial(tier), 1));
        int rarity = calculatingRarity();
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("weaponType", "hoe");
        nbtItem.setInteger("tier", tier);
        int minDamage = 0;
        int maxDamage = 1;
        int roll1 = WeaponTXDamage.getWeaponDamage(tier, rarity);
        int roll2 = WeaponTXDamage.getWeaponDamage(tier, rarity);
        if (roll1>roll2){
            minDamage = roll2;
            maxDamage = roll1;
        }else{
            minDamage = roll1;
            maxDamage = roll2;
        }
        nbtItem.setInteger("minDamage", minDamage);
        nbtItem.setInteger("maxDamage", maxDamage);
        ItemStack itemStack = new ItemStack(nbtItem.getItem());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T" + tier + " hoe");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§cDamage: " + minDamage + " -> " + maxDamage);
        lore.add("");
        lore.add(rarityToString(rarity));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    public static ItemStack createTXWeaponRandomType(int tier){
        NBTItem nbtItem = new NBTItem(new ItemStack(getRandomMaterial(tier), 1));
        int rarity = calculatingRarity();
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("weaponType", new ItemStack(nbtItem.getItem()).getType().toString());
        nbtItem.setInteger("tier", tier);
        int minDamage = 0;
        int maxDamage = 1;
        int roll1 = WeaponTXDamage.getWeaponDamage(tier, rarity);
        int roll2 = WeaponTXDamage.getWeaponDamage(tier, rarity);
        if (roll1>roll2){
            minDamage = roll2;
            maxDamage = roll1;
        }else{
            minDamage = roll1;
            maxDamage = roll2;
        }
        nbtItem.setInteger("minDamage", minDamage);
        nbtItem.setInteger("maxDamage", maxDamage);
        ItemStack itemStack = new ItemStack(nbtItem.getItem());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T" + tier + " " + new ItemStack(nbtItem.getItem()).getType());

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§cDamage: " + minDamage + " -> " + maxDamage);
        lore.add("");
        lore.add(rarityToString(rarity));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    public static Material getAxeMaterial(int tier) {
        switch(tier){
            case 1:
                return Material.WOODEN_AXE;
            case 2:
                return Material.STONE_AXE;
            case 3:
                return Material.IRON_AXE;
            case 4:
                return Material.DIAMOND_AXE;
            case 5:
                return Material.GOLDEN_AXE;
        }
        throw new RuntimeException();
    }
    public static Material getSwordMaterial(int tier) {
        switch(tier){
            case 1:
                return Material.WOODEN_SWORD;
            case 2:
                return Material.STONE_SWORD;
            case 3:
                return Material.IRON_SWORD;
            case 4:
                return Material.DIAMOND_SWORD;
            case 5:
                return Material.GOLDEN_SWORD;
        }
        throw new RuntimeException();
    }
    public static Material getShovelMaterial(int tier) {
        switch(tier){
            case 1:
                return Material.WOODEN_SHOVEL;
            case 2:
                return Material.STONE_SHOVEL;
            case 3:
                return Material.IRON_SHOVEL;
            case 4:
                return Material.DIAMOND_SHOVEL;
            case 5:
                return Material.GOLDEN_SHOVEL;
        }
        throw new RuntimeException();
    }
    public static Material getHoeMaterial(int tier) {
        switch(tier){
            case 1:
                return Material.WOODEN_HOE;
            case 2:
                return Material.STONE_HOE;
            case 3:
                return Material.IRON_HOE;
            case 4:
                return Material.DIAMOND_HOE;
            case 5:
                return Material.GOLDEN_HOE;
        }
        throw new RuntimeException();
    }
    private static Material getRandomMaterial(int tier) {
        Random random = new Random();
        int randomValue = random.nextInt(4) + 1;
        if (randomValue == 1){
            return getAxeMaterial(tier);
        } else if (randomValue == 2) {
            return getSwordMaterial(tier);
        } else if (randomValue == 3) {
            return getHoeMaterial(tier);
        } else {
            return getShovelMaterial(tier);
        }
    }
}
