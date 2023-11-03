package me.brzeph.customitems.CustomGUIs;

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
        inventory.setItem(1, create_dr_stone_pickaxe());
        inventory.setItem(2, create_dr_iron_pickaxe());
        inventory.setItem(3, create_dr_diamond_pickaxe());
        inventory.setItem(4, create_dr_gold_pickaxe());
        player.openInventory(inventory);
    }
    public static ItemStack sellingT1Pickaxe(){
        ItemStack itemStack = create_dr_wooden_pickaxe();
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();
        lore.add("§aThis item costs: §c100G");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
