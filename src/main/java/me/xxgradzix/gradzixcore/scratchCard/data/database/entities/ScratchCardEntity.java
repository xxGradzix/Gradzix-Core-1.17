package me.xxgradzix.gradzixcore.scratchCard.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;
import me.xxgradzix.gradzixcore.scratchCard.data.database.persisters.ItemStackArrayPersister;
import org.bukkit.inventory.ItemStack;

@DatabaseTable(tableName = "gradzixcore_scratch_card")
@Getter
@Setter
public class ScratchCardEntity {

    public ScratchCardEntity() {
        this.id = 1;
    }

    public ScratchCardEntity(ItemStack[] items) {
        this.id = 1;
        this.items = items;
    }

    @DatabaseField(id = true, unique = true)
    private int id;

    @DatabaseField(persisterClass = ItemStackArrayPersister.class, columnDefinition = "LONGBLOB")
    private ItemStack[] items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public void setItems(ItemStack[] items) {
        this.items = items;
    }
}
