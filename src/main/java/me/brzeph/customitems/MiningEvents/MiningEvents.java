package me.brzeph.customitems.MiningEvents;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
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
                    if (didBlockBreak(itemInHand, blockBroken, player)) {
                        event.setDropItems(false);
                        blockBrokenResult(itemInHand, blockBroken, player);
                        didPickLevelUp(player);
                        didPickChangeTier(itemInHand, blockBroken);
                        oreRespawnMechanic(blockBroken, player);

                        if (didPickaxeLoseDurability(itemInHand, player)){
                            event.setCancelled(true);
                        }
                    } else{
                        player.sendMessage("§9Failed to break the ore");
                        event.setCancelled(true);
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
    public void oreRespawnMechanic(Block blockBroken, Player player) {
        player.sendMessage("DEBUG: oreRespawnMechanic starting");

        BlockState originalBlockState = blockBroken.getState();

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            blockBroken.setType(Material.STONE);
        }, 1);

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            // Restore the original block state
            originalBlockState.update(true, false);
            player.sendMessage("DEBUG: Block has been restored");
        }, 20 * 5);

        player.sendMessage("DEBUG: oreRespawnMechanic ending");
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
    public boolean didPickaxeLoseDurability(ItemStack itemHeld, Player player){

        NBTItem nbtItem = new NBTItem(itemHeld);
        int enchantmentDurability = nbtItem.getInteger("enchantmentDurability");
        int durabilityEnchantmentRoll = durabilityEnchantmentRandomRoll();
        if (durabilityEnchantmentRoll < enchantmentDurability){
            player.sendMessage("The Durability enchantment just worked, you did not lose any durability!");
            return true;
        }
        return false;
    }
    public void blockBrokenResult(ItemStack itemHeld, Block blockBroken, Player player) {

        NBTItem nbtItem = new NBTItem(itemHeld);

        int XPGained = 0;
        int amountOfOreDropped = 1;
        int tierGemFindAdaptation = 0;
        int enchantmentDoubleOre = nbtItem.getInteger("enchantmentDoubleOre");
        int enchantmentTripleOre = nbtItem.getInteger("enchantmentTripleOre");  //the mining success enchantment does not need to be here for it's already being
        int enchantmentGemFind = nbtItem.getInteger("enchantmentGemFind");      //check in the previous method of ''didBlockBreak''
        int enchantmentTreasureFind = nbtItem.getInteger("enchantmentTreasureFind");

        if (blockBroken.getType() == Material.COAL_ORE){
            tierGemFindAdaptation = 5;
            XPGained = t1OreXPGenerator();

            if (doubleOreRandomRoll() <= enchantmentDoubleOre && doubleOreRandomRoll() != 0){
                player.sendMessage("Congratulations, the double ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*2;
            }

            if (tripleOreRandomRoll() <= enchantmentTripleOre && tripleOreRandomRoll() != 0){
                player.sendMessage("Congratulations, the triple ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*3;
            }

            if (gemFindRandomRollForEnchantment() <= enchantmentGemFind && gemFindRandomRollForEnchantment() != 0) {
                int intGem = gemFindRandomRollForGems()/tierGemFindAdaptation;
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
                int intGem = 640/tierGemFindAdaptation;
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
                player.sendMessage(ChatColor.RED + "You don't have enough slots, the item[s] have been dropped on the ground");
            }

            player.sendMessage("Successfully mined a coal ore");
            player.sendMessage("You got " + amountOfOreDropped + " ores");
            player.sendMessage("+" + XPGained + " XP gained");

        } else if (blockBroken.getType() == Material.EMERALD_ORE){
            tierGemFindAdaptation = 4;
            XPGained = t2OreXPGenerator();

            if (doubleOreRandomRoll() <= enchantmentDoubleOre && doubleOreRandomRoll() != 0){
                player.sendMessage("Congratulations, the double ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*2;
            }

            if (tripleOreRandomRoll() <= enchantmentTripleOre && tripleOreRandomRoll() != 0){
                player.sendMessage("Congratulations, the triple ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*3;
            }

            if (gemFindRandomRollForEnchantment() <= enchantmentGemFind && gemFindRandomRollForEnchantment() != 0) {
                int intGem = gemFindRandomRollForGems()/tierGemFindAdaptation;
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
                int intGem = 640/tierGemFindAdaptation;
                ItemStack treasure = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(treasure);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, treasure);
                }
                player.sendMessage("You have found " + intGem + " gems");
            }

            ItemStack emeraldResult = new ItemStack(Material.EMERALD_ORE, amountOfOreDropped);
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(emeraldResult);

            } else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, emeraldResult);
                player.sendMessage(ChatColor.RED + "You don't have enough slots, the item[s] have been dropped on the ground");
            }

            player.sendMessage("Successfully mined an emerald ore");
            player.sendMessage("You got " + amountOfOreDropped + " ores");
            player.sendMessage("+" + XPGained + " XP gained");

        }else if (blockBroken.getType() == Material.IRON_ORE){
            tierGemFindAdaptation = 3;
            XPGained = t3OreXPGenerator();

            if (doubleOreRandomRoll() <= enchantmentDoubleOre && doubleOreRandomRoll() != 0){
                player.sendMessage("Congratulations, the double ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*2;
            }

            if (tripleOreRandomRoll() <= enchantmentTripleOre && tripleOreRandomRoll() != 0){
                player.sendMessage("Congratulations, the triple ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*3;
            }

            if (gemFindRandomRollForEnchantment() <= enchantmentGemFind && gemFindRandomRollForEnchantment() != 0) {
                int intGem = gemFindRandomRollForGems()/tierGemFindAdaptation;
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
                int intGem = 640/tierGemFindAdaptation;
                ItemStack treasure = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(treasure);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, treasure);
                }
                player.sendMessage("You have found " + intGem + " gems");
            }

            ItemStack ironResult = new ItemStack(Material.IRON_ORE, amountOfOreDropped);
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(ironResult);

            } else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, ironResult);
                player.sendMessage(ChatColor.RED + "You don't have enough slots, the item[s] have been dropped on the ground");
            }

            player.sendMessage("Successfully mined an iron ore");
            player.sendMessage("You got " + amountOfOreDropped + " ores");
            player.sendMessage("+" + XPGained + " XP gained");

        }else if (blockBroken.getType() == Material.DIAMOND_ORE){
            tierGemFindAdaptation = 2;
            XPGained = t4OreXPGenerator();

            if (doubleOreRandomRoll() <= enchantmentDoubleOre && doubleOreRandomRoll() != 0){
                player.sendMessage("Congratulations, the double ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*2;
            }

            if (tripleOreRandomRoll() <= enchantmentTripleOre && tripleOreRandomRoll() != 0){
                player.sendMessage("Congratulations, the triple ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*3;
            }

            if (gemFindRandomRollForEnchantment() <= enchantmentGemFind && gemFindRandomRollForEnchantment() != 0) {
                int intGem = gemFindRandomRollForGems()/tierGemFindAdaptation;
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
                int intGem = 640/tierGemFindAdaptation;
                ItemStack treasure = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(treasure);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, treasure);
                }
                player.sendMessage("You have found " + intGem + " gems");
            }

            ItemStack diamondResult = new ItemStack(Material.DIAMOND_ORE, amountOfOreDropped);
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(diamondResult);

            } else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, diamondResult);
                player.sendMessage(ChatColor.RED + "You don't have enough slots, the item[s] have been dropped on the ground");
            }

            player.sendMessage("Successfully mined a diamond ore");
            player.sendMessage("You got " + amountOfOreDropped + " ores");
            player.sendMessage("+" + XPGained + " XP gained");

        }else if (blockBroken.getType() == Material.GOLD_ORE){
            tierGemFindAdaptation = 1;
            XPGained = t5OreXPGenerator();

            if (doubleOreRandomRoll() <= enchantmentDoubleOre && doubleOreRandomRoll() != 0){
                player.sendMessage("Congratulations, the double ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*2;
            }

            if (tripleOreRandomRoll() <= enchantmentTripleOre && tripleOreRandomRoll() != 0){
                player.sendMessage("Congratulations, the triple ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*3;
            }

            if (gemFindRandomRollForEnchantment() <= enchantmentGemFind && gemFindRandomRollForEnchantment() != 0) {
                int intGem = gemFindRandomRollForGems()/tierGemFindAdaptation;
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
                int intGem = 640/tierGemFindAdaptation;
                ItemStack treasure = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(treasure);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, treasure);
                }
                player.sendMessage("You have found " + intGem + " gems");
            }

            ItemStack goldResult = new ItemStack(Material.GOLD_ORE, amountOfOreDropped);
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(goldResult);

            } else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, goldResult);
                player.sendMessage(ChatColor.RED + "You don't have enough slots, the item[s] have been dropped on the ground");
            }

            player.sendMessage("Successfully mined a gold ore");
            player.sendMessage("You got " + amountOfOreDropped + " ores");
            player.sendMessage("+" + XPGained + " XP gained");

        }
        int newCurrentXP = nbtItem.getInteger("currentXP") + XPGained;
        modifyItemLore(player, 3, "§aCurrent XP: §6" + newCurrentXP);
        nbtItem.setInteger("currentXP", newCurrentXP);
        player.getInventory().setItemInMainHand(nbtItem.getItem());
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
    public boolean didBlockBreak (ItemStack itemHeld, Block block, Player player){
        int baseT1BreakOreChance = 80;
        int baseT2BreakOreChance = 75;
        int baseT3BreakOreChance = 70;
        int baseT4BreakOreChance = 65;
        int baseT5BreakOreChance = 60;


        NBTItem nbtItem = new NBTItem(itemHeld);
        int currentLevel = nbtItem.getInteger("currentLevel");
        int pickaxeTier = nbtItem.getInteger("tier");
        int blockTier = 0;
        int enchantmentMiningSuccess = nbtItem.getInteger("enchantmentMiningSuccess");
        int incrementInBreakOreChanceT1 = currentLevel * 2;
        int incrementInBreakOreChanceT2 = (currentLevel - 20) * 2;
        int incrementInBreakOreChanceT3 = (currentLevel - 40) * 2;
        int incrementInBreakOreChanceT4 = (currentLevel - 60) * 2;
        int incrementInBreakOreChanceT5 = (currentLevel - 80) * 2;

        int baseRoll = miningRandomRoll();

        int miningRollT1 = baseRoll - enchantmentMiningSuccess - incrementInBreakOreChanceT1;
        int miningRollT2 = baseRoll - enchantmentMiningSuccess - incrementInBreakOreChanceT2;
        int miningRollT3 = baseRoll - enchantmentMiningSuccess - incrementInBreakOreChanceT3;
        int miningRollT4 = baseRoll - enchantmentMiningSuccess - incrementInBreakOreChanceT4;
        int miningRollT5 = baseRoll - enchantmentMiningSuccess - incrementInBreakOreChanceT5;

        if (block.getType() == Material.COAL_ORE){
            blockTier = 1;
        }
        if (block.getType() == Material.EMERALD_ORE){
            blockTier = 2;
        }
        if (block.getType() == Material.IRON_ORE){
            blockTier = 3;
        }
        if (block.getType() == Material.DIAMOND_ORE){
            blockTier = 4;
        }
        if (block.getType() == Material.GOLD_ORE){
            blockTier = 5;
        }
        if (pickaxeTier > blockTier){
            return true;
        } else if(pickaxeTier < blockTier){
            return false;
        }if (block.getType() == Material.COAL_ORE && miningRollT1 < baseT1BreakOreChance){
            return true;
        }if (block.getType() == Material.EMERALD_ORE && miningRollT2 < baseT2BreakOreChance){
            return true;
        }if (block.getType() == Material.IRON_ORE && miningRollT3 < baseT3BreakOreChance){
            return true;
        }if (block.getType() == Material.DIAMOND_ORE && miningRollT4 < baseT4BreakOreChance){
            return true;
        }if (block.getType() == Material.GOLD_ORE && miningRollT5 < baseT5BreakOreChance){
            return true;
        }
        return false;
    }


    public void didPickLevelUp (Player player){
        ItemStack itemHeld = new ItemStack(player.getInventory().getItemInMainHand());

        NBTItem nbtItem = new NBTItem(itemHeld);
        int currentXP = nbtItem.getInteger("currentXP");
        int currentLevel = nbtItem.getInteger("currentLevel");
        int requiredXP = XPToLevelUpRequiredMethod(itemHeld);

        if (currentXP >= requiredXP){    //todo: make this not be this ugly lol
            //todo: create the method that soldado mentioned to create items from nbt tags to facilitate the lore update of items etc

            int newLevel = currentLevel + 1;
            int newXP = currentXP - requiredXP;

            String newLevelString = "§aCurrent level: §6" + Integer.toString(newLevel);
            String newXPString = "§aCurrent XP: §6" + Integer.toString(newXP);
            modifyItemLore(player, 3, newXPString);
            modifyItemLore(player, 4, newLevelString);

            NBTItem nbti = new NBTItem(player.getInventory().getItemInMainHand());

            nbti.setInteger("currentXP", newXP);
            nbti.setInteger("currentLevel", newLevel);
            player.getInventory().setItemInMainHand(nbti.getItem());

            player.sendMessage("§cCongratulations, your pickaxe just leveled up from " + (newLevel - 1) + " to " + newLevel);
            player.sendMessage("§ccurrentXP: " + newXP);
        }else {
            player.sendMessage("§ccurrentXP: " + currentXP);
        }
    }
    public boolean didPickChangeTier (ItemStack itemHeld, Block block){
        return true; //checks for pick change tier, put this method with the didPickLevelUp method
    }
}