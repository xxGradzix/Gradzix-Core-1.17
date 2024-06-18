package me.xxgradzix.gradzixcore.achievements.database;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementsProgressEntity;
import me.xxgradzix.gradzixcore.achievements.database.managers.PlayerAchievementProgressEntityManager;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DataManager {

    private static final boolean useDB = Gradzix_Core.USE_DB;



    private PlayerAchievementProgressEntityManager playerAchievementProgressEntityManager;

    public DataManager(PlayerAchievementProgressEntityManager playerAchievementProgressEntityManager) {
        this.playerAchievementProgressEntityManager = playerAchievementProgressEntityManager;
    }

    public void createPlayerAchievementsIfNotExists(Player player) {
        if (useDB) {
            try {
                PlayerAchievementsProgressEntity villagerUpgradeShop = PlayerAchievementsProgressEntity.builder()
                        .id(player.getUniqueId())
                        .nickname(player.getName())
                        .build();
                playerAchievementProgressEntityManager.createEntityIfNotExists(villagerUpgradeShop);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getAchievements(Player player) {
        if (useDB) {
            try {
                playerAchievementProgressEntityManager.getEntityById(player.getUniqueId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void updateAchievements(PlayerAchievementsProgressEntity playerAchievementsProgressEntity) {
        if (useDB) {
            try {
                playerAchievementProgressEntityManager.createOrUpdateEntity(playerAchievementsProgressEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

