package me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xxgradzix.gradzixcore.upgradeItem.data.database.persisters.ItemStackClassPersister;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.persisters.ItemStackConverter;
import org.bukkit.inventory.ItemStack;

@DatabaseTable(tableName = "gradzixcore_upgradeshop_product")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class VillagerUpgradeShopProductEntity {

    @DatabaseField(generatedId = true)
    private Long id;

// ...
    @DatabaseField(unique = true, persisterClass = ItemStackClassPersister.class, columnDefinition = "BLOB")
    private ItemStack item;

    @DatabaseField
    private double price;

    @DatabaseField
    private int shopSlot;

    @DatabaseField(unique = true, persisterClass = ItemStackClassPersister.class, columnDefinition = "BLOB")
    private ItemStack neededItem;

    @DatabaseField(foreign = true)
    private VillagerUpgradeShopEntity shopEntity;

    public ItemStack getItem() {
        return item.clone();
    }
}
