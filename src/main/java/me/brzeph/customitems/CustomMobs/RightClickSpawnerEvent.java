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
            SharedData.callingBlock.put(event.getPlayer(), event.getClickedBlock());
            spawnerOpenGUI(event.getPlayer());
            event.setCancelled(true);
            //DEBUG
            NBTTileEntity nbtTileEntity = new NBTTileEntity(event.getClickedBlock().getState());
            event.getPlayer().sendMessage("[DEBUG]: " + "\n" + "tier " +
            nbtTileEntity.getPersistentDataContainer().getInteger("tier") + "\n" + "mobTypeValue " +
            nbtTileEntity.getPersistentDataContainer().getInteger("mobType") + "\n" + "respawnRate " +
            nbtTileEntity.getPersistentDataContainer().getInteger("respawnRate")+ "\n" + "mobCap " +
            nbtTileEntity.getPersistentDataContainer().getInteger("maxAmountOfMobs") + "\n" + "size " +
            nbtTileEntity.getPersistentDataContainer().getInteger("size") + "\n" + "UUID " +
            nbtTileEntity.getPersistentDataContainer().getUUID("randomID") + "\n" +
            nbtTileEntity.getPersistentDataContainer().getInteger("1") + "\n" +
            nbtTileEntity.getPersistentDataContainer().getInteger("2") + "\n" +
            nbtTileEntity.getPersistentDataContainer().getInteger("3") + "\n" +
            nbtTileEntity.getPersistentDataContainer().getInteger("4") + "\n" +
            nbtTileEntity.getPersistentDataContainer().getInteger("5") + "\n" +
            nbtTileEntity.getPersistentDataContainer().getInteger("6") + "\n" +
            nbtTileEntity.getPersistentDataContainer().getInteger("7")
            );
        }
    }
}