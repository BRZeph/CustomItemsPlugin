package me.brzeph.customitems.CustomMobs;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class SpawnerPlaceEvent implements Listener {
    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack itemHeld = player.getInventory().getItemInMainHand();
        Block placedBlock = event.getBlockPlaced();
        if (itemHeld.getItemMeta().getDisplayName().equals("Custom Spawner")){
            NBTItem nbtItem = new NBTItem(itemHeld);
            int customBlock = nbtItem.getInteger("customBlock");
            int tier = nbtItem.getInteger("tier");
            int mobType = nbtItem.getInteger("mobType");
            int respawnRate = nbtItem.getInteger("respawnRate");
            int maxAmountOfMobs = nbtItem.getInteger("maxAmountOfMobs");
            int size = nbtItem.getInteger("size");
            placedBlock.setType(Material.SPAWNER);
            NBT.modifyPersistentData(placedBlock.getState(), nbt -> {
                nbt.setInteger("customBlock", customBlock);
                nbt.setInteger("tier", tier);
                nbt.setInteger("mobType", mobType);
                nbt.setInteger("respawnRate", respawnRate);
                nbt.setInteger("maxAmountOfMobs", maxAmountOfMobs);
                nbt.setInteger("size", size);
            });
            SpawnerRegistry.spawnerList.put(placedBlock.getLocation(), placedBlock);
        }
    }
}
