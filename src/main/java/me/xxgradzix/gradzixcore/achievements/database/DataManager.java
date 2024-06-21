package me.xxgradzix.gradzixcore.achievements.database;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.achievements.AchievementType;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementEntity;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementsProgressEntity;
import me.xxgradzix.gradzixcore.achievements.database.managers.PlayerAchievementEntityManager;
import me.xxgradzix.gradzixcore.achievements.database.managers.PlayerAchievementProgressEntityManager;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DataManager {

    private static final boolean useDB = Gradzix_Core.USE_DB;

    private PlayerAchievementProgressEntityManager playerAchievementProgressEntityManager;
    private PlayerAchievementEntityManager playerAchievementEntityManager;

    public DataManager(PlayerAchievementProgressEntityManager playerAchievementProgressEntityManager, PlayerAchievementEntityManager playerAchievementEntityManager) {
        this.playerAchievementProgressEntityManager = playerAchievementProgressEntityManager;
        this.playerAchievementEntityManager = playerAchievementEntityManager;
    }

    private PlayerAchievementsProgressEntity getPlayerAchievements(Player player) {
        if (useDB) {
            try {
                PlayerAchievementsProgressEntity entity = playerAchievementProgressEntityManager.getEntityById(player.getUniqueId());

                if(entity == null) {
                    entity = PlayerAchievementsProgressEntity.builder()
                            .id(player.getUniqueId())
                            .nickname(player.getName())
                            .build();
//                    for (AchievementType achievementType : AchievementType.values()) {
//                        createPlayerAchievementEntity(player, achievementType, entity);
//                    }
                    create(entity);
                    return getPlayerAchievements(player);
                }

                return entity;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void createPlayerAchievementEntity(AchievementType achievementType, Player player) {

        PlayerAchievementEntity playerAchievementEntity = PlayerAchievementEntity.builder()
                .achievementType(achievementType)
                .nickname(player.getName())
                .progress(0)
                .achievementCompleted(false)
                .claimed(false)
                .playerAchievementsProgressEntity(getPlayerAchievements(player))
                .build();

        try {
            playerAchievementEntityManager.createOrUpdateEntity(playerAchievementEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void createPlayerAchievementsIfNotExists(Player player) {
//        if (useDB) {
//            try {
//                PlayerAchievementsProgressEntity villagerUpgradeShop = PlayerAchievementsProgressEntity.builder()
//                        .id(player.getUniqueId())
//                        .nickname(player.getName())
//                        .build();
//                playerAchievementProgressEntityManager.createEntityIfNotExists(villagerUpgradeShop);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void getAchievements(Player player) {
//        if (useDB) {
//            try {
//                playerAchievementProgressEntityManager.getEntityById(player.getUniqueId());
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    private void create(PlayerAchievementsProgressEntity playerAchievementsProgressEntity) {
        if (useDB) {
            try {
                playerAchievementProgressEntityManager.createOrUpdateEntity(playerAchievementsProgressEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<AchievementType, Integer> getAllAchievements(Player player) {
        if(useDB) {
            HashMap<AchievementType, Integer> achievements = new HashMap<>();

            PlayerAchievementsProgressEntity playerAchievements = getPlayerAchievements(player);

            for (PlayerAchievementEntity achievement : playerAchievements.getAchievements()) {
                achievements.put(achievement.getAchievementType(), achievement.getProgress());
            }
            for (AchievementType achievementType : AchievementType.values()) {
                if (!achievements.containsKey(achievementType)) {
                    createPlayerAchievementEntity(achievementType, player);
                    achievements.put(achievementType, 0);
                }
            }
            return achievements;
        }
        throw new NullPointerException("Database is disabled");
    }

    public void completeAchievement(Player player, AchievementType type) {
        getPlayerAchievements(player).getAchievements().stream()
                .filter(achievement -> achievement.getAchievementType().equals(type))
                .findFirst()
                .ifPresent(achievement -> {
                    achievement.setAchievementCompleted(true);
                    try {
                        playerAchievementEntityManager.createOrUpdateEntity(achievement);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }
    public void setAchievementClaimed(Player player, AchievementType type) {
        getPlayerAchievements(player).getAchievements().stream()
                .filter(achievement -> achievement.getAchievementType().equals(type))
                .findFirst()
                .ifPresent(achievement -> {
                    achievement.setClaimed(true);
                    try {
                        playerAchievementEntityManager.createOrUpdateEntity(achievement);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }
    public boolean canClaimAchievement(Player player, AchievementType type) {
        return getPlayerAchievements(player).getAchievements().stream()
                .filter(achievement -> achievement.getAchievementType().equals(type))
                .findFirst()
                .map(PlayerAchievementEntity::isAchievementCompleted)
                .orElse(false);
    }
    public boolean isAchievementClaimed(Player player, AchievementType type) {
        return getPlayerAchievements(player).getAchievements().stream()
                .filter(achievement -> achievement.getAchievementType().equals(type))
                .findFirst()
                .map(PlayerAchievementEntity::isClaimed)
                .orElse(false);
    }

    public void saveAchievements(Player player, HashMap<AchievementType, Integer> achievementTypeIntegerHashMap) {
        if(useDB) {
            PlayerAchievementsProgressEntity playerAchievements = getPlayerAchievements(player);

            for (PlayerAchievementEntity achievement : playerAchievements.getAchievements()) {
                achievement.setProgress(achievementTypeIntegerHashMap.getOrDefault(achievement.getAchievementType(), 0));
                try {
                    playerAchievementEntityManager.createOrUpdateEntity(achievement);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

