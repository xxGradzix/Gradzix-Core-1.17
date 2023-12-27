package me.xxgradzix.gradzixcore.incognito.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_icognito")
@Getter
@Setter
public class IncognitoModeEntity {

    public IncognitoModeEntity() {
    }

    @DatabaseField(unique = true, id = true)
    private UUID uuid;

    @DatabaseField
    private String incognitoNick;

    @DatabaseField(columnName = "incognitoModeEnabled")
    private boolean incognitoModeEnabled;

    public IncognitoModeEntity(UUID uuid, String incognitoNick, boolean incognitoModeEnabled) {
        this.uuid = uuid;
        this.incognitoNick = incognitoNick;
        this.incognitoModeEnabled = incognitoModeEnabled;
    }
}
