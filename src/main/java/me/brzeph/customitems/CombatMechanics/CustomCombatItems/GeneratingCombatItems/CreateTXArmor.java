package me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.CombatMechanics.CustomCombatItems.ComatItemsEnums.ArmorArmorEnum;
import me.brzeph.customitems.CombatMechanics.CustomCombatItems.ComatItemsEnums.ArmorTXHPEnum;
import me.brzeph.customitems.CombatMechanics.CustomCombatItems.BaseArmorStats;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems.CalculatingRarity.calculatingRarity;
import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems.CalculatingRarity.rarityToString;
import static me.brzeph.customitems.Utils.Utils.getRandomValue;

public class CreateTXArmor {
    public static ItemStack createTXHelmet(int tier, int level){
        NBTItem nbtItem = new NBTItem(new ItemStack(getHelmetMaterial(tier), 1));
        int rarity = calculatingRarity();
        int baseTierLevel = 20*(tier-1);
        if (level > baseTierLevel + 21 || level < baseTierLevel){throw new RuntimeException("Illegal level compared to tier");}
        float levelHPMultiplier = 1;
        float i;
        for (i = 1; i <= baseTierLevel + 21; i = i + 3) {
            if (baseTierLevel + i <= level && level < baseTierLevel + i + 3) {
                levelHPMultiplier = 0.6F + i/30;
            }
        }
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("armorType", "helmet");
        nbtItem.setInteger("tier", tier);
        nbtItem.setInteger("level", level);
        nbtItem.setInteger("bonusHealth", (int) (levelHPMultiplier * ArmorTXHPEnum.getArmorHP(tier, rarity)));
        nbtItem.setInteger("bonusArmor", ArmorArmorEnum.getRandomValueByTier(tier));
        int decidingHPSorEnergy = getRandomValue(100, 1);
        if (decidingHPSorEnergy >= 0 && decidingHPSorEnergy < 33){
            nbtItem.setFloat("armorHPS", (float) ArmorTXHPEnum.getArmorHPS(tier, rarity));
        }
        if (decidingHPSorEnergy >= 33 && decidingHPSorEnergy <= 100){
            nbtItem.setFloat("armorEnergy", (float) ArmorTXHPEnum.getArmorEnergy(tier, rarity));
        }
        Field[] fields = BaseArmorStats.class.getDeclaredFields();
        for (Field field : fields){
            if (field.getType() == int.class){
                try {
                    int value = field.getInt(null);
                    nbtItem.setInteger(field.getName(), value);
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T" + tier + " helmet");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }

    public static ItemStack createTXChestPlate(int tier, int level){
        NBTItem nbtItem = new NBTItem(new ItemStack(getChestPlateMaterial(tier), 1));
        int rarity = calculatingRarity();
        int baseTierLevel = 20*(tier-1);
        if (level > baseTierLevel + 21 || level < baseTierLevel){throw new RuntimeException("Illegal level compared to tier");}
        float levelHPMultiplier = 1;
        float i;
        for (i = 1; i <= baseTierLevel + 21; i = i + 3) {
            if (baseTierLevel + i <= level && level < baseTierLevel + i + 3) {
                levelHPMultiplier = 0.6F + i/30;
            }
        }
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("armorType", "chestPlate");
        nbtItem.setInteger("tier", tier);
        nbtItem.setInteger("level", level);
        nbtItem.setInteger("bonusHealth", (int) (levelHPMultiplier * ArmorTXHPEnum.getArmorHP(tier, rarity)));
        nbtItem.setInteger("bonusArmor", ArmorArmorEnum.getRandomValueByTier(tier));
        int decidingHPSorEnergy = getRandomValue(100, 1);
        if (decidingHPSorEnergy >= 0 && decidingHPSorEnergy < 33){
            nbtItem.setFloat("armorHPS", (float) ArmorTXHPEnum.getArmorHPS(tier, rarity));
        }
        if (decidingHPSorEnergy >= 33 && decidingHPSorEnergy <= 101){
            nbtItem.setFloat("armorEnergy", (float) ArmorTXHPEnum.getArmorEnergy(tier, rarity));
        }
        Field[] fields = BaseArmorStats.class.getDeclaredFields();
        for (Field field : fields){
            if (field.getType() == int.class){
                try {
                    int value = field.getInt(null);
                    nbtItem.setInteger(field.getName(), value);
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T" + tier + " chestPlate");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
    public static ItemStack createTXLeggings(int tier, int level){
        NBTItem nbtItem = new NBTItem(new ItemStack(getLeggingsMaterial(tier), 1));
        int rarity = calculatingRarity();
        int baseTierLevel = 20*(tier-1);
        if (level > baseTierLevel + 21 || level < baseTierLevel){throw new RuntimeException("Illegal level compared to tier");}
        float levelHPMultiplier = 1;
        float i;
        for (i = 1; i <= baseTierLevel + 21; i = i + 3) {
            if (baseTierLevel + i <= level && level < baseTierLevel + i + 3) {
                levelHPMultiplier = 0.6F + i/30;
            }
        }
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("armorType", "leggings");
        nbtItem.setInteger("tier", tier);
        nbtItem.setInteger("level", level);
        nbtItem.setInteger("bonusHealth", (int) (levelHPMultiplier * ArmorTXHPEnum.getArmorHP(tier, rarity)));
        nbtItem.setInteger("bonusArmor", ArmorArmorEnum.getRandomValueByTier(tier));
        int decidingHPSorEnergy = getRandomValue(100, 1);
        if (decidingHPSorEnergy >= 0 && decidingHPSorEnergy < 33){
            nbtItem.setFloat("armorHPS", (float) ArmorTXHPEnum.getArmorHPS(tier, rarity));
        }
        if (decidingHPSorEnergy >= 33 && decidingHPSorEnergy <= 101){
            nbtItem.setFloat("armorEnergy", (float) ArmorTXHPEnum.getArmorEnergy(tier, rarity));
        }
        Field[] fields = BaseArmorStats.class.getDeclaredFields();
        for (Field field : fields){
            if (field.getType() == int.class){
                try {
                    int value = field.getInt(null);
                    nbtItem.setInteger(field.getName(), value);
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T" + tier + " leggings");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
    public static ItemStack createTXBoots(int tier, int level){
        NBTItem nbtItem = new NBTItem(new ItemStack(getBootsMaterial(tier), 1));
        int rarity = calculatingRarity();
        int baseTierLevel = 20*(tier-1);
        if (level > baseTierLevel + 21 || level < baseTierLevel){throw new RuntimeException("Illegal level compared to tier");}
        float levelHPMultiplier = 1;
        float i;
        for (i = 1; i <= baseTierLevel + 21; i = i + 3) {
            if (baseTierLevel + i <= level && level < baseTierLevel + i + 3) {
                levelHPMultiplier = 0.6F + i/30;
            }
        }
        nbtItem.setString("rarity", rarityToString(rarity));
        nbtItem.setString("armorType", "boots");
        nbtItem.setInteger("tier", tier);
        nbtItem.setInteger("level", level);
        nbtItem.setInteger("bonusHealth", (int) (levelHPMultiplier * ArmorTXHPEnum.getArmorHP(tier, rarity)));
        nbtItem.setInteger("bonusArmor", ArmorArmorEnum.getRandomValueByTier(tier));
        int decidingHPSorEnergy = getRandomValue(100, 1);
        if (decidingHPSorEnergy >= 0 && decidingHPSorEnergy < 33){
            nbtItem.setFloat("armorHPS", (float) ArmorTXHPEnum.getArmorHPS(tier, rarity));
        }
        if (decidingHPSorEnergy >= 33 && decidingHPSorEnergy <= 101){
            nbtItem.setFloat("armorEnergy", (float) ArmorTXHPEnum.getArmorEnergy(tier, rarity));
        }
        Field[] fields = BaseArmorStats.class.getDeclaredFields();
        for (Field field : fields){
            if (field.getType() == int.class){
                try {
                    int value = field.getInt(null);
                    nbtItem.setInteger(field.getName(), value);
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T" + tier + " boots");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return new ItemStack(itemStack);
    }
    private static Material getHelmetMaterial(int tier) {
        switch(tier){
            case 1:
                return Material.LEATHER_HELMET;
            case 2:
                return Material.CHAINMAIL_HELMET;
            case 3:
                return Material.IRON_HELMET;
            case 4:
                return Material.DIAMOND_HELMET;
            case 5:
                return Material.GOLDEN_HELMET;
        }
        throw new RuntimeException();
    }
    private static Material getChestPlateMaterial(int tier) {
        switch(tier){
            case 1:
                return Material.LEATHER_CHESTPLATE;
            case 2:
                return Material.CHAINMAIL_CHESTPLATE;
            case 3:
                return Material.IRON_CHESTPLATE;
            case 4:
                return Material.DIAMOND_CHESTPLATE;
            case 5:
                return Material.GOLDEN_CHESTPLATE;
        }
        throw new RuntimeException();
    }
    private static Material getLeggingsMaterial(int tier) {
        switch(tier){
            case 1:
                return Material.LEATHER_LEGGINGS;
            case 2:
                return Material.CHAINMAIL_LEGGINGS;
            case 3:
                return Material.IRON_LEGGINGS;
            case 4:
                return Material.DIAMOND_LEGGINGS;
            case 5:
                return Material.GOLDEN_LEGGINGS;
        }
        throw new RuntimeException();
    }
    private static Material getBootsMaterial(int tier) {
        switch(tier){
            case 1:
                return Material.LEATHER_BOOTS;
            case 2:
                return Material.CHAINMAIL_BOOTS;
            case 3:
                return Material.IRON_BOOTS;
            case 4:
                return Material.DIAMOND_BOOTS;
            case 5:
                return Material.GOLDEN_BOOTS;
        }
        throw new RuntimeException();
    }
}
