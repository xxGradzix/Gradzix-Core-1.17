package me.xxgradzix.gradzixcore.clansExtension.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xxgradzix.gradzixcore.clansExtension.data.database.persisters.LocalDateTimeClassPersister;

import java.time.LocalDateTime;
import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_war_record")
@Data
@NoArgsConstructor
public class WarRecordEntity {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(columnName = "record_owner_id")
    private UUID warRecordOwner;

    @DatabaseField(columnName = "owner_tag")
    private String ownerTag;

    @DatabaseField(columnName = "enemy_tag")
    private String enemyTag;

    @DatabaseField
    private int ownerScore;

    @DatabaseField
    private int enemyScore;

    @DatabaseField(persisterClass = LocalDateTimeClassPersister.class, columnDefinition = "LONGBLOB")
    private LocalDateTime warStart;

    @DatabaseField(persisterClass = LocalDateTimeClassPersister.class, columnDefinition = "LONGBLOB")
    private LocalDateTime warEnd;

    public WarRecordEntity(UUID warRecordOwner, String ownerTag, String enemyTag, int ownerScore, int enemyScore, LocalDateTime warStart, LocalDateTime warEnd) {
        this.warRecordOwner = warRecordOwner;
        this.ownerTag = ownerTag;
        this.enemyTag = enemyTag;
        this.ownerScore = ownerScore;
        this.enemyScore = enemyScore;
        this.warStart = warStart;
        this.warEnd = warEnd;
    }
}
