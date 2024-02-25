package me.xxgradzix.gradzixcore.clansCore.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserEntityManager {

    private Dao<UserEntity, UUID> entityDao;

    public UserEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, UserEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateUserEntity(UserEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<UserEntity> geUserEntityByUUID(UUID id) {
        try {
            UserEntity entity = entityDao.queryForId(id);

            if(entity == null) return Optional.empty();

            return Optional.of(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<UserEntity> geUserEntityByName(String name) {
        try {
            UserEntity entity = entityDao.queryForEq("name", name).stream().findFirst().orElse(null);

            if(entity == null) return Optional.empty();

            return Optional.of(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<UserEntity> getAllUserEntities() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}