package me.brzeph.customitems;

// TODO: implement new treasure find list on mining events
// TODO: create the method soldado mentioned to create items from nbt tags


import me.brzeph.customitems.Commands.Commands;
import me.brzeph.customitems.Commands.CreateArmorCommands;
import me.brzeph.customitems.Commands.NPCCommands;
import me.brzeph.customitems.Events.MiningEvents.MiningEvents;
import me.brzeph.customitems.Events.NPCEvents.SkillTrainerEvents;
import me.brzeph.customitems.Events.OnJoinEvents.PlayerRegister;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    public static Main getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("[CustomItems] plugin is now active");

        getServer().getPluginManager().registerEvents(new MiningEvents(), this);
        getServer().getPluginManager().registerEvents(new SkillTrainerEvents(), this);
        getServer().getPluginManager().registerEvents(new PlayerRegister(), this);

        String[] commands = {"nbtTags", "nbtplayer", "nbt", "t1pick", "t2pick", "t3pick", "t4pick", "t5pick", "pick"};
        for (String command : commands) {
            this.getCommand(command).setExecutor(new Commands());
        }
        String[] armorCommands = {"t1helmet"};
        for (String command: armorCommands){
            this.getCommand(command).setExecutor(new CreateArmorCommands());
        }

        this.getCommand("skilltrainer").setExecutor(new NPCCommands());
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
