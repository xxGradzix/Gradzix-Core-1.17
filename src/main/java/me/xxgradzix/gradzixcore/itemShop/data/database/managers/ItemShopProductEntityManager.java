package me.xxgradzix.gradzixcore.itemShop.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.abstraction.BasicEntityManager;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopCategoryEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopProductEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ItemShopProductEntityManager extends BasicEntityManager<ItemShopProductEntity, Long> {
    public ItemShopProductEntityManager(ConnectionSource connectionSource) {
        super(connectionSource, ItemShopProductEntity.class);
    }
}