package me.xxgradzix.gradzixcore.generators.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.generators.data.database.entities.Generator;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorLocation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GeneratorLocationManager {
    private Dao<GeneratorLocation, Long> entityDao;

    public GeneratorLocationManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, GeneratorLocation.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateGeneratorLocation(GeneratorLocation entity) {
        try {
            entityDao.createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<GeneratorLocation> getGeneratorLocationByID(Long id) {
        try {
            GeneratorLocation entity = entityDao.queryForId(id);
            if(entity == null) return Optional.empty();
            return Optional.of(entity);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public List<GeneratorLocation> getAllGeneratorLocationsByWorldUUID(UUID worldUUID) {
        try {
            return entityDao.queryForEq("worldUUID", worldUUID);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<GeneratorLocation> getAllGeneratorLocationsByGenerator(Generator generator) {
        try {
            return entityDao.queryForEq("generator_id", generator.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public void deleteAllGeneratorLocationsByGenerator(Generator generator) {
        try {
            entityDao.delete(getAllGeneratorLocationsByGenerator(generator));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteGeneratorLocationById(Long id) {
        try {
            entityDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}