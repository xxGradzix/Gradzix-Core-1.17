package me.xxgradzix.gradzixcore.zdrapka;


import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.zdrapka.commands.GiveZdrapkaCommand;
import me.xxgradzix.gradzixcore.zdrapka.commands.ZdrapkaCommand;
import me.xxgradzix.gradzixcore.zdrapka.data.database.entities.ScratchCardEntity;
import me.xxgradzix.gradzixcore.zdrapka.data.database.managers.ScratchCardEntityManager;
import me.xxgradzix.gradzixcore.zdrapka.items.ItemManager;
import me.xxgradzix.gradzixcore.zdrapka.listeners.OnLeftClick;
import me.xxgradzix.gradzixcore.zdrapka.listeners.OnRightClick;

import java.sql.SQLException;

public final class Zdrapka {

    private Gradzix_Core plugin;
    // db change
    private ConnectionSource connectionSource;

    private static ScratchCardEntityManager scratchCardEntityManager;

    public static ScratchCardEntityManager getScratchCardEntityManager() {
        return scratchCardEntityManager;
    }
    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, ScratchCardEntity.class);
        scratchCardEntityManager = new ScratchCardEntityManager(connectionSource);
    }
    ////////////

    public Zdrapka(Gradzix_Core plugin, ConnectionSource connectionSource) {
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

        plugin.getCommand("givezdrapka").setExecutor(new GiveZdrapkaCommand());
        plugin.getCommand("zdrapka").setExecutor(new ZdrapkaCommand());

        plugin.getServer().getPluginManager().registerEvents(new OnLeftClick(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnRightClick(), plugin);

//        ZdrapkaConfigFile.setup();
//
//        List<ItemStack> list = new ArrayList<ItemStack>();
//
//        ZdrapkaConfigFile.getCustomFile().addDefault("items", list);
//
//        ZdrapkaConfigFile.getCustomFile().options().copyDefaults(true);
//        ZdrapkaConfigFile.save();
    }


    public void onDisable() {
        // Plugin shutdown logic
    }


}
