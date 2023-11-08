package me.brzeph.customitems;

// TODO: implement new treasure find list on mining events
// TODO: create the method soldado mentioned to create items from nbt tags

import me.brzeph.customitems.Commands.Commands;
import me.brzeph.customitems.Commands.CreateArmorCommands;
import me.brzeph.customitems.Commands.CustomMobsCommands;
import me.brzeph.customitems.Commands.NPCCommands;
import me.brzeph.customitems.CustomMobs.CustomMobsListEnum;
import me.brzeph.customitems.CustomMobs.GUI.*;
import me.brzeph.customitems.CustomMobs.RightClickSpawnerEvent;
import me.brzeph.customitems.CustomMobs.SpawnerPlaceEvent;
import me.brzeph.customitems.Events.MiningEvents.MiningEvents;
import me.brzeph.customitems.Events.NPCEvents.SkillTrainerEvents;
import me.brzeph.customitems.Events.OnJoinEvents.PlayerRegister;
import me.brzeph.customitems.Events.PlayerStatsRelatedEvents.OnArmorEquip;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.text.DecimalFormat;
import java.util.*;

import static me.brzeph.customitems.CustomItemList.CustomArmor.GeneratingArmor.CustomArmorBoots.createT1Boots;
import static me.brzeph.customitems.CustomItemList.CustomArmor.UpdatingArmorLore.upgradingArmorLore;

public final class Main extends JavaPlugin implements Listener {
    private Map<Entity, CustomMobsListEnum> entities = new HashMap<>();
    private World world;
    private BukkitTask task;
    private DecimalFormat formatter = new DecimalFormat("#.##");
    private Map<Entity, Integer> indicators = new HashMap<>();
    private static Main instance;
    public static Main getInstance() {return instance;}
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("[CustomItems] plugin is now active");

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

        spawnMobs(9, 10, 5*20, 1, new Location(world,0,0,0));

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
    }
    public void spawnMobs(int size, int mobCap, int spawnTime, int tier, Location spawnerLocation){
        CustomMobsListEnum[] mobTypes = CustomMobsListEnum.values();
        getServer().getConsoleSender().sendMessage("[DEBUG] plugin is now active");
        task = new BukkitRunnable(){
            Set<Entity> spawned = entities.keySet();
            List<Entity> removal = new ArrayList<>();
            @Override
            public void run() { //spawning algorithm
                for (Entity entity : spawned){
                    if (!entity.isValid() || entity.isDead()) removal.add(entity);
                } //this handles tracking dead mobs for the mob cap
                spawned.removeAll(removal);

                int missingMobs = mobCap - entities.size();
                if (missingMobs <= 0) return;
                int spawnAmount = (int) (Math.random()*(missingMobs + 1)), count = 0;
                while (count <= spawnAmount){
                    count++;
                    int randomX = (int) (getRandomWithNegative(size) + spawnerLocation.getX());
                    int randomZ = (int) (getRandomWithNegative(size) + spawnerLocation.getZ());
                    Block block = world.getHighestBlockAt(randomX, randomZ);
                    double xOffset = randomOffset();
                    double zOffset = randomOffset();
                    Location location = block.getLocation().clone().add(xOffset,1,zOffset);
                    if (!isSpawnable(location)) continue;
                    double random = Math.random()*101, previous = 0;
                    CustomMobsListEnum typeToSpawn = mobTypes[0];
                    for (CustomMobsListEnum type : mobTypes){
                        previous += type.getSpawnChance();
                        if (random <= previous){ //48:10 on the video
                            typeToSpawn = type;
                            break;
                        }
                    }
                    entities.put(typeToSpawn.spawn(location), typeToSpawn); //this handles the mob cap
                }
            }
        }.runTaskTimer(this, 0L, spawnTime); //every [spawnTime] seconds will spawn 0 ~ [mobCap] mobs in random amounts
    }
    private boolean isSpawnable(Location location){
        Block feetBlock = location.getBlock();
        Block headBlock = location.clone().add(0,2,0).getBlock();
        Block middleBlock = location.clone().add(0,1,0).getBlock();
        return feetBlock.isPassable() && headBlock.isPassable() && middleBlock.isPassable() &&
                !feetBlock.isLiquid() && !headBlock.isLiquid() && !middleBlock.isLiquid();
    }
    private int getRandomWithNegative(int size){
        int random = (int) (Math.random()*(size + 1));
        if (Math.random() > 0.5 ) random *= -1;
        return random;
    }
    private double randomOffset(){
        double random =Math.random();
        if (Math.random() > 0.5 ) random *= -1;
        return random;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        Entity rawEntity = event.getEntity();
        if (!entities.containsKey(rawEntity)) return;
        CustomMobsListEnum mob = entities.get(rawEntity);
        LivingEntity entity = (LivingEntity) rawEntity;
        double damage = event.getFinalDamage();
        double health = entity.getHealth() + entity.getAbsorptionAmount();
        if (health > damage){ //checks if the entity survived
            health -= damage;
            entity.setCustomName(mob.getName() + " " + (int) health + "/" + (int) mob.getMaxHealth() + "♥");
        }
        Location loc = entity.getLocation().clone().add(randomOffset(), 1, randomOffset());
        world.spawn(loc, ArmorStand.class, armorStand -> {
            armorStand.setMarker(true);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName("&c" + formatter.format(damage));
            indicators.put(armorStand, 20*2); //armorStand will last 2 seconds
        });

    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!entities.containsKey(event.getEntity())) return;
        event.setDroppedExp(0);
        event.getDrops().clear();
        entities.remove(event.getEntity()).tryDropLoot(event.getEntity().getLocation());
        event.getDrops().add(upgradingArmorLore(createT1Boots()));
    }
}
