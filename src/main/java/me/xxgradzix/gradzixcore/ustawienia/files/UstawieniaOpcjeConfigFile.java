package me.xxgradzix.gradzixcore.ustawienia.files;

import me.xxgradzix.gradzixcore.chatopcje.files.ChatOpcjeConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UstawieniaOpcjeConfigFile {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Gradzix-Core").getDataFolder(), "ustawieniaOptions.yml");
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


    // auto wymiana
    public static void setAutoWymianaStatus(Player player, boolean b) {
        getCustomFile().set("players." + player.getUniqueId().toString() + ".autoWymiana", b);
        save();
    }


    public static boolean getAutoWymianaStatus(Player player) {
        return getCustomFile().getBoolean("players." + player.getUniqueId().toString() + ".autoWymiana");
    }




    public static List<String> getAutoWymianaStatusUUIDsList(boolean expectedValue) {
        List<String> uuidList = new ArrayList<>();

        ConfigurationSection playersSection = ChatOpcjeConfigFile.getCustomFile().getConfigurationSection("players");
        if (playersSection != null) {
            for (String key : playersSection.getKeys(false)) {
                ConfigurationSection playerSection = playersSection.getConfigurationSection(key);
                if (playerSection != null) {
                    boolean autoWymiana = playerSection.getBoolean("autoWymiana");
                    if (autoWymiana == expectedValue) {
                        uuidList.add(key);
                    }
                }
            }
        }
        return uuidList;
    }

    // autosprzedaz

    public static void setAutoSprzedazStatus(Player player, boolean b) {
        getCustomFile().set("players." + player.getUniqueId().toString() + ".autoSprzedaz", b);
        save();
    }


    public static boolean getAutoSprzedazStatus(Player player) {
        return getCustomFile().getBoolean("players." + player.getUniqueId().toString() + ".autoSprzedaz");
    }


}
