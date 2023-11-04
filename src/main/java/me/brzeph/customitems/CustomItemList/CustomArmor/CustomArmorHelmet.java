package me.brzeph.customitems.CustomItemList.CustomArmor;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CustomArmorHelmet {
    public static ItemStack createT1Helmet(){
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.LEATHER_HELMET, 1));
        nbtItem.setInteger("tier", 1);
        nbtItem.setInteger("bonusHealth", 30);
        nbtItem.setInteger("bonusArmor", 2);
        Field[] fields = BaseArmorStats.class.getDeclaredFields();
        for (Field field : fields){
            if (field.getType() == int.class){
                try {
                    int value = field.getInt(null);
                    nbtItem.setInteger(field.getName(), value);
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        ItemStack itemStack = nbtItem.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Custom T1 helmet");

        List<String> lore = new ArrayList<>();
        lore.add(".");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);




        return new ItemStack(itemStack);
    }
}
