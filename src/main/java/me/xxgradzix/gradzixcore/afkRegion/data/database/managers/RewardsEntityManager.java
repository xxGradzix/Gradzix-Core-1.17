package me.xxgradzix.gradzixcore.afkRegion.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.afkRegion.data.database.entities.RewardsEntity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class RewardsEntityManager {
    private Dao<RewardsEntity, Integer> entityDao;

    public RewardsEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, RewardsEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateRewardsEntity(RewardsEntity entity) {
        try {
            entity.setId(1);
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RewardsEntity getRewardsEntity() {
        try {
            RewardsEntity entity = entityDao.queryForId(1);
            if(entity == null) {
                entity =  new RewardsEntity(new ItemStack(Material.DIAMOND), new ItemStack(Material.DIAMOND));
                createOrUpdateRewardsEntity(entity);
            }
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRewardsEntity(RewardsEntity entity) {
        try {
            entity.setId(1);
            entityDao.update(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}