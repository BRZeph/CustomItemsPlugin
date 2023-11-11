package me.brzeph.customitems.Events.PlayerStatsRelatedEvents;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static me.brzeph.customitems.CustomItemList.CustomCombatItems.UpdatingPlayerHealth.updatingPlayerHealth;

public class OnArmorEquip implements Listener {
    @EventHandler
    public void onArmorEquip(PlayerArmorChangeEvent event){
        updatingPlayerHealth(event.getPlayer());
        NBTEntity nbtEntity = new NBTEntity(event.getPlayer());
        NBTCompound playerData = nbtEntity.getPersistentDataContainer();
        event.getPlayer().sendMessage("§a[DEBUG]: "+ playerData.getFloat("bonusHealth"));
        event.getPlayer().sendMessage("§a[DEBUG]: "+ playerData.getFloat("currentMaxHealth"));
        event.getPlayer().sendMessage("§c------------------------");
    }
}
