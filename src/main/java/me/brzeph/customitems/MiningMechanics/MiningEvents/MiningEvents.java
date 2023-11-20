package me.brzeph.customitems.MiningMechanics.MiningEvents;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.Main;
import me.brzeph.customitems.MiningMechanics.MiningEvents.Enums.enchantmentEnums.OreRespawnCooldown;
import me.brzeph.customitems.MiningMechanics.MiningEvents.Enums.otherMiningRelatedEnums.pickGemFindValueByTierEnum;
import me.brzeph.customitems.MiningMechanics.MiningEvents.Enums.otherMiningRelatedEnums.pickRollExperienceEnum;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

import static me.brzeph.customitems.MiningMechanics.MiningEvents.Methods.ModifyItemLore.modifyItemLore;
import static me.brzeph.customitems.MiningMechanics.MiningEvents.Methods.UpdateProgressBar.updateProgressBar;
import static me.brzeph.customitems.MiningMechanics.MiningEvents.Methods.UpgradeTier.upgradeTier;
import static me.brzeph.customitems.MiningMechanics.MiningEvents.MiningXPLevelsTable.XPToLevelUpRequiredMethod;
import static me.brzeph.customitems.MiningMechanics.PickaxeCreator.updatePickaxeLoreOnEnchantmentAdd;
import static me.brzeph.customitems.Utils.Utils.getRandomValue;

public class MiningEvents implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        Block blockBroken = event.getBlock();

        if (player.getGameMode() == GameMode.CREATIVE) {
        } else {
            Set<Material> validMaterials = new HashSet<>();
            validMaterials.add(Material.COAL_ORE);
            validMaterials.add(Material.EMERALD_ORE);
            validMaterials.add(Material.IRON_ORE);
            validMaterials.add(Material.DIAMOND_ORE);
            validMaterials.add(Material.GOLD_ORE);
            if (validMaterials.contains(blockBroken.getType())) {
                if (isCustomPickaxe(itemInHand)) {
                    if (pickaxeCanBreakOre(itemInHand, blockBroken)) {
                        if (didBlockBreak(itemInHand, blockBroken, player)) {
                            event.setDropItems(false);
                            event.setExpToDrop(0);
                            blockBrokenResult(itemInHand, blockBroken, player);
                            didPickLevelUp(player);
                            didPickChangeTier(player);
                            oreRespawnMechanic(blockBroken);
                            updateProgressBar(player);

                            if (didPickaxeLoseDurability(itemInHand, player)) {
                                event.setCancelled(true);
                            }
                        } else {
                            player.sendMessage("§cFailed to break the ore!");
                            oreRespawnMechanic(blockBroken);
                            event.setCancelled(true);
                        }
                    } else {
                        player.sendMessage("Your pickaxe tier is not great enough to break this ore!");
                        event.setCancelled(true);
                    }
                } else {
                    player.sendMessage("You are not using a custom pickaxe!");
                    event.setCancelled(true);//any custom item used to break a block different from pickaxes will not work
                }
            }else {
                player.sendMessage("You can not break this block!");
                event.setCancelled(true);
            }
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
        return item.getInteger("tier") != 0;
    }
    public void oreRespawnMechanic(Block blockBroken) {

        BlockState originalBlockState = blockBroken.getState();

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> blockBroken.setType(Material.STONE), 1);

        if (blockBroken.getType() == Material.COAL_ORE){
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                originalBlockState.update(true, false);
            }, 20L * OreRespawnCooldown.getOreRespawnTime(1));

        }if (blockBroken.getType() == Material.EMERALD_ORE){
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                originalBlockState.update(true, false);
            }, 20L * OreRespawnCooldown.getOreRespawnTime(2));

        }if (blockBroken.getType() == Material.IRON_ORE){
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                originalBlockState.update(true, false);
            }, 20L * OreRespawnCooldown.getOreRespawnTime(3));

        }if (blockBroken.getType() == Material.DIAMOND_ORE){
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                originalBlockState.update(true, false);
            }, 20L * OreRespawnCooldown.getOreRespawnTime(4));

        }if (blockBroken.getType() == Material.GOLD_ORE){
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                originalBlockState.update(true, false);
            }, 20L * OreRespawnCooldown.getOreRespawnTime(5));
        }
    }

    public boolean pickaxeCanBreakOre(ItemStack itemHeld, Block blockBroken) {

        // Check if the itemHeld has customPickaxe NBT tag
        NBTItem nbtItem = new NBTItem(itemHeld);
        int pickaxeTier = nbtItem.getInteger("tier");

        if (blockBroken.getType() == Material.COAL_ORE) {
            return pickaxeTier >= 1;
        } else if (blockBroken.getType() == Material.EMERALD_ORE){
            return pickaxeTier >= 2;
        } else if (blockBroken.getType() == Material.IRON_ORE){
            return pickaxeTier >= 3;
        } else if (blockBroken.getType() == Material.DIAMOND_ORE){
            return pickaxeTier >= 4;
        } else if (blockBroken.getType() == Material.GOLD_ORE){
            return pickaxeTier >= 5;
        }
        return false;
    }
    public boolean didPickaxeLoseDurability(ItemStack itemHeld, Player player){

        NBTItem nbtItem = new NBTItem(itemHeld);
        int enchantmentDurability = nbtItem.getInteger("enchantmentDurability");
        int durabilityEnchantmentRoll = getRandomValue(100,1);
        if (durabilityEnchantmentRoll < enchantmentDurability){
            player.sendMessage("§aThe Durability enchantment just worked, you did not lose any durability!");
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
            XPGained = pickRollExperienceEnum.getRandomValueByTier(1);

            if (getRandomValue(100,1) <= enchantmentDoubleOre){
                player.sendMessage("§aCongratulations, the double ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*2;
            }

            if (getRandomValue(100,1) <= enchantmentTripleOre){
                player.sendMessage("§aCongratulations, the triple ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*3;
            }

            if (getRandomValue(100,1) <= enchantmentGemFind) {
                int intGem = pickGemFindValueByTierEnum.getRandomValueByTier(1);
                ItemStack gems = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(gems);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, gems);
                }
                player.sendMessage("§aYou have found " + intGem + " gems");
            }

            if (getRandomValue(100,1) <= enchantmentTreasureFind){
                int intGem = 640/tierGemFindAdaptation;
                ItemStack treasure = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(treasure);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, treasure);
                }
                player.sendMessage("§aYou have found " + intGem + " gems");
            }

            ItemStack coalResult = new ItemStack(Material.COAL_ORE, amountOfOreDropped);
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(coalResult);

            } else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, coalResult);
                player.sendMessage(ChatColor.RED + "§cYou don't have enough slots, the item[s] have been dropped on the ground");
            }

            player.sendMessage("§aSuccessfully mined a coal ore");
            player.sendMessage("You got " + amountOfOreDropped + " ores");
            player.sendMessage("+" + XPGained + " XP gained");

        } else if (blockBroken.getType() == Material.EMERALD_ORE){
            tierGemFindAdaptation = 4;
            XPGained = pickRollExperienceEnum.getRandomValueByTier(2);

            if (getRandomValue(100,1) <= enchantmentDoubleOre){
                player.sendMessage("§aCongratulations, the double ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*2;
            }

            if (getRandomValue(100,1) <= enchantmentTripleOre){
                player.sendMessage("§aCongratulations, the triple ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*3;
            }

            if (getRandomValue(100,1) <= enchantmentGemFind) {
                int intGem = pickGemFindValueByTierEnum.getRandomValueByTier(1);
                ItemStack gems = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(gems);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, gems);
                }
                player.sendMessage("§aYou have found " + intGem + " gems");
            }

            if (getRandomValue(100,1) <= enchantmentTreasureFind){
                int intGem = 640/tierGemFindAdaptation;
                ItemStack treasure = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(treasure);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, treasure);
                }
                player.sendMessage("§aYou have found " + intGem + " gems");
            }

            ItemStack emeraldResult = new ItemStack(Material.EMERALD_ORE, amountOfOreDropped);
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(emeraldResult);

            } else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, emeraldResult);
                player.sendMessage(ChatColor.RED + "§cYou don't have enough slots, the item[s] have been dropped on the ground");
            }

            player.sendMessage("§aSuccessfully mined an emerald ore");
            player.sendMessage("You got " + amountOfOreDropped + " ores");
            player.sendMessage("+" + XPGained + " XP gained");

        }else if (blockBroken.getType() == Material.IRON_ORE){
            tierGemFindAdaptation = 3;
            XPGained = pickRollExperienceEnum.getRandomValueByTier(3);

            if (getRandomValue(100,1) <= enchantmentDoubleOre){
                player.sendMessage("§aCongratulations, the double ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*2;
            }

            if (getRandomValue(100,1) <= enchantmentTripleOre){
                player.sendMessage("§aCongratulations, the triple ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*3;
            }

            if (getRandomValue(100,1) <= enchantmentGemFind) {
                int intGem = pickGemFindValueByTierEnum.getRandomValueByTier(1);
                ItemStack gems = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(gems);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, gems);
                }
                player.sendMessage("§aYou have found " + intGem + " gems");
            }

            if (getRandomValue(100,1) <= enchantmentTreasureFind){
                int intGem = 640/tierGemFindAdaptation;
                ItemStack treasure = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(treasure);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, treasure);
                }
                player.sendMessage("§aYou have found " + intGem + " gems");
            }

            ItemStack ironResult = new ItemStack(Material.IRON_ORE, amountOfOreDropped);
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(ironResult);

            } else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, ironResult);
                player.sendMessage(ChatColor.RED + "§cYou don't have enough slots, the item[s] have been dropped on the ground");
            }

            player.sendMessage("§aSuccessfully mined an iron ore");
            player.sendMessage("You got " + amountOfOreDropped + " ores");
            player.sendMessage("+" + XPGained + " XP gained");

        }else if (blockBroken.getType() == Material.DIAMOND_ORE){
            tierGemFindAdaptation = 2;
            XPGained = pickRollExperienceEnum.getRandomValueByTier(4);

            if (getRandomValue(100,1) <= enchantmentDoubleOre){
                player.sendMessage("§aCongratulations, the double ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*2;
            }

            if (getRandomValue(100,1) <= enchantmentTripleOre){
                player.sendMessage("§aCongratulations, the triple ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*3;
            }

            if (getRandomValue(100,1) <= enchantmentGemFind) {
                int intGem = pickGemFindValueByTierEnum.getRandomValueByTier(1);
                ItemStack gems = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(gems);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, gems);
                }
                player.sendMessage("§aYou have found " + intGem + " gems");
            }

            if (getRandomValue(100,1) <= enchantmentTreasureFind){
                int intGem = 640/tierGemFindAdaptation;
                ItemStack treasure = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(treasure);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, treasure);
                }
                player.sendMessage("§aYou have found " + intGem + " gems");
            }

            ItemStack diamondResult = new ItemStack(Material.DIAMOND_ORE, amountOfOreDropped);
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(diamondResult);

            } else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, diamondResult);
                player.sendMessage(ChatColor.RED + "§cYou don't have enough slots, the item[s] have been dropped on the ground");
            }

            player.sendMessage("§aSuccessfully mined a diamond ore");
            player.sendMessage("You got " + amountOfOreDropped + " ores");
            player.sendMessage("+" + XPGained + " XP gained");

        }else if (blockBroken.getType() == Material.GOLD_ORE){
            tierGemFindAdaptation = 1;
            XPGained = pickRollExperienceEnum.getRandomValueByTier(5);

            if (getRandomValue(100,1) <= enchantmentDoubleOre){
                player.sendMessage("§aCongratulations, the double ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*2;
            }

            if (getRandomValue(100,1) <= enchantmentTripleOre){
                player.sendMessage("§aCongratulations, the triple ore enchantment just worked");
                amountOfOreDropped = amountOfOreDropped*3;
            }

            if (getRandomValue(100,1) <= enchantmentGemFind) {
                int intGem = pickGemFindValueByTierEnum.getRandomValueByTier(1);
                ItemStack gems = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(gems);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, gems);
                }
                player.sendMessage("§aYou have found " + intGem + " gems");
            }

            if (getRandomValue(100,1) <= enchantmentTreasureFind){
                int intGem = 640/tierGemFindAdaptation;
                ItemStack treasure = new ItemStack(Material.EMERALD, intGem);
                if (player.getInventory().firstEmpty() >= 0) {
                    player.getInventory().addItem(treasure);
                }else {
                    Location dropLocation = player.getLocation();
                    player.getWorld().dropItemNaturally(dropLocation, treasure);
                }
                player.sendMessage("§aYou have found " + intGem + " gems");
            }

            ItemStack goldResult = new ItemStack(Material.GOLD_ORE, amountOfOreDropped);
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(goldResult);

            } else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, goldResult);
                player.sendMessage(ChatColor.RED + "§cYou don't have enough slots, the item[s] have been dropped on the ground");
            }

            player.sendMessage("§aSuccessfully mined a gold ore");
            player.sendMessage("You got " + amountOfOreDropped + " ores");
            player.sendMessage("+" + XPGained + " XP gained");

        }
        int newCurrentXP = nbtItem.getInteger("currentXP") + XPGained;
        modifyItemLore(player, 1, "§7XP: §6" + newCurrentXP + "/" + XPToLevelUpRequiredMethod(player.getInventory().getItemInMainHand()));

        NBTItem nbti = new NBTItem(player.getInventory().getItemInMainHand());
        nbti.setInteger("currentXP", newCurrentXP);
        player.getInventory().setItemInMainHand(nbti.getItem());
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

        int baseRoll = getRandomValue(100,1);

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
        }
        return block.getType() == Material.GOLD_ORE && miningRollT5 < baseT5BreakOreChance;
    }


    public void didPickLevelUp (Player player){
        ItemStack itemHeld = new ItemStack(player.getInventory().getItemInMainHand());

        NBTItem nbtItem = new NBTItem(itemHeld);
        int currentXP = nbtItem.getInteger("currentXP");
        int currentLevel = nbtItem.getInteger("currentLevel");
        int requiredXP = XPToLevelUpRequiredMethod(itemHeld);

        if (currentXP >= requiredXP){

            int newLevel = currentLevel + 1;
            int newXP = currentXP - requiredXP;

            String newLevelString = "§7Level: §6" + newLevel;
            String newXPString = "§7XP: §6" + newXP + "/" + XPToLevelUpRequiredMethod(player.getInventory().getItemInMainHand());
            modifyItemLore(player, 0, newLevelString);
            modifyItemLore(player, 1, newXPString);

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
    public void didPickChangeTier (Player player){
        ItemStack itemHeld = new ItemStack(player.getInventory().getItemInMainHand());
        NBTItem nbtItem = new NBTItem(itemHeld);
        int currentLevel = nbtItem.getInteger("currentLevel");
        int currentTier = nbtItem.getInteger("tier");

        if (currentLevel == currentTier*20){
            upgradeTier(player);
            updatePickaxeLoreOnEnchantmentAdd(player.getInventory().getItemInMainHand());
        }
    }

}