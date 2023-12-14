package me.xxgradzix.gradzixcore.clansExtension.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WAR_STATE;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarRecordEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.*;

public class WarRecordEntityManager {
    private Dao<WarRecordEntity, Long> entityDao;

    public WarRecordEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, WarRecordEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createWarRecordEntity(WarRecordEntity entity) {
        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createOrUpdateWarRecordEntity(WarRecordEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<WarRecordEntity> getWarByID(Long id) {
        try {
            WarRecordEntity entity = entityDao.queryForId(id);
            if(entity == null) return Optional.empty();
            return Optional.of(entity);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void deleteWarRecordEntityById(Long id) {
        try {
            entityDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<WarRecordEntity> getWarRecordsByGuildId(UUID id) {
        try {
            return entityDao.queryForEq("record_owner_id", id);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}