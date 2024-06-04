package me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.inventory.ItemStack;

@DatabaseTable(tableName = "gradzixcore_upgradeshop_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VillagerUpgradeShopProductEntity {

    @DatabaseField(id = true, unique = true)
    private Long id;

    private ItemStack item;

    private double price;
    private int shopSlot;
    private ItemStack neededItem;

}
