package me.xxgradzix.gradzixcore.villagerUpgradeShop.database.managers;

import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.abstraction.BasicEntityManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopEntity;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopProductEntity;

public class VillagerUpgradeShopProductEntityManager extends BasicEntityManager<VillagerUpgradeShopProductEntity, Long> {
    public VillagerUpgradeShopProductEntityManager(ConnectionSource connectionSource) {
        super(connectionSource, VillagerUpgradeShopProductEntity.class);
    }
}
