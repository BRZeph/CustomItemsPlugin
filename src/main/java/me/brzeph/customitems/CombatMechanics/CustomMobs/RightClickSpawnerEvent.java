package me.brzeph.customitems.CombatMechanics.CustomMobs;

import me.brzeph.customitems.CombatMechanics.CustomMobs.GUI.SharedData;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.brzeph.customitems.CombatMechanics.CustomMobs.GUI.SpawnerGUI.spawnerOpenGUI;

public class RightClickSpawnerEvent implements Listener {
    @EventHandler
    public void onSpawnerRightClick(PlayerInteractEvent event){
        if (event.getAction().isRightClick() && event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.SPAWNER) {
            SharedData.callingBlock.put(event.getPlayer(), event.getClickedBlock());
            spawnerOpenGUI(event.getPlayer());
            event.setCancelled(true);
        }
    }
}