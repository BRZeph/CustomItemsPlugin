package me.brzeph.customitems.CombatMechanics;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import static me.brzeph.customitems.CombatMechanics.CombatSystem.PlayerHealthRegeneration.playerHealthRegenTickCount;
import static me.brzeph.customitems.CombatMechanics.CombatSystem.SetPlayerHPToXPBar.setPlayerHPToXPBar;
import static org.bukkit.Bukkit.getServer;

public class OnArmorEquip implements Listener {
    @EventHandler
    public void onArmorEquip(PlayerArmorChangeEvent event){
        if (new NBTEntity(event.getPlayer()).getPersistentDataContainer().getBoolean("onCombat")){
            return;
        }
        float previousMaxHealth = new NBTEntity(event.getPlayer()).getPersistentDataContainer().getFloat("currentMaxHealth");
        int previousHealth = 0;
        int newHealth = 0;
        if (event.getOldItem() != null && event.getOldItem().getType() != Material.AIR  && event.getOldItem().getAmount() != 0) {
            getServer().getConsoleSender().sendMessage("[DEBUG1]");
            NBTItem nbtOldArmor = new NBTItem(event.getOldItem());
            previousHealth = nbtOldArmor.getInteger("bonusHealth");
        }
        if (event.getNewItem() != null && event.getNewItem().getType() != Material.AIR  && event.getNewItem().getAmount() != 0) {
            getServer().getConsoleSender().sendMessage("[DEBUG2]");
            NBTItem nbtNewArmor = new NBTItem(event.getNewItem());
            newHealth = nbtNewArmor.getInteger("bonusHealth");
        }
        float currentHP = new NBTEntity(event.getPlayer()).getPersistentDataContainer().getFloat("currentHP");
        float newMaxHealth = previousMaxHealth - previousHealth + newHealth;
        if (currentHP >= newMaxHealth){currentHP = newMaxHealth;}
        float previousVanillaHealth = (float)event.getPlayer().getHealth();
        float playerVanillaHealth = ((20 * currentHP) /newMaxHealth);
        if (playerVanillaHealth != 20 && !new NBTEntity(event.getPlayer()).getPersistentDataContainer().getBoolean("onCombat")){
            playerHealthRegenTickCount.put(event.getPlayer(), 1);
        }
        getServer().getConsoleSender().sendMessage("[DEBUG] previous max health = " + previousMaxHealth);
        getServer().getConsoleSender().sendMessage("[DEBUG] new max health = " + newMaxHealth);
        getServer().getConsoleSender().sendMessage("[DEBUG] current hp = " + currentHP);
        getServer().getConsoleSender().sendMessage("[DEBUG] previous player vanilla health = " + previousVanillaHealth);
        getServer().getConsoleSender().sendMessage("[DEBUG] player vanilla health = " + playerVanillaHealth);
        event.getPlayer().setHealth(playerVanillaHealth);
        updatingPlayerMaxHealth(event.getPlayer(), false);
        setPlayerHPToXPBar(event.getPlayer());
    }
    public static void updatingPlayerMaxHealth(Player player, boolean setPlayerMaxHealth){
        checkingForBonusHP(player);
        NBTEntity nbtEntity = new NBTEntity(player);
        NBTCompound playerData = nbtEntity.getPersistentDataContainer();
        float baseHealth = playerData.getFloat("baseHealth");
        float bonusHealth = playerData.getFloat("bonusHealth");
        float currentMaxHealth = baseHealth + bonusHealth;
        playerData.setFloat("currentMaxHealth", currentMaxHealth);
        if (setPlayerMaxHealth){
            playerData.setFloat("currentHP", currentMaxHealth);
        }
        if (playerData.getFloat("currentHP") > currentMaxHealth){
            playerData.setFloat("currentHP", currentMaxHealth);
        }
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
        nbtEntity.mergeCompound(playerData);
    }
}
