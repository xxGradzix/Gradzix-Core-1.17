package me.xxgradzix.gradzixcore.incognito.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_icognito_admin")
@Getter
@Setter
public class IncognitoAdminEntity {

    @DatabaseField(unique = true, id = true)
    private UUID uuid;

    @DatabaseField
    private String nick;

    public IncognitoAdminEntity(UUID uuid, String nick) {
        this.uuid = uuid;
        this.nick = nick;
    }

    public IncognitoAdminEntity() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
