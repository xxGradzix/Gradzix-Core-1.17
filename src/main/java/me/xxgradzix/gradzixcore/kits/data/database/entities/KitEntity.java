package me.xxgradzix.gradzixcore.kits.data.database.entities;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.xxgradzix.gradzixcore.serverconfig.data.database.persisters.ItemStackListClassPersister;
import me.xxgradzix.gradzixcore.upgradeItem.data.database.persisters.ItemStackClassPersister;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@DatabaseTable(tableName = "gradzixcore_kit")
@Getter
@Setter
@NoArgsConstructor
public class KitEntity {

    @DatabaseField(unique = true, id = true)
    private String kitName;

    @DatabaseField
    private String permission;

    @DatabaseField
    private int slot;

    @DatabaseField
    private int cooldownSeconds;

    @DatabaseField(persisterClass = ItemStackClassPersister.class, columnDefinition = "LONGBLOB")
    private ItemStack noPermissionItem;

    @DatabaseField(persisterClass = ItemStackClassPersister.class, columnDefinition = "LONGBLOB")
    private ItemStack permissionItem;

    @ForeignCollectionField()
    private ForeignCollection<ItemInKitEntity> kitItems;

    public KitEntity(String kitName, String permission, int slot, int cooldownSeconds, ItemStack noPermissionItem, ItemStack permissionItem) {
        this.kitName = kitName;
        this.permission = permission;
        this.slot = slot;
        this.cooldownSeconds = cooldownSeconds;
        this.noPermissionItem = noPermissionItem;
        this.permissionItem = permissionItem;
    }
}

