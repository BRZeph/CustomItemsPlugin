package me.brzeph.customitems.MiningEvents;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.List;

import static me.brzeph.customitems.MiningEvents.MiningXPLevelsTable.XPToLevelUpRequiredMethod;
import static me.brzeph.customitems.MiningEvents.RandomValueGenerators.*;

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
                    if (didBlockBreak(itemInHand, blockBroken)) {
                        event.setDropItems(false);
                        blockBrokenResult(itemInHand, blockBroken, player);
                        didPickLevelUp(itemInHand, player);
                        didPickChangeTier(itemInHand, blockBroken);
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
    public void blockBrokenResult(ItemStack itemHeld, Block blockBroken, Player player) {

        NBTItem nbtItem = new NBTItem(itemHeld);
        int XPGained = t1OreXPGenerator(); //todo: replace this line later
        int newCurrentXP = nbtItem.getInteger("currentXP") + XPGained;
        nbtItem.setInteger("currentXP", newCurrentXP);
        player.getInventory().setItemInMainHand(nbtItem.getItem());

        int enchantmentDoubleOre = nbtItem.getInteger("enchantmentDoubleOre");
        int enchantmentTripleOre = nbtItem.getInteger("enchantmentTripleOre");
        int enchantmentMiningSuccess = nbtItem.getInteger("enchantmentMiningSuccess");
        int enchantmentGemFind = nbtItem.getInteger("enchantmentGemFind");
        int enchantmentTreasureFind = nbtItem.getInteger("enchantmentTreasureFind");
        int enchantmentDurability = nbtItem.getInteger("enchantmentDurability");

        if (blockBroken.getType() == Material.COAL_ORE){
            int amountOfOreDropped = 1;
            if (doubleOreRandomRoll() <= enchantmentDoubleOre && doubleOreRandomRoll() != 0){
                player.sendMessage("Congratulations, the double ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*2;
            }
            if (tripleOreRandomRoll() <= enchantmentTripleOre && tripleOreRandomRoll() != 0){
                player.sendMessage("Congratulations, the triple ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*3;
            }
            if (gemFindRandomRollForEnchantment() <= enchantmentGemFind && gemFindRandomRollForEnchantment() != 0) {
                int intGem = gemFindRandomRollForGems()/5;
                ItemStack gems = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(gems);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, gems);
                }
                player.sendMessage("You have found " + intGem + " gems");
            }
            if (treasureFindRandomRoll() <= enchantmentTreasureFind && treasureFindRandomRoll() != 0){
                int intGem = 640/5;
                ItemStack treasure = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(treasure);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, treasure);
                }
                player.sendMessage("You have found " + intGem + " gems");
            }

            ItemStack coalResult = new ItemStack(Material.COAL_ORE, amountOfOreDropped);
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(coalResult);

            } else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, coalResult);
            }

            player.sendMessage("Successfully mined a coal ore");
            player.sendMessage("You got " + amountOfOreDropped + " ores");
            player.sendMessage("+" + XPGained + " XP gained");

        } else if (blockBroken.getType() == Material.EMERALD_ORE){

        }else if (blockBroken.getType() == Material.IRON_ORE){

        }else if (blockBroken.getType() == Material.DIAMOND_ORE){

        }else if (blockBroken.getType() == Material.GOLD_ORE){

        }
        modifyItemLore(player, 3, ChatColor.GREEN + "Current XP: " + newCurrentXP);
    }
    public void modifyItemLore(Player player, int lineIndex, String newLore){
        ItemStack itemHeld = player.getInventory().getItemInMainHand();

        if (itemHeld != null){
            ItemMeta itemMeta = itemHeld.getItemMeta();

            if (itemMeta != null && itemMeta.hasLore()){
                List<String> lore = itemMeta.getLore();

                if (lineIndex >= 0 && lineIndex < lore.size()){
                    lore.set(lineIndex, newLore);
                    itemMeta.setLore(lore);
                    itemHeld.setItemMeta(itemMeta);
                    player.getInventory().setItemInMainHand(itemHeld);
                }
            }
        }
    }
    public boolean didBlockBreak (ItemStack itemHeld, Block block){
        return true;
    } //checks mining success, make this method here so that it works before the blockBrokenResult
    public void didPickLevelUp (ItemStack itemHeld, Player player){

        NBTItem nbtItem = new NBTItem(itemHeld);
        int currentXP = nbtItem.getInteger("currentXP");
        int currentLevel = nbtItem.getInteger("currentLevel");
        int requiredXP = XPToLevelUpRequiredMethod(itemHeld);

        if (currentXP >= requiredXP){        //todo: make this not be this ugly lol
            //todo: create the method that soldado mentioned to create items from nbt tags to facilitate the lore update of items etc

            int newLevel = currentLevel + 1;
            nbtItem.setInteger("currentLevel", newLevel);
            int newXP = currentXP - requiredXP;
            nbtItem.setInteger("CurrentXP", newXP);

            String newLevelString = ChatColor.GREEN + "Current level: " + Integer.toString(newLevel);
            String newXPString = ChatColor.GREEN + "Current XP: " + Integer.toString(newXP);

            player.sendMessage("Congratulations, your pickaxe just leveled up from " + (newLevel - 1) + " to " + newLevel);
            modifyItemLore(player, 3, newXPString);
            modifyItemLore(player, 4, newLevelString);
        }
    }
    public boolean didPickChangeTier (ItemStack itemHeld, Block block){
        return true; //checks for pick change tier, put this method with the didPickLevelUp method
    }
}