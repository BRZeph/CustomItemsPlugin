package me.brzeph.customitems.CustomGUIs;

import me.brzeph.customitems.Events.NPCEvents.VendingMethods.pickaxeVendingCost;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static me.brzeph.customitems.CustomItemList.CustomPickaxe.*;

public class SkillTrainerGUIs {
    public static void skillTrainerOpenGUI(Player player){
        Inventory inventory = Bukkit.createInventory(player, 9, "§0SkillTrainer");

        inventory.setItem(0, sellingT1Pickaxe());
        inventory.setItem(1, sellingT2Pickaxe());
        inventory.setItem(2, sellingT3Pickaxe());
        inventory.setItem(3, sellingT4Pickaxe());
        inventory.setItem(4, sellingT5Pickaxe());
        player.openInventory(inventory);
    }
    public static ItemStack sellingT1Pickaxe(){
        ItemStack itemStack = create_dr_wooden_pickaxe();
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();
        lore.add("");
        lore.add("§aThis item costs: §c" + pickaxeVendingCost.PickaxeVendingCost(1) + "G");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack sellingT2Pickaxe(){
        ItemStack itemStack = create_dr_stone_pickaxe();

        int i;
        int startLine = 7;
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();

        for (i = lore.size() - 1; i >= startLine; i--){
            lore.remove(i);
        }

        lore.add("§cMining Success: 5");
        lore.add("");
        lore.add("§6[+1 random enchantment]");
        lore.add("");
        lore.add("§aThis item costs: §c" + pickaxeVendingCost.PickaxeVendingCost(2) + "G");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack sellingT3Pickaxe(){
        ItemStack itemStack = create_dr_iron_pickaxe();

        int i;
        int startLine = 7;
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();

        for (i = lore.size() - 1; i >= startLine; i--){
            lore.remove(i);
        }

        lore.add("§cMining Success: 5");
        lore.add("");
        lore.add("§6[+1 random enchantment]");
        lore.add("§6[+1 random enchantment]");
        lore.add("");
        lore.add("§aThis item costs: §c" + pickaxeVendingCost.PickaxeVendingCost(3) + "G");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack sellingT4Pickaxe(){
        ItemStack itemStack = create_dr_diamond_pickaxe();

        int i;
        int startLine = 7;
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();

        for (i = lore.size() - 1; i >= startLine; i--){
            lore.remove(i);
        }

        lore.add("§cMining Success: 5");
        lore.add("");
        lore.add("§6[+1 random enchantment]");
        lore.add("§6[+1 random enchantment]");
        lore.add("§6[+1 random enchantment]");
        lore.add("");
        lore.add("§aThis item costs: §c" + pickaxeVendingCost.PickaxeVendingCost(4) + "G");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack sellingT5Pickaxe(){
        ItemStack itemStack = create_dr_gold_pickaxe();

        int i;
        int startLine = 7;
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();

        for (i = lore.size() - 1; i >= startLine; i--){
            lore.remove(i);
        }

        lore.add("§cMining Success: 5");
        lore.add("");
        lore.add("§6[+1 random enchantment]");
        lore.add("§6[+1 random enchantment]");
        lore.add("§6[+1 random enchantment]");
        lore.add("§6[+1 random enchantment]");
        lore.add("");
        lore.add("§aThis item costs: §c" + pickaxeVendingCost.PickaxeVendingCost(5) + "G");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
