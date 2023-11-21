package me.brzeph.customitems.CombatMechanics.CombatSystem;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import io.papermc.paper.event.entity.EntityDamageItemEvent;
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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Objects;

import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems.CreateTXArmor.*;
import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.GeneratingCombatItems.CreateTXWeapon.*;
import static me.brzeph.customitems.CombatMechanics.CustomCombatItems.UpgradingArmorLore.upgradingArmorLore;
import static me.brzeph.customitems.CombatMechanics.CombatSystem.SetPlayerHPToXPBar.setPlayerHPToXPBar;
import static me.brzeph.customitems.CombatMechanics.CustomMobs.SpawnerFunctionality.randomOffset;
import static me.brzeph.customitems.Utils.Utils.getRandomValue;
import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Sound.*;

public class CombatEvents implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        NBTEntity nbtEntity = new NBTEntity(entity);
        int mobLevel = nbtEntity.getPersistentDataContainer().getInteger("mobLevel");
        if (entity.customName() == null) return;
        event.setDroppedExp(0);
        event.getDrops().clear();
        if (event.getEntity().getLastDamageCause() != null &&
                event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.CUSTOM) {
            int mobTier = nbtEntity.getPersistentDataContainer().getInteger("mobTier");

            int dropChance = 100 / mobTier;
            int dropRoll = getRandomValue(100, 1);
            ItemStack item = new ItemStack(Material.CLOCK);
            if (dropRoll < dropChance) {
                int droppedItem = getRandomValue(17, 1);
                if (droppedItem == 1) {
                    event.getDrops().add(item = createTXWeaponAxe(mobTier));
                }
                if (droppedItem == 2) {
                    event.getDrops().add(item = createTXWeaponSword(mobTier));
                }
                if (droppedItem == 3) {
                    event.getDrops().add(item = createTXWeaponHoe(mobTier));
                }
                if (droppedItem == 4) {
                    event.getDrops().add(item = createTXWeaponShovel(mobTier));
                }
                if (droppedItem > 4 && droppedItem <= 7) {
                    event.getDrops().add(item = upgradingArmorLore(createTXHelmet(mobTier, mobLevel)));
                }
                if (droppedItem > 8 && droppedItem <= 11) {
                    event.getDrops().add(item = upgradingArmorLore(createTXChestPlate(mobTier, mobLevel)));
                }
                if (droppedItem > 11 && droppedItem <= 14) {
                    event.getDrops().add(item = upgradingArmorLore(createTXLeggings(mobTier, mobLevel)));
                }
                if (droppedItem > 14 && droppedItem <= 17) {
                    event.getDrops().add(item = upgradingArmorLore(createTXBoots(mobTier, mobLevel)));
                }
            }
            if (item.getType() != Material.CLOCK) {
                NBTItem nbtItem = new NBTItem(item);
                String rarity = nbtItem.getString("rarity");
                event.setDeathSoundPitch(1f);
                event.setDeathSoundVolume(1f);
                if (Objects.equals(rarity, "§fCommon")) {
                    event.setDeathSound(BLOCK_NOTE_BLOCK_BELL);
                }
                if (Objects.equals(rarity, "§aUncommon")) {
                    event.setDeathSound(BLOCK_NOTE_BLOCK_CHIME);
                }
                if (Objects.equals(rarity, "§9Rare")) {
                    event.setDeathSound(BLOCK_ANVIL_PLACE);
                }
                if (Objects.equals(rarity, "§5Epic")) {
                    event.setDeathSound(ENTITY_WITHER_SPAWN);
                }
                if (Objects.equals(rarity, "§eLegendary")) {
                    event.setDeathSound(ENTITY_ENDER_DRAGON_DEATH);
                }
            }
        }
    }
    private void applyCustomKnockback(Player player, Entity target) {
        Vector yAxisModifier;
        Vector yAxisRemove = new Vector(1,0,1);
        Vector direction = target.getLocation().toVector().subtract(player.getLocation().toVector()).multiply(yAxisRemove).normalize();

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
            float damageFromWeapon;
            float fireDMG, iceDMG, pureDMG, poisonDMG, VSMonster;
            boolean BFireDMG, BIceDMG, BPureDMG, BPoison, criticalHit;
            int lifeSteal = 0;
            boolean BlifeSteal = false;
            if (player.getEquipment().getItemInMainHand().isEmpty()){
                damageFromWeapon = 1;
                fireDMG = 0;
                iceDMG = 0;
                pureDMG = 0;
                poisonDMG = 0;
            } else {
                NBTItem nbtItem = new NBTItem(player.getEquipment().getItemInMainHand());
                fireDMG = nbtItem.getInteger("Fire");
                iceDMG = nbtItem.getInteger("Ice");
                pureDMG = nbtItem.getInteger("Pure");
                poisonDMG = nbtItem.getInteger("Poison");
                if (fireDMG > 0) BFireDMG = true;
                if (iceDMG > 0) BIceDMG = true;
                if (pureDMG > 0) BPureDMG = true;
                if (poisonDMG > 0) BPoison = true;
                damageFromWeapon = getRandomValue(nbtItem.getInteger("maxDamage"), nbtItem.getInteger("minDamage"));
                VSMonster = nbtItem.getInteger("VSMonster");
                damageFromWeapon = damageFromWeapon*(100 + VSMonster)/100;
                int criticalHitChance = nbtItem.getInteger("CriticalHit");
                if (getRandomValue(100, 1) <= criticalHitChance) {
                    damageFromWeapon = 2* damageFromWeapon;
                    criticalHit = true;
                }
                lifeSteal = nbtItem.getInteger("LifeSteal");
                if (lifeSteal > 0){BlifeSteal = true;}
            }
            NBTEntity nbtEntity = new NBTEntity(entityHitted);
            int maxHP = nbtEntity.getPersistentDataContainer().getInteger("maxHP");
            int HPBeforeHit = nbtEntity.getPersistentDataContainer().getInteger("currentHP");
            int totalArmor = nbtEntity.getPersistentDataContainer().getInteger("totalArmor");
            int elementalResistance = nbtEntity.getPersistentDataContainer().getInteger("elementalResistance");
            if (damageFromWeapon != 1){
                fireDMG = fireDMG*(100 - elementalResistance)/100;
                iceDMG = iceDMG*(100 - elementalResistance)/100;
                poisonDMG = poisonDMG*(100 - elementalResistance)/100;
                damageFromWeapon = damageFromWeapon + fireDMG + iceDMG + poisonDMG;
                damageFromWeapon = damageFromWeapon * (100-totalArmor)/100;
                damageFromWeapon = damageFromWeapon + pureDMG; //pure goes through armor
            }

            int totalDamage = (int) (damageFromWeapon);
            if (BlifeSteal) {
                float playerLifeSteal = totalDamage * ((float)lifeSteal/100);
                NBTEntity nbtEntityPlayer = new NBTEntity(player);
                NBTCompound playerData = nbtEntityPlayer.getPersistentDataContainer();
                float playerMaxHP = playerData.getFloat("currentMaxHealth");
                float playerHPBeforeHit = playerData.getFloat("currentHP");
                float HPAfterHit = playerHPBeforeHit + playerLifeSteal;
                if (HPAfterHit > playerMaxHP){
                    player.setHealth(20);
                    playerData.setFloat("currentHP", playerMaxHP);
                    player.sendMessage("+" + (int) playerLifeSteal + " HP recovered");
                    player.sendMessage("[" + (int)playerMaxHP + "/" + (int)playerMaxHP + "]" + " HP");
                } else {
                    playerData.setFloat("currentHP", HPAfterHit);
                    float newLife = 20 * HPAfterHit / playerMaxHP;
                    player.setHealth(newLife);
                    player.sendMessage("[" + (int)HPAfterHit + "/" + (int)playerMaxHP + "]" + " HP");
                }
                nbtEntityPlayer.mergeCompound(playerData);
                setPlayerHPToXPBar(player);
            }
            nbtEntity.getPersistentDataContainer().setInteger("currentHP", HPBeforeHit - totalDamage);
            int HPAfterHit = HPBeforeHit - totalDamage;

            NBTEntity nbtEntity1 = new NBTEntity(player);
            nbtEntity1.getPersistentDataContainer().setInteger("CombatTimer", nbtEntity1.getPersistentDataContainer().getInteger("baseCombatTimer")); //int in seconds
            nbtEntity1.getPersistentDataContainer().setBoolean("onCombat", true);


            PlayerCombatTime.playerCombatTickCount.put(player, nbtEntity1.getPersistentDataContainer().getInteger("baseCombatTimer"));
//TODO: implement the situation of PVP

            double ratio = (double) HPAfterHit /maxHP;
            int barLength = 40;
            int greenBlocks = (int) (barLength*ratio);
            StringBuilder newLore = new StringBuilder();
            for (int i = 0; i < barLength; i++){
                if (i<greenBlocks){
                    newLore.append("§a:");
                }else {
                    newLore.append("§4:");
                }
            }
            String name = entityHitted.getName();
            String nameWithoutHealth = name.split(" ♥ ")[0].trim();
            String[] parts = entityHitted.getName().split(" ♥ ");
            String wordsBeforeHeart = parts[0].trim();
            if (HPBeforeHit > totalDamage) {
                player.sendMessage("§c" + totalDamage + " DMG -> §f " + nameWithoutHealth + " [" + HPAfterHit + " HP]");
                entityHitted.setCustomName(" ♥ " + Main.getInstance().formatter.format(HPAfterHit) + "/" + maxHP + " ♥" + newLore);
            } else{
                player.sendMessage("§c" + totalDamage + " DMG -> §f " + wordsBeforeHeart + " [0 HP]");
                LivingEntity livingEntity = (LivingEntity) entityHitted;
                livingEntity.damage(50);
            }
            Location loc = entityHitted.getLocation().clone().add(randomOffset(), randomOffset(), randomOffset());
            Main.getInstance().flatworld.spawn(loc, ArmorStand.class, armorStand -> {
                armorStand.setMarker(true);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setSmall(true);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName("§c" + Main.getInstance().formatter.format(totalDamage) + " DMG");
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
                player.setHealth(20*HPAfterHit/maxHP);
            }
            setPlayerHPToXPBar(player);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) { //prevent sun burn
        if (event.getCause() != EntityDamageEvent.DamageCause.CUSTOM){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onEntityArmorDamage(EntityDamageItemEvent event){ //prevent losing armor to sun burn/excessive hitting
        if (!(event.getEntity() instanceof Player)){
            event.setCancelled(true);
        }
    }
}
