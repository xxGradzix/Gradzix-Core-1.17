package me.xxgradzix.gradzixcore.playerAbilities.data.configfiles;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class AbilitiesModifiersConfigFile {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Gradzix-Core").getDataFolder(), "modyfikatoryUmiejetnosci.yml");
        if(!file.exists()) {
            try{
                file.createNewFile();
            } catch (IOException ignored) {

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

    // strength

    public static double getSilaMultiplier(int level) {
        return (double) getCustomFile().get("umiejetnosci.sila." + level);
    }

    public static void setSilaLevel(int level, double multiplier) {

        getCustomFile().set("umiejetnosci.sila." + level, multiplier);
        save();

    }

    // drop

    public static double getDropMultiplier(int level) {
        return getCustomFile().getDouble("umiejetnosci.drop." + level);
    }

    public static void setDropLevel(int level, double multiplier) {

        getCustomFile().set("umiejetnosci.drop." + level, multiplier);
        save();

    }

    // rank
    public static double getRankMultiplier(int level) {
        return getCustomFile().getDouble("umiejetnosci.rank." + level);
    }

    public static void setRankLevel(int level, double multiplier) {

        getCustomFile().set("umiejetnosci.rank." + level, multiplier);
        save();

    }


}
