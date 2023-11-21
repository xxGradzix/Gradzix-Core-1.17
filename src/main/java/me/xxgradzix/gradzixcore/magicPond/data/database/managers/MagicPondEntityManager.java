package me.xxgradzix.gradzixcore.magicPond.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.magicPond.data.database.entities.MagicPondEntity;

import java.sql.SQLException;
import java.util.HashMap;

public class MagicPondEntityManager {
    private Dao<MagicPondEntity, Integer> entityDao;

    public MagicPondEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, MagicPondEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateMagicPondEntity(MagicPondEntity magicPondEntity) {
        try {
            magicPondEntity.setId();
            entityDao.createOrUpdate(magicPondEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MagicPondEntity getMagicPondEntity() {
        try {
            MagicPondEntity magicPondEntity = entityDao.queryForId(1);
            if(magicPondEntity == null) {

                magicPondEntity =  new MagicPondEntity(new HashMap<>());
                createOrUpdateMagicPondEntity(magicPondEntity);
            }
            return magicPondEntity;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}