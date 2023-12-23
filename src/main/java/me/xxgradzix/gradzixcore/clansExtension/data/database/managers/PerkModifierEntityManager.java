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
    public static PerkModifierEntity getPerkModifierEntityByID(ClanPerk id) {
        try {
            PerkModifierEntity entity = entityDao.queryForId(id);
            if(entity == null) {
                switch (id) {
                    case WAR_AMOUNT:
                        entity = new PerkModifierEntity(id,
                                1,
                                2,
                                3,
                                4,
                                64,
                                126,
                                256,
                                512);
                        break;
                    case RANK:
                        entity = new PerkModifierEntity(id,
                                5,
                                10,
                                15,
                                20,
                                64,
                                126,
                                256,
                                512);
                        break;
                    default: throw new IllegalArgumentException("Unknown ClanPerk: " + id);
                }
                createOrUpdateWarRecordEntity(entity);
            }
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deletePerkModifierEntityById(ClanPerk id) {
        try {
            entityDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}