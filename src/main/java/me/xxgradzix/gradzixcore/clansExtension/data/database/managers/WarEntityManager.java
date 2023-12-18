package me.xxgradzix.gradzixcore.clansExtension.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WAR_STATE;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.*;

public class WarEntityManager {
    private Dao<WarEntity, Long> entityDao;

    public WarEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, WarEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createWar(WarEntity entity) {
        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createOrUpdateWar(WarEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<WarEntity> getWarByID(Long id) {
        try {
            WarEntity entity = entityDao.queryForId(id);
            if(entity == null) return Optional.empty();
            return Optional.of(entity);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<WarEntity> getWarsByGuildId(@NotNull UUID guildId, @Nullable WAR_STATE warState) {
        try {
            QueryBuilder<WarEntity, Long> queryBuilder = entityDao.queryBuilder();
            Where<WarEntity, Long> where = queryBuilder.where();
            where.or(
                    where.eq("invader_id", guildId),
                    where.eq("invaded_id", guildId)
            );
            if(warState != null) where.and().eq("warState", warState);

            return queryBuilder.query();
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    public List<WarEntity> getActiveWarsByGuildId(@NotNull UUID guildId) {
        try {
            QueryBuilder<WarEntity, Long> queryBuilder = entityDao.queryBuilder();
            Where<WarEntity, Long> where = queryBuilder.where();
            where.or(
                    where.eq("invader_id", guildId),
                    where.eq("invaded_id", guildId)
            );
            where.and().or(
                    where.eq("warState", WAR_STATE.CURRENT),
                    where.eq("warState", WAR_STATE.FUTURE)
            );

            return queryBuilder.query();
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    public List<WarEntity> getWarByGuildIds(@NotNull UUID id1, @NotNull UUID id2, @Nullable WAR_STATE warState) {
        try {
            QueryBuilder<WarEntity, Long> queryBuilder = entityDao.queryBuilder();
            Where<WarEntity, Long> where = queryBuilder.where();
            where.and(
                    where.or(
                            where.eq("invader_id", id1),
                            where.eq("invaded_id", id1)
                    ),
                    where.or(
                            where.eq("invader_id", id2),
                            where.eq("invaded_id", id2)
                    )
            );
            if(warState != null) where.and().eq("warState", warState);

            return queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<WarEntity> getAllWars() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public void deleteWarById(Long id) {
        try {
            entityDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}