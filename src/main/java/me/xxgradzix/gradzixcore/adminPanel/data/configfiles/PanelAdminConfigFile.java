package me.xxgradzix.gradzixcore.adminPanel.data.configfiles;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PanelAdminConfigFile {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Gradzix-Core").getDataFolder(), "ustawieniaAdmin.yml");
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

    // global chat

    public static boolean getChatStatus() {

        return getCustomFile().getBoolean("isChatEnabled");

    }


    public static void setChatStatus(boolean status) {
        getCustomFile().set("isChatEnabled", status);
        save();
    }

    // scratch card

    public static boolean getScratchCardStatus() {

        return getCustomFile().getBoolean("isZdrapkaEnabled");

    }


    public static void setScratchCardStatus(boolean status) {
        getCustomFile().set("isScratchCardEnabled", status);
        save();
    }

    // kits

    public static boolean getKitsStatus() {

        return getCustomFile().getBoolean("isKitsEnabled");

    }


    public static void setKitsStatus(boolean status) {
        getCustomFile().set("isKitsEnabled", status);
        save();
    }

    // achievements
    public static boolean getAchievementsStatus() {

        return getCustomFile().getBoolean("isAchievementsEnabled");

    }

    public static void setAchievementsStatus(boolean status) {
        getCustomFile().set("isAchievementsEnabled", status);
        save();
    }

}
