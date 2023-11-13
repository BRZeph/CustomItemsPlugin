package me.brzeph.customitems.Events.OnJoinEvents;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

import static me.brzeph.customitems.CustomItemList.CustomCombatItems.UpdatingPlayerHealth.updatingPlayerHealth;
import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Material.*;

public class PlayerRegister implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        NBTEntity nbtEntity = new NBTEntity(player);
        NBTCompound playerData = nbtEntity.getPersistentDataContainer();
        playerData.setString("registeredPlayer", "true");
        playerData.setFloat("baseHealth", 50F);
        playerData.setFloat("bonusHealth", 0F);
        playerData.setFloat("currentMaxHealth", 50F);
        playerData.setFloat("currentHP", 50F);
        playerData.setInteger("baseDamage", 0);
        playerData.setInteger("baseArmor", 5);
        playerData.setInteger("baseDPS", 5);
        nbtEntity.mergeCompound(playerData);
        updatingPlayerHealth(player);
    }
    @EventHandler
    public void onEatingFood (PlayerItemConsumeEvent event){ //prevents vanilla regeneration
        getServer().getConsoleSender().sendMessage("[DEBUG]: food thing: " + isFood(event.getItem()));
        if (isFood(event.getItem())) {
            event.getPlayer().setSaturation(0);
            event.getPlayer().setSaturatedRegenRate(999999999);
        } else {
            event.setCancelled(true);
        }
    }
    //TODO: create health regeneration system
    private boolean isFood(ItemStack item) {
        Set<Material> foodList = new HashSet<>();
        foodList.add(Material.APPLE);
        foodList.add(Material.BREAD);
        foodList.add(Material.CARROT);
        foodList.add(Material.POTATO);
        foodList.add(Material.COOKED_BEEF);
        return foodList.contains(item.getType());
    }
}