package me.xxgradzix.gradzixcore.rewardSystem;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.rewardSystem.commands.CollectRewardsCommand;
import me.xxgradzix.gradzixcore.rewardSystem.database.entities.PlayerRewardsEntity;
import me.xxgradzix.gradzixcore.rewardSystem.database.managers.PlayerRewardsEntityManager;

import java.sql.SQLException;

public class RewardSystem {



    private Gradzix_Core plugin;

    // db change
    private ConnectionSource connectionSource;


    private PlayerRewardsEntityManager playerRewardsEntityManager;
    public PlayerRewardsEntityManager playerRewardsEntityManager() {
        return playerRewardsEntityManager;
    }
    public RewardSystem(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }
    public void configureDB() throws SQLException {
        /* DATABASE LOCAL */
        String databaseUrl = "jdbc:mysql://localhost:3306/rewardSystem";
        connectionSource = new JdbcConnectionSource(databaseUrl, "root", "");

        /* DATABASE AGEPLAY */
//        String databaseUrl = "jdbc:mysql://185.16.39.57:3306/s286_database";
//        connectionSource = new JdbcConnectionSource(databaseUrl, "u286_f8T7gXXzU1", "a65qmwbgH8Y@cg3dXm^qgSm6");

        TableUtils.createTableIfNotExists(connectionSource, PlayerRewardsEntity.class);

        this.playerRewardsEntityManager = new PlayerRewardsEntityManager(connectionSource);

    }

    public void onEnable() {
        // Plugin startup logic

        plugin.getCommand("odbierz").setExecutor(new CollectRewardsCommand(playerRewardsEntityManager));

    }

}
