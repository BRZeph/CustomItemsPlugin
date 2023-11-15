package me.brzeph.customitems.CombatMechanics.CustomMobs.GUI;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTTileEntity;
import me.brzeph.customitems.CombatMechanics.CombatSystem.CustomMobsListEnum2;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RegisterMobToSpawnGUI implements Listener {
    public static void registerMobToSpawnOpenGUI(Player player){
        Inventory inventory = Bukkit.createInventory(player, 27, "§0Register mob spawn chance");
        int k = 19;
        NBTTileEntity nbtTileEntity = new NBTTileEntity(SharedData.callingBlock.get(player).getState());
        int i;
        int j = 0;
        for (i = 1; i <= CustomMobsListEnum2.values().length; i++){
            k = nbtTileEntity.getPersistentDataContainer().getInteger(String.valueOf(i));
            if (k != 0){
                j = j + k;
            }
        }
        for (i = 0; i <= 19 - j/5; i++) {
            inventory.setItem(i, registerMobToSpawn(i));
        }
        player.openInventory(inventory);
    }

    private static ItemStack registerMobToSpawn(int i) {
        ItemStack itemStack = new ItemStack(Material.CLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Set this mob to be §c" + 5*(i+1) + "§f% of the mobs spawned in this spawner");
        itemStack.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setInteger("mobPercentage", 5*(i+1));
        return nbtItem.getItem();
    }
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase("§0Register mob spawn chance")) {
            if (event.getCurrentItem() == null){
                event.setCancelled(true);
            } else {
                Block block = SharedData.callingBlock.get(player);
                ItemStack itemStack = SharedData.callingItemStack.get(block);
                NBTItem nbtItem = new NBTItem(itemStack);
                NBTItem nbtItem1 = new NBTItem(event.getCurrentItem());
                String mobIDString = nbtItem.getInteger("uniqueMobID").toString();
                int mobPercentage = nbtItem1.getInteger("mobPercentage");
                NBT.modifyPersistentData(block.getState(), nbt -> {
                    nbt.setInteger(mobIDString, mobPercentage);
                });
                player.closeInventory();
                event.setCancelled(true);
            }
        }
    }
}
