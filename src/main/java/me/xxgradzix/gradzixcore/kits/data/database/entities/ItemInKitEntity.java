package me.xxgradzix.gradzixcore.kits.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xxgradzix.gradzixcore.itemShop.data.database.persisters.ItemStackPersister;
import org.bukkit.inventory.ItemStack;

@DatabaseTable(tableName = "gradzixcore_item_in_kit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemInKitEntity {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(foreign = true)
    private KitEntity kitEntity;

    @DatabaseField(persisterClass = ItemStackPersister.class, columnDefinition = "LONGBLOB")
    private ItemStack itemStack;

    @DatabaseField
    private int slot;

}
