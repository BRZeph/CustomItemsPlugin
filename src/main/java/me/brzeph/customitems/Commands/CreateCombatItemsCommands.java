package me.brzeph.customitems.Commands;

import me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems.CreateTXArmor;
import me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems.CreateTXWeapon;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.UpdatingArmorLore.upgradingArmorLore;

public class CreateCombatItemsCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("heal")){
            player.setHealth(20);
        }
        if (cmd.getName().equalsIgnoreCase("t1armor")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXHelmet(1, 10)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXChestPlate(1, 10)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXLeggings(1, 10)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXBoots(1, 10)));
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponRandomType(1));
        }
        if (cmd.getName().equalsIgnoreCase("t2armor")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXHelmet(2, 30)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXChestPlate(2, 30)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXLeggings(2, 30)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXBoots(2, 30)));
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponRandomType(2));
        }
        if (cmd.getName().equalsIgnoreCase("t3armor")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXHelmet(3, 50)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXChestPlate(3, 50)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXLeggings(3, 50)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXBoots(3, 50)));
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponRandomType(3));
        }
        if (cmd.getName().equalsIgnoreCase("t4armor")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXHelmet(4, 70)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXChestPlate(4, 70)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXLeggings(4, 70)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXBoots(4, 70)));
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponRandomType(4));
        }
        if (cmd.getName().equalsIgnoreCase("t5armor")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXHelmet(5, 90)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXChestPlate(5, 90)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXLeggings(5, 90)));
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXBoots(5, 90)));
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponRandomType(5));
        }


        if (cmd.getName().equalsIgnoreCase("t1helmet")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXHelmet(1, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t2helmet")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXHelmet(2, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t3helmet")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXHelmet(3, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t4helmet")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXHelmet(4, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t5helmet")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXHelmet(5, 10)));
        }


        if (cmd.getName().equalsIgnoreCase("t1chestplate")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXChestPlate(1, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t2chestplate")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXChestPlate(2, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t3chestplate")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXChestPlate(3, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t4chestplate")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXChestPlate(4, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t5chestplate")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXChestPlate(5, 10)));
        }


        if (cmd.getName().equalsIgnoreCase("t1leggings")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXLeggings(1, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t2leggings")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXLeggings(2, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t3leggings")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXLeggings(3, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t4leggings")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXLeggings(4, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t5leggings")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXLeggings(5, 10)));
        }


        if (cmd.getName().equalsIgnoreCase("t1boots")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXBoots(1, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t2boots")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXBoots(2, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t3boots")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXBoots(3, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t4boots")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXBoots(4, 10)));
        }
        if (cmd.getName().equalsIgnoreCase("t5boots")){
            player.getInventory().addItem(upgradingArmorLore(CreateTXArmor.createTXBoots(5, 10)));
        }


        if (cmd.getName().equalsIgnoreCase("t1axe")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponAxe(1));
        }
        if (cmd.getName().equalsIgnoreCase("t2axe")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponAxe(2));
        }
        if (cmd.getName().equalsIgnoreCase("t3axe")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponAxe(3));
        }
        if (cmd.getName().equalsIgnoreCase("t4axe")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponAxe(4));
        }
        if (cmd.getName().equalsIgnoreCase("t5axe")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponAxe(5));
        }
        if (cmd.getName().equalsIgnoreCase("t1sword")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponSword(1));
        }
        if (cmd.getName().equalsIgnoreCase("t2sword")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponSword(2));
        }
        if (cmd.getName().equalsIgnoreCase("t3sword")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponSword(3));
        }
        if (cmd.getName().equalsIgnoreCase("t4sword")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponSword(4));
        }
        if (cmd.getName().equalsIgnoreCase("t5sword")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponSword(5));
        }
        if (cmd.getName().equalsIgnoreCase("t1shovel")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponShovel(1));
        }
        if (cmd.getName().equalsIgnoreCase("t2shovel")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponShovel(2));
        }
        if (cmd.getName().equalsIgnoreCase("t3shovel")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponShovel(3));
        }
        if (cmd.getName().equalsIgnoreCase("t4shovel")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponShovel(4));
        }
        if (cmd.getName().equalsIgnoreCase("t5shovel")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponShovel(5));
        }
        if (cmd.getName().equalsIgnoreCase("t1hoe")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponHoe(1));
        }
        if (cmd.getName().equalsIgnoreCase("t2hoe")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponHoe(2));
        }
        if (cmd.getName().equalsIgnoreCase("t3hoe")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponHoe(3));
        }
        if (cmd.getName().equalsIgnoreCase("t4hoe")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponHoe(4));
        }
        if (cmd.getName().equalsIgnoreCase("t5hoe")){
            player.getInventory().addItem(CreateTXWeapon.createTXWeaponHoe(5));
        }
        return true;
    }
}
