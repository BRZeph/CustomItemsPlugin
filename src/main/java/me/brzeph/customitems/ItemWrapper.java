package me.brzeph.customitems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemWrapper extends ItemStack{
    public static ItemStack itemWrapperGUIItems(Material material, int amount ,String name, String... lore) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        List<String> loreList = new ArrayList<>(List.of(lore));
        itemMeta.setLore(loreList);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
