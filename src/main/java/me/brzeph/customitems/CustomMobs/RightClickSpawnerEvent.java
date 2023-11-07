package me.brzeph.customitems.CustomMobs;

import de.tr7zw.nbtapi.NBTTileEntity;
import me.brzeph.customitems.CustomMobs.GUI.SharedData;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.brzeph.customitems.CustomMobs.GUI.SpawnerGUI.spawnerOpenGUI;

public class RightClickSpawnerEvent implements Listener {
    @EventHandler
    public void onSpawnerRightClick(PlayerInteractEvent event){
        if (event.getAction().isRightClick() && event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.SPAWNER) {
            spawnerOpenGUI(event.getPlayer());
            SharedData.callingBlock.put(event.getPlayer(), event.getClickedBlock());


            //DEBUG
            NBTTileEntity nbtTileEntity = new NBTTileEntity(event.getClickedBlock().getState());
            event.getPlayer().sendMessage("[DEBUG]: " + "\n" +
            nbtTileEntity.getPersistentDataContainer().getInteger("tier") + "\n" +
            nbtTileEntity.getPersistentDataContainer().getInteger("mobType") + "\n" +
            nbtTileEntity.getPersistentDataContainer().getInteger("respawnRate")+ "\n" +
            nbtTileEntity.getPersistentDataContainer().getInteger("maxAmountOfMobs"));
        }
    }
}