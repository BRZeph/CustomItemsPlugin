package me.brzeph.customitems;

// TODO: implement new treasure find list on mining events
// TODO: create the method soldado mentioned to create items from nbt tags
//

import me.brzeph.customitems.Commands.Commands;
import me.brzeph.customitems.Commands.CreateArmorCommands;
import me.brzeph.customitems.Commands.CustomMobsCommands;
import me.brzeph.customitems.Commands.NPCCommands;
import me.brzeph.customitems.CustomMobs.GUI.*;
import me.brzeph.customitems.CustomMobs.RightClickSpawnerEvent;
import me.brzeph.customitems.CustomMobs.SpawnerPlaceEvent;
import me.brzeph.customitems.Events.MiningEvents.MiningEvents;
import me.brzeph.customitems.Events.NPCEvents.SkillTrainerEvents;
import me.brzeph.customitems.Events.OnJoinEvents.PlayerRegister;
import me.brzeph.customitems.Events.PlayerStatsRelatedEvents.OnArmorEquip;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.*;

public final class Main extends JavaPlugin implements Listener {
    public World world;
    public DecimalFormat formatter = new DecimalFormat("#.##");
    public Map<Entity, Integer> indicators = new HashMap<>();
    private static Main instance;
    public static Main getInstance() {return instance;}
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("[CustomItems] plugin is now active");

        getServer().getPluginManager().registerEvents(new MiningEvents(), this);
        getServer().getPluginManager().registerEvents(new SkillTrainerEvents(), this);
        getServer().getPluginManager().registerEvents(new PlayerRegister(), this);
        getServer().getPluginManager().registerEvents(new OnArmorEquip(), this);

        getServer().getPluginManager().registerEvents(new SpawnerPlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new RightClickSpawnerEvent(), this);
        getServer().getPluginManager().registerEvents(new SpawnerGUI(), this);
        getServer().getPluginManager().registerEvents(new ChangeTierGUI(), this);
        getServer().getPluginManager().registerEvents(new ChangeMobTypeGUI(), this);
        getServer().getPluginManager().registerEvents(new ChangeRespawnRateGUI(), this);
        getServer().getPluginManager().registerEvents(new ChangeMaxAmountOfMobsGUI(), this);
        getServer().getPluginManager().registerEvents(this, this);
        world = Bukkit.getWorld("world");

        String[] commands = {"nbtTags", "nbtplayer", "nbt", "t1pick", "t2pick", "t3pick", "t4pick", "t5pick", "pick"};
        for (String command : commands) {
            this.getCommand(command).setExecutor(new Commands());
        }

        String[] armorCommands = {"t1helmet", "t2helmet", "t3helmet", "t4helmet", "t5helmet", "t1chestplate", "t2chestplate", "t3chestplate", "t4chestplate"
                , "t5chestplate", "t1leggings", "t2leggings", "t3leggings", "t4leggings", "t5leggings", "t1boots", "t2boots", "t3boots", "t4boots", "t5boots"
                , "t1armor", "t2armor", "t3armor", "t4armor", "t5armor"};
        for (String command: armorCommands){
            this.getCommand(command).setExecutor(new CreateArmorCommands());
        }

        this.getCommand("skilltrainer").setExecutor(new NPCCommands());
        this.getCommand("spawner").setExecutor(new CustomMobsCommands());

        instance = this;
        new BukkitRunnable() {
            Set<Entity> stands = indicators.keySet();
            List<Entity> removal = new ArrayList<>();
            @Override
            public void run() {
                for (Entity stand : stands) {
                    int ticksLeft = indicators.get(stand);
                    if (ticksLeft == 0) {
                        stand.remove();
                        removal.add(stand);
                        continue;
                    }
                    ticksLeft--;
                    indicators.put(stand, ticksLeft);
                }
                stands.removeAll(removal);
            }
        }.runTaskTimer(this, 0L, 1L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
