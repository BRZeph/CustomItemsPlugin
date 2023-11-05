package me.brzeph.customitems.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.brzeph.customitems.CustomItemList.CustomArmor.GeneratingArmor.CustomArmorBoots.*;
import static me.brzeph.customitems.CustomItemList.CustomArmor.GeneratingArmor.CustomArmorChestPlate.*;
import static me.brzeph.customitems.CustomItemList.CustomArmor.GeneratingArmor.CustomArmorHelmet.*;
import static me.brzeph.customitems.CustomItemList.CustomArmor.GeneratingArmor.CustomArmorLeggings.*;
import static me.brzeph.customitems.CustomItemList.CustomArmor.UpdatingArmorLore.upgradingArmorLore;

public class CreateArmorCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("t1armor")){
            player.getInventory().addItem(upgradingArmorLore(createT1Helmet()));
            player.getInventory().addItem(upgradingArmorLore(createT1ChestPlate()));
            player.getInventory().addItem(upgradingArmorLore(createT1Leggings()));
            player.getInventory().addItem(upgradingArmorLore(createT1Boots()));
        }
        if (cmd.getName().equalsIgnoreCase("t2armor")){
            player.getInventory().addItem(upgradingArmorLore(createT2Helmet()));
            player.getInventory().addItem(upgradingArmorLore(createT2ChestPlate()));
            player.getInventory().addItem(upgradingArmorLore(createT2Leggings()));
            player.getInventory().addItem(upgradingArmorLore(createT2Boots()));
        }
        if (cmd.getName().equalsIgnoreCase("t3armor")){
            player.getInventory().addItem(upgradingArmorLore(createT3Helmet()));
            player.getInventory().addItem(upgradingArmorLore(createT3ChestPlate()));
            player.getInventory().addItem(upgradingArmorLore(createT3Leggings()));
            player.getInventory().addItem(upgradingArmorLore(createT3Boots()));
        }
        if (cmd.getName().equalsIgnoreCase("t4armor")){
            player.getInventory().addItem(upgradingArmorLore(createT4Helmet()));
            player.getInventory().addItem(upgradingArmorLore(createT4ChestPlate()));
            player.getInventory().addItem(upgradingArmorLore(createT4Leggings()));
            player.getInventory().addItem(upgradingArmorLore(createT4Boots()));
        }
        if (cmd.getName().equalsIgnoreCase("t5armor")){
            player.getInventory().addItem(upgradingArmorLore(createT5Helmet()));
            player.getInventory().addItem(upgradingArmorLore(createT5ChestPlate()));
            player.getInventory().addItem(upgradingArmorLore(createT5Leggings()));
            player.getInventory().addItem(upgradingArmorLore(createT5Boots()));
        }


        if (cmd.getName().equalsIgnoreCase("t1helmet")){
            player.getInventory().addItem(upgradingArmorLore(createT1Helmet()));
        }
        if (cmd.getName().equalsIgnoreCase("t2helmet")){
            player.getInventory().addItem(upgradingArmorLore(createT2Helmet()));
        }
        if (cmd.getName().equalsIgnoreCase("t3helmet")){
            player.getInventory().addItem(upgradingArmorLore(createT3Helmet()));
        }
        if (cmd.getName().equalsIgnoreCase("t4helmet")){
            player.getInventory().addItem(upgradingArmorLore(createT4Helmet()));
        }
        if (cmd.getName().equalsIgnoreCase("t5helmet")){
            player.getInventory().addItem(upgradingArmorLore(createT5Helmet()));
        }


        if (cmd.getName().equalsIgnoreCase("t1chestplate")){
            player.getInventory().addItem(upgradingArmorLore(createT1ChestPlate()));
        }
        if (cmd.getName().equalsIgnoreCase("t2chestplate")){
            player.getInventory().addItem(upgradingArmorLore(createT2ChestPlate()));
        }
        if (cmd.getName().equalsIgnoreCase("t3chestplate")){
            player.getInventory().addItem(upgradingArmorLore(createT3ChestPlate()));
        }
        if (cmd.getName().equalsIgnoreCase("t4chestplate")){
            player.getInventory().addItem(upgradingArmorLore(createT4ChestPlate()));
        }
        if (cmd.getName().equalsIgnoreCase("t5chestplate")){
            player.getInventory().addItem(upgradingArmorLore(createT5ChestPlate()));
        }


        if (cmd.getName().equalsIgnoreCase("t1leggings")){
            player.getInventory().addItem(upgradingArmorLore(createT1Leggings()));
        }
        if (cmd.getName().equalsIgnoreCase("t2leggings")){
            player.getInventory().addItem(upgradingArmorLore(createT2Leggings()));
        }
        if (cmd.getName().equalsIgnoreCase("t3leggings")){
            player.getInventory().addItem(upgradingArmorLore(createT3Leggings()));
        }
        if (cmd.getName().equalsIgnoreCase("t4leggings")){
            player.getInventory().addItem(upgradingArmorLore(createT4Leggings()));
        }
        if (cmd.getName().equalsIgnoreCase("t5leggings")){
            player.getInventory().addItem(upgradingArmorLore(createT5Leggings()));
        }


        if (cmd.getName().equalsIgnoreCase("t1boots")){
            player.getInventory().addItem(upgradingArmorLore(createT1Boots()));
        }
        if (cmd.getName().equalsIgnoreCase("t2boots")){
            player.getInventory().addItem(upgradingArmorLore(createT2Boots()));
        }
        if (cmd.getName().equalsIgnoreCase("t3boots")){
            player.getInventory().addItem(upgradingArmorLore(createT3Boots()));
        }
        if (cmd.getName().equalsIgnoreCase("t4boots")){
            player.getInventory().addItem(upgradingArmorLore(createT4Boots()));
        }
        if (cmd.getName().equalsIgnoreCase("t5boots")){
            player.getInventory().addItem(upgradingArmorLore(createT5Boots()));
        }

        return true;
    }
}
