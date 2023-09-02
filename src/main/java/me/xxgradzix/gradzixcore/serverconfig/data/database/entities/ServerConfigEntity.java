package me.xxgradzix.gradzixcore.serverconfig.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;
import me.xxgradzix.gradzixcore.serverconfig.data.database.persisters.ItemStackListClassPersister;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "gradzixcore_server_config")
@Getter
@Setter
public class ServerConfigEntity {

    @DatabaseField(unique = true, id = true)
    private Long id;
    @DatabaseField
    private double serverDamageModifier;

    @DatabaseField(persisterClass = ItemStackListClassPersister.class, columnDefinition = "LONGBLOB")
    private List<ItemStack> itemPriorities;

    public ServerConfigEntity() {
        this.id = 1L;
    }

    public ServerConfigEntity(double serverDamageModifier, List<ItemStack> itemPriorities) {
        this.id = 1L;
        this.serverDamageModifier = serverDamageModifier;
        this.itemPriorities = itemPriorities;
    }

    public double getServerDamageModifier() {
        return serverDamageModifier;
    }

    public void setServerDamageModifier(double serverDamageModifier) {
        this.serverDamageModifier = serverDamageModifier;
    }

    public List<ItemStack> getItemPriorities() {
        return new ArrayList<>(itemPriorities);
    }

    public void setItemPriorities(List<ItemStack> itemPriorities) {
        this.itemPriorities = itemPriorities;
    }

    public Long getId() {
        return id;
    }
}

