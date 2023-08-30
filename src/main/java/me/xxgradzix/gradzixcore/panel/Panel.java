package me.xxgradzix.gradzixcore.panel;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.panel.commands.PanelCommand;
import me.xxgradzix.gradzixcore.panel.data.configfiles.PanelAdminConfigFile;
import me.xxgradzix.gradzixcore.panel.data.database.entities.PanelOptionsEntity;
import me.xxgradzix.gradzixcore.panel.data.database.managers.PanelOptionsEntityManager;
import me.xxgradzix.gradzixcore.panel.items.ItemManager;
import me.xxgradzix.gradzixcore.panel.listeners.ChatEvent;
import me.xxgradzix.gradzixcore.panel.listeners.KityBlock;
import me.xxgradzix.gradzixcore.panel.listeners.OsiagnieciaBlock;

import java.sql.SQLException;

public class Panel {

    private Gradzix_Core plugin;
    // db change
    private ConnectionSource connectionSource;

    private static PanelOptionsEntityManager panelOptionsEntityManager;

    public static PanelOptionsEntityManager getPanelOptionsEntityManager() {
        return panelOptionsEntityManager;
    }
    ////////////
    public Panel(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, PanelOptionsEntity.class);
        panelOptionsEntityManager= new PanelOptionsEntityManager(connectionSource);
    }

    //////////////////

    public void onEnable() {

        try {
            configureDB();

//            getPanelOptionsEntityManager().createOrUpdatePanelOptionsEntity(new PanelOptionsEntity(true, true, true, true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ItemManager.init();

        plugin.getServer().getPluginManager().registerEvents(new ChatEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new KityBlock(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OsiagnieciaBlock(), plugin);
        plugin.getCommand("panel").setExecutor(new PanelCommand());

        PanelAdminConfigFile.setup();
        PanelAdminConfigFile.getCustomFile().addDefault("isChatEnabled", true);
        PanelAdminConfigFile.getCustomFile().addDefault("isZdrapkaEnabled", true);
        PanelAdminConfigFile.getCustomFile().addDefault("isKityEnabled", true);
        PanelAdminConfigFile.getCustomFile().addDefault("isOsiagnieciaEnabled", true);
        PanelAdminConfigFile.getCustomFile().options().copyDefaults(true);
        PanelAdminConfigFile.save();

    }

}
