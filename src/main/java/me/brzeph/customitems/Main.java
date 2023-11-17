package me.brzeph.customitems;


// TODO: consider creating a method that automatically updates the player vanilla HP based on the NBTTags of currentHealth and maxHP
// TODO: create method that sets the player level to the player hp

import me.brzeph.customitems.CombatMechanics.CombatSystem.CombatEvents;
import me.brzeph.customitems.CombatMechanics.CombatSystem.PlayerCombatTime;
import me.brzeph.customitems.CombatMechanics.CombatSystem.PlayerHealthRegeneration;
import me.brzeph.customitems.CombatMechanics.ReWritingVanillaDamageCode;
import me.brzeph.customitems.Commands.*;
import me.brzeph.customitems.CombatMechanics.CustomMobs.*;
import me.brzeph.customitems.CombatMechanics.CustomMobs.GUI.*;
import me.brzeph.customitems.GUIs.GUIEventsHandler;
import me.brzeph.customitems.MiningMechanics.MiningEvents.MiningEvents;
import me.brzeph.customitems.Events.OnJoinEvents.PlayerRegister;
import me.brzeph.customitems.CombatMechanics.OnArmorEquip;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.*;

public final class Main extends JavaPlugin{
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
        getServer().getPluginManager().registerEvents(new PlayerRegister(), this);
        getServer().getPluginManager().registerEvents(new OnArmorEquip(), this);

        getServer().getPluginManager().registerEvents(new SpawnerPlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new RightClickSpawnerEvent(), this);
        getServer().getPluginManager().registerEvents(new SpawnerGUI(), this);
        getServer().getPluginManager().registerEvents(new ChangeTierGUI(), this);
        getServer().getPluginManager().registerEvents(new ChangeMobTypeGUI(), this);
        getServer().getPluginManager().registerEvents(new ChangeRespawnRateGUI(), this);
        getServer().getPluginManager().registerEvents(new ChangeMaxAmountOfMobsGUI(), this);
        getServer().getPluginManager().registerEvents(new SpawnerFunctionality(), this);
        getServer().getPluginManager().registerEvents(new ChangeSpawnRadiusGUI(), this);
        getServer().getPluginManager().registerEvents(new ChangeMobTypeGUI(), this);
        getServer().getPluginManager().registerEvents(new RegisterMobToSpawnGUI(), this);

        getServer().getPluginManager().registerEvents(new CombatEvents(), this);
        getServer().getPluginManager().registerEvents(new PlayerCombatTime(), this);
        getServer().getPluginManager().registerEvents(new PlayerHealthRegeneration(), this);
        getServer().getPluginManager().registerEvents(new ReWritingVanillaDamageCode(), this);
        getServer().getPluginManager().registerEvents(new GUIEventsHandler(this), this);


        world = Bukkit.getWorld("world");

        String[] commands = {"nbt", "nbtTags", "nbtplayer", "nbtArmor", "nbtSpawner", "nbtPick", "nbtWeapon","t1pick", "t2pick", "t3pick", "t4pick", "t5pick", "pick"};
        for (String command : commands) {
            this.getCommand(command).setExecutor(new Commands());
        }

        String[] combatItemsCommand = {"heal", "t1helmet", "t2helmet", "t3helmet", "t4helmet", "t5helmet", "t1chestplate", "t2chestplate", "t3chestplate", "t4chestplate"
                , "t5chestplate", "t1leggings", "t2leggings", "t3leggings", "t4leggings", "t5leggings", "t1boots", "t2boots", "t3boots", "t4boots", "t5boots"
                , "t1armor", "t2armor", "t3armor", "t4armor", "t5armor", "t1axe", "t2axe", "t3axe", "t4axe", "t5axe", "t1sword", "t2sword", "t3sword", "t4sword"
                , "t5sword", "t1shovel", "t2shovel", "t3shovel", "t4shovel", "t5shovel", "t1hoe", "t2hoe", "t3hoe", "t4hoe", "t5hoe"};
        for (String command: combatItemsCommand){
            this.getCommand(command).setExecutor(new CreateCombatItemsCommands());
        }
        this.getCommand("spawner").setExecutor(new CustomMobsCommands());
        this.getCommand("skilltrainer").setExecutor(new NPCCommands());
        this.getCommand("spawnergui").setExecutor(new NPCCommands());

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