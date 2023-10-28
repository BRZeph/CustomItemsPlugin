package me.brzeph.customitems.Commands;

import de.tr7zw.nbtapi.NBTCompound;
import me.brzeph.customitems.CustomItemList.CustomOres;
import me.brzeph.customitems.CustomItemList.CustomPickaxe;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import de.tr7zw.nbtapi.NBTItem;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {
    @NotNull
    private static List<String> getStrings() {
        List<String> desiredOrder = new ArrayList<>();
        desiredOrder.add("tier");
        desiredOrder.add("descriptionIfBreakT1");
        desiredOrder.add("descriptionIfBreakT2");
        desiredOrder.add("descriptionIfBreakT3");
        desiredOrder.add("descriptionIfBreakT4");
        desiredOrder.add("descriptionIfBreakT5");
        desiredOrder.add("customOre");
        desiredOrder.add("ItemIdentifications");
        return desiredOrder;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] strings) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        //creates /nbt command

        if (cmd.getName().equalsIgnoreCase("nbt")) {
            ItemStack heldItem = player.getInventory().getItemInMainHand();

            if (heldItem != null) {
                NBTItem nbtItem = new NBTItem(heldItem);

                if (nbtItem.hasNBTData()) {
                    player.sendMessage(ChatColor.GREEN + "The NBT tags on the item are:");
                    List<String> desiredOrder = getStrings();

                    for (String tagName : desiredOrder) {
                        NBTCompound customData = nbtItem.getCompound("customData");
                        if (customData != null && customData.hasKey(tagName)) {
                            String tagValue = customData.getString(tagName);
                            player.sendMessage(ChatColor.GREEN + tagName + ": " + tagValue);
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.GREEN + "The held item does not have any NBT tags.");
                }
            } else {
                player.sendMessage(ChatColor.GREEN + "You are not holding any items");
            }
        }

        //creates /t1pick command

        if (cmd.getName().equalsIgnoreCase("t1pick")){
            ItemStack customPickaxe = CustomPickaxe.create_dr_wooden_pickaxe();
            player.getInventory().addItem(customPickaxe);
            player.sendMessage(ChatColor.GREEN + "You received a Tier 1 pickaxe!");
        }

        //creates /t1ore command

        if (cmd.getName().equalsIgnoreCase("t1ore")) {
            int amount = 1; // Default amount
            if (strings.length > 0) {
                try {
                    amount = Integer.parseInt(strings[0]);
                    amount = Math.min(amount, 64); // Limit to 64 if X > 64
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Invalid input. Using default amount (1).");
                }
            }

            for (int i = 0; i < amount; i++) {
                ItemStack customOre = CustomOres.create_dr_coal_ore();
                player.getInventory().addItem(customOre);
            }

            player.sendMessage(ChatColor.GREEN + "You received " + amount + " Tier 1 ore(s)!");
        }

        //creates /t2ore command

        if (cmd.getName().equalsIgnoreCase("t2ore")) {
            int amount = 1; // Default amount
            if (strings.length > 0) {
                try {
                    amount = Integer.parseInt(strings[0]);
                    amount = Math.min(amount, 64); // Limit to 64 if X > 64
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Invalid input. Using default amount (1).");
                }
            }

            for (int i = 0; i < amount; i++) {
                ItemStack customOre = CustomOres.create_dr_emerald_ore();
                player.getInventory().addItem(customOre);
            }

            player.sendMessage(ChatColor.GREEN + "You received " + amount + " Tier 2 ore(s)!");
        }

        return true;
    }
}
