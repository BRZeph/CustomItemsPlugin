package me.brzeph.customitems.Commands;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import me.brzeph.customitems.CustomItemList.CustomPickaxe;
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

import static me.brzeph.customitems.CustomItemList.CustomArmor.UpdatingArmorLore.upgradingArmorLore;
import static me.brzeph.customitems.Events.MiningEvents.Methods.UpdateLoreEnchantment.updateLoreForNewEnchantment;

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

        //creates /nbt command
        if (cmd.getName().equalsIgnoreCase("nbtTags")) {
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
            }else{
                String[] desiredOrderArmor ={
                        "tier",
                        "bonusHealth",
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
                    "baseHealth",
                    "baseDamage",
                    "baseArmor",
                    "baseDPS"
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

        if (cmd.getName().equalsIgnoreCase("pick")) {
            ItemStack customPickaxeT1 = CustomPickaxe.create_dr_wooden_pickaxe();
            ItemStack customPickaxeT2 = CustomPickaxe.create_dr_stone_pickaxe();
            ItemStack customPickaxeT3 = CustomPickaxe.create_dr_iron_pickaxe();
            ItemStack customPickaxeT4 = CustomPickaxe.create_dr_diamond_pickaxe();
            ItemStack customPickaxeT5 = CustomPickaxe.create_dr_gold_pickaxe();
            player.getInventory().addItem(customPickaxeT1);
            player.getInventory().addItem(customPickaxeT2);
            player.getInventory().addItem(customPickaxeT3);
            player.getInventory().addItem(customPickaxeT4);
            player.getInventory().addItem(customPickaxeT5);
            player.sendMessage(ChatColor.GREEN + "You received pickaxes of all tiers");
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
                        updateLoreForNewEnchantment(player);
                    } else{
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

        //creates /tXPick command

        if (cmd.getName().equalsIgnoreCase("t1pick")) {
            ItemStack customPickaxe = CustomPickaxe.create_dr_wooden_pickaxe();
            player.getInventory().addItem(customPickaxe);
            player.sendMessage(ChatColor.GREEN + "You received a Tier 1 pickaxe!");
        }
        if (cmd.getName().equalsIgnoreCase("t2pick")) {
            ItemStack customPickaxe = CustomPickaxe.create_dr_stone_pickaxe();
            player.getInventory().addItem(customPickaxe);
            player.sendMessage(ChatColor.GREEN + "You received a Tier 2 pickaxe!");
        }
        if (cmd.getName().equalsIgnoreCase("t3pick")) {
            ItemStack customPickaxe = CustomPickaxe.create_dr_iron_pickaxe();
            player.getInventory().addItem(customPickaxe);
            player.sendMessage(ChatColor.GREEN + "You received a Tier 3 pickaxe!");
        }
        if (cmd.getName().equalsIgnoreCase("t4pick")) {
            ItemStack customPickaxe = CustomPickaxe.create_dr_diamond_pickaxe();
            player.getInventory().addItem(customPickaxe);
            player.sendMessage(ChatColor.GREEN + "You received a Tier 4 pickaxe!");
        }
        if (cmd.getName().equalsIgnoreCase("t5pick")) {
            ItemStack customPickaxe = CustomPickaxe.create_dr_gold_pickaxe();
            player.getInventory().addItem(customPickaxe);
            player.sendMessage(ChatColor.GREEN + "You received a Tier 5 pickaxe!");
        }
        return true;
    }
}
