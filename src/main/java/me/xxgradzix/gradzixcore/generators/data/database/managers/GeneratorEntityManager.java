package me.xxgradzix.gradzixcore.generators.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeneratorEntityManager {
    private Dao<GeneratorEntity, String> entityDao;

    public GeneratorEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, GeneratorEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createGenerator(GeneratorEntity entity) {
        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    public Optional<GeneratorEntity> getGeneratorByID(Long id) {
//        try {
//            GeneratorEntity entity = entityDao.queryForId(id);
//            if(entity == null) return Optional.empty();
//            return Optional.of(entity);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return Optional.empty();
//        }
//    }
    public Optional<GeneratorEntity> getGeneratorByName(String name) {
        try {
            GeneratorEntity entity = entityDao.queryForId(name);
            if(entity == null) return Optional.empty();
            return Optional.of(entity);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<GeneratorEntity> getAllGenerators() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public void deleteGeneratorById(String id) {
        try {
            entityDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}