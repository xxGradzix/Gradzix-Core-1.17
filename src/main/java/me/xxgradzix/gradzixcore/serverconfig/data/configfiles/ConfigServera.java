package me.xxgradzix.gradzixcore.serverconfig.data.configfiles;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigServera {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Gradzix-Core").getDataFolder(), "serverConfig.yml");
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



    public static double getDamageMultiplier() {

        return getCustomFile().getDouble("damageMultiplier");

    }


    public static void setDamageMultiplier(double multiplier) {
        getCustomFile().set("damageMultiplier", multiplier);
        save();
    }

    public static ArrayList<ItemStack> getItemPriorities() {

        return (ArrayList<ItemStack>) getCustomFile().getList("itemPriorities");

    }


    public static void setItemPriorities(ArrayList<ItemStack> itemPriorities) {
        getCustomFile().set("itemPriorities", itemPriorities);
        save();
    }


}
