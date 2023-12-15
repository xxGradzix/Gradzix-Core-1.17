package me.xxgradzix.gradzixcore.clansExtension.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WAR_STATE;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.War;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class WarEntityManager {
    private Dao<War, Long> entityDao;

    public WarEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, War.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createWar(War entity) {
        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createOrUpdateWar(War entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<War> getWarByID(Long id) {
        try {
            War entity = entityDao.queryForId(id);
            if(entity == null) return Optional.empty();
            return Optional.of(entity);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

//    public List<War> getWarsByGuildId(UUID id, @Nullable WAR_STATE warState) {
//        try {
//            List<War> wars;
//
//            if(warState != null) {
//                Where where = entityDao.queryBuilder().where();
//                wars = where
//                        .or(
//                                where.eq("invader_id", id),
//                                where.eq("invaded_id", id)
//                        )
//                        .and()
//                        .eq("warState", warState)
//                        .query();
//            } else {
//                Where where = entityDao.queryBuilder().where();
//                wars = where
//                        .or(
//                                where.eq("invader_id", id),
//                                where.eq("invaded_id", id)
//                        )
//                        .query();
//            }
//
//            return wars;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
    public List<War> getWarsByGuildId(UUID guildId, WAR_STATE warState) { // TODO warstate
        try {
            QueryBuilder<War, Long> queryBuilder = entityDao.queryBuilder();
            Where<War, Long> where = queryBuilder.where();
            where.or(
                    where.eq("invader_guild_id", guildId),
                    where.eq("invaded_guild_id", guildId)
            );
            return queryBuilder.query();
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    public List<War> getWarByGuildIds(UUID id1, UUID id2, WAR_STATE warState) {
        try {
            QueryBuilder<War, Long> queryBuilder = entityDao.queryBuilder();
            Where<War, Long> where = queryBuilder.where();
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
            return queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<War> getAllWars() {
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