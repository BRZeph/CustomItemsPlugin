package me.brzeph.customitems.CustomMobs;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class LootDrops {
    private ItemStack item;
    private int min = 1, max = 1;
    private double dropRate;
    private static Random randomizer = new Random();

    public LootDrops(ItemStack item, double dropRate) { //use this constructor to when you want to drop only 1 item
        this.item = item;
        this.dropRate = dropRate;
    }

    public LootDrops(ItemStack item, int min, int max, double dropRate) { //use this for when you want to drop many items
        this.item = item;
        this.min = min;
        this.max = max;
        this.dropRate = dropRate;
    }
    public void tryDropItem(Location location){
        if (Math.random()*101 > dropRate) return; //this means that the rng did not smile upon us
        int amount = randomizer.nextInt(max - min + 1) + min;
        if (amount == 0) return;
        ItemStack item = this.item.clone(); //cloning the item to set the amount
        item.setAmount(amount);
        location.getWorld().dropItemNaturally(location, item);
    }
}