package me.xxgradzix.gradzixcore.itemShop.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopCategoryEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.enums.ShopType;

import javax.management.InstanceAlreadyExistsException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemShopCategoryEntityManager {

    private Dao<ItemShopCategoryEntity, Long> entityDao;

    public ItemShopCategoryEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, ItemShopCategoryEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateAbilityModifierEntity(ItemShopCategoryEntity entity) throws InstanceAlreadyExistsException {
        try {
            Optional<ItemShopCategoryEntity> optionalCategory = getItemShopCategoryEntityByNameAndShopType(entity.getName(), entity.getShopType());
            if(optionalCategory.isPresent()) {
                throw new InstanceAlreadyExistsException("Entity with name " + entity.getName() + " and shoptype " + entity.getShopType().name() + " already exists");
            }
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<ItemShopCategoryEntity> getItemShopCategoryEntityById(Long id) {
        try {

            ItemShopCategoryEntity entity = entityDao.queryForId(id);

            if(entity == null) {
                return Optional.empty();
            }
            return Optional.of(entity);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public Optional<ItemShopCategoryEntity> getItemShopCategoryEntityByNameAndShopType(String name, ShopType shopType) {
        try {

            List<ItemShopCategoryEntity> itemShopCategoryEntityList = entityDao.queryForEq("name", name);

//            if(itemShopCategoryEntityList.stream().map(ItemShopCategoryEntity::getShopType).collect(Collectors.toList()).contains(shopType)){
//                return Optional.of(itemShopCategoryEntityList.matc)
//            }

            return itemShopCategoryEntityList.stream()
                    .filter(entity -> entity.getShopType().equals(shopType))
                    .findFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<ItemShopCategoryEntity> getItemShopCategoryEntities() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<ItemShopCategoryEntity> getItemShopCategoriesByShopType(ShopType shopType) {
        try {
            return entityDao.queryForEq("shopType", shopType);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void deleteItemShopCategoryEntity(ItemShopCategoryEntity entity) {
        try {
            entityDao.delete(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public Optional<ItemShopCategoryEntity> getItemShopCategoryByName(String productCategory) {
//        try {
//            return entityDao.queryForEq("name", productCategory);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}