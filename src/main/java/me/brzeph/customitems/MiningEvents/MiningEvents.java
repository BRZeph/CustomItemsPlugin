package me.brzeph.customitems.MiningEvents;


import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTTileEntity;
import me.brzeph.customitems.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class MiningEvents implements Listener {
    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event){
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        Block blockPlaced = event.getBlockPlaced();
        NBTTileEntity aoba = new NBTTileEntity(blockPlaced.getState());
        NBTItem item = new NBTItem(itemInHand);
        NBTCompound itemCompound = aoba.addCompound("customData");
        int i = item.getCompound("customData").getInteger("tier");
        String tier = item.getCompound("customData").getString("customPickaxe");
        itemCompound.setInteger("tier", i);
        itemCompound.setString("customPickaxe", tier);
        Bukkit.getConsoleSender().sendMessage("Debug: NBT Data of item: " + aoba);


    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        Block blockBroke = event.getBlock();

        if (isCustomPickaxe(itemInHand)) {
                if (isCustomOre(blockBroke)) {
                    if (pickaxeCanBreakOre(itemInHand, blockBroke)) {

                        ItemStack diamonds = new ItemStack(Material.DIAMOND, 10);
                        if (player.getInventory().firstEmpty() >= 0) {
                            player.getInventory().addItem(diamonds);
                            player.sendMessage("it worked");
                        } else {
                            // Player's inventory is full, drop diamonds on the ground.
                            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), diamonds);
                        }
                    } else{
                        player.sendMessage("Your pickaxe tier is not great enough to break this ore");
                        event.setCancelled(true);
                    }
                } else {
                    player.sendMessage("You can not break this block for it is a fool's ore");
                    event.setCancelled(true);
                }
        } else {
            // Cancel the block break event to prevent it from breaking
            player.sendMessage("You are not using a custom pickaxe");
            event.setCancelled(true);
        } //any custom item used to break a block different from pickaxes will not work
    } //to add new custom items add a ''if'' after the last ''else'' and create the item there

    private boolean isCustomPickaxe(ItemStack itemHeld) {
        Main plugin = Main.getInstance();

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
        NBTCompound customData = item.getCompound("customData");
        for (String key : customData.getKeys()) {
        }
        // Check if the customPickaxe key is present and retrieve its value.
        if (customData.hasTag("customPickaxe")) {
            String customPickaxe = customData.getString("customPickaxe");

            // Check if the value is in the expected format.
            if (customPickaxe.startsWith("tier ") && isNumeric(customPickaxe.substring(5))) {
                int tier = Integer.parseInt(customPickaxe.substring(5));

                if (tier >= 1 && tier <= 5) {
                    return true;
                }
            }
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

    public boolean isCustomOre(Block blockBroke) {
        if (blockBroke.hasMetadata("customOre")) {
            // Check if the customOre metadata exists
            String customOre = blockBroke.getMetadata("customOre").get(0).asString();

            // Assuming the "customOre" metadata is in the format "tier X" where X is 1-5.
            String[] parts = customOre.split(" ");
            if (parts.length == 2) {
                try {
                    int tier = Integer.parseInt(parts[1]);
                    if (tier >= 1 && tier <= 5) {
                        return true;
                    }
                } catch (NumberFormatException e) {
                    // Parsing the tier as an integer failed, not a valid custom ore.
                }
            }
        }

        return false;
    }
    public boolean pickaxeCanBreakOre(ItemStack itemHeld, Block blockBroke) {
        // Check if the itemHeld has customPickaxe NBT tag
        NBTItem nbtItem = new NBTItem(itemHeld);
        if (nbtItem.hasKey("customPickaxe")) {
            String customPickaxe = nbtItem.getString("customPickaxe");
            int pickaxeTier = Integer.parseInt(customPickaxe.split(" ")[1]); // Extract the tier

            // Check if the blockBroke has customOre metadata
            if (blockBroke.hasMetadata("customOre")) {
                String customOre = blockBroke.getMetadata("customOre").get(0).asString();
                int oreTier = Integer.parseInt(customOre.split(" ")[1]); // Extract the tier

                // Compare the tiers
                return pickaxeTier >= oreTier;
            }
        }

        // No customPickaxe tag found, or no customOre metadata found, or the tiers don't match.
        return false;
    }
}