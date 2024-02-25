package me.xxgradzix.gradzixcore.clansCore.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import me.xxgradzix.gradzixcore.clansCore.Clans;

import java.io.Serializable;
import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_clans_users")
public class UserEntity implements Serializable {

    public UserEntity(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.points = Clans.DEFAULT_POINTS;
        this.kills = 0;
        this.deaths = 0;
    }

    public UserEntity() {
    }

    @DatabaseField(id = true, unique = true, columnName = "uuid")
    private UUID uuid;

    @DatabaseField(unique = true)
    private String name;

    @DatabaseField
    private int points;

    @DatabaseField
    private int kills;
    @DatabaseField
    private int deaths;

    public void setPoints(int points) {
        this.points = points;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

}
