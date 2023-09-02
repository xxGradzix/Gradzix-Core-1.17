package me.xxgradzix.gradzixcore.ustawienia.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_settings_options")
@Getter
@Setter
public class SettingsEntity {

    public SettingsEntity() {
    }

    public SettingsEntity(UUID uuid, String playerName, boolean autoSell, boolean autoExchange) {
        this.uuid = uuid;
        this.playerName = playerName;
        this.autoSell = autoSell;
        this.autoExchange = autoExchange;
    }

    @DatabaseField(unique = true, id = true)
    private UUID uuid;

    @DatabaseField
    private String playerName;

    @DatabaseField
    private boolean autoSell;

    @DatabaseField
    private boolean autoExchange;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isAutoSell() {
        return autoSell;
    }

    public void setAutoSell(boolean autoSell) {
        this.autoSell = autoSell;
    }

    public boolean isAutoExchange() {
        return autoExchange;
    }

    public void setAutoExchange(boolean autoExchange) {
        this.autoExchange = autoExchange;
    }
}
