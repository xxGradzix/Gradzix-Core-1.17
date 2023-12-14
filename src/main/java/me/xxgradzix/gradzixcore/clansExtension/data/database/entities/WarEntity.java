package me.xxgradzix.gradzixcore.clansExtension.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xxgradzix.gradzixcore.clansExtension.data.database.persisters.LocalDateTimeClassPersister;

import java.time.LocalDateTime;
import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_war")
@Data
@NoArgsConstructor
public class WarEntity {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(columnName = "invader_id")
    private UUID invaderGuildId;

    @DatabaseField(columnName = "invaded_id")
    private UUID invadedGuildId;

    @DatabaseField
    private int invaderScore;

    @DatabaseField
    private int invadedScore;

    @DatabaseField(persisterClass = LocalDateTimeClassPersister.class, columnDefinition = "LONGBLOB")
    private LocalDateTime warStart;

    @DatabaseField(persisterClass = LocalDateTimeClassPersister.class, columnDefinition = "LONGBLOB")
    private LocalDateTime warEnd;

//    @DatabaseField(columnName = "warState")
//    private WAR_STATE warState;
    @DatabaseField(columnName = "isActive")
    private boolean isActive;

    @DatabaseField(columnName = "isFinished")
    private boolean isFinished;

    public WarEntity(UUID invaderGuildId, UUID invadedGuildId, int invaderScore, int invadedScore, LocalDateTime warStart, LocalDateTime warEnd
//            , WAR_STATE warState
    ) {
        this.invaderGuildId = invaderGuildId;
        this.invadedGuildId = invadedGuildId;
        this.invaderScore = invaderScore;
        this.invadedScore = invadedScore;
        this.warStart = warStart;
        this.warEnd = warEnd;
        this.isActive = false;
        this.isFinished = false;
//        this.warState = warState;
    }
}

