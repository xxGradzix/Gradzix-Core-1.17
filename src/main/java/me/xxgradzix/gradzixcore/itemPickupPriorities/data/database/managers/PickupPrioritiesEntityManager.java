package me.xxgradzix.gradzixcore.itemPickupPriorities.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.itemPickupPriorities.data.database.entities.PickupPrioritiesEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class PickupPrioritiesEntityManager {
    private Dao<PickupPrioritiesEntity, Long> entityDao;

    public PickupPrioritiesEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, PickupPrioritiesEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdatePickupPrioritiesEntity(PickupPrioritiesEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PickupPrioritiesEntity getPickupPrioritiesEntity() {
        try {
            PickupPrioritiesEntity entity = entityDao.queryForFirst();
            if(entity == null) {
                entity = new PickupPrioritiesEntity(new ArrayList<>());
                createOrUpdatePickupPrioritiesEntity(entity);
            }

            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}