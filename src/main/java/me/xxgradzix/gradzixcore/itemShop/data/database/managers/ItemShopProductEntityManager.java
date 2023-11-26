package me.xxgradzix.gradzixcore.itemShop.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopCategoryEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopProductEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ItemShopProductEntityManager {
    private Dao<ItemShopProductEntity, Long> entityDao;

    public ItemShopProductEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, ItemShopProductEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createOrUpdateItemShopProductEntity(ItemShopProductEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Optional<ItemShopProductEntity> getItemShopProductEntityById(Long id) {
        try {
            ItemShopProductEntity entity = entityDao.queryForId(id);
            if(entity == null) return Optional.empty();
            return Optional.of(entity);

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public List<ItemShopProductEntity> getItemShopProductEntities() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<ItemShopProductEntity> getItemShopProductEntitiesByCategory(ItemShopCategoryEntity entity) {
        try {
            return entityDao.queryForEq("category_id", entity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteItemShopProductEntity(ItemShopProductEntity itemShopProductEntity) {
        try {
            entityDao.delete(itemShopProductEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteItemShopProductEntitiesByCategory(ItemShopCategoryEntity entity) {
        try {
            entityDao.deleteIds(getItemShopProductEntitiesByCategory(entity).stream().map(ItemShopProductEntity::getId).collect(Collectors.toList()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}