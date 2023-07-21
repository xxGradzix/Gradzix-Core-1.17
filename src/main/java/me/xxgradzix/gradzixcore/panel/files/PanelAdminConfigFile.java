package me.xxgradzix.gradzixcore.panel.files;

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

    // zdrapka

    public static boolean getZdrapkaStatus() {

        return getCustomFile().getBoolean("isZdrapkaEnabled");

    }


    public static void setZdrapkaStatus(boolean status) {
        getCustomFile().set("isZdrapkaEnabled", status);
        save();
    }

    // kity

    public static boolean getKityStatus() {

        return getCustomFile().getBoolean("isKityEnabled");

    }


    public static void setKityStatus(boolean status) {
        getCustomFile().set("isKityEnabled", status);
        save();
    }

    // osiagniecia
    public static boolean getOsiagnieciaStatus() {

        return getCustomFile().getBoolean("isOsiagnieciaEnabled");

    }


    public static void setOsiagnieciaStatus(boolean status) {
        getCustomFile().set("isOsiagnieciaEnabled", status);
        save();
    }

}
