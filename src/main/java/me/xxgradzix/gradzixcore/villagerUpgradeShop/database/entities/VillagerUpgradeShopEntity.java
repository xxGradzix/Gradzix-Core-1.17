package me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.persisters.HashMapMaterialDoubleType;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import jakarta.persistence.*;

import java.util.List;

@DatabaseTable(tableName = "gradzixcore_upgradeshop")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VillagerUpgradeShopEntity {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(canBeNull = false, unique = true, columnName = "name")
    private String name;

    @ForeignCollectionField
    private ForeignCollection<VillagerUpgradeShopProductEntity> products;

}
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity(name = "gradzixcore_upgradeshop")
//public class VillagerUpgradeShopEntity {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @DatabaseField(canBeNull = false, unique = true)
//    private String name;
//
//    @OneToMany
//    private List<VillagerUpgradeShopProductEntity> products;
//
//}

