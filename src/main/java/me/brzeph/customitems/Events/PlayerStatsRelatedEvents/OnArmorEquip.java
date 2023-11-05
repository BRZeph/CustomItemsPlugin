package me.brzeph.customitems.Events.PlayerStatsRelatedEvents;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static me.brzeph.customitems.CustomItemList.CustomArmor.UpdatingPlayerHealth.updatingPlayerHealth;

public class OnArmorEquip implements Listener {
    @EventHandler
    public void onArmorEquip(PlayerArmorChangeEvent event){
        updatingPlayerHealth(event.getPlayer());
    }
}
