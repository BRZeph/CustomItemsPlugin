package me.brzeph.customitems.CombatSystem;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.CustomItemList.CustomCombatItems.GeneratingCombatItems.CreateTXArmor;
import me.brzeph.customitems.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.util.Vector;

import static me.brzeph.customitems.CustomItemList.CustomCombatItems.UpdatingArmorLore.upgradingArmorLore;
import static me.brzeph.customitems.CombatSystem.SetPlayerHPToXPBar.setPlayerHPToXPBar;
import static me.brzeph.customitems.CustomMobs.SpawnerFunctionality.getRandomValue;
import static me.brzeph.customitems.CustomMobs.SpawnerFunctionality.randomOffset;

public class CombatEvents implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity.customName() == null) return;
        event.setDroppedExp(0);
        event.getDrops().clear();
        event.getDrops().add(upgradingArmorLore(CreateTXArmor.createTXBoots(1)));
    }
    private void applyCustomKnockback(Player player, Entity target) {
        Vector yAxisModifier;
        Vector yAxisDelete = new Vector(1,0,1);
        Vector direction = target.getLocation().toVector().subtract(player.getLocation().toVector()).multiply(yAxisDelete).normalize();

        double regularCustomKnockback = (double) getRandomValue(3, 1) /10;
        double funkyCustomKnockback = (double) getRandomValue(20, 10)/10;
        double knockbackApplied;
        int decidingTypeOfKnockback = getRandomValue(100,1);
        if (decidingTypeOfKnockback <= 5){
            yAxisModifier = new Vector(0, 0, 0); //no up knockback to prevent sending the mob REALLY far away
            knockbackApplied = funkyCustomKnockback;
        } else {
            knockbackApplied = regularCustomKnockback;
            if (target.getLocation().add(0,-1,0).getBlock().getType() != Material.AIR){
                yAxisModifier = new Vector(0, (double) getRandomValue(100,1)/100, 0);
            } else {
                yAxisModifier = new Vector(0, 0, 0); //no knockback to prevent air combo
            }
        }
        Vector knockbackVector = direction.add(yAxisModifier).multiply(knockbackApplied);
        target.setVelocity(knockbackVector);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        event.setDamage(0);
        Entity entityHitting = event.getDamager();
        Entity entityHitted = event.getEntity();
        if (new NBTEntity(entityHitted).getPersistentDataContainer().getString("customMob").equals("yes")) {
            //player hitting mob situation
            Player player = (Player) entityHitting;
            if (PlayerCombatTime.playerCombatTickCount.get(player) == null){
                player.sendMessage("");
                player.sendMessage("");
                player.sendMessage("§cEntering Combat");
                player.sendMessage("");
                player.sendMessage("");
            }
            int damageFromWeapon;
            if (player.getEquipment().getItemInMainHand().isEmpty()){
                damageFromWeapon = 1;
            } else {
                NBTItem nbtItemPlayer = new NBTItem(player.getItemInHand());
                damageFromWeapon = getRandomValue(nbtItemPlayer.getInteger("maxDamage"), nbtItemPlayer.getInteger("minDamage"));
            }
            NBTEntity nbtEntity = new NBTEntity(entityHitted);
            int maxHP = nbtEntity.getPersistentDataContainer().getInteger("maxHP");
            int HPBeforeHit = nbtEntity.getPersistentDataContainer().getInteger("currentHP");
            nbtEntity.getPersistentDataContainer().setInteger("currentHP", HPBeforeHit - damageFromWeapon);
            int HPAfterHit = HPBeforeHit - damageFromWeapon;

            NBTEntity nbtEntity1 = new NBTEntity(player);
            nbtEntity1.getPersistentDataContainer().setInteger("CombatTimer", nbtEntity1.getPersistentDataContainer().getInteger("baseCombatTimer")); //int in seconds
            nbtEntity1.getPersistentDataContainer().setBoolean("onCombat", true);

            PlayerCombatTime.playerCombatTickCount.put(player, nbtEntity1.getPersistentDataContainer().getInteger("baseCombatTimer"));

//TODO: implement the situation of PVP

            String name = entityHitted.getName();
            String nameWithoutHealth = name.split(" ♥ ")[0].trim();
            String[] parts = entityHitted.getName().split(" ♥ ");
            String wordsBeforeHeart = parts[0].trim();
            if (HPBeforeHit > damageFromWeapon) {
                player.sendMessage("§c" + damageFromWeapon + " DMG -> §f " + wordsBeforeHeart + " [" + HPAfterHit + " HP]");
                entityHitted.setCustomName(nameWithoutHealth + " ♥ " + Main.getInstance().formatter.format(HPAfterHit) + "/" + maxHP + " ♥");
            } else{
                player.sendMessage("§c" + damageFromWeapon + " DMG -> §f " + wordsBeforeHeart + " [0 HP]");
                LivingEntity livingEntity = (LivingEntity) entityHitted;
                livingEntity.damage(50);
            }
            Location loc = entityHitted.getLocation().clone().add(randomOffset(), randomOffset(), randomOffset());
            Main.getInstance().world.spawn(loc, ArmorStand.class, armorStand -> {
                armorStand.setMarker(true);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setSmall(true);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName("§c" + Main.getInstance().formatter.format(damageFromWeapon) + " DMG");
                Main.getInstance().indicators.put(armorStand, 20 * 2); //armorStand will last 2 seconds
            });

            applyCustomKnockback(player, entityHitted);
            event.setCancelled(true);
        }
        if (entityHitted instanceof Player && new NBTEntity(entityHitting).getPersistentDataContainer().getString("customMob").equals("yes")){
            //mob hitting player situation
            Player player = (Player) entityHitted;
            PlayerHealthRegeneration.playerHealthRegenTickCount.put(player, 1);
            if (PlayerCombatTime.playerCombatTickCount.get(player) == null){
                player.sendMessage("");
                player.sendMessage("");
                player.sendMessage("§cEntering Combat");
                player.sendMessage("");
                player.sendMessage("");
            }
            LivingEntity mob = (LivingEntity) entityHitting;
            int damageFromWeapon;
            if (mob.getEquipment().getItemInMainHand().isEmpty()){
                damageFromWeapon = 1;
            } else {
                NBTItem nbtItemMob = new NBTItem(mob.getEquipment().getItemInMainHand());
                damageFromWeapon = getRandomValue(nbtItemMob.getInteger("maxDamage"), nbtItemMob.getInteger("minDamage"))*3;
            }


            NBTEntity nbtEntityPlayer = new NBTEntity(player);
            NBTCompound playerData = nbtEntityPlayer.getPersistentDataContainer();
            float maxHP = playerData.getFloat("currentMaxHealth");
            float HPBeforeHit = playerData.getFloat("currentHP");
            float HPAfterHit = HPBeforeHit - damageFromWeapon;
            playerData.setFloat("currentHP", HPAfterHit);
            playerData.setBoolean("onCombat", true);
            playerData.setInteger("CombatTimer", nbtEntityPlayer.getPersistentDataContainer().getInteger("baseCombatTimer")); //int in seconds
            nbtEntityPlayer.mergeCompound(playerData);
            float damageOnHealth = 20*damageFromWeapon/maxHP;


            PlayerCombatTime.playerCombatTickCount.put(player, nbtEntityPlayer.getPersistentDataContainer().getInteger("baseCombatTimer"));


            String[] parts = mob.getName().split(" ♥ ");
            String wordsBeforeHeart = parts[0].trim();
            player.sendMessage("§c-" + damageFromWeapon + "HP (" + wordsBeforeHeart + ")" + " §a[" + (int)HPAfterHit + " HP]");
            if (HPAfterHit <= 0){
                player.damage(50);
            } else {
                player.damage(damageOnHealth);
            }
            setPlayerHPToXPBar(player);
        }
    }
}
