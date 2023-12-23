package me.xxgradzix.gradzixcore.clansExtension.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerksEntity;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.*;

public class ClanPerksEntityManager {
    private Dao<ClanPerksEntity, UUID> entityDao;

    public ClanPerksEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, ClanPerksEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createClanPerksEntity(ClanPerksEntity entity) {
        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createOrUpdateClanPerksEntity(ClanPerksEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ClanPerksEntity getClanPerksEntityByID(@NotNull UUID id) {
        try {
            ClanPerksEntity entity = entityDao.queryForId(id);
            if(entity == null) {
                entity = new ClanPerksEntity(id);
                createOrUpdateClanPerksEntity(entity);
            }
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ClanPerksEntity> getClanPerksEntity() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    public void deleteClanPerksEntityById(UUID id) {
        try {
            entityDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}