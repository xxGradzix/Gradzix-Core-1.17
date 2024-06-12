package me.xxgradzix.gradzixcore.itemShop.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.abstraction.BasicEntityManager;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopCategoryEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.enums.ShopType;

import javax.management.InstanceAlreadyExistsException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemShopCategoryEntityManager extends BasicEntityManager<ItemShopCategoryEntity, Long> {


    public ItemShopCategoryEntityManager(ConnectionSource connectionSource) {
        super(connectionSource, ItemShopCategoryEntity.class);
    }
}