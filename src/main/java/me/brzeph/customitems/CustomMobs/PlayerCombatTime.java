package me.brzeph.customitems.CustomMobs;

import de.tr7zw.nbtapi.NBTEntity;
import me.brzeph.customitems.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class PlayerCombatTime implements Listener {
    BukkitTask task1;
    static HashMap<Player, Integer> playerCombatTickCount = new HashMap<>();
    @EventHandler
    public void onServerStart(ServerLoadEvent event){
        playerCombatTickCount();
    }
    public void playerCombatTickCount(){
        task1 = new BukkitRunnable() {
            @Override
            public void run() {
                for (Map.Entry<Player, Integer> entry : playerCombatTickCount.entrySet()){
                    Player player = entry.getKey();
                    NBTEntity nbtEntity = new NBTEntity(player);
                    int previousTickCount = entry.getValue();
                    int newTickCount = previousTickCount - 1;
                    if (previousTickCount < 0 || previousTickCount > 6){
                        throw new IllegalStateException("Player combat tick count error.");
                    }
                    if (newTickCount <= 0){
                        nbtEntity.getPersistentDataContainer().setBoolean("onCombat", false);
                        nbtEntity.getPersistentDataContainer().removeKey("combatTimer");
                        player.sendMessage("§aLeft combat");
                        playerCombatTickCount.remove(player);
                    } else {
                        nbtEntity.getPersistentDataContainer().setInteger("combatTimer", newTickCount);
                        player.sendMessage("§c[In combat for more §f" + newTickCount + "§c seconds]");
                        playerCombatTickCount.put(player, newTickCount);
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20);
    }
}
