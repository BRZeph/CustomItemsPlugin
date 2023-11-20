package me.brzeph.customitems.Commands;

import me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems.GeneratingWeaponOrb;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static me.brzeph.customitems.GUIs.GUIGenerator.openSkillTrainerGUI;
import static me.brzeph.customitems.GUIs.GUIGenerator.openSpawnerGUI;

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
        if (cmd.getName().equalsIgnoreCase("spawnergui")){
            openSpawnerGUI(player);
        }
        if (cmd.getName().equalsIgnoreCase("orb")){
            player.setItemInHand(GeneratingWeaponOrb.orbWeapon(player.getItemInHand()));
        }
        return true;
    }
}
