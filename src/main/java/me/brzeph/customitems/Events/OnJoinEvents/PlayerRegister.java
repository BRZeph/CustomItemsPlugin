package me.brzeph.customitems.Events.OnJoinEvents;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.brzeph.customitems.CustomItemList.CustomArmor.UpdatingPlayerHealth.updatingPlayerHealth;

public class PlayerRegister implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.getPlayer().sendMessage("[DEBUG]: onplayerjoin is working");
        Player player = event.getPlayer();
        NBTEntity nbtEntity = new NBTEntity(player);
        NBTCompound playerData = nbtEntity.getPersistentDataContainer();
        playerData.setFloat("baseHealth", 50F);
        playerData.setFloat("bonusHealth", 0F);
        playerData.setFloat("currentMaxHealth", 50F);
        playerData.setInteger("baseDamage", 0);
        playerData.setInteger("baseArmor", 5);
        playerData.setInteger("baseDPS", 5);
        nbtEntity.mergeCompound(playerData);
        updatingPlayerHealth(player);
    }
}
