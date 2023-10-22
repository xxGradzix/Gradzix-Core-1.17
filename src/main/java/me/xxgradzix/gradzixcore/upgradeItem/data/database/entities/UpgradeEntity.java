package me.xxgradzix.gradzixcore.upgradeItem.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;
import me.xxgradzix.gradzixcore.upgradeItem.data.database.persisters.ItemStackClassPersister;
import org.bukkit.inventory.ItemStack;

@DatabaseTable(tableName = "gradzixcore_upgrade_items")
@Getter
@Setter
public class UpgradeEntity {

    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField(unique = true, persisterClass = ItemStackClassPersister.class, columnDefinition = "BLOB")
    private ItemStack currentItem;

    @DatabaseField(persisterClass = ItemStackClassPersister.class, columnDefinition = "TEXT")
    private ItemStack itemNeeded;

    @DatabaseField(persisterClass = ItemStackClassPersister.class, columnDefinition = "TEXT")
    private ItemStack nextItem;


    public UpgradeEntity(ItemStack currentItem, ItemStack itemNeeded, ItemStack nextItem) {
        this.currentItem = currentItem;
        this.itemNeeded = itemNeeded;
        this.nextItem = nextItem;
    }

    public UpgradeEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemStack getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(ItemStack currentItem) {
        this.currentItem = currentItem;
    }

    public ItemStack getItemNeeded() {
        return itemNeeded;
    }

    public void setItemNeeded(ItemStack itemNeeded) {
        this.itemNeeded = itemNeeded;
    }

    public ItemStack getNextItem() {
        return nextItem;
    }

    public void setNextItem(ItemStack nextItem) {
        this.nextItem = nextItem;
    }
}

