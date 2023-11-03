package me.brzeph.customitems;

// TODO: make sure that whenever the player breaks a block that is auto generated, it does not break by failing the ''onBlockBreakEvent''
// TODO: implement new treasure find list on mining events
// TODO: create the method soldado mentioned to create items from nbt tags

// TODO: change pickaxe lore to be more like DR.
// TODO: line 1: "Level: [currentLevel]"
// TODO: line 2: "[currentXP]/[XPRequiredToLevelUp]"
// TODO: line 3: "[bar that is proportional to the progress of XP]"


import me.brzeph.customitems.Commands.Commands;
import me.brzeph.customitems.Commands.NPCCommands;
import me.brzeph.customitems.Events.MiningEvents.MiningEvents;
import me.brzeph.customitems.Events.NPCEvents.SkillTrainerEvents;
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

        String[] commands = {"nbtTags", "nbt", "t1pick", "t2pick", "t3pick", "t4pick", "t5pick", "pick"};
        for (String command : commands) {
            this.getCommand(command).setExecutor(new Commands());
        }
        this.getCommand("skilltrainer").setExecutor(new NPCCommands());
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
