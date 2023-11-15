package me.brzeph.customitems.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.brzeph.customitems.GUIs.test2.GUIGenerator.openSkillTrainerGUI;

public class NPCCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("skilltrainer")){
            openSkillTrainerGUI(player);
        }
        return true;
    }
}
