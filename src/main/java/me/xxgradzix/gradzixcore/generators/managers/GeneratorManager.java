package me.xxgradzix.gradzixcore.generators.managers;

import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import me.xxgradzix.gradzixcore.generators.Generators;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorEntity;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorLocationEntity;
import me.xxgradzix.gradzixcore.generators.data.database.managers.GeneratorEntityManager;
import me.xxgradzix.gradzixcore.generators.data.database.managers.GeneratorLocationEntityManager;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.*;

public class GeneratorManager {

    private final GeneratorLocationEntityManager generatorLocationManager;
    private final GeneratorEntityManager generatorEntityManager;
    private final WorldGuardPlugin worldGuardPlugin;

    public GeneratorManager(GeneratorLocationEntityManager generatorLocationManager, GeneratorEntityManager generatorEntityManager, WorldGuardPlugin worldGuardPlugin) {
        this.generatorLocationManager = generatorLocationManager;
        this.generatorEntityManager = generatorEntityManager;
        this.worldGuardPlugin = worldGuardPlugin;
    }

    // GENERATOR MANAGER

    public List<GeneratorEntity> getAllGenerators() {
        return generatorEntityManager.getAllGenerators();
    }

    public void deleteGeneratorByName(String name) {
        Optional<GeneratorEntity> entity = generatorEntityManager.getGeneratorByName(name);
        if(!entity.isPresent()) return;
        generatorEntityManager.deleteGeneratorById(name);
        generatorLocationManager.deleteAllGeneratorLocationsByGenerator(entity.get());
    }

    public void createGenerator(String name, int cooldown, ArrayList<Material> materials) {
        GeneratorEntity entity = new GeneratorEntity(name, cooldown, materials);
        createGenerator(entity);
    }
    public void createGenerator(GeneratorEntity entity) {
        generatorEntityManager.createGenerator(entity);
    }

    public Optional<GeneratorEntity> getGeneratorByName(String userInputName) {
        return generatorEntityManager.getGeneratorByName(userInputName);
    }

    // GENERATOR LOCATION MANAGER

    public void createGeneratorLocation(Region selection, GeneratorEntity generator, UUID worldUUID, Location min, Location max) throws StorageException {
        GeneratorLocationEntity entity = new GeneratorLocationEntity(generator, worldUUID, min, max);
        createGeneratorLocation(selection, entity);
    }
    public void createGeneratorLocation(Region selection, GeneratorLocationEntity generatorLocation) throws StorageException {
        generatorLocationManager.createOrUpdateGeneratorLocation(generatorLocation);

        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(selection.getWorld());

        ProtectedCuboidRegion newRegion = new ProtectedCuboidRegion(Generators.GENERATOR_REGION_PREFIX + generatorLocation.getId(),
                selection.getMinimumPoint(),
                selection.getMaximumPoint());


        regionManager.addRegion(newRegion);
        regionManager.save();

    }


    public List<GeneratorLocationEntity> getAllGeneratorLocationsByWorldUUID(UUID uid) {
        return generatorLocationManager.getAllGeneratorLocationsByWorldUUID(uid);
    }

    public void deleteGeneratorLocationById(Long id) {
        generatorLocationManager.deleteGeneratorLocationById(id);
    }
}
