package me.xxgradzix.gradzixcore.villagerUpgradeShop.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.abstraction.BasicEntityManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopEntity;

import java.sql.SQLException;

public class VillagerUpgradeShopEntityManager extends BasicEntityManager<VillagerUpgradeShopEntity, Long> {
    public VillagerUpgradeShopEntityManager(ConnectionSource connectionSource) {
        super(connectionSource, VillagerUpgradeShopEntity.class);
    }
}
