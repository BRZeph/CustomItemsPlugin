package me.brzeph.customitems.CombatMechanics;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import static me.brzeph.customitems.CombatMechanics.CombatSystem.SetPlayerHPToXPBar.setPlayerHPToXPBar;

public class OnArmorEquip implements Listener {
    @EventHandler
    public void onArmorEquip(PlayerArmorChangeEvent event){
        updatingPlayerMaxHealth(event.getPlayer());
        setPlayerHPToXPBar(event.getPlayer());
    }
    public static void updatingPlayerMaxHealth(Player player){
        checkingForBonusHP(player);
        NBTEntity nbtEntity = new NBTEntity(player);
        NBTCompound playerData = nbtEntity.getPersistentDataContainer();
        float baseHealth = playerData.getFloat("baseHealth");
        float bonusHealth = playerData.getFloat("bonusHealth");
        float currentMaxHealth = baseHealth + bonusHealth;
        playerData.setFloat("currentMaxHealth", currentMaxHealth);
        playerData.setFloat("currentHP", currentMaxHealth);
        nbtEntity.mergeCompound(playerData);
    }
    public static void checkingForBonusHP(Player player){
        float armorBonusHealth = 0;
        if (player.getEquipment().getHelmet() != null){
            ItemStack helmet = new ItemStack(player.getEquipment().getHelmet());
            NBTItem nbtItem = new NBTItem(helmet);
            float helmetBonusHealth = nbtItem.getFloat("bonusHealth");
            armorBonusHealth += helmetBonusHealth;
        }
        if (player.getEquipment().getChestplate() != null){
            ItemStack chestPlate = new ItemStack(player.getEquipment().getChestplate());
            NBTItem nbtItem = new NBTItem(chestPlate);
            float chestPlateBonusHealth = nbtItem.getFloat("bonusHealth");
            armorBonusHealth += chestPlateBonusHealth;
        }
        if (player.getEquipment().getLeggings() != null){
            ItemStack leggings = new ItemStack(player.getEquipment().getLeggings());
            NBTItem nbtItem = new NBTItem(leggings);
            float leggingsBonusHealth = nbtItem.getFloat("bonusHealth");
            armorBonusHealth += leggingsBonusHealth;
        }
        if (player.getEquipment().getBoots() != null){
            ItemStack boots = new ItemStack(player.getEquipment().getBoots());
            NBTItem nbtItem = new NBTItem(boots);
            float bootsBonusHealth = nbtItem.getFloat("bonusHealth");
            armorBonusHealth += bootsBonusHealth;
        }
        NBTEntity nbtEntity = new NBTEntity(player);
        NBTCompound playerData = nbtEntity.getPersistentDataContainer();
        playerData.setFloat("bonusHealth", armorBonusHealth);
        player.setLevel((int) armorBonusHealth + nbtEntity.getInteger("baseHealth"));
        nbtEntity.mergeCompound(playerData);
    }
}
