package me.xxgradzix.gradzixcore.ustawienia.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.ustawienia.data.database.entities.SettingsItemsEntity;

import java.sql.SQLException;
import java.util.HashMap;

public class SettingItemsEntityManager {
    private Dao<SettingsItemsEntity, Integer> entityDao;

    public SettingItemsEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, SettingsItemsEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void createSettingsItemsEntity(UpgradeEntity entity) {
//
//        try {
//            if (entityDao.queryForAll().size() < 1) {
//                entityDao.create(entity);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    public void createOrUpdateSettingsItemsEntity(SettingsItemsEntity entity) {
        try {
            entity.setId();
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SettingsItemsEntity getSettingsItemsEntity() {
        try {
            SettingsItemsEntity entity = entityDao.queryForId(1);
            if(entity == null) {
                entity = new SettingsItemsEntity(new HashMap<>(), new HashMap<>());
                createOrUpdateSettingsItemsEntity(entity);
            }
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
//    public List<PanelOptionsEntity> getPanelOptionsEntityWhereShowDeathMessageIs(boolean expectedValue) {
//        try {
//            return chatOptionsEntityDao.queryForEq("showDeathMessages", expectedValue);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public List<PlayerAbilitiesEntity> getChatOptionsEntitiesWhereShowScratchCardMessageIs(boolean expectedValue) {
//        try {
//            return chatOptionsEntityDao.queryForEq("showScratchCardMessages", expectedValue);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public List<PlayerAbilitiesEntity> getChatOptionsEntitiesWhereShowChatMessageIs(boolean expectedValue) {
//        try {
//            return chatOptionsEntityDao.queryForEq("showChatMessages", expectedValue);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public void updateSettingsItemsEntity(SettingsItemsEntity entity) {
        try {
            entityDao.update(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSettingsItemsEntity(SettingsItemsEntity entity) {
        try {
            entityDao.delete(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}