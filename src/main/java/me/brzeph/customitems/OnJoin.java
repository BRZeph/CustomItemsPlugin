package me.brzeph.customitems;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    @EventHandler
    public  void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        player.getInventory().addItem(MessingWithNBT.create_dr_wooden_pickaxe());
    }
}
