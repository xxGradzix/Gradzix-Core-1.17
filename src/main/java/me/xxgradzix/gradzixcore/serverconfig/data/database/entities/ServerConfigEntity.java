package me.xxgradzix.gradzixcore.serverconfig.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

@DatabaseTable(tableName = "gradzixcore_server_config")
@Getter
@Setter
public class ServerConfigEntity {

    @DatabaseField(unique = true, id = true)
    private Long id;
    @DatabaseField
    private double serverDamageModifier;

    public ServerConfigEntity() {
        this.id = 1L;
    }

    public ServerConfigEntity(double serverDamageModifier) {
        this.id = 1L;
        this.serverDamageModifier = serverDamageModifier;
    }

    public double getServerDamageModifier() {
        return serverDamageModifier;
    }

    public void setServerDamageModifier(double serverDamageModifier) {
        this.serverDamageModifier = serverDamageModifier;
    }
    public Long getId() {
        return id;
    }
}

