package me.xxgradzix.gradzixcore.ustawienia.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.ustawienia.data.database.entities.SettingsEntity;
import org.bukkit.Bukkit;

import java.sql.SQLException;
import java.util.UUID;

public class SettingOptionsEntityManager {
    private Dao<SettingsEntity, UUID> entityDao;

    public SettingOptionsEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, SettingsEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSettingsEntity(SettingsEntity entity) {

        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createOrUpdateSettingsEntity(SettingsEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SettingsEntity getSettingsEntityByUUID(UUID playerUUID) {
        try {
            SettingsEntity entity = entityDao.queryForId(playerUUID);
            if(entity == null) {
                entity = new SettingsEntity(playerUUID, Bukkit.getPlayer(playerUUID).getName(), false, false);
                createSettingsEntity(entity);
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

    public void updateSettingsEntity(SettingsEntity entity) {
        try {
            entityDao.update(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSettingsEntity(SettingsEntity entity) {
        try {
            entityDao.delete(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}