package me.xxgradzix.gradzixcore.umiejetnosci.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import me.xxgradzix.gradzixcore.umiejetnosci.data.database.entities.enums.Ability;

@DatabaseTable(tableName = "gradzixcore_ability_modifier")
public class AbilityModifierEntity {

//    @DatabaseField(unique = true, id = true)
//    private Long id;

    @DatabaseField(unique = true, id = true, canBeNull = false)
    private Ability abilityName;

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

    public AbilityModifierEntity() {
    }

    public AbilityModifierEntity(Ability abilityName, double level1Modifier, double level2Modifier, double level3Modifier, double level4Modifier, int level1Price, int level2Price, int level3Price, int level4Price) {
        this.abilityName = abilityName;
        this.level1Modifier = level1Modifier;
        this.level2Modifier = level2Modifier;
        this.level3Modifier = level3Modifier;
        this.level4Modifier = level4Modifier;
        this.level1Price = level1Price;
        this.level2Price = level2Price;
        this.level3Price = level3Price;
        this.level4Price = level4Price;
    }

    public double getAbilityModifier(int level) {

        if(level == 1) return level1Modifier;
        if(level == 2) return level2Modifier;
        if(level == 3) return level3Modifier;
        if(level == 4) return level4Modifier;

        return 1;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getAbilityName() {
//        return abilityName;
//    }
//
//    public void setAbilityName(String abilityName) {
//        this.abilityName = abilityName;
//    }


    public Ability getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(Ability abilityName) {
        this.abilityName = abilityName;
    }

    public double getLevel1Modifier() {
        return level1Modifier;
    }

    public void setLevel1Modifier(double level1Modifier) {
        this.level1Modifier = level1Modifier;
    }

    public double getLevel2Modifier() {
        return level2Modifier;
    }

    public void setLevel2Modifier(double level2Modifier) {
        this.level2Modifier = level2Modifier;
    }

    public double getLevel3Modifier() {
        return level3Modifier;
    }

    public void setLevel3Modifier(double level3Modifier) {
        this.level3Modifier = level3Modifier;
    }

    public double getLevel4Modifier() {
        return level4Modifier;
    }

    public void setLevel4Modifier(double level4Modifier) {
        this.level4Modifier = level4Modifier;
    }

    public int getLevel1Price() {
        return level1Price;
    }

    public void setLevel1Price(int level1Price) {
        this.level1Price = level1Price;
    }

    public int getLevel2Price() {
        return level2Price;
    }

    public void setLevel2Price(int level2Price) {
        this.level2Price = level2Price;
    }

    public int getLevel3Price() {
        return level3Price;
    }

    public void setLevel3Price(int level3Price) {
        this.level3Price = level3Price;
    }

    public int getLevel4Price() {
        return level4Price;
    }

    public void setLevel4Price(int level4Price) {
        this.level4Price = level4Price;
    }

    public void setAbilityModifier(int level, double modifier) {

        if(level == 1) setLevel1Modifier(modifier);
        if(level == 2) setLevel2Modifier(modifier);
        if(level == 3) setLevel3Modifier(modifier);
        if(level == 4) setLevel4Modifier(modifier);

    }
}


