package me.brzeph.customitems.Events.NPCEvents;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static me.brzeph.customitems.CustomItemList.CustomPickaxe.create_dr_wooden_pickaxe;
import static org.bukkit.Bukkit.getServer;


public class SkillTrainerEvents implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase("§0SkillTrainer")){
            if (event.getCurrentItem() == null){
                getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on null");
                return;
            }
                if (event.getCurrentItem().getType() == Material.WOODEN_PICKAXE){
                    getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on wooden pickaxe");
                    if (player.getInventory().contains(Material.EMERALD, 100)){
                        ItemStack payment = new ItemStack(Material.EMERALD, 100);
                        player.getInventory().removeItem(payment).isEmpty();
                        if (player.getInventory().firstEmpty() >= 0) {
                            player.getInventory().addItem(create_dr_wooden_pickaxe());
                            player.sendMessage("§aHere is a T1 Pickaxe!");
                        } else {
                            Location dropLocation = player.getLocation();
                            player.getWorld().dropItemNaturally(dropLocation, create_dr_wooden_pickaxe());
                            player.sendMessage("§cYou don't have enough slots, the item[s] have been dropped on the ground");
                        }
                        event.setCancelled(true);
                    }else{
                        player.sendMessage("§cYou do not have enough gems!");
                        player.sendMessage("§cGem cost: 100G!");
                    }
                }
            event.setCancelled(true);
        }
    }
}
