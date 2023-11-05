package me.brzeph.customitems.CustomItemList.CustomArmor;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UpdatingPlayerHealth {
    public static void updatingBonusHealth(Player player){
        float armorBonusHealth = 0;
        if (player.getEquipment().getHelmet() != null){
            ItemStack helmet = new ItemStack(player.getEquipment().getHelmet());
            NBTItem nbtItem = new NBTItem(helmet);
            float helmetBonusHealth = nbtItem.getInteger("bonusHealth");
            armorBonusHealth += helmetBonusHealth;
        }
        if (player.getEquipment().getChestplate() != null){
            ItemStack chestPlate = new ItemStack(player.getEquipment().getHelmet());
            NBTItem nbtItem = new NBTItem(chestPlate);
            float chestPlateBonusHealth = nbtItem.getInteger("bonusHealth");
            armorBonusHealth += chestPlateBonusHealth;
        }
        if (player.getEquipment().getLeggings() != null){
            ItemStack leggings = new ItemStack(player.getEquipment().getHelmet());
            NBTItem nbtItem = new NBTItem(leggings);
            float leggingsBonusHealth = nbtItem.getInteger("bonusHealth");
            armorBonusHealth += leggingsBonusHealth;
        }
        if (player.getEquipment().getBoots() != null){
            ItemStack boots = new ItemStack(player.getEquipment().getHelmet());
            NBTItem nbtItem = new NBTItem(boots);
            float bootsBonusHealth = nbtItem.getInteger("bonusHealth");
            armorBonusHealth += bootsBonusHealth;
        }
        NBTEntity nbtEntity = new NBTEntity(player);
        NBTCompound playerData = nbtEntity.getPersistentDataContainer();
        playerData.setFloat("bonusHealth", armorBonusHealth);
        nbtEntity.mergeCompound(playerData);
    }
    public static void updatingPlayerHealth(Player player){
        NBTEntity nbtEntity = new NBTEntity(player);
        NBTCompound playerData = nbtEntity.getPersistentDataContainer();
        float baseHealth = playerData.getInteger("baseHealth");
        float bonusHealth = playerData.getInteger("bonusHealth");
        float currentMaxHealth = baseHealth + bonusHealth;
        playerData.setFloat("currentMaxHealth", currentMaxHealth);
        nbtEntity.mergeCompound(playerData);
        updatingBonusHealth(player);
    }
}
