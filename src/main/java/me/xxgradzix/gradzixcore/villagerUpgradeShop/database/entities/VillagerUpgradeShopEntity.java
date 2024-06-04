package me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.persisters.HashMapMaterialDoubleType;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;

@DatabaseTable(tableName = "gradzixcore_upgradeshop")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VillagerUpgradeShopEntity {

    @DatabaseField(generatedId = true, unique = true)
    private Long id;

    @DatabaseField(canBeNull = false, unique = true)
    private String name;

    @OneToMany
    private List<VillagerUpgradeShopProductEntity> products;

}
