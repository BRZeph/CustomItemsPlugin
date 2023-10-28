package me.brzeph.customitems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MessingWithNBT {
    public static ItemStack create_dr_wooden_pickaxe() {
        ItemStack item = new ItemStack(Material.WOODEN_PICKAXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Tier 1 pickaxe");
        List<String> lore = new ArrayList<>();
        lore.add("This is a tier 1 pickaxe");
        lore.add("This only breaks custom coal ore");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

}
