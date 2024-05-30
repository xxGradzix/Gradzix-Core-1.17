package me.xxgradzix.gradzixcore.autodropsell.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.autodropsell.data.database.entities.AutoSellEntity;

import java.sql.SQLException;

public class AutoSellEntityManager {
    private Dao<AutoSellEntity, Integer> entityDao;

    public AutoSellEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, AutoSellEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AutoSellEntity getAutoSellEntity() {
        try {
            AutoSellEntity entity = entityDao.queryForId(1);
            if(entity == null) {
                entity = new AutoSellEntity();
                updateAutoSellEntity(entity);
            }
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateAutoSellEntity(AutoSellEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
