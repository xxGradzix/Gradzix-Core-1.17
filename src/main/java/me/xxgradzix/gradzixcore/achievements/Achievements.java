package me.xxgradzix.gradzixcore.achievements;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.achievements.database.DataManager;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementsProgressEntity;
import me.xxgradzix.gradzixcore.achievements.database.managers.PlayerAchievementProgressEntityManager;


import java.sql.SQLException;

public class Achievements {

    private final Gradzix_Core plugin;
    private final ConnectionSource connectionSource;

    private static PlayerAchievementProgressEntityManager playerAchievementProgressEntityManager;

    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, PlayerAchievementsProgressEntity.class);
        playerAchievementProgressEntityManager = new PlayerAchievementProgressEntityManager(connectionSource);
    }
    public Achievements(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    private static DataManager dataManager;

    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dataManager = new DataManager(playerAchievementProgressEntityManager);
//        plugin.getServer().getPluginManager().registerEvents(new BlockGrief(), plugin);

//        plugin.getCommand("osiagniecia").setExecutor(new UpgradeShopCommand(dataManager));

    }

}
