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

    public void createOrUpdateGeneratorLocation(Region selection, GeneratorLocation entity) {
        try {
            entityDao.createOrUpdate(entity);

            ProtectedCuboidRegion newRegion = new ProtectedCuboidRegion("generator",
                    selection.getMinimumPoint(),
                    selection.getMaximumPoint());

            RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(entity.getMinLocation().getWorld()));


            System.out.println(newRegion.toString());
            regionManager.addRegion(newRegion);

            regionManager.save();
        } catch (SQLException | StorageException e) {
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
//            entityDao.delete(getAllGeneratorLocationsByGenerator(generator));
            for (GeneratorLocation generatorLocation : getAllGeneratorLocationsByGenerator(generator)) {
                RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(generatorLocation.getMinLocation().getWorld()));
                regionManager.removeRegion("generator");
                regionManager.save();
                entityDao.delete(generatorLocation);
            }

        } catch (StorageException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteGeneratorLocationById(Long id) {

        try {
            Optional<GeneratorLocation> generatorLocation = getGeneratorLocationByID(id);
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