package me.brzeph.customitems;

// TODO: create new class to create the functionality of the pickaxe
// TODO: tip: make it so that whenever the player event of ''breaking block'' happens, the code check to see if he used the custom t1 pick, check if the ore was a custom ore and check if the tier of pick/ore makes sense
// TODO: create pickaxe's level system
// TODO: create pickaxe's enchantment system

import me.brzeph.customitems.Commands.Commands;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("[CustomItems] plugin is now active");
        String[] commands = {"nbt", "t1pick", "t2pick", "t3pick", "t4pick", "t5pick", "t1ore", "t2ore", "t3ore", "t4ore", "t5ore"};
        for (String command : commands) {
            this.getCommand(command).setExecutor(new Commands());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
