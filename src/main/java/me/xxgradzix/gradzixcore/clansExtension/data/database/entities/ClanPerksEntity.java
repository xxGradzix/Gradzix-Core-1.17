package me.xxgradzix.gradzixcore.clansExtension.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xxgradzix.gradzixcore.clansExtension.data.database.persisters.LocalDateTimeClassPersister;

import java.time.LocalDateTime;
import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_clan_perks")
@NoArgsConstructor
public class ClanPerksEntity {

    @DatabaseField(id = true, unique = true, canBeNull = false)
    private UUID clanId;

    @DatabaseField
    private int clanRankPerkLevel;

    @DatabaseField
    private int clanWarAmountPerkLevel;

    public ClanPerksEntity(UUID clanId) {
        this.clanId = clanId;
        this.clanRankPerkLevel = 0;
        this.clanWarAmountPerkLevel = 0;
    }
    public int getClanPerkLevel(ClanPerk perk) {
        switch (perk) {
            case RANK:
                return clanRankPerkLevel;
            case WAR_AMOUNT:
                return clanWarAmountPerkLevel;
        }
        return -1;
    }
    public void setClanPerkLevel(ClanPerk perk, int level) {
        switch (perk) {
            case RANK:
                clanRankPerkLevel = level;
                break;
            case WAR_AMOUNT:
                clanWarAmountPerkLevel = level;
                break;
        }
    }
}

