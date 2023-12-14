package me.xxgradzix.gradzixcore.clansExtension.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xxgradzix.gradzixcore.clansExtension.data.database.persisters.LocalDateTimeClassPersister;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_war_schedule")
public class WarScheduleEntity {

    public WarScheduleEntity() {
        id = 1L;
        warStart = LocalDateTime.now();
        warEnd = LocalDateTime.now().plusDays(1);
    }

    @DatabaseField(id = true, unique = true)
    private Long id;

    @DatabaseField(persisterClass = LocalDateTimeClassPersister.class, columnDefinition = "LONGBLOB")
    @NotNull
    private LocalDateTime warStart;

    @DatabaseField(persisterClass = LocalDateTimeClassPersister.class, columnDefinition = "LONGBLOB")
    @NotNull
    private LocalDateTime warEnd;

    public WarScheduleEntity(LocalDateTime warStart, LocalDateTime warEnd) {
        id = 1L;
        this.warStart = warStart;
        this.warEnd = warEnd;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getWarStart() {
        return warStart;
    }

    public LocalDateTime getWarEnd() {
        return warEnd;
    }
}

