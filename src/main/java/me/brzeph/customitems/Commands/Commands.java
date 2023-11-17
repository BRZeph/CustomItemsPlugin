package me.brzeph.customitems.Commands;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import de.tr7zw.nbtapi.NBTItem;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.UpdatingArmorLore.upgradingArmorLore;
import static me.brzeph.customitems.MiningMechanics.PickaxeCreator.*;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] strings) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        Set<Material> validMaterialsPickaxe = new HashSet<>();
        validMaterialsPickaxe.add(Material.WOODEN_PICKAXE);
        validMaterialsPickaxe.add(Material.STONE_PICKAXE);
        validMaterialsPickaxe.add(Material.IRON_PICKAXE);
        validMaterialsPickaxe.add(Material.DIAMOND_PICKAXE);
        validMaterialsPickaxe.add(Material.GOLDEN_PICKAXE);

        if (cmd.getName().equalsIgnoreCase("nbtSpawner")) {
            NBTItem heldItem = new NBTItem(player.getInventory().getItemInMainHand());
            if (player.getInventory().getItemInMainHand().getType() == Material.SPAWNER) {
                String[] desiredOrderPickaxe = {
                        "customBlock",
                        "tier",
                        "mobType",
                        "respawnRate",
                        "maxAmountOfMobs",
                        "size"
                };
                StringBuilder nbtTags = new StringBuilder();
                for (String key : desiredOrderPickaxe) {
                    if (heldItem.hasKey(key)) {
                        String tagName = heldItem.getString(key);
                        int tagValue = heldItem.getInteger(key);
                        nbtTags.append(key).append(": ").append(tagName).append(tagValue).append("\n");
                    }
                }
                player.sendMessage("§aThe nbt tags on your pickaxe are: " + "\n" + nbtTags);
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("nbtPick")){
            NBTItem heldItem = new NBTItem(player.getInventory().getItemInMainHand());
            if (validMaterialsPickaxe.contains(heldItem.getItem().getType())) {
                String[] desiredOrderPickaxe = {
                        "uniqueItemID",
                        "tier",
                        "currentXP",
                        "currentLevel",
                        "enchantmentDoubleOre",
                        "enchantmentTripleOre",
                        "enchantmentMiningSuccess",
                        "enchantmentGemFind",
                        "enchantmentTreasureFind",
                        "enchantmentDurability"
                };
                StringBuilder nbtTags = new StringBuilder();
                for (String key : desiredOrderPickaxe) {
                    if (heldItem.hasKey(key)) {
                        String tagName = heldItem.getString(key);
                        int tagValue = heldItem.getInteger(key);
                        nbtTags.append(key).append(": ").append(tagName).append(tagValue).append("\n");
                    }
                }
                player.sendMessage("§aThe nbt tags on your pickaxe are: " + "\n" + nbtTags);
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("nbtWeapon")){
            NBTItem heldItem = new NBTItem(player.getInventory().getItemInMainHand());
            if (heldItem.getInteger("armorType") != null) {
                String[] desiredOrderArmor ={
                        "rarity",
                        "weaponType",
                        "tier",
                        "minDamage",
                        "maxDamage"
                };
                StringBuilder nbtTags = new StringBuilder();
                for (String key : desiredOrderArmor){
                    if (heldItem.hasKey(key)){
                        String tagName = heldItem.getString(key);
                        int tagValue = heldItem.getInteger(key);
                        nbtTags.append(key).append("§a: ").append(tagName).append(tagValue).append("§a\n");
                    }
                }
                player.sendMessage("§aThe nbt tags on your armor are: " + "\n" + nbtTags);
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("nbtArmor")){
            NBTItem heldItem = new NBTItem(player.getInventory().getItemInMainHand());
            if (heldItem.getInteger("armorType") != null) {
                String[] desiredOrderArmor ={
                        "tier",
                        "bonusHealth",
                        "armorHPS",
                        "armorEnergy",
                        "armorStatsVitality",
                        "armorStatsDexterity",
                        "armorStatsStrength",
                        "armorStatsAgility",
                        "armorStatsDodge",
                        "armorStatsBlock",
                        "armorStatsReflect"
                };
                StringBuilder nbtTags = new StringBuilder();
                for (String key : desiredOrderArmor){
                    if (heldItem.hasKey(key)){
                        String tagName = heldItem.getString(key);
                        int tagValue = heldItem.getInteger(key);
                        nbtTags.append(key).append(": ").append(tagName).append(tagValue).append("\n");
                    }
                }
                player.sendMessage("§aThe nbt tags on your armor are: " + "\n" + nbtTags);
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("nbtplayer")) {
            NBTEntity nbtEntity = new NBTEntity(player);
            NBTCompound playerData = nbtEntity.getPersistentDataContainer();
            String[] desiredOrder = {
                    "registeredPlayer",
                    "baseHealth",
                    "bonusHealth",
                    "currentMaxHealth",
                    "currentHP",
                    "baseDamage",
                    "baseHPS",
                    "baseArmor",
                    "baseDPS",
                    "onCombat",
                    "baseCombatTimer"
            };
            StringBuilder nbtTags = new StringBuilder();
            for (String key : desiredOrder) {
                if (playerData.hasKey(key)) {
                    int tagValue = playerData.getInteger(key);
                    nbtTags.append(key).append(": ").append(tagValue).append("\n"); // Add spaces here
                }
            }
            player.sendMessage("§aThe NBT tags are: \n" + nbtTags);
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("nbt")) {
            ItemStack heldItem = new ItemStack(player.getInventory().getItemInMainHand());

            if (heldItem != null) {
                if (strings.length == 2) {
                    String nbtKey = strings[0].replace("modifiers.", ""); // Remove the "modifiers." part
                    int nbtValue = Integer.parseInt(strings[1]);

                    NBTItem nbti = new NBTItem(heldItem);
                    nbti.setInteger(nbtKey, nbtValue);
                    player.getInventory().setItemInMainHand(nbti.getItem());

                    if (validMaterialsPickaxe.contains(player.getInventory().getItemInMainHand().getType())) {
                    } else if (player.getInventory().getItemInMainHand().getType() == Material.SPAWNER) {
                    } else {
                        player.getInventory().setItemInMainHand(upgradingArmorLore(player.getInventory().getItemInMainHand()));
                    }
                    player.sendMessage("Successfully changed the NBT tag " + nbtKey + "'s value to " + nbtValue);
                } else {
                    player.sendMessage("That is an invalid input, ask upper staff how to use this command");
                }
            } else {
                player.sendMessage("You are not holding any items");
            }
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("pick")) {
            player.getInventory().addItem(createPreGeneratedPickaxes(createT1Pickaxe));
            player.getInventory().addItem(createPreGeneratedPickaxes(createT2Pickaxe));
            player.getInventory().addItem(createPreGeneratedPickaxes(createT3Pickaxe));
            player.getInventory().addItem(createPreGeneratedPickaxes(createT4Pickaxe));
            player.getInventory().addItem(createPreGeneratedPickaxes(createT5Pickaxe));
            player.sendMessage(ChatColor.GREEN + "You received pickaxes of all tiers");
        }
        if (cmd.getName().equalsIgnoreCase("t1pick")) {
            player.getInventory().addItem(createPreGeneratedPickaxes(createT1Pickaxe));
            player.sendMessage(ChatColor.GREEN + "You received a Tier 1 pickaxe!");
        }
        if (cmd.getName().equalsIgnoreCase("t2pick")) {
            player.getInventory().addItem(createPreGeneratedPickaxes(createT2Pickaxe));
            player.sendMessage(ChatColor.GREEN + "You received a Tier 2 pickaxe!");
        }
        if (cmd.getName().equalsIgnoreCase("t3pick")) {
            player.getInventory().addItem(createPreGeneratedPickaxes(createT3Pickaxe));
            player.sendMessage(ChatColor.GREEN + "You received a Tier 3 pickaxe!");
        }
        if (cmd.getName().equalsIgnoreCase("t4pick")) {
            player.getInventory().addItem(createPreGeneratedPickaxes(createT4Pickaxe));
            player.sendMessage(ChatColor.GREEN + "You received a Tier 4 pickaxe!");
        }
        if (cmd.getName().equalsIgnoreCase("t5pick")) {
            player.getInventory().addItem(createPreGeneratedPickaxes(createT5Pickaxe));
            player.sendMessage(ChatColor.GREEN + "You received a Tier 5 pickaxe!");
        }
        return true;
    }
}
