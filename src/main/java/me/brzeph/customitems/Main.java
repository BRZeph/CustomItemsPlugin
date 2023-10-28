package me.brzeph.customitems;

import me.brzeph.customitems.Commands.Commands;
import me.brzeph.customitems.GivingPlayerCustomItem.OnJoin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getConsoleSender().sendMessage("[CustomItems] plugin is now active");
        getServer().getPluginManager().registerEvents(new OnJoin(), this);
        this.getCommand("nbt").setExecutor(new Commands());
        this.getCommand("t1pick").setExecutor(new Commands());
        this.getCommand("t1ore").setExecutor(new Commands());
        this.getCommand("t2ore").setExecutor(new Commands());


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
