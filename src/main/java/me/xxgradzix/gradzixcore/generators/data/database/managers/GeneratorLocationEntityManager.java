package me.xxgradzix.gradzixcore.generators.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorEntity;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorLocationEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GeneratorLocationEntityManager {
    private Dao<GeneratorLocationEntity, Long> entityDao;

    public GeneratorLocationEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, GeneratorLocationEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateGeneratorLocation(GeneratorLocationEntity entity) {
        try {
            entityDao.createOrUpdate(entity);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<GeneratorLocationEntity> getGeneratorLocationByID(Long id) {
        try {
            GeneratorLocationEntity entity = entityDao.queryForId(id);
            if(entity == null) return Optional.empty();
            return Optional.of(entity);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public List<GeneratorLocationEntity> getAllGeneratorLocationsByWorldUUID(UUID worldUUID) {
        try {
            return entityDao.queryForEq("worldUUID", worldUUID);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<GeneratorLocationEntity> getAllGeneratorLocationsByGenerator(GeneratorEntity generator) {
        try {
            return entityDao.queryForEq("generator_name", generator.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public void deleteAllGeneratorLocationsByGenerator(GeneratorEntity generator) {
        try {
//            entityDao.delete(getAllGeneratorLocationsByGenerator(generator));
            for (GeneratorLocationEntity generatorLocation : getAllGeneratorLocationsByGenerator(generator)) {

                entityDao.delete(generatorLocation);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteGeneratorLocationById(Long id) {

        try {
            Optional<GeneratorLocationEntity> generatorLocation = getGeneratorLocationByID(id);
            if(generatorLocation.isPresent()) {
                RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(generatorLocation.get().getMinLocation().getWorld()));

                regionManager.removeRegion("generator");
                regionManager.save();
            }
            entityDao.deleteById(id);

        } catch (StorageException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}