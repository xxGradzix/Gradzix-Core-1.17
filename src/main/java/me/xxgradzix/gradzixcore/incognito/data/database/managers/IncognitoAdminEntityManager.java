package me.xxgradzix.gradzixcore.incognito.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource;
import lombok.Data;
import me.xxgradzix.gradzixcore.incognito.data.database.entities.IncognitoAdminEntity;
import me.xxgradzix.gradzixcore.incognito.data.database.entities.IncognitoModeEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class IncognitoAdminEntityManager {

    private Dao<IncognitoAdminEntity, UUID> entityDao;

    public IncognitoAdminEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, IncognitoAdminEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createIncognitoAdminEntity(IncognitoAdminEntity entity) {
        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createOrUpdateIncognitoAdminEntity(IncognitoAdminEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public IncognitoAdminEntity getIncognitoAdminEntityById(UUID playerUUID) {
        try {
            return entityDao.queryForId(playerUUID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<IncognitoAdminEntity> getAllIncognitoAdminEntities() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteIncognitoModeEntity(IncognitoAdminEntity entity) {
        try {
            entityDao.delete(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
