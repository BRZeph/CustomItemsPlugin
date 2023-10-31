package me.brzeph.customitems.MiningEvents;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.*;

public class MiningEvents implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        Block blockBroken = event.getBlock();

        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        } else {
            if (isCustomPickaxe(itemInHand)) {
                if (pickaxeCanBreakOre(itemInHand, blockBroken)) {
                    ItemStack diamonds = new ItemStack(Material.DIAMOND, 10);
                    if (player.getInventory().firstEmpty() >= 0) {
                        player.getInventory().addItem(diamonds);
                        player.sendMessage("it worked");
                    } else {
                        // Player's inventory is full, drop diamonds on the ground.
                        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), diamonds);
                    }
                } else {
                    player.sendMessage("Your pickaxe tier is not great enough to break this ore");
                    event.setCancelled(true);
                }
            } else {
                // Cancel the block break event to prevent it from breaking
                player.sendMessage("You are not using a custom pickaxe");
                event.setCancelled(true);
            } //any custom item used to break a block different from pickaxes will not work
        }
    }//to add new custom items add a ''if'' after the last ''else'' and create the item there

    private boolean isCustomPickaxe(ItemStack itemHeld) {
        // Check if the item exists.
        if (itemHeld == null) {
            return false;
        }
        // Check if the item has item meta.
        if (!itemHeld.hasItemMeta()) {
            return false;
        }
        // Log the keys in the persistent data container.
        NBTItem item = new NBTItem(itemHeld);
        if (item.getInteger("tier") != 0) {
            return true;
        }
        return false;
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean pickaxeCanBreakOre(ItemStack itemHeld, Block blockBroken) {

        // Check if the itemHeld has customPickaxe NBT tag
        NBTItem nbtItem = new NBTItem(itemHeld);
        int pickaxeTier = nbtItem.getInteger("tier");

            // Print the values for debugging
        getConsoleSender().sendMessage("pickaxeTier: " + pickaxeTier);
        getConsoleSender().sendMessage("blockType: " + blockBroken.getType());

            if (blockBroken.getType() == Material.COAL_ORE) {
                if (pickaxeTier >= 1){
                    return true;
                }
            } else if (blockBroken.getType() == Material.EMERALD_ORE){
                if (pickaxeTier >= 2) {
                    return true;
                }
            } else if (blockBroken.getType() == Material.IRON_ORE){
                if (pickaxeTier >= 3) {
                    return true;
                }
            } else if (blockBroken.getType() == Material.DIAMOND_ORE){
                if (pickaxeTier >= 4) {
                    return true;
                }
            } else if (blockBroken.getType() == Material.GOLD_ORE){
                if (pickaxeTier >= 5) {
                    return true;
                }
            }
        return false;
    }

}