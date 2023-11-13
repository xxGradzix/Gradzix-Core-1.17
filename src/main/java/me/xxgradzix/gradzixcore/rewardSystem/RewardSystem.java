package me.xxgradzix.gradzixcore.rewardSystem;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.rewardSystem.commands.CollectRewardsCommand;
import me.xxgradzix.gradzixcore.rewardSystem.database.entities.PlayerRewardsEntity;
import me.xxgradzix.gradzixcore.rewardSystem.database.managers.PlayerRewardsEntityManager;
import me.xxgradzix.gradzixcore.rewardSystem.managers.RewardManager;

import java.sql.SQLException;

public class RewardSystem {



    private final Gradzix_Core plugin;

    // db change
    private ConnectionSource connectionSource;


    private PlayerRewardsEntityManager playerRewardsEntityManager;

    public static RewardManager rewardManager;
    public PlayerRewardsEntityManager playerRewardsEntityManager() {
        return playerRewardsEntityManager;
    }
    public RewardManager getRewardManager() {
        return rewardManager;
    }
    public RewardSystem(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }
    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, PlayerRewardsEntity.class);
        this.playerRewardsEntityManager = new PlayerRewardsEntityManager(connectionSource);
    }

    public void onEnable() {
        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        rewardManager = new RewardManager(playerRewardsEntityManager);
        plugin.getCommand("odbierz").setExecutor(new CollectRewardsCommand(playerRewardsEntityManager, rewardManager));

    }

}
