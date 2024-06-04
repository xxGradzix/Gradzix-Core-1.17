package me.xxgradzix.gradzixcore.abstraction;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.entities.VPLNOrderEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopCategoryEntity;

import java.sql.SQLException;
import java.util.List;

public abstract class BasicEntityManager<T, ID> {

    private Dao<T, ID> entityDao;

    public BasicEntityManager(ConnectionSource connectionSource, Class<T> entityClass) {
        try {
            entityDao = DaoManager.createDao(connectionSource, entityClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateEntity(T entity) throws SQLException {
        entityDao.createOrUpdate(entity);
    }

    public void deleteEntity(T entity) throws SQLException {
        entityDao.delete(entity);
    }

    public T getEntityById(ID id) throws SQLException {
        return entityDao.queryForId(id);
    }

    public List<T> getAllEntities() throws SQLException {
        return entityDao.queryForAll();
    }

    public List<T> getAllEntitiesByField(String fieldName, Object value) throws SQLException {
        return entityDao.queryForEq(fieldName, value);
    }
}
