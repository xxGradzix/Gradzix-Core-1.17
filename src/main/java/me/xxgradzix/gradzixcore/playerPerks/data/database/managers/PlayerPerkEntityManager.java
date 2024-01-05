package me.xxgradzix.gradzixcore.playerPerks.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class PlayerPerkEntityManager {
    private Dao<PlayerPerksEntity, UUID> entityDao;

    public PlayerPerkEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, PlayerPerksEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayerPerksEntity(PlayerPerksEntity entity) {
        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createOrUpdatePlayerPerksEntity(PlayerPerksEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PlayerPerksEntity getPlayerPerksEntityById(UUID playerUUID) {
        try {
            PlayerPerksEntity playerPerksEntity = entityDao.queryForId(playerUUID);
            if(playerPerksEntity == null) {
                playerPerksEntity = new PlayerPerksEntity(playerUUID);
                createOrUpdatePlayerPerksEntity(playerPerksEntity);
            }
            return playerPerksEntity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<PlayerPerksEntity> getPlayerPerksEntities() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletePlayerPerksEntity(PlayerPerksEntity playerPerksEntity) {
        try {
            entityDao.delete(playerPerksEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}