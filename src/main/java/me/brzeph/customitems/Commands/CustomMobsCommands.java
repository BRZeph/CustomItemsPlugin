package me.brzeph.customitems.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.brzeph.customitems.CombatMechanics.CustomMobs.GeneratingSpawner.spawnerItemStack;

public class CustomMobsCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("spawner")){
            player.getInventory().addItem(spawnerItemStack());
        }
        return true;
    }

}
