package me.xxgradzix.gradzixcore.achievements.database.managers;

import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.abstraction.BasicEntityManager;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementEntity;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementsProgressEntity;

import java.util.UUID;

public class PlayerAchievementEntityManager extends BasicEntityManager<PlayerAchievementEntity, Long> {
    public PlayerAchievementEntityManager(ConnectionSource connectionSource) {
        super(connectionSource, PlayerAchievementEntity.class);
    }
}
