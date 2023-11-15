package me.brzeph.customitems.GUIs.test2;

import me.brzeph.customitems.Main;
import me.brzeph.customitems.MiningMechanics.pickaxeVendingCost;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.function.BiConsumer;

import static me.brzeph.customitems.GUIs.test2.GUIEventsHandler.playerBuyItemEvent;
import static me.brzeph.customitems.MiningMechanics.PickaxeCreator.*;
import static org.bukkit.Bukkit.getServer;

public enum GUIGenerator {

    GUISkillTrainer("Skill trainer GUI", 27,
            createClickableItem2(0, createNewGeneratedPickaxe(Pickaxe.createPickaxe("Custom T1 Pickaxe", true, false, 1, 0, 1,
                    "§aItem cost: §c" + pickaxeVendingCost.PickaxeVendingCost(1) + "G")), (player, event) -> {
                playerBuyItemEvent(player, createPreGeneratedPickaxes(createT1Pickaxe), pickaxeVendingCost.PickaxeVendingCost(1));
                event.setCancelled(true);
            }),
            createClickableItem2(2, createNewGeneratedPickaxe(Pickaxe.createPickaxe("Custom T2 Pickaxe", true, false, 2, 1, 20,
                    "§6[+1] Custom enchantment", "" , "§aItem cost: §c" + pickaxeVendingCost.PickaxeVendingCost(2) + "G")), (player, event) -> {
                playerBuyItemEvent(player, createPreGeneratedPickaxes(createT2Pickaxe), pickaxeVendingCost.PickaxeVendingCost(2));
                event.setCancelled(true);
            }),
            createClickableItem2(4, createNewGeneratedPickaxe(Pickaxe.createPickaxe("Custom T3 Pickaxe", true, false, 3, 2, 40,
                    "§6[+2] Custom enchantment", "" , "§aItem cost: §c" + pickaxeVendingCost.PickaxeVendingCost(3) + "G")), (player, event) -> {
                playerBuyItemEvent(player, createPreGeneratedPickaxes(createT3Pickaxe), pickaxeVendingCost.PickaxeVendingCost(3));
                event.setCancelled(true);
            }),
            createClickableItem2(6, createNewGeneratedPickaxe(Pickaxe.createPickaxe("Custom T4 Pickaxe", true, false, 4, 3, 60,
                    "§6[+3] Custom enchantment", "" , "§aItem cost: §c" + pickaxeVendingCost.PickaxeVendingCost(4) + "G")), (player, event) -> {
                playerBuyItemEvent(player, createPreGeneratedPickaxes(createT4Pickaxe), pickaxeVendingCost.PickaxeVendingCost(4));
                event.setCancelled(true);
            }),
            createClickableItem2(8, createNewGeneratedPickaxe(Pickaxe.createPickaxe("Custom T5 Pickaxe", true, false, 5, 4, 80,
                    "§6[+4] Custom enchantment", "" , "§aItem cost: §c" + pickaxeVendingCost.PickaxeVendingCost(5) + "G")), (player, event) -> {
                playerBuyItemEvent(player, createPreGeneratedPickaxes(createT5Pickaxe), pickaxeVendingCost.PickaxeVendingCost(5));
                event.setCancelled(true);
            })
    );
    public static class ClickableItem extends ItemStack {

        private final BiConsumer<Player, InventoryClickEvent> clickAction;
        private final int itemPostion;

        public ClickableItem(int itemPostion, ItemStack itemStack, BiConsumer<Player, InventoryClickEvent> clickAction) {
            super(itemStack);
            this.clickAction = clickAction;
            this.itemPostion = itemPostion;
        }

        public int getItemPosition() {
            return itemPostion;
        }

        public void handleClick(Player player, InventoryClickEvent event) {
            clickAction.accept(player, event);
        }
    }
    private static ClickableItem createClickableItem( int itemPosition, Material material, BiConsumer<Player, InventoryClickEvent> clickAction) {
        return new ClickableItem(itemPosition, new ItemStack(material), clickAction);
    }
    private static ClickableItem createClickableItem2(int itemPosition, ItemStack itemStack, BiConsumer<Player, InventoryClickEvent> clickAction) {
        return new ClickableItem(itemPosition,itemStack, clickAction);
    }


    private final String guiName;
    private final int guiSize;
    private final ClickableItem[] items;

    GUIGenerator(String guiName, int guiSize, ClickableItem... items) {
        this.guiName = guiName;
        this.guiSize = guiSize;
        this.items = items;
    }

    public static void handleEvent(InventoryClickEvent event, Main plugin) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem != null) {
            for (ClickableItem item : GUISkillTrainer.items) {
                if (item.isSimilar(clickedItem)) {
                    item.handleClick(player, event);
                    break;
                }
            }
        }
    }

    public static void openSkillTrainerGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, GUISkillTrainer.guiSize, GUISkillTrainer.guiName);
        for (ClickableItem item : GUISkillTrainer.items) {
            getServer().getConsoleSender().sendMessage("[DEBUG]: item positions is " + item.getItemPosition());
            int position = item.getItemPosition();
            gui.setItem(position, item);
        }
        player.openInventory(gui);
    }
}
