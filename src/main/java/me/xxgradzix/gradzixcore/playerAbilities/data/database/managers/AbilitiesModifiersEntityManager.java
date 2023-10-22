package me.xxgradzix.gradzixcore.playerAbilities.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.AbilityModifierEntity;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;

import java.sql.SQLException;

public class AbilitiesModifiersEntityManager {

    private Dao<AbilityModifierEntity, Ability> entityDao;

    public AbilitiesModifiersEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, AbilityModifierEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateAbilityModifierEntity(AbilityModifierEntity entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AbilityModifierEntity getAbilityModifierEntityByAbilityType(Ability ability) {
        try {

            AbilityModifierEntity entity = entityDao.queryForId(ability);
            if(entity == null) {
                entity = new AbilityModifierEntity(ability,
                        1.1, 1.2, 1.3, 1.4,
                        64, 128, 192, 256);
                createOrUpdateAbilityModifierEntity(entity);
            }

            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateAbilityModifierEntity(AbilityModifierEntity entity) {
        try {
            entityDao.update(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAbilityModifierEntity(AbilityModifierEntity entity) {
        try {
            entityDao.delete(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}