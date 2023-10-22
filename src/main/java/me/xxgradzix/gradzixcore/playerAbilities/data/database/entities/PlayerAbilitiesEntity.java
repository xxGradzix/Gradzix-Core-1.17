package me.xxgradzix.gradzixcore.playerAbilities.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;

import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_player_abilities")
public class PlayerAbilitiesEntity {


    public PlayerAbilitiesEntity(UUID uuid, String playerName, int strengthAbilityLevel, int dropAbilityLevel, int rankAbilityLevel) {
        this.uuid = uuid;
        this.playerName = playerName;
        this.strengthAbilityLevel = strengthAbilityLevel;
        this.dropAbilityLevel = dropAbilityLevel;
        this.rankAbilityLevel = rankAbilityLevel;
    }

    public PlayerAbilitiesEntity() {
    }

    @DatabaseField(unique = true, id = true)
    private UUID uuid;

    @DatabaseField
    private String playerName;

    @DatabaseField
    private int strengthAbilityLevel;

    @DatabaseField
    private int dropAbilityLevel;
    @DatabaseField
    private int rankAbilityLevel;

    public void incrementAbilityLevel(Ability ability) {
        switch (ability){

            case STRENGTH:
                strengthAbilityLevel++;
                break;
            case RANK:
                rankAbilityLevel++;
                break;
            case DROP:
                dropAbilityLevel++;
                break;
        }
    }
    public int getAbilityLevel(Ability ability) {
        switch (ability){

            case STRENGTH:
                return strengthAbilityLevel;

            case RANK:
                return rankAbilityLevel++;

            case DROP:
                return dropAbilityLevel++;

        }
        return 0;
    }

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

    public int getStrengthAbilityLevel() {
        return strengthAbilityLevel;
    }

    public void setStrengthAbilityLevel(int strengthAbilityLevel) {
        this.strengthAbilityLevel = strengthAbilityLevel;
    }

    public int getDropAbilityLevel() {
        return dropAbilityLevel;
    }

    public void setDropAbilityLevel(int dropAbilityLevel) {
        this.dropAbilityLevel = dropAbilityLevel;
    }

    public int getRankAbilityLevel() {
        return rankAbilityLevel;
    }

    public void setRankAbilityLevel(int rankAbilityLevel) {
        this.rankAbilityLevel = rankAbilityLevel;
    }
}
