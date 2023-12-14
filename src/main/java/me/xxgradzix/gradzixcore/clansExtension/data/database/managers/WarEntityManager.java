package me.xxgradzix.gradzixcore.clansExtension.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WAR_STATE;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarEntity;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//    public Optional<WarEntity> getWarByID(Long id) {
//        try {
//            WarEntity entity = entityDao.queryForId(id);
//            if(entity == null) return Optional.empty();
//            return Optional.of(entity);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return Optional.empty();
//        }
//    }

    public Set<WarEntity> getInvaderWarsByGuildId(@NotNull UUID guildId, boolean isActive, boolean isFinished) {
        try {
            QueryBuilder<WarEntity, Long> queryBuilder = entityDao.queryBuilder();
            Where<WarEntity, Long> where = queryBuilder.where();
            where.and(
                    where.eq("invader_id", guildId),
                    where.eq("isActive", isActive),
                    where.eq("isFinished", isFinished)
            );
            List<WarEntity> wars = where.query();
            return new HashSet<>(wars);
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
            return Collections.emptySet();
        }
    }
    public Set<WarEntity> getInvadedWarsByGuildId(@NotNull UUID guildId, boolean isActive, boolean isFinished) {
        try {
            QueryBuilder<WarEntity, Long> queryBuilder = entityDao.queryBuilder();
            Where<WarEntity, Long> where = queryBuilder.where();
            where.and(
                    where.eq("invaded_id", guildId),
                    where.eq("isActive", isActive),
                    where.eq("isFinished", isFinished)
            );
            List<WarEntity> wars = where.query();
            return new HashSet<>(wars);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }
    public Set<WarEntity> getWarsByGuildId(@NotNull UUID guildId, boolean isActive, boolean isFinished) {
        Set<WarEntity> invaderWars = getInvaderWarsByGuildId(guildId, isActive, isFinished);
        Set<WarEntity> invadedWars = getInvadedWarsByGuildId(guildId, isActive, isFinished);
        return Stream.concat(invaderWars.stream(), invadedWars.stream()).collect(Collectors.toSet());
    }


    public Set<WarEntity> getWarByGuildIds(@NotNull UUID id1, @NotNull UUID id2, boolean isActive, boolean isFinished) {
        try {

            QueryBuilder<WarEntity, Long> queryBuilder = entityDao.queryBuilder();
            Where<WarEntity, Long> where = queryBuilder.where();


            where.and(
                    where.eq("isActive", isActive),
                    where.eq("isFinished", isFinished),
                    where.or(
                            where.and(
                                    where.eq("invader_id", id1),
                                    where.eq("invaded_id", id2)
                            ),
                            where.and(
                                    where.eq("invader_id", id2),
                                    where.eq("invaded_id", id1)
                            )
                    )
            );

            List<WarEntity> wars = where.query();
            return new HashSet<>(wars);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
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