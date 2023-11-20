package me.xxgradzix.gradzixcore.adminPanel;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.adminPanel.commands.PanelCommand;
import me.xxgradzix.gradzixcore.adminPanel.data.database.entities.PanelOptionsEntity;
import me.xxgradzix.gradzixcore.adminPanel.data.database.managers.PanelOptionsEntityManager;
import me.xxgradzix.gradzixcore.adminPanel.items.ItemManager;
import me.xxgradzix.gradzixcore.adminPanel.listeners.ChatEvent;
import me.xxgradzix.gradzixcore.adminPanel.listeners.KityBlock;
import me.xxgradzix.gradzixcore.adminPanel.listeners.OsiagnieciaBlock;

import java.sql.SQLException;

public class Panel {

    private final Gradzix_Core plugin;
    // db change
    private final ConnectionSource connectionSource;

    private static PanelOptionsEntityManager panelOptionsEntityManager;

    public static PanelOptionsEntityManager getPanelOptionsEntityManager() {
        return panelOptionsEntityManager;
    }

    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, PanelOptionsEntity.class);
        panelOptionsEntityManager= new PanelOptionsEntityManager(connectionSource);
    }

    public Panel(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ItemManager.init();

        plugin.getServer().getPluginManager().registerEvents(new ChatEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new KityBlock(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OsiagnieciaBlock(), plugin);
        plugin.getCommand("panel").setExecutor(new PanelCommand());


    }

}
