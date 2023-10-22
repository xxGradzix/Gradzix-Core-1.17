package me.xxgradzix.gradzixcore.adminPanel.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

@DatabaseTable(tableName = "gradzixcore_panel_options")
@Getter
@Setter
public class PanelOptionsEntity {

    public PanelOptionsEntity( boolean isChatEnabled, boolean isScratchCardEnabled, boolean isKitsEnabled, boolean isAchievementsEnabled) {
        this.id = 1;
        this.isChatEnabled = isChatEnabled;
        this.isScratchCardEnabled = isScratchCardEnabled;
        this.isKitsEnabled = isKitsEnabled;
        this.isAchievementsEnabled = isAchievementsEnabled;
    }


    public void setId(int id) {
        this.id = id;
    }

    public PanelOptionsEntity() {
    }
    @DatabaseField(id = true, unique = true)
    private int id;

    @DatabaseField
    private boolean isChatEnabled;

    @DatabaseField
    private boolean isScratchCardEnabled;
    @DatabaseField
    private boolean isKitsEnabled;

    @DatabaseField
    private boolean isAchievementsEnabled;

    public boolean isChatEnabled() {
        return isChatEnabled;
    }
    
    public void setChatEnabled(boolean chatEnabled) {
        isChatEnabled = chatEnabled;
    }

    public boolean isScratchCardEnabled() {
        return isScratchCardEnabled;
    }

    public void setScratchCardEnabled(boolean scratchCardEnabled) {
        isScratchCardEnabled = scratchCardEnabled;
    }

    public boolean isKitsEnabled() {
        return isKitsEnabled;
    }

    public void setKitsEnabled(boolean kitsEnabled) {
        isKitsEnabled = kitsEnabled;
    }

    public boolean isAchievementsEnabled() {
        return isAchievementsEnabled;
    }

    public void setAchievementsEnabled(boolean achievementsEnabled) {
        isAchievementsEnabled = achievementsEnabled;
    }
}
