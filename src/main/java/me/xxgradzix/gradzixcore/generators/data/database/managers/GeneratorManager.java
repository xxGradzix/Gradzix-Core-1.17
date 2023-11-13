package me.xxgradzix.gradzixcore.generators.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.generators.data.database.entities.Generator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeneratorManager {
    private Dao<Generator, Long> entityDao;

    public GeneratorManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, Generator.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createGenerator(Generator entity) {
        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<Generator> getGeneratorByID(Long id) {
        try {
            Generator entity = entityDao.queryForId(id);
            if(entity == null) return Optional.empty();
            return Optional.of(entity);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Generator> getAllGenerators() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public void deleteGeneratorById(Long id) {
        try {
            entityDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}