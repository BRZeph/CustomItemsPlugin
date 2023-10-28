package me.brzeph.customitems.Commands;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTContainer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] strings) {
        if (!(sender instanceof Player)){return true;}
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("nbt")){
            ItemStack heldItem = player.getInventory().getItemInMainHand();

            if (heldItem != null){
                NBTItem nbtItem = new NBTItem(heldItem);

                if (nbtItem.hasNBTData()) {
                    player.sendMessage("The NBT tags on the item are:");

                    for (String tagName : nbtItem.getKeys()) {
                        String tagValue = nbtItem.getString(tagName); // You may need to change this based on the actual type of the tag
                        player.sendMessage(tagName + ": " + tagValue);
                    }
                } else {
                    player.sendMessage("The held item does not have any NBT tags.");
                }
            } else {
                player.sendMessage("You are not holding any items");
            }
        }
        return true;
    }
}
