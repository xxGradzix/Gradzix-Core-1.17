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

    public Set<WarEntity> getWarsByGuildId(@NotNull UUID guildId, @Nullable WAR_STATE warState) {
        try {
            QueryBuilder<WarEntity, Long> queryBuilder = entityDao.queryBuilder();
            Where<WarEntity, Long> where = queryBuilder.where();

            where.or(
                    where.eq("invader_id", guildId),
                    where.eq("invaded_id", guildId)
            );

            List<WarEntity> wars = where.query();

            if(warState != null) wars.stream().filter(war -> war.getWarState().equals(warState));

            return new HashSet<>(queryBuilder.query());
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
            return Collections.emptySet();
        }
    }
    public Set<WarEntity> getInvaderWarsByGuildId(@NotNull UUID guildId, @Nullable WAR_STATE warState) {
        try {
            QueryBuilder<WarEntity, Long> queryBuilder = entityDao.queryBuilder();
            Where<WarEntity, Long> where = queryBuilder.where();
            where.eq("invader_id", guildId);

            List<WarEntity> wars = where.query();


            if(warState != null) wars.stream().filter(war -> war.getWarState().equals(warState));
            List<WarEntity> query = queryBuilder.query();
            return new HashSet<>(query);
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
            return Collections.emptySet();
        }
    }
    public Set<WarEntity> getActiveWarsByGuildId(@NotNull UUID guildId) {
        try {
//            QueryBuilder<WarEntity, Long> queryBuilder = entityDao.queryBuilder();
//            Where<WarEntity, Long> where = queryBuilder.where();
//            where.or(
//                    where.eq("invader_id", guildId),
//                    where.eq("invaded_id", guildId)
//            );
//            where.and().or(
//                    where.eq("warState", WAR_STATE.CURRENT),
//                    where.eq("warState", WAR_STATE.FUTURE)
//            );
//            List<WarEntity> wars = queryBuilder.query();

            Where<WarEntity, Long> where = entityDao.queryBuilder()
                    .where();
            where.or(
                    where.eq("invader_id", guildId),
                    where.eq("invaded_id", guildId)
            );

            List<WarEntity> wars = where.query();

            wars.stream().filter(war -> war.getWarState().equals(WAR_STATE.CURRENT) || war.getWarState().equals(WAR_STATE.FUTURE));

            return new HashSet<>(wars);
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
            return Collections.emptySet();
        }
    }


    public Set<WarEntity> getWarByGuildIds(@NotNull UUID id1, @NotNull UUID id2, @Nullable WAR_STATE warState) {
//        try {
//            QueryBuilder<WarEntity, Long> queryBuilder = entityDao.queryBuilder();
//            Where<WarEntity, Long> where = queryBuilder.where();
//
//            where.or(
//                    where.eq("invader_id", id1),
//                    where.eq("invaded_id", id2)
//            );
//            where.and().or(
//                    where.eq("invader_id", id2),
//                    where.eq("invaded_id", id1)
//            );

            Set<WarEntity> wars = getActiveWarsByGuildId(id1);
            // get only wars where only guilds id1 and id2 are involved

        Stream<WarEntity> warEntityStream = wars.stream().filter(war -> (
                (war.getInvaderGuildId().equals(id1) && war.getInvadedGuildId().equals(id2))
                        || (war.getInvaderGuildId().equals(id2) && war.getInvadedGuildId().equals(id1))));
        // TODO nie bierze wojny miedzy graczami

        if(warState != null) warEntityStream.filter(war -> war.getWarState().equals(warState));

            return new HashSet<>(warEntityStream.collect(Collectors.toSet()));
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return Collections.emptySet();
//        }
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