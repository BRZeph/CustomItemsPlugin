package me.brzeph.customitems.GUIs;

import me.brzeph.customitems.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIEventsHandler implements Listener {

    private final Main plugin;

    public GUIEventsHandler(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        GUIGenerator.handleEvent(event, plugin);
    }
    public static void playerBuyItemEvent(Player player, ItemStack vendingItem, int itemCost){
        if (player.getInventory().contains(Material.EMERALD, itemCost)){
            ItemStack payment = new ItemStack(Material.EMERALD, itemCost);
            player.getInventory().removeItem(payment).isEmpty();
            if (player.getInventory().firstEmpty() >= 0) {
                player.getInventory().addItem(vendingItem);
                player.sendMessage("§aHere is your §2" + vendingItem.getI18NDisplayName() + " §aitem!");
            } else {
                Location dropLocation = player.getLocation();
                player.getWorld().dropItemNaturally(dropLocation, vendingItem);
                player.sendMessage("§cYou don't have enough slots, the item[s] have been dropped on the ground");
            }
        }else{
            player.sendMessage("§cYou do not have enough gems!");
            player.sendMessage("§cGem cost: " + itemCost + "G!");
        }
        player.closeInventory();
    }
}
