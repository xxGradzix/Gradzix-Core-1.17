package me.xxgradzix.gradzixcore.achievements.database.managers;

import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.abstraction.BasicEntityManager;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementsProgressEntity;

import java.util.UUID;

public class PlayerAchievementProgressEntityManager extends BasicEntityManager<PlayerAchievementsProgressEntity, UUID> {
    public PlayerAchievementProgressEntityManager(ConnectionSource connectionSource) {
        super(connectionSource, PlayerAchievementsProgressEntity.class);
    }
}
