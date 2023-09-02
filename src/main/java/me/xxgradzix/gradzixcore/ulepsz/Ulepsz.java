package me.xxgradzix.gradzixcore.ulepsz;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.ulepsz.commands.UlepszCommand;
import me.xxgradzix.gradzixcore.ulepsz.commands.UlepszConfigCommand;
import me.xxgradzix.gradzixcore.ulepsz.data.database.entities.UpgradeEntity;
import me.xxgradzix.gradzixcore.ulepsz.data.database.managers.UpgradeEntityManager;
import me.xxgradzix.gradzixcore.ulepsz.files.UlepszConfigFile;
import me.xxgradzix.gradzixcore.ulepsz.gui.UlepszGuiClick;
import me.xxgradzix.gradzixcore.ulepsz.gui.UlepszGuiClose;

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


        plugin.getCommand("ulepsz").setExecutor(new UlepszCommand());
        plugin.getCommand("ulepszconfig").setExecutor(new UlepszConfigCommand());


        plugin.getServer().getPluginManager().registerEvents(new UlepszGuiClick(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new UlepszGuiClose(), plugin);

        UlepszConfigFile.setup();


//        UlepszConfigFile.getCustomFile().addDefault("items." + slot0 + ".currentItem", currentItem);
//        UlepszConfigFile.getCustomFile().addDefault("items." + slot0 + ".nextItem", nextItem);
//        UlepszConfigFile.getCustomFile().addDefault("items." + slot0 + ".itemRequired", itemRequired);
//
//        UlepszConfigFile.getCustomFile().addDefault("items." + slot1 + ".currentItem", currentItem2);
//        UlepszConfigFile.getCustomFile().addDefault("items." + slot1 + ".nextItem", nextItem2);
//        UlepszConfigFile.getCustomFile().addDefault("items." + slot1 + ".itemRequired", itemRequired2);



        UlepszConfigFile.getCustomFile().options().copyDefaults(true);
        UlepszConfigFile.save();

    }

}
