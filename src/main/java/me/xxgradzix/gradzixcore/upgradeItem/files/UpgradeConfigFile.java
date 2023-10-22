package me.xxgradzix.gradzixcore.upgradeItem.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UpgradeConfigFile {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Gradzix-Core").getDataFolder(), "ulepsz.yml");
        if(!file.exists()) {
            try{
                file.createNewFile();
            } catch (IOException e) {
                //
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getCustomFile() {
        return customFile;
    }

    public static void save() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            System.out.println("Couldn't save file");
        }
    }
    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static ItemStack findNextItem(ItemStack currentItem) {
        FileConfiguration config = getCustomFile();
        ConfigurationSection itemsSection = config.getConfigurationSection("items");

        if (itemsSection != null) {
            for (String key : itemsSection.getKeys(false)) {
                ConfigurationSection itemSection = itemsSection.getConfigurationSection(key);


                ItemStack item = itemSection.getItemStack("currentItem");


                if (item != null && item.equals(currentItem)) {

                    ItemStack nextItem = itemSection.getItemStack("nextItem");
                    return nextItem;
                }
            }
        }

        return null;
    }

    public static ItemStack findRequiredItem(ItemStack currentItem) {
        FileConfiguration config = getCustomFile();
        ConfigurationSection itemsSection = config.getConfigurationSection("items");

        if (itemsSection != null) {
            for (String key : itemsSection.getKeys(false)) {
                ConfigurationSection itemSection = itemsSection.getConfigurationSection(key);


                ItemStack item = itemSection.getItemStack("currentItem");


                if (item != null && item.equals(currentItem)) {

                    ItemStack requiredItem = itemSection.getItemStack("itemRequired");
                    return requiredItem;
                }
            }
        }

        return null;
    }
    public static List<ItemStack[]> getAllItems() {
        FileConfiguration config = getCustomFile();
        ConfigurationSection itemsSection = config.getConfigurationSection("items");
        List<ItemStack[]> itemStackList = new ArrayList<>();

        if (itemsSection != null) {
            Set<String> keys = itemsSection.getKeys(false);

            for (String key : keys) {
                ConfigurationSection itemSection = itemsSection.getConfigurationSection(key);

                ItemStack currentItem = itemSection.getItemStack("currentItem");
                ItemStack nextItem = itemSection.getItemStack("nextItem");
                ItemStack requiredItem = itemSection.getItemStack("itemRequired");

                ItemStack[] itemStackArray = new ItemStack[]{currentItem, requiredItem, nextItem};
                itemStackList.add(itemStackArray);
            }
        }

        return itemStackList;
    }

    private static void reset(){
        getCustomFile().set("items", null);
        save();
    }
    public static void setAllItems(List<ItemStack[]> itemStacksList) {


        reset();

        int point = 0;
        for(ItemStack[] itemStacks : itemStacksList) {

            if(itemStacks == null) continue;

            getCustomFile().set("items." + point + ".currentItem", itemStacks[0]);
            getCustomFile().set("items." + point + ".itemRequired", itemStacks[1]);
            getCustomFile().set("items." + point + ".nextItem", itemStacks[2]);
            save();

            point++;
        }

    }



}
