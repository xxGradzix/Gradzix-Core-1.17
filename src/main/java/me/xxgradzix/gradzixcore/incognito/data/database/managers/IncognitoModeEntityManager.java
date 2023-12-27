package me.xxgradzix.gradzixcore.incognito.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.incognito.data.database.entities.IncognitoModeEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class IncognitoModeEntityManager {
    private Dao<IncognitoModeEntity, UUID> entityDao;

    public IncognitoModeEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, IncognitoModeEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createIncognitoModeEntity(IncognitoModeEntity entity) {
        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createOrIncognitoModeEntity(IncognitoModeEntity incognitoModeEntity) {
        try {
            entityDao.createOrUpdate(incognitoModeEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public IncognitoModeEntity getIncognitoModeEntityById(UUID playerUUID) {
        try {
            return entityDao.queryForId(playerUUID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<IncognitoModeEntity> getAllIncognitoModeEntities() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<IncognitoModeEntity> getAllIncognitoModeEntitiesByIncognitoModeEnabled(boolean incognitoModeEnabled) {
        try {
            return entityDao.queryBuilder().where().eq("incognitoModeEnabled", incognitoModeEnabled).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void deleteIncognitoModeEntity(IncognitoModeEntity incognitoModeEntity) {
        try {
            entityDao.delete(incognitoModeEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}