package me.xxgradzix.gradzixcore.itemShop.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopCategoryEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopPlayerBalanceEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ItemShopPlayerBalanceEntityManager {

    private Dao<ItemShopPlayerBalanceEntity, UUID> entityDao;

    public ItemShopPlayerBalanceEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, ItemShopPlayerBalanceEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateAItemShopPlayerBalanceEntity(ItemShopPlayerBalanceEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<ItemShopPlayerBalanceEntity> getItemShopCategoryEntityById(UUID id) {
        try {

            ItemShopPlayerBalanceEntity entity = entityDao.queryForId(id);

            if(entity == null) {
                return Optional.empty();
            }
            return Optional.of(entity);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public List<ItemShopPlayerBalanceEntity> getItemShopPlayerBalanceEntities() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void deleteItemShopPlayerBalanceEntity(ItemShopPlayerBalanceEntity entity) {
        try {
            entityDao.delete(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}