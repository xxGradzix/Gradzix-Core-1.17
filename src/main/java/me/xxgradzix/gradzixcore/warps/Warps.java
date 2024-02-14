package me.xxgradzix.gradzixcore.warps;

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
import me.xxgradzix.gradzixcore.warps.commands.WarpCommand;
import me.xxgradzix.gradzixcore.warps.items.ItemManager;

import java.sql.SQLException;

public class Warps {

    private Gradzix_Core plugin;

    // db change
    private ConnectionSource connectionSource;
//
//    private static UpgradeEntityManager upgradeEntityManager;
//
//    public static UpgradeEntityManager getUpgradeEntityManager() {
//        return upgradeEntityManager;
//    }
//
//    public void configureDB() throws SQLException {
//        TableUtils.createTableIfNotExists(connectionSource, UpgradeEntity.class);
//        upgradeEntityManager = new UpgradeEntityManager(connectionSource);
//    }
    ////////////

    public Warps(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public void onEnable() {

        ItemManager.init();
        plugin.getCommand("warp").setExecutor(new WarpCommand());
//        plugin.getServer().getPluginManager().registerEvents(new UpgradeGuiClick(), plugin);

    }

}
