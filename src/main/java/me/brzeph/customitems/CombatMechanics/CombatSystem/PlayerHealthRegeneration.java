package me.brzeph.customitems.CombatMechanics.CombatSystem;

import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

import static me.brzeph.customitems.CombatMechanics.CombatSystem.SetPlayerHPToXPBar.setPlayerHPToXPBar;

public class PlayerHealthRegeneration implements Listener {
    public static HashMap<Player, Integer> playerHealthRegenTickCount = new HashMap<>();
    //TODO: consider turning the HashMap into a Set without the ''integer'' Value, maybe that does not matter
    BukkitTask task2;
    @EventHandler
    public void onServerStart(ServerLoadEvent event){
        playerHealthRegeneration();
    }
    public void playerHealthRegeneration(){
        task2 = new BukkitRunnable() {
            @Override
            public void run() {
                for (Map.Entry<Player, Integer> entry : playerHealthRegenTickCount.entrySet()) {
                    Player player = entry.getKey();
                    if (player.getHealth() == 20){
                        playerHealthRegenTickCount.remove(player);
                        return;
                    } else {
                        //handle in here other health regen such as in combat regen [forgot the name of the armor orb]
                        //life steal should be handled on "combat events"

                        NBTEntity nbtPlayer = new NBTEntity(player);
                        int baseHPS = nbtPlayer.getPersistentDataContainer().getInteger("baseHPS");
                        int helmetHPS = 0; //TODO: change this to make it more OOP on the player
                        int chestPlateHPS = 0;
                        int leggingsHPS = 0;
                        int bootsHPS = 0;
                        if (player.getEquipment().getHelmet() != null) {
                            helmetHPS = new NBTItem(player.getEquipment().getHelmet()).getInteger("armorHPS");
                        }
                        if (player.getEquipment().getChestplate() != null) {
                            chestPlateHPS = new NBTItem(player.getEquipment().getChestplate()).getInteger("armorHPS");
                        }
                        if (player.getEquipment().getLeggings() != null) {
                            leggingsHPS = new NBTItem(player.getEquipment().getLeggings()).getInteger("armorHPS");
                        }
                        if (player.getEquipment().getBoots() != null) {
                            bootsHPS = new NBTItem(player.getEquipment().getBoots()).getInteger("armorHPS");
                        }
                        int totalHPS = helmetHPS + chestPlateHPS + leggingsHPS + bootsHPS + baseHPS;
                        float maxHealth = nbtPlayer.getPersistentDataContainer().getFloat("currentMaxHealth");
                        float currentHealth = nbtPlayer.getPersistentDataContainer().getFloat("currentHP");
                        boolean inCombat = nbtPlayer.getPersistentDataContainer().getBoolean("onCombat");
                        int newHealth = (int) currentHealth + totalHPS;
                        if (!inCombat) {
                            if (newHealth >= maxHealth) {
                                player.setHealth(20);
                                nbtPlayer.getPersistentDataContainer().setFloat("currentHP", maxHealth);
                                player.sendMessage("§a+" + totalHPS + "HP");
                                player.sendMessage("§aHP: " + (int) maxHealth);
                                setPlayerHPToXPBar(player);
                            } else {
                                player.setHealth((currentHealth + totalHPS) * 20 / maxHealth);
                                nbtPlayer.getPersistentDataContainer().setFloat("currentHP", currentHealth + totalHPS);
                                player.sendMessage("§a+" + totalHPS + "HP");
                                player.sendMessage("§aHP: " + newHealth + "/" + (int) maxHealth);
                                setPlayerHPToXPBar(player);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20);
    }
}
