package me.xxgradzix.gradzixcore.adminPanel.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

@DatabaseTable(tableName = "gradzixcore_panel_options")
@Getter
@Setter
public class PanelOptionsEntity {

    public PanelOptionsEntity( boolean isChatEnabled, boolean isZdrapkaEnabled, boolean isKityEnabled, boolean isOsiagnieciaEnabled) {
        this.id = 1;
        this.isChatEnabled = isChatEnabled;
        this.isZdrapkaEnabled = isZdrapkaEnabled;
        this.isKityEnabled = isKityEnabled;
        this.isOsiagnieciaEnabled = isOsiagnieciaEnabled;
    }

    public int getId() {
        return id;
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
    private boolean isZdrapkaEnabled;
    @DatabaseField
    private boolean isKityEnabled;

    @DatabaseField
    private boolean isOsiagnieciaEnabled;

    public boolean isChatEnabled() {
        return isChatEnabled;
    }



    public void setChatEnabled(boolean chatEnabled) {
        isChatEnabled = chatEnabled;
    }

    public boolean isZdrapkaEnabled() {
        return isZdrapkaEnabled;
    }

    public void setZdrapkaEnabled(boolean zdrapkaEnabled) {
        isZdrapkaEnabled = zdrapkaEnabled;
    }

    public boolean isKityEnabled() {
        return isKityEnabled;
    }

    public void setKityEnabled(boolean kityEnabled) {
        isKityEnabled = kityEnabled;
    }

    public boolean isOsiagnieciaEnabled() {
        return isOsiagnieciaEnabled;
    }

    public void setOsiagnieciaEnabled(boolean osiagnieciaEnabled) {
        isOsiagnieciaEnabled = osiagnieciaEnabled;
    }
}
