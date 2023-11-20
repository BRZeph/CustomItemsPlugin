package me.brzeph.customitems.CombatMechanics.CustomMobs.GUI;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class SharedData {
    public static HashMap<Player, Block> callingBlock = new HashMap<>();
    public static HashMap<Block, ItemStack> callingItemStack = new HashMap<>();
}
