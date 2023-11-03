package me.brzeph.customitems.Events.NPCEvents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static me.brzeph.customitems.Events.NPCEvents.VendingMethods.VendingCustomTieredPickaxes.*;
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
                    vendingT1PickaxeOnSkillTrainer(player);
                } else if (event.getCurrentItem().getType() == Material.STONE_PICKAXE){
                    getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on stone pickaxe");
                    vendingT2PickaxeOnSkillTrainer(player);
                } else if (event.getCurrentItem().getType() == Material.IRON_PICKAXE) {
                    getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on iron pickaxe");
                    vendingT3PickaxeOnSkillTrainer(player);
                } else if (event.getCurrentItem().getType() == Material.DIAMOND_PICKAXE){
                    getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on diamond pickaxe");
                    vendingT4PickaxeOnSkillTrainer(player);
                } else if (event.getCurrentItem().getType() == Material.GOLDEN_PICKAXE){
                    getServer().getConsoleSender().sendMessage("[DEBUG]: clicked on gold pickaxe");
                    vendingT5PickaxeOnSkillTrainer(player);
                }
            event.setCancelled(true);
        }
    }
}
