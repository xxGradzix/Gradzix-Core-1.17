package me.xxgradzix.gradzixcore.clansExtension.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import me.xxgradzix.gradzixcore.clansExtension.data.database.persisters.LocalDateTimeClassPersister;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;
import me.xxgradzix.gradzixcore.upgradeItem.data.database.persisters.ItemStackClassPersister;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;

@DatabaseTable(tableName = "gradzixcore_clan_perk_modifier")
public class PerkModifierEntity {

    @DatabaseField(unique = true, id = true, canBeNull = false)
    private ClanPerk clanPerk;

    @DatabaseField
    private double level1Modifier;

    @DatabaseField
    private double level2Modifier;

    @DatabaseField
    private double level3Modifier;

    @DatabaseField
    private double level4Modifier;

    @DatabaseField
    private int level1Price;
    @DatabaseField
    private int level2Price;

    @DatabaseField
    private int level3Price;

    @DatabaseField
    private int level4Price;

    public PerkModifierEntity() {
    }


    public PerkModifierEntity(ClanPerk clanPerk, double level1Modifier, double level2Modifier, double level3Modifier, double level4Modifier, int level1Price, int level2Price, int level3Price, int level4Price) {
        this.clanPerk = clanPerk;
        this.level1Modifier = level1Modifier;
        this.level2Modifier = level2Modifier;
        this.level3Modifier = level3Modifier;
        this.level4Modifier = level4Modifier;
        this.level1Price = level1Price;
        this.level2Price = level2Price;
        this.level3Price = level3Price;
        this.level4Price = level4Price;
    }
    public double getPerkModifierPerLevel(int level) throws IllegalArgumentException {

        if(level == 1) return level1Modifier;
        if(level == 2) return level2Modifier;
        if(level == 3) return level3Modifier;
        if(level == 4) return level4Modifier;

        throw new IllegalArgumentException("Unknown level: " + level);
    }
    public int getPerkPricePerLevel(int level) throws IllegalArgumentException {

            if(level == 1) return level1Price;
            if(level == 2) return level2Price;
            if(level == 3) return level3Price;
            if(level == 4) return level4Price;

            throw new IllegalArgumentException("Unknown level: " + level);
    }

    public ClanPerk getAbilityName() {
        return clanPerk;
    }

    private void setLevel1Modifier(double level1Modifier) {
        this.level1Modifier = level1Modifier;
    }

    private void setLevel2Modifier(double level2Modifier) {
        this.level2Modifier = level2Modifier;
    }

    private void setLevel3Modifier(double level3Modifier) {
        this.level3Modifier = level3Modifier;
    }


    private void setLevel4Modifier(double level4Modifier) {
        this.level4Modifier = level4Modifier;
    }

    public void setAbilityModifier(int level, double modifier) {

        if(level == 1) setLevel1Modifier(modifier);
        if(level == 2) setLevel2Modifier(modifier);
        if(level == 3) setLevel3Modifier(modifier);
        if(level == 4) setLevel4Modifier(modifier);

    }
}


