package me.xxgradzix.gradzixcore.umiejetnosci.data.configfiles;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class UmiejetnosciConfigFile {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Gradzix-Core").getDataFolder(), "umiejetnosci.yml");
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

    // sila
    public static int getSilaLevel(Player player) {
        return getCustomFile().getInt("umiejetnosci." + player.getUniqueId().toString() + ".sila");
    }

    public static void incrementSilaLevel(Player player) {

        getCustomFile().set("umiejetnosci." + player.getUniqueId().toString() + ".sila", getCustomFile().getInt("umiejetnosci." + player.getUniqueId().toString() + ".sila") + 1);
        save();
    }

    // drop

    public static int getDropLevel(Player player) {
        return getCustomFile().getInt("umiejetnosci." + player.getUniqueId().toString() + ".drop");
    }

    public static void incrementDropLevel(Player player) {

        getCustomFile().set("umiejetnosci." + player.getUniqueId().toString() + ".drop", getCustomFile().getInt("umiejetnosci." + player.getUniqueId().toString() + ".drop") + 1);
        save();
    }

    // rank

    public static int getRankLevel(Player player) {
        return getCustomFile().getInt("umiejetnosci." + player.getUniqueId().toString() + ".rank");
    }

    public static void incrementRankLevel(Player player) {

        getCustomFile().set("umiejetnosci." + player.getUniqueId().toString() + ".rank", getCustomFile().getInt("umiejetnosci." + player.getUniqueId().toString() + ".rank") + 1);
        save();
    }


    public static void resetLevels() {
        getCustomFile().set("umiejetnosci", null);
        save();
    }
}
