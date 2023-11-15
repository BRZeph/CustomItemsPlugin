package me.brzeph.customitems.MiningMechanics;

import de.tr7zw.nbtapi.NBTItem;
import me.brzeph.customitems.MiningMechanics.MiningEvents.Enums.enchantmentEnums.*;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static me.brzeph.customitems.MiningMechanics.MiningEvents.RandomValueGenerators.randomlyChoosingNewEnchantment;
import static org.bukkit.Bukkit.getServer;

public enum PickaxeCreator {
    createT1Pickaxe("Custom T1 Pickaxe", true, true, 1, 0, 1),
    createT2Pickaxe("Custom T2 Pickaxe", true, true, 2, 1, 20),
    createT3Pickaxe("Custom T3 Pickaxe", true, true, 3, 2, 40),
    createT4Pickaxe("Custom T4 Pickaxe", true, true, 4, 3, 60),
    createT5Pickaxe("Custom T5 Pickaxe", true, true, 5, 4, 80);

    PickaxeCreator(String pickaxeName, boolean levelAndXpTracker, boolean enchantmentLore, int tier, int amountOfEnchantment, int level, String... lore){
        this.pickaxeName = pickaxeName;
        this.levelAndXpTracker = levelAndXpTracker;
        this.enchantmentLore = enchantmentLore;
        this.tier = tier;
        this.amountOfEnchantment = amountOfEnchantment;
        this.level = level;
        this.lore = lore;
    }
    public static class Pickaxe extends ItemStack {
        private final String pickaxeName;
        private final boolean levelAndXpTracker;
        private final boolean enchantmentLore;
        private final int tier;
        private final int amountOfEnchantment;
        private final int level;
        private final String[] lore;

        public Pickaxe(String pickaxeName, boolean levelAndXpTracker, boolean enchantmentLore, int tier, int amountOfEnchantment, int level, String... lore){
            this.pickaxeName = pickaxeName;
            this.levelAndXpTracker = levelAndXpTracker;
            this.enchantmentLore = enchantmentLore;
            this.tier = tier;
            this.amountOfEnchantment = amountOfEnchantment;
            this.level = level;
            this.lore = lore;
        }

        @Override
        public @NotNull HoverEvent<HoverEvent.ShowItem> asHoverEvent() {
            return super.asHoverEvent();
        }
         public static Pickaxe createPickaxe(String pickaxeName, boolean levelAndXpTracker, boolean enchantmentLore, int tier, int amountOfEnchantment, int level, String... lore) {
            return new Pickaxe(pickaxeName, levelAndXpTracker, enchantmentLore, tier, amountOfEnchantment, level, lore);
        }

        public String getPickaxeName() {
            return pickaxeName;
        }

        public boolean isLevelAndXpTracker() {
            return levelAndXpTracker;
        }

        public boolean isEnchantmentLore() {
            return enchantmentLore;
        }

        public int getTier() {
            return tier;
        }

        public int getAmountOfEnchantment() {
            return amountOfEnchantment;
        }

        public int getLevel() {
            return level;
        }

        public String[] getLore2() {
            return lore;
        }
    }
    private int amountOfEnchantment;
    private String pickaxeName;
    private boolean levelAndXpTracker;
    private boolean enchantmentLore;
    private int tier;
    private int level;
    private String[] lore;

    public String getPickaxeName() {
        return pickaxeName;
    }

    public int getAmountOfEnchantment() {
        return amountOfEnchantment;
    }

    public boolean isLevelAndXpTracker() {
        return levelAndXpTracker;
    }

    public int getTier() {
        return tier;
    }

    public int getLevel() {
        return level;
    }

    public String[] getLore() {
        return lore;
    }

    public boolean isEnchantmentLore() {
        return enchantmentLore;
    }

    public static ItemStack createPreGeneratedPickaxes(PickaxeCreator creator){
        int tier = creator.getTier();
        Material pickaxeMaterial = Material.NETHERITE_PICKAXE;
        if (tier == 1){ pickaxeMaterial = Material.WOODEN_PICKAXE; }
        if (tier == 2){ pickaxeMaterial = Material.STONE_PICKAXE; }
        if (tier == 3){ pickaxeMaterial = Material.IRON_PICKAXE; }
        if (tier == 4){ pickaxeMaterial = Material.DIAMOND_PICKAXE; }
        if (tier == 5){ pickaxeMaterial = Material.GOLDEN_PICKAXE; }
        ItemStack pickaxe = new ItemStack(pickaxeMaterial);
        if (pickaxe.getType() == Material.NETHERITE_PICKAXE){ throw new IllegalStateException("Illegal tier detected.");}
        ItemMeta itemMeta = pickaxe.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (itemMeta != null){
            if (creator.levelAndXpTracker) {
                lore.add("§7Level: §6" + creator.getLevel());
                lore.add("§7XP: §6" + 0 + "/" + creator.getLevel() * 300);
                lore.add("§7::::::::::::::::::::::::::::::::::::::::");
                lore.add("");
                lore.add("§6Pickaxe tier §f" + creator.getTier());
                if (tier == 1) {lore.add("§6Can only break the following ores: coal");}
                if (tier == 2) {lore.add("§6Can only break the following ores: coal and emerald");}
                if (tier == 3) {lore.add("§6Can only break the following ores: coal, emerald and iron");}
                if (tier == 4) {lore.add("§6Can only break the following ores: coal, emerald, iron and diamond");}
                if (tier == 5) {lore.add("§6Can only break the following ores: coal, emerald, iron, diamond and gold");}
                lore.add("");
            }
            List<String> mergedList = new ArrayList<>(lore);
            mergedList.addAll(Arrays.asList(creator.getLore()));
            itemMeta.setDisplayName(creator.getPickaxeName());
            itemMeta.setLore(mergedList);
            pickaxe.setItemMeta(itemMeta);
        }
        NBTItem nbtItem = new NBTItem(pickaxe);
        nbtItem.setString("uniqueID", UUID.randomUUID().toString());
        nbtItem.setInteger("tier", creator.getTier());
        nbtItem.setInteger("currentXP", 0);
        nbtItem.setInteger("currentLevel", creator.getLevel());
        nbtItem.setInteger("enchantmentDoubleOre", 0);
        nbtItem.setInteger("enchantmentTripleOre", 0);
        nbtItem.setInteger("enchantmentMiningSuccess", 5);
        nbtItem.setInteger("enchantmentGemFind", 0);
        nbtItem.setInteger("enchantmentTreasureFind", 0);
        nbtItem.setInteger("enchantmentDurability", 0);
        if (!creator.isEnchantmentLore()){return nbtItem.getItem();}
        else {return pickaxeAddEnchantments(creator.getAmountOfEnchantment(), nbtItem.getItem());}
    }
    public static ItemStack createNewGeneratedPickaxe(Pickaxe creator){
        int tier = creator.getTier();
        Material pickaxeMaterial = Material.NETHERITE_PICKAXE;
        if (tier == 1){ pickaxeMaterial = Material.WOODEN_PICKAXE; }
        if (tier == 2){ pickaxeMaterial = Material.STONE_PICKAXE; }
        if (tier == 3){ pickaxeMaterial = Material.IRON_PICKAXE; }
        if (tier == 4){ pickaxeMaterial = Material.DIAMOND_PICKAXE; }
        if (tier == 5){ pickaxeMaterial = Material.GOLDEN_PICKAXE; }
        ItemStack pickaxe = new ItemStack(pickaxeMaterial);
        if (pickaxe.getType() == Material.NETHERITE_PICKAXE){ throw new IllegalStateException("Illegal tier detected.");}
        ItemMeta itemMeta = pickaxe.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (itemMeta != null){
            if (creator.levelAndXpTracker) {
                lore.add("§7Level: §6" + creator.getLevel());
                lore.add("§7XP: §6" + 0 + "/" + creator.getLevel() * 300);
                lore.add("§7::::::::::::::::::::::::::::::::::::::::");
                lore.add("");
                lore.add("§6Pickaxe tier §f" + creator.getTier());
                if (tier == 1) {lore.add("§6Can only break the following ores: coal");}
                if (tier == 2) {lore.add("§6Can only break the following ores: coal and emerald");}
                if (tier == 3) {lore.add("§6Can only break the following ores: coal, emerald and iron");}
                if (tier == 4) {lore.add("§6Can only break the following ores: coal, emerald, iron and diamond");}
                if (tier == 5) {lore.add("§6Can only break the following ores: coal, emerald, iron, diamond and gold");}
                lore.add("");
            }
            List<String> mergedList = new ArrayList<>(lore);
            mergedList.addAll(Arrays.asList(creator.getLore2()));
            itemMeta.setDisplayName(creator.getPickaxeName());
            itemMeta.setLore(mergedList);
            pickaxe.setItemMeta(itemMeta);
        }
        NBTItem nbtItem = new NBTItem(pickaxe);
        nbtItem.setString("uniqueID", UUID.randomUUID().toString());
        nbtItem.setInteger("tier", creator.getTier());
        nbtItem.setInteger("currentXP", 0);
        nbtItem.setInteger("currentLevel", creator.getLevel());
        nbtItem.setInteger("enchantmentDoubleOre", 0);
        nbtItem.setInteger("enchantmentTripleOre", 0);
        nbtItem.setInteger("enchantmentMiningSuccess", 5);
        nbtItem.setInteger("enchantmentGemFind", 0);
        nbtItem.setInteger("enchantmentTreasureFind", 0);
        nbtItem.setInteger("enchantmentDurability", 0);
        if (!creator.isEnchantmentLore()){
            return nbtItem.getItem();
        }
        else {return pickaxeAddEnchantments(creator.getAmountOfEnchantment(), nbtItem.getItem());}
    }

    public static ItemStack pickaxeAddEnchantments(int amountOfEnchantments, ItemStack pickaxe) {
        NBTItem nbtItem = new NBTItem(pickaxe);
        int currentTier = nbtItem.getInteger("tier") - 1;
        // the "-1" prevents a pickaxe going from t3 to t4 and getting and enchantment it should get on t4 to t5

        HashMap<String, Integer> enchantments = new HashMap<>();
        enchantments.put("enchantmentDoubleOre", nbtItem.getInteger("enchantmentDoubleOre"));
        enchantments.put("enchantmentTripleOre", nbtItem.getInteger("enchantmentTripleOre"));
        enchantments.put("enchantmentMiningSuccess", nbtItem.getInteger("enchantmentMiningSuccess"));
        enchantments.put("enchantmentGemFind", nbtItem.getInteger("enchantmentGemFind"));
        enchantments.put("enchantmentTreasureFind", nbtItem.getInteger("enchantmentTreasureFind"));
        enchantments.put("enchantmentDurability", nbtItem.getInteger("enchantmentDurability"));
        for (int i = 1; i <= amountOfEnchantments; i++) {
            int currentTier2 = currentTier;
            currentTier2 -= i - 1;
            //this prevents creating a t5 pick with 4 enchants that can only be generated when the pickaxe turns into t5

            boolean definedNewEnchantment = false;
            while (!definedNewEnchantment) {
                int choosingEnchantment = randomlyChoosingNewEnchantment();

                if (choosingEnchantment == 1) { //double ore
                    int rollValue = pickDoubleOreEnum.getRandomValueByTier(currentTier2);
                    if (rollValue > enchantments.get("enchantmentDoubleOre")) {
                        enchantments.put("enchantmentDoubleOre", rollValue);
                        nbtItem.setInteger("enchantmentDoubleOre", rollValue);
                        definedNewEnchantment = true;
                    }
                }
                if (choosingEnchantment == 2) { //triple ore
                    int rollValue = pickTripleOreEnum.getRandomValueByTier(currentTier2);
                    if (rollValue > enchantments.get("enchantmentTripleOre")) {
                        enchantments.put("enchantmentTripleOre", rollValue);
                        nbtItem.setInteger("enchantmentTripleOre", rollValue);
                        definedNewEnchantment = true;
                    }
                }
                if (choosingEnchantment == 3) { //mining success
                    int rollValue = pickMiningSuccessEnum.getRandomValueByTier(currentTier2);
                    if (rollValue > enchantments.get("enchantmentMiningSuccess")) {
                        enchantments.put("enchantmentMiningSuccess", rollValue);
                        nbtItem.setInteger("enchantmentMiningSuccess", rollValue);
                        definedNewEnchantment = true;
                    }
                }
                if (choosingEnchantment == 4) { //gem find
                    int rollValue = pickGemFindEnum.getRandomValueByTier(currentTier2);
                    if (rollValue > enchantments.get("enchantmentGemFind")) {
                        enchantments.put("enchantmentGemFind", rollValue);
                        nbtItem.setInteger("enchantmentGemFind", rollValue);
                        definedNewEnchantment = true;
                    }
                }
                if (choosingEnchantment == 5) { //treasure find
                    if (currentTier2 >= 3) {
                        int rollValue = pickTreasureFindEnum.getRandomValueByTier(currentTier2);
                        if (rollValue > enchantments.get("enchantmentTreasureFind")) {
                            enchantments.put("enchantmentTreasureFind", rollValue);
                            nbtItem.setInteger("enchantmentTreasureFind", rollValue);
                            definedNewEnchantment = true;
                        }
                    }
                }
                if (choosingEnchantment == 6) { //durability
                    int rollValue = pickDurabilityEnum.getRandomValueByTier(currentTier2);
                    if (rollValue > enchantments.get("enchantmentDurability")) {
                        enchantments.put("enchantmentDurability", rollValue);
                        nbtItem.setInteger("enchantmentDurability", rollValue);
                        definedNewEnchantment = true;
                    }
                }
            }
        }
        return updatePickaxeLoreOnEnchantmentAdd(nbtItem.getItem());
    }

    public static ItemStack updatePickaxeLoreOnEnchantmentAdd(ItemStack pickaxe) {
        NBTItem nbtItem = new NBTItem(pickaxe);
        HashMap<String, Integer> hashmap = new HashMap<>();
        hashmap.put("Double ore: ", nbtItem.getInteger("enchantmentDoubleOre"));
        hashmap.put("Triple ore: ", nbtItem.getInteger("enchantmentTripleOre"));
        hashmap.put("Mining success: ", nbtItem.getInteger("enchantmentMiningSuccess"));
        hashmap.put("Gem find: ", nbtItem.getInteger("enchantmentGemFind"));
        hashmap.put("Treasure find: ", nbtItem.getInteger("enchantmentTreasureFind"));
        hashmap.put("Durability: ", nbtItem.getInteger("enchantmentDurability"));
        removeLoreAfter(pickaxe, "Double ore:");
        removeLoreAfter(pickaxe, "Triple ore:");
        removeLoreAfter(pickaxe, "Mining success:");
        removeLoreAfter(pickaxe, "Gem find:");
        removeLoreAfter(pickaxe, "Treasure find:");
        removeLoreAfter(pickaxe, "Durability:");
        for (Map.Entry<String, Integer> entry : hashmap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value > 0){
                addLore(pickaxe, "§c" + key + " " + value);
            }
        }
        return pickaxe;
    }
    public static void addLore(ItemStack itemStack, String additionalLore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = (itemMeta.hasLore()) ? new ArrayList<>(itemMeta.getLore()) : new ArrayList<>();
        lore.add(additionalLore);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

    public static void removeLore(ItemStack itemStack, String loreToBeRemoved){
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();

        for (int i = 1; i < lore.size(); i++){
            if (lore.get(i).equalsIgnoreCase(loreToBeRemoved)){
                lore.remove(loreToBeRemoved);
            }
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }
    public static void removeLoreAfter(ItemStack itemStack, String loreKeyword) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();
        if (lore != null) {
            Iterator<String> iterator = lore.iterator();
            boolean foundKeyword = false;
            while (iterator.hasNext()) {
                String currentLine = iterator.next();
                if (currentLine.contains(loreKeyword)) {
                    foundKeyword = true;
                }
                if (foundKeyword) {
                    iterator.remove();
                }
            }
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }
    }
}
