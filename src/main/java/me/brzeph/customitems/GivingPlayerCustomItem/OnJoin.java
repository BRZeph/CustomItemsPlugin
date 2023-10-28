package me.brzeph.customitems.GivingPlayerCustomItem;

import me.brzeph.customitems.CustomItemList.CustomOres;
import me.brzeph.customitems.CustomItemList.CustomPickaxe;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    @EventHandler
    public  void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        player.getInventory().addItem(CustomPickaxe.create_dr_wooden_pickaxe());
        player.sendMessage(ChatColor.YELLOW + "Here is a T1 pickaxe for you, enjoy :D");
        player.getInventory().addItem(CustomOres.create_dr_coal_ore());
        player.getInventory().addItem(CustomOres.create_dr_emerald_ore());
    }
}
