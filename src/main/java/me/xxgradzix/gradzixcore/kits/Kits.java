package me.xxgradzix.gradzixcore.kits;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.kits.commands.KitCommand;
import me.xxgradzix.gradzixcore.kits.commands.KitCreateCommand;
import me.xxgradzix.gradzixcore.kits.commands.KitEditorCommand;
import me.xxgradzix.gradzixcore.kits.data.DataManager;
import me.xxgradzix.gradzixcore.kits.data.database.entities.ItemInKitEntity;
import me.xxgradzix.gradzixcore.kits.data.database.entities.KitEntity;
import me.xxgradzix.gradzixcore.kits.data.database.managers.ItemInKitEntityManager;
import me.xxgradzix.gradzixcore.kits.data.database.managers.KitEntityManager;
import me.xxgradzix.gradzixcore.kits.managers.KitManager;

import java.sql.SQLException;

public class Kits {

    private KitEntityManager kitEntityManager;
    private ItemInKitEntityManager itemInKitEntityManager;

    private ConnectionSource connectionSource;

    private Gradzix_Core plugin;

    private DataManager dataManager;

    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, KitEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, ItemInKitEntity.class);
        kitEntityManager = new KitEntityManager(connectionSource);
        itemInKitEntityManager = new ItemInKitEntityManager(connectionSource);
        dataManager = new DataManager(kitEntityManager, itemInKitEntityManager);
    }
    public Kits(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new KitManager(dataManager);
        plugin.getCommand("createkit").setExecutor(new KitCreateCommand());
        plugin.getCommand("kit").setExecutor(new KitCommand());
        plugin.getCommand("kiteditor").setExecutor(new KitEditorCommand());

    }

}
