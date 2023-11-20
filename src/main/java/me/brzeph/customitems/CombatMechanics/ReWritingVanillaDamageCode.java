package me.brzeph.customitems.CombatMechanics;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static me.brzeph.customitems.CombatMechanics.CombatSystem.PlayerHealthRegeneration.playerHealthRegenTickCount;
import static me.brzeph.customitems.CombatMechanics.OnArmorEquip.updatingPlayerMaxHealth;
import static org.bukkit.Bukkit.getServer;

public class ReWritingVanillaDamageCode implements Listener {
    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            // Check if it's fall damage

            double fallMultiplier = calculateCustomFallDamage(event.getEntity().getFallDistance())/10;
            if (event.getEntity() instanceof Player) {
                Player player = ((Player) event.getEntity()).getPlayer();
                NBTEntity nbtEntity = new NBTEntity(player);
                NBTCompound playerData = nbtEntity.getPersistentDataContainer();
                float HPBeforeFall = playerData.getFloat("currentHP");
                float HPAfterFall = (float) (HPBeforeFall - fallMultiplier*HPBeforeFall);
                float maxHealth = playerData.getFloat("currentMaxHealth");
                playerData.setFloat("currentHP", (float) (HPBeforeFall - fallMultiplier*HPBeforeFall));
                nbtEntity.mergeCompound(playerData);
                updatingPlayerMaxHealth(player, false);
                player.sendMessage("§c[FALL]: -" + (int)(fallMultiplier*HPBeforeFall) + "HP");
                player.sendMessage("§cHP: " + HPAfterFall);
                player.setHealth(20*HPAfterFall/maxHealth);
                if (!playerData.getBoolean("onCombat")){
                    playerHealthRegenTickCount.put(player, 1);
                }
            }
            event.setDamage(0);
        }
        if (event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION){
            event.setDamage(0);
        }
    }

    private double calculateCustomFallDamage(double fallDistance) {
        if (fallDistance <= 3) {
            return 0.0;
        } else if (fallDistance <= 4) {
            getServer().getConsoleSender().sendMessage("[DEBUG] 1");
            // 0.5 hearts for falls of 4 blocks
            return 1.0; // 1 heart = 2 damage points
        } else if (fallDistance <= 10) {
            // Linear progression from 0.5 to 4 hearts for falls between 4 and 10 blocks
            return (fallDistance - 4) * (4.0 - 0.5) / (10 - 4) + 0.5;
        } else if (fallDistance <= 20) {
            // Linear progression from 4 to 8 hearts for falls between 10 and 20 blocks
            return (fallDistance - 10) * (8.0 - 4) / (20 - 10) + 4.0;
        } else if (fallDistance <= 50) {
            // Constant 9 hearts for falls between 20 and 50 blocks
            return 9.0;
        } else {
            // Constant 9.9 hearts for falls greater than 50 block
            return 9.9;
        }
    }
}
