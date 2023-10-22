package me.xxgradzix.gradzixcore.itemPickupPriorities.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;
import me.xxgradzix.gradzixcore.itemPickupPriorities.data.database.persisters.ItemStackListClassPersister;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "gradzixcore_pickup_priorities")
@Getter
@Setter
public class PickupPrioritiesEntity {

    @DatabaseField(unique = true, id = true)
    private Long id;

    @DatabaseField(persisterClass = ItemStackListClassPersister.class, columnDefinition = "LONGBLOB")
    private List<ItemStack> itemPriorities;

    public PickupPrioritiesEntity() {
        this.id = 1L;
    }

    public PickupPrioritiesEntity(List<ItemStack> itemPriorities) {
        this.id = 1L;
        this.itemPriorities = itemPriorities;
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

