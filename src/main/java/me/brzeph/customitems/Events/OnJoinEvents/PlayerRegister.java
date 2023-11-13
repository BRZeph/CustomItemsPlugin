package me.brzeph.customitems.Events.OnJoinEvents;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import me.brzeph.customitems.CombatSystem.PlayerHealthRegeneration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.brzeph.customitems.CombatSystem.SetPlayerHPToXPBar.setPlayerHPToXPBar;
import static me.brzeph.customitems.Events.PlayerStatsRelatedEvents.OnArmorEquip.updatingPlayerMaxHealth;

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
        playerData.setBoolean("onCombat", false);
        playerData.setInteger("baseCombatTimer", 10); //int in seconds
        nbtEntity.mergeCompound(playerData);
        updatingPlayerMaxHealth(player);
        setPlayerHPToXPBar(player);
        if (PlayerHealthRegeneration.playerHealthRegenTickCount.get(player) != null) {
            PlayerHealthRegeneration.playerHealthRegenTickCount.remove(player);
        }
    }
    @EventHandler
    public void noVanillaRegen(EntityRegainHealthEvent event){
        event.setCancelled(true);
    }
}