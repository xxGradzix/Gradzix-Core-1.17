package me.xxgradzix.gradzixcore.VPLNShop.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.*;
import me.xxgradzix.gradzixcore.clansExtension.data.database.persisters.LocalDateTimeClassPersister;

import java.time.LocalDateTime;
import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_vpln_orders")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VPLNOrderEntity {

    @DatabaseField(generatedId = true, unique = true, canBeNull = false)
    private Long id;

    @DatabaseField
    private String orderCreator;

    @DatabaseField(columnName = "player_name")
    private String playerName;

    @DatabaseField(persisterClass = LocalDateTimeClassPersister.class, columnDefinition = "LONGBLOB")
    private LocalDateTime orderDateTime;

    @DatabaseField
    @Setter
    private String orderStatus;

    @DatabaseField
    private Double VPLNAmount;


}

