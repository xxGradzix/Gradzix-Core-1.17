package me.xxgradzix.gradzixcore.VPLNShop.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.*;

import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_vpln_accounts")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VPLNAccountEntity {

    @DatabaseField(unique = true, id = true)
    private UUID uuid;

    @DatabaseField
    private String playerName;

    @DatabaseField
    @Setter
    @Getter
    private Double VPLNAmount;

}
