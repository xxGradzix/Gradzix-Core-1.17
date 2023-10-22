package me.xxgradzix.gradzixcore.upgradeItem;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.upgradeItem.commands.UpgradeCommand;
import me.xxgradzix.gradzixcore.upgradeItem.commands.UpgradeConfigCommand;
import me.xxgradzix.gradzixcore.upgradeItem.data.database.entities.UpgradeEntity;
import me.xxgradzix.gradzixcore.upgradeItem.data.database.managers.UpgradeEntityManager;
import me.xxgradzix.gradzixcore.upgradeItem.files.UpgradeConfigFile;
import me.xxgradzix.gradzixcore.upgradeItem.gui.UpgradeGuiClick;
import me.xxgradzix.gradzixcore.upgradeItem.gui.UpgradeGuiClose;

import java.sql.SQLException;

public class Ulepsz {


    private Gradzix_Core plugin;

    // db change
    private ConnectionSource connectionSource;

    private static UpgradeEntityManager upgradeEntityManager;

    public static UpgradeEntityManager getUpgradeEntityManager() {
        return upgradeEntityManager;
    }

    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, UpgradeEntity.class);
        upgradeEntityManager = new UpgradeEntityManager(connectionSource);
    }
    ////////////

    public Ulepsz(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        plugin.getCommand("ulepsz").setExecutor(new UpgradeCommand());
        plugin.getCommand("ulepszconfig").setExecutor(new UpgradeConfigCommand());


        plugin.getServer().getPluginManager().registerEvents(new UpgradeGuiClick(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new UpgradeGuiClose(), plugin);

        UpgradeConfigFile.setup();


        UpgradeConfigFile.getCustomFile().options().copyDefaults(true);
        UpgradeConfigFile.save();

    }

}
