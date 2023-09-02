package me.xxgradzix.gradzixcore.umiejetnosci.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.umiejetnosci.data.database.entities.PlayerAbilitiesEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class PlayerAbilitiesEntityManager {
    private Dao<PlayerAbilitiesEntity, UUID> entityDao;

    public PlayerAbilitiesEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, PlayerAbilitiesEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayerAbilitiesEntity(PlayerAbilitiesEntity entity) {
        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createOrUpdatePlayerAbilitiesEntity(PlayerAbilitiesEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PlayerAbilitiesEntity getPlayerAbilitiesEntityById(UUID playerUUID) {
        try {
            return entityDao.queryForId(playerUUID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<PlayerAbilitiesEntity> getPlayerAbilitiesEntities() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updatePlayerAbilitiesEntity(PlayerAbilitiesEntity playerAbilitiesEntity) {
        try {
            entityDao.update(playerAbilitiesEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlayerAbilitiesEntity(PlayerAbilitiesEntity playerAbilitiesEntity) {
        try {
            entityDao.delete(playerAbilitiesEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}