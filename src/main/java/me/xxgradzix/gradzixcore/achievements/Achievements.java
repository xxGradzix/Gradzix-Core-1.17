package me.xxgradzix.gradzixcore.achievements;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.achievements.commands.AchievementsCommand;
import me.xxgradzix.gradzixcore.achievements.database.DataManager;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementEntity;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementsProgressEntity;
import me.xxgradzix.gradzixcore.achievements.database.managers.PlayerAchievementEntityManager;
import me.xxgradzix.gradzixcore.achievements.database.managers.PlayerAchievementProgressEntityManager;
import me.xxgradzix.gradzixcore.achievements.listeners.AchievementListener;
import me.xxgradzix.gradzixcore.achievements.listeners.OnJoinInitializePlayerAchievements;
import me.xxgradzix.gradzixcore.achievements.managers.AchievementManager;


import java.sql.SQLException;

public class Achievements {

    public static final Integer BLOCKS_BROKEN_GOAL = 100;
    public static final Integer BLOCKS_PLACED_GOAL = 100;
    public static final Integer PLAYERS_KILLED_GOAL = 10;
    public static final Integer TOTEM_OF_UNDYING_GOAL = 5;

    public static Integer getGoal(AchievementType type) {
        switch (type) {
            case BLOCKS_BROKEN:
                return BLOCKS_BROKEN_GOAL;
            case BLOCKS_PLACED:
                return BLOCKS_PLACED_GOAL;
            case PLAYERS_KILLED:
                return PLAYERS_KILLED_GOAL;
            case TOTEM_OF_UNDYING:
                return TOTEM_OF_UNDYING_GOAL;
            default:
                return 0;
        }
    }

    private final Gradzix_Core plugin;
    private final ConnectionSource connectionSource;

    private static PlayerAchievementProgressEntityManager playerAchievementProgressEntityManager;
    private static PlayerAchievementEntityManager playerAchievementEntityManager;

    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, PlayerAchievementsProgressEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, PlayerAchievementEntity.class);
        playerAchievementProgressEntityManager = new PlayerAchievementProgressEntityManager(connectionSource);
        playerAchievementEntityManager = new PlayerAchievementEntityManager(connectionSource);
    }
    public Achievements(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    private DataManager dataManager;
    private AchievementManager achievementManager;

    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dataManager = new DataManager(playerAchievementProgressEntityManager, playerAchievementEntityManager);

        achievementManager = new AchievementManager(dataManager);

        plugin.getServer().getPluginManager().registerEvents(new AchievementListener(achievementManager), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnJoinInitializePlayerAchievements(dataManager, achievementManager), plugin);

        plugin.getCommand("osiagniecia").setExecutor(new AchievementsCommand(achievementManager, dataManager));

    }

    public void onDisable() {
        if(achievementManager != null) achievementManager.saveAllAchievements();
    }

}
