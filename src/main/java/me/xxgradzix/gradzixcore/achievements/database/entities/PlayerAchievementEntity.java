package me.xxgradzix.gradzixcore.achievements.database.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xxgradzix.gradzixcore.achievements.AchievementType;
import org.bukkit.entity.Player;

import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_player_achievements")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerAchievementEntity {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(dataType = DataType.ENUM_TO_STRING)
    private AchievementType achievementType;

    @DatabaseField
    private String nickname;

    @DatabaseField
    private int progress;

    @DatabaseField
    private boolean achievementCompleted;

    @DatabaseField
    private boolean claimed;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private PlayerAchievementsProgressEntity playerAchievementsProgressEntity;

    public PlayerAchievementEntity(AchievementType achievementType, Player player, PlayerAchievementsProgressEntity playerAchievementsProgressEntity) {
        this.achievementType = achievementType;
        this.nickname = player.getName();
        this.progress = 0;
        this.achievementCompleted = false;
        this.claimed = false;
        this.playerAchievementsProgressEntity = playerAchievementsProgressEntity;
    }
}

