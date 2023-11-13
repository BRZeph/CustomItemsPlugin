package me.brzeph.customitems.CustomItemList.CustomPickaxe.SkillTrainerGUI;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.brzeph.customitems.CustomItemList.CustomPickaxe.CustomPickaxe.*;

public class VendingCustomTieredPickaxes {
    public static void vendingT1PickaxeOnSkillTrainer(Player player){
        if (player.getInventory().contains(Material.EMERALD, pickaxeVendingCost.PickaxeVendingCost(1))){
            ItemStack payment = new ItemStack(Material.EMERALD, pickaxeVendingCost.PickaxeVendingCost(1));
            player.getInventory().removeItem(payment).isEmpty();
            if (player.getInventory().firstEmpty() >= 0) {
                player.getInventory().addItem(create_dr_wooden_pickaxe());
                player.sendMessage("§aHere is a T1 Pickaxe!");
            } else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, create_dr_wooden_pickaxe());
                player.sendMessage("§cYou don't have enough slots, the item[s] have been dropped on the ground");
            }
        }else{
            player.sendMessage("§cYou do not have enough gems!");
            player.sendMessage("§cGem cost: " + pickaxeVendingCost.PickaxeVendingCost(1) + "!");
        }
    }
    public static void vendingT2PickaxeOnSkillTrainer(Player player){
        if (player.getInventory().contains(Material.EMERALD, pickaxeVendingCost.PickaxeVendingCost(2))){
            ItemStack payment = new ItemStack(Material.EMERALD, pickaxeVendingCost.PickaxeVendingCost(2));
            player.getInventory().removeItem(payment).isEmpty();
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(create_dr_stone_pickaxe());
                player.sendMessage("§aHere is a T2 Pickaxe!");
            }else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, create_dr_stone_pickaxe());
                player.sendMessage("§cYou don't have enough slots, the item[s] have been dropped on the ground");
            }
        }else{
            player.sendMessage("§cYou do not have enough gems!");
            player.sendMessage("§cGem cost: " + pickaxeVendingCost.PickaxeVendingCost(2) + "!");
        }
    }
    public static void vendingT3PickaxeOnSkillTrainer(Player player){
        if (player.getInventory().contains(Material.EMERALD, pickaxeVendingCost.PickaxeVendingCost(3))){
            ItemStack payment = new ItemStack(Material.EMERALD, pickaxeVendingCost.PickaxeVendingCost(3));
            player.getInventory().removeItem(payment).isEmpty();
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(create_dr_iron_pickaxe());
                player.sendMessage("§aHere is a T3 Pickaxe!");
            }else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, create_dr_iron_pickaxe());
                player.sendMessage("§cYou don't have enough slots, the item[s] have been dropped on the ground");
            }
        }else{
            player.sendMessage("§cYou do not have enough gems!");
            player.sendMessage("§cGem cost: " + pickaxeVendingCost.PickaxeVendingCost(3) + "!");
        }
    }
    public static void vendingT4PickaxeOnSkillTrainer(Player player){
        if (player.getInventory().contains(Material.EMERALD, pickaxeVendingCost.PickaxeVendingCost(4))){
            ItemStack payment = new ItemStack(Material.EMERALD, pickaxeVendingCost.PickaxeVendingCost(4));
            player.getInventory().removeItem(payment).isEmpty();
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(create_dr_diamond_pickaxe());
                player.sendMessage("§aHere is a T4 Pickaxe!");
            }else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, create_dr_diamond_pickaxe());
                player.sendMessage("§cYou don't have enough slots, the item[s] have been dropped on the ground");
            }
        }else{
            player.sendMessage("§cYou do not have enough gems!");
            player.sendMessage("§cGem cost: " + pickaxeVendingCost.PickaxeVendingCost(4) + "!");
        }
    }
    public static void vendingT5PickaxeOnSkillTrainer(Player player){
        if (player.getInventory().contains(Material.EMERALD, pickaxeVendingCost.PickaxeVendingCost(5))){
            ItemStack payment = new ItemStack(Material.EMERALD, pickaxeVendingCost.PickaxeVendingCost(5));
            player.getInventory().removeItem(payment).isEmpty();
            if (player.getInventory().firstEmpty() >= 0){
                player.getInventory().addItem(create_dr_gold_pickaxe());
                player.sendMessage("§aHere is a T5 Pickaxe!");
            }else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, create_dr_gold_pickaxe());
                player.sendMessage("§cYou don't have enough slots, the item[s] have been dropped on the ground");
            }
        }else{
            player.sendMessage("§cYou do not have enough gems!");
            player.sendMessage("§cGem cost: " + pickaxeVendingCost.PickaxeVendingCost(5) + "!");
        }
    }
}
