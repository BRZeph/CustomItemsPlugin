package me.brzeph.customitems.CombatMechanics.CustomMobs;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GeneratingSpawner {
    public static ItemStack spawnerItemStack(){
        ItemStack spawner = new ItemStack(Material.SPAWNER, 64);
        ItemMeta itemMeta = spawner.getItemMeta();
        itemMeta.setDisplayName("Custom Spawner");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§4This is a custom spawner");
        itemMeta.setLore(lore);
        spawner.setItemMeta(itemMeta);

        NBTItem nbtItem = new NBTItem(spawner);
        int tier = 1;
        int respawnRate = 10; //units in seconds, please
        int mobCap = 5;
        int mobType = 1; //mobType{1,2,...} == {zombie,skeleton,...}
        int size = 3;

        nbtItem.setInteger("customBlock", 1);
        nbtItem.setInteger("tier", tier);
        nbtItem.setInteger("mobType", mobType);
        nbtItem.setInteger("respawnRate", respawnRate);
        nbtItem.setInteger("maxAmountOfMobs", mobCap);
        nbtItem.setInteger("size", size);
        return nbtItem.getItem();
    }
}
