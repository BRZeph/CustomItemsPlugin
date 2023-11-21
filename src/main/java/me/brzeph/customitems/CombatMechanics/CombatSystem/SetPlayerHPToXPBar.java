package me.brzeph.customitems.CombatMechanics.CombatSystem;

import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class SetPlayerHPToXPBar {
    public static void setPlayerHPToXPBar(Player player){
        NBTEntity nbtEntity = new NBTEntity(player);
        float currentHP = nbtEntity.getPersistentDataContainer().getFloat("currentHP");
        if (currentHP > 0) {
            player.setLevel((int) currentHP);
        }
        else {
            getServer().getConsoleSender().sendMessage("player died " + player.getName());
        }
    }
}

