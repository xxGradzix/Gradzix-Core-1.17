package me.xxgradzix.gradzixcore.socialMediaRewards.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.socialMediaRewards.data.database.entities.SocialMediaRewardsEntity;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class SocialMediaRewardsEntityManager {
    private Dao<SocialMediaRewardsEntity, UUID> entityDao;

    public SocialMediaRewardsEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, SocialMediaRewardsEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateSocialMediaRewardsEntity(SocialMediaRewardsEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<SocialMediaRewardsEntity> getSocialMediaRewardsEntity(UUID id) {
        try {

            SocialMediaRewardsEntity entity = entityDao.queryForId(id);

            return entity == null ? Optional.empty() : Optional.of(entity);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteSocialMediaRewardsEntity(SocialMediaRewardsEntity entity) {
        try {
            entityDao.delete(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}