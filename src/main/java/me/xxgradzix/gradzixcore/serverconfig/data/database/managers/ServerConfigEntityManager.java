package me.xxgradzix.gradzixcore.serverconfig.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.serverconfig.data.database.entities.ServerConfigEntity;

import java.sql.SQLException;

public class ServerConfigEntityManager {
    private Dao<ServerConfigEntity, Long> entityDao;

    public ServerConfigEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, ServerConfigEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateServerConfigEntity(ServerConfigEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ServerConfigEntity getServerConfigEntity() {
        try {
            ServerConfigEntity entity = entityDao.queryForFirst();
            if(entity == null) {
                entity = new ServerConfigEntity(1);
                createOrUpdateServerConfigEntity(entity);
            }

            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}