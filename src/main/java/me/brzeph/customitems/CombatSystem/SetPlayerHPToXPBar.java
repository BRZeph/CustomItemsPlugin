package me.brzeph.customitems.CombatSystem;

import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.entity.Player;

public class SetPlayerHPToXPBar {
    public static void setPlayerHPToXPBar(Player player){
        NBTEntity nbtEntity = new NBTEntity(player);
        float currentHP = nbtEntity.getPersistentDataContainer().getFloat("currentHP");
        player.setLevel((int)currentHP);
    }
}

