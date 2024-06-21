package me.xxgradzix.gradzixcore.achievements.database.entities;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_player_achievements_progress")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerAchievementsProgressEntity {

    @DatabaseField(id = true, unique = true, columnName = "id")
    private UUID id;

    @DatabaseField
    private String nickname;

//    @DatabaseField
//    private int blocksBreaked;
//
//    @DatabaseField
//    private boolean blocksBreakedCompleted;
//
//    @DatabaseField
//    private int blocksPlaced;
//
//    @DatabaseField
//    private boolean blocksPlacedCompleted;
//
//    @DatabaseField
//    private int totemsOfUndyingUsed;
//
//    @DatabaseField
//    private boolean totemsOfUndyingUsedCompleted;
//
//    @DatabaseField
//    private int playerKills;
//
//    @DatabaseField
//    private boolean playerKillsCompleted;

    @ForeignCollectionField
    private ForeignCollection<PlayerAchievementEntity> achievements;

    public PlayerAchievementsProgressEntity(UUID id, String nickname) {
        this.id = id;
        this.nickname = nickname;
//        this.blocksBreaked = 0;
//        this.blocksBreakedCompleted = false;
//        this.blocksPlaced = 0;
//        this.blocksPlacedCompleted = false;
//        this.totemsOfUndyingUsed = 0;
//        this.totemsOfUndyingUsedCompleted = false;
//        this.playerKills = 0;
//        this.playerKillsCompleted = false;
    }
}

