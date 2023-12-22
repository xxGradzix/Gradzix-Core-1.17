package me.xxgradzix.gradzixcore.clansExtension.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerk;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.PerkModifierEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarRecordEntity;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PerkModifierEntityManager {
    private static Dao<PerkModifierEntity, ClanPerk> entityDao;

    public PerkModifierEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, PerkModifierEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createPerkModifierEntityEntity(PerkModifierEntity entity) {
        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createOrUpdateWarRecordEntity(PerkModifierEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Optional<PerkModifierEntity> getPerkModifierEntityByID(ClanPerk id) {
        try {
            PerkModifierEntity entity = entityDao.queryForId(id);
            if(entity == null) return Optional.empty();
            return Optional.of(entity);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static void deletePerkModifierEntityById(Long id) {
        try {
            entityDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}