package me.xxgradzix.gradzixcore.clansExtension.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WAR_STATE;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarScheduleEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class WarScheduleEntityManager {
    private Dao<WarScheduleEntity, Long> entityDao;

    public WarScheduleEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, WarScheduleEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createWarScheduleEntity(WarScheduleEntity entity) {
        try {
            // Check if there are any records in the table
            if (!getWarScheduleEntities().isEmpty()) {
                // If there is a record, delete it
                deleteWarSchedules();
            }
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private List<WarScheduleEntity> getWarScheduleEntities() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Optional<WarScheduleEntity> getWarScheduleEntity() {
        try {
            return Optional.ofNullable(entityDao.queryForId(1L));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

//    public List<WarScheduleEntity> getWarsWithGoodTime() {
//        try {
//            QueryBuilder<WarScheduleEntity, Long> queryBuilder = entityDao.queryBuilder();
//            Where<WarScheduleEntity, Long> where = queryBuilder.where();
//            where.lt("warStart", LocalDateTime.now()).and().gt("warEnd", LocalDateTime.now());
//            return queryBuilder.query();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return Collections.emptyList();
//        }
//    }

    public void deleteWarSchedules() {
        try {
            for (WarScheduleEntity entity : entityDao.queryForAll()) {
                entityDao.delete(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}