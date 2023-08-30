package me.xxgradzix.gradzixcore.chatopcje;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.chatopcje.commands.ChatCommands;
import me.xxgradzix.gradzixcore.chatopcje.data.database.entities.ChatOptionsEntity;
import me.xxgradzix.gradzixcore.chatopcje.items.ItemManager;
import me.xxgradzix.gradzixcore.chatopcje.listeners.OnPlayerChat;
import me.xxgradzix.gradzixcore.chatopcje.listeners.PlayerDeathMessage;
import me.xxgradzix.gradzixcore.chatopcje.data.database.managers.ChatOptionsEntityManager;

import java.sql.SQLException;

public final class Chatopcje {

    private Gradzix_Core plugin;
    // db change
    private ConnectionSource connectionSource;
    private static ChatOptionsEntityManager chatOptionsEntityManager;

    public static ChatOptionsEntityManager getChatOptionsEntityManager() {
        return chatOptionsEntityManager;
    }
    ////////////

    public Chatopcje(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }


    // database method
    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, ChatOptionsEntity.class);
        chatOptionsEntityManager= new ChatOptionsEntityManager(connectionSource);
    }

    //////////////////
    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ItemManager.init();

        plugin.getCommand("chatopcje").setExecutor(new ChatCommands());

        plugin.getServer().getPluginManager().registerEvents(new PlayerDeathMessage(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnPlayerChat(), plugin);


//        List<String> list = new ArrayList<>();
//
//        ChatOpcjeConfigFile.setup();
//        ChatOpcjeConfigFile.getCustomFile().addDefault("players", list);
//        ChatOpcjeConfigFile.getCustomFile().options().copyDefaults(true);
//        ChatOpcjeConfigFile.save();

    }

    public void onDisable() {
        // Plugin shutdown logic
    }
}
