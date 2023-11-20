package me.brzeph.customitems.CombatMechanics.CustomMobs.GUI;

import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTTileEntity;
import me.brzeph.customitems.CombatMechanics.CustomCombatItems.ComatItemsEnums.ArmorTXHPEnum;
import me.brzeph.customitems.CombatMechanics.CustomCombatItems.ComatItemsEnums.WeaponTXDamage;
import me.brzeph.customitems.CombatMechanics.CombatSystem.CustomMobsListEnum2;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static me.brzeph.customitems.CombatMechanics.CustomMobs.GUI.RegisterMobToSpawnGUI.registerMobToSpawnOpenGUI;
import static org.bukkit.Bukkit.getServer;

public class ChangeMobTypeGUI implements Listener {
    public static void changeMobTypeOpenGUI(Player player){
        Block block = SharedData.callingBlock.get(player);
        int tier = new NBTTileEntity(block.getState()).getPersistentDataContainer().getInteger("tier");
        HashMap<CustomMobsListEnum2, EntityType> mobOptionsForTier = new HashMap<>();

        for (CustomMobsListEnum2 mob : CustomMobsListEnum2.values()) {
            if (mob.getTier() == tier) {
                EntityType entityType = checkEntityType(mob);
                mobOptionsForTier.put(mob, entityType);
            }
        }
        int hashmapSize = mobOptionsForTier.size();
        int inventorySize = (hashmapSize / 9 + (hashmapSize % 9 == 0 ? 0 : 1)) * 9;
        Inventory inventory = Bukkit.createInventory(player, inventorySize, "§0Choose mob type GUI");
        int slot = 0;
        for (CustomMobsListEnum2 mob : mobOptionsForTier.keySet()) {
            ItemStack mobItem = createItemStackForMob(mob, tier); // You need to implement this method
            inventory.setItem(slot, mobItem);
            slot++;
            if (slot >= inventorySize) {
                break; // Stop if we filled all slots
            }
        }
        player.openInventory(inventory);
    }

    private static ItemStack createItemStackForMob(CustomMobsListEnum2 mob, int tier) {
        float mobHPMultiplierLevel = mob.getMobHPLevelMultiplier();
        int minDamage = WeaponTXDamage.getMinDamageValueTier(tier);
        int maxDamage = WeaponTXDamage.getMaxDamageValueTier(tier);
        int minArmorHP = (int) (mobHPMultiplierLevel*4*ArmorTXHPEnum.getMinHealthValueTier(tier));
        int maxArmorHP = (int) (mobHPMultiplierLevel*4*ArmorTXHPEnum.getMaxHealthValueTier(tier));

        Material mobSkullMaterial = Material.DRAGON_HEAD;
        boolean registeredMob = true;
        if (mob.getMobType() == 1){
            mobSkullMaterial = Material.ZOMBIE_HEAD;
        }else if(mob.getMobType() == 2){
            mobSkullMaterial = Material.SKELETON_SKULL;
        } else{
            registeredMob = false;
        }
        NBTItem nbtItem = new NBTItem(new ItemStack(mobSkullMaterial));
        nbtItem.setInteger("uniqueMobID", mob.getUniqueMobID());
        ItemStack itemStack = new ItemStack(nbtItem.getItem());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(mob.getName());
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§fTier: " + mob.getTier());
        lore.add("§fMob type: " + mob.getMobType());
        lore.add("§fMob level: " + mob.getMobLevel());
        lore.add("§fWeapon type: " + mob.getItemMainHandMaterial());
        lore.add("§fWeapon damage: " + minDamage + " -> " + maxDamage);
        lore.add("§fArmor health: " + minArmorHP + " -> " + maxArmorHP);
        if (!registeredMob){
            lore.add("Invalid mob, not registered properly on ''ChangeMobTypeGUI''");
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static EntityType checkEntityType(CustomMobsListEnum2 mob) {
        int i = mob.getMobType();
        EntityType entityType;
        if (i == 1){
            entityType = EntityType.ZOMBIE;
        } else {
            entityType = EntityType.SKELETON;
        }
        return entityType;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase("§0Choose mob type GUI")) {
            if (event.getCurrentItem() == null) {
                return;
            }
            NBTItem nbtItem = new NBTItem(event.getCurrentItem());
            if (nbtItem.hasTag("uniqueMobID")){
                SharedData.callingItemStack.put(SharedData.callingBlock.get(player), nbtItem.getItem());
                registerMobToSpawnOpenGUI(player);
            }
            event.setCancelled(true);
        }
    }
}
