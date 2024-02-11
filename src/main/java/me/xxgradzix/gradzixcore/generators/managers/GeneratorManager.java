package me.xxgradzix.gradzixcore.generators.managers;

import com.sk89q.worldedit.regions.Region;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
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
    public GeneratorManager(GeneratorLocationEntityManager generatorLocationManager, GeneratorEntityManager generatorEntityManager) {
        this.generatorLocationManager = generatorLocationManager;
        this.generatorEntityManager = generatorEntityManager;
    }

    public void createGenerator(String name, int cooldown, ArrayList<Material> materials) {
        GeneratorEntity entity = new GeneratorEntity(name, cooldown, materials);
        createGenerator(entity);
    }
    public void createGenerator(GeneratorEntity entity) {
        generatorEntityManager.createGenerator(entity);
    }

//    public Optional<GeneratorEntity> getGeneratorByID(Long userInputId) {
//        return generatorEntityManager.getGeneratorByID(userInputId);
//    }

    public Optional<GeneratorEntity> getGeneratorByName(String userInputName) {
        return generatorEntityManager.getGeneratorByName(userInputName);
    }

    public void createGeneratorLocation(Region selection, GeneratorEntity generator, UUID worldUUID, Location min, Location max) {
        GeneratorLocationEntity entity = new GeneratorLocationEntity(generator, worldUUID, min, max);
        createGeneratorLocation(entity);
    }
    public void createGeneratorLocation(GeneratorLocationEntity generatorLocation) {
        generatorLocationManager.createOrUpdateGeneratorLocation(generatorLocation);
    }

    public List<GeneratorEntity> getAllGenerators() {
        return generatorEntityManager.getAllGenerators();
    }

    public List<GeneratorLocationEntity> getAllGeneratorLocationsByWorldUUID(UUID uid) {
        return generatorLocationManager.getAllGeneratorLocationsByWorldUUID(uid);
    }

    public void deleteGeneratorByName(String name) {
        Optional<GeneratorEntity> entity = generatorEntityManager.getGeneratorByName(name);
        if(!entity.isPresent()) return;
        generatorEntityManager.deleteGeneratorById(name);
        generatorLocationManager.deleteAllGeneratorLocationsByGenerator(entity.get());
    }

    public void deleteGeneratorLocationById(Long id) {
        generatorLocationManager.deleteGeneratorLocationById(id);
    }
}
