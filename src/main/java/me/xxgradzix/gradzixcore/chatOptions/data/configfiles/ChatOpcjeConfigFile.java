package me.xxgradzix.gradzixcore.chatOptions.data.configfiles;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatOpcjeConfigFile {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Gradzix-Core").getDataFolder(), "playerChatOptions.yml");
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

    // death messages

    public static void setShowDeathMessage(Player player, boolean b) {
        getCustomFile().set("players." + player.getUniqueId().toString() + ".hideDeathMessage", b);
        save();
    }

    public static boolean getShowDeathMessageStatus(Player player) {
        return getCustomFile().getBoolean("players." + player.getUniqueId().toString() + ".hideDeathMessage");
    }

    public static List<String> getShowDeathMessageStatusUUIDsList(boolean expectedValue) {
            List<String> uuidList = new ArrayList<>();

            ConfigurationSection playersSection = ChatOpcjeConfigFile.getCustomFile().getConfigurationSection("players");
            if (playersSection != null) {
                for (String key : playersSection.getKeys(false)) {
                    ConfigurationSection playerSection = playersSection.getConfigurationSection(key);
                    if (playerSection != null) {
                        boolean hideDeathMessage = playerSection.getBoolean("hideDeathMessage");
                        if (hideDeathMessage == expectedValue) {
                            uuidList.add(key);
                        }
                    }
                }
            }
            return uuidList;
    }

    // scratch card messages

    public static List<String> getShowScratchCardsMessageStatusList(boolean expectedValue) {
        List<String> uuidList = new ArrayList<>();

        ConfigurationSection playersSection = ChatOpcjeConfigFile.getCustomFile().getConfigurationSection("players");
        if (playersSection != null) {
            for (String key : playersSection.getKeys(false)) {
                ConfigurationSection playerSection = playersSection.getConfigurationSection(key);
                if (playerSection != null) {
                    boolean hideZdrapkaMessage = playerSection.getBoolean("hideZdrapkaMessage");
                    if (hideZdrapkaMessage == expectedValue) {
                        uuidList.add(key);
                    }
                }
            }
        }
        return uuidList;
    }

    public static void setShowScratchCardMessages(Player player, boolean b) {
        getCustomFile().set("players." + player.getUniqueId().toString() + ".hideScratchCardMessages", b);
        save();
    }

    public static boolean getShowScratchCardMessages(Player player) {
        return getCustomFile().getBoolean("players." + player.getUniqueId().toString() + ".hideScratchCardMessages");
    }

    // chat messages

    public static List<String> getShowChatMessageStatusUUIDsList(boolean expectedValue) {
        List<String> uuidList = new ArrayList<>();

        ConfigurationSection playersSection = ChatOpcjeConfigFile.getCustomFile().getConfigurationSection("players");
        if (playersSection != null) {
            for (String key : playersSection.getKeys(false)) {
                ConfigurationSection playerSection = playersSection.getConfigurationSection(key);
                if (playerSection != null) {
                    boolean hideChatMessage = playerSection.getBoolean("hideChatMessage");
                    if (hideChatMessage == expectedValue) {
                        uuidList.add(key);
                    }
                }
            }
        }

        return uuidList;
    }

    public static void setShowChatMessage(Player player, boolean b) {
        getCustomFile().set("players." + player.getUniqueId().toString() + ".hideChatMessage", b);
        save();
    }

    public static boolean getShowChatMessageStatus(Player player) {
        return getCustomFile().getBoolean("players." + player.getUniqueId().toString() + ".hideChatMessage");
    }

    // shop messages

    public static List<String> getShowShopMessageStatusUUIDsList(boolean expectedValue) {
        List<String> uuidList = new ArrayList<>();

        ConfigurationSection playersSection = ChatOpcjeConfigFile.getCustomFile().getConfigurationSection("players");
        if (playersSection != null) {
            for (String key : playersSection.getKeys(false)) {
                ConfigurationSection playerSection = playersSection.getConfigurationSection(key);
                if (playerSection != null) {
                    boolean hideShopMessage = playerSection.getBoolean("hideShopMessage");
                    if (hideShopMessage == expectedValue) {
                        uuidList.add(key);
                    }
                }
            }
        }

        return uuidList;
    }

    public static void setShowShopMessage(Player player, boolean b) {
        getCustomFile().set("players." + player.getUniqueId().toString() + ".hideShopMessage", b);
        save();
    }

    public static boolean getShowShopMessageStatus(Player player) {
        return getCustomFile().getBoolean("players." + player.getUniqueId().toString() + ".hideShopMessage");
    }


}
