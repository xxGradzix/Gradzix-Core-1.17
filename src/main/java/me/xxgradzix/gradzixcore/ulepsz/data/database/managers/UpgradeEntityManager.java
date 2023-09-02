package me.xxgradzix.gradzixcore.ulepsz.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.ulepsz.data.database.entities.UpgradeEntity;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.List;

public class UpgradeEntityManager {
    private Dao<UpgradeEntity, Long> entityDao;
//    private Dao<UpgradeEntity, ItemStack> entityDao;

    public UpgradeEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, UpgradeEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUpgradeEntity(UpgradeEntity entity) {

        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createOrUpdateUpgradeEntity(UpgradeEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UpgradeEntity getUpgradeEntityByItemStackKey(ItemStack itemStack) {
        try {
            List<UpgradeEntity> upgradeEntityList = entityDao.queryForEq("currentItem", itemStack);
            if(upgradeEntityList == null || upgradeEntityList.isEmpty()) return null;
            if(upgradeEntityList.size() > 1) throw new RuntimeException("Obiekt w bazie jest wiecej niz 1 unikalnych obiektów - nigdy ten blad nie powinien sie pojawic");

            return upgradeEntityList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<UpgradeEntity> getUpgradeEntities() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateUpgradeEntity(UpgradeEntity entity) {
        try {
            entityDao.update(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUpgradeEntity(UpgradeEntity entity) {
        try {
            entityDao.delete(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllUpgradeEntities() {
        for(UpgradeEntity upgradeEntity : getUpgradeEntities()) {
            deleteUpgradeEntity(upgradeEntity);
        }
    }
}