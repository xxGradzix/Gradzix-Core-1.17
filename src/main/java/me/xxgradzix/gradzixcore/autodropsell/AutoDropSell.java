package me.xxgradzix.gradzixcore.autodropsell;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.autodropsell.commands.AutoSellCommand;
import me.xxgradzix.gradzixcore.autodropsell.commands.MinerCommand;
import me.xxgradzix.gradzixcore.autodropsell.listeners.BlockBreakAutoSellEvent;
import me.xxgradzix.gradzixcore.playerSettings.data.database.entities.AutoSellEntity;
import me.xxgradzix.gradzixcore.playerSettings.data.database.managers.AutoSellEntityManager;

import java.sql.SQLException;

public class AutoDropSell {

    private final Gradzix_Core plugin;

    private final ConnectionSource connectionSource;


    private static AutoSellEntityManager autoSellEntityManager;

    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, AutoSellEntity.class);
        autoSellEntityManager = new AutoSellEntityManager(connectionSource);
    }

    public AutoDropSell(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public void onEnable() {
        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        plugin.getCommand("autosprzedaz").setExecutor(new AutoSellCommand(autoSellEntityManager));
        plugin.getCommand("gornik").setExecutor(new MinerCommand(autoSellEntityManager));
        plugin.getServer().getPluginManager().registerEvents(new BlockBreakAutoSellEvent(autoSellEntityManager), plugin);

    }



}
