package me.xxgradzix.gradzixcore.generators.commands;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.function.pattern.RandomPattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.generators.Generators;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorEntity;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorLocationEntity;
import me.xxgradzix.gradzixcore.generators.managers.GeneratorManager;
import me.xxgradzix.gradzixcore.generators.managers.HologramManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RefillGenerators implements CommandExecutor {

    private final Gradzix_Core plugin;
    private final GeneratorManager generatorManager;
    private final BukkitScheduler scheduler = Bukkit.getScheduler();
    private static final ArrayList<Integer> taskIds = new ArrayList<>();

    private WorldEdit worldEdit;

    public RefillGenerators(Gradzix_Core plugin, WorldEdit worldEdit, GeneratorManager generatorManager) {
        this.plugin = plugin;
        this.generatorManager = generatorManager;
        this.worldEdit = worldEdit;
        refreshRefilling();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("Odswiezyles dzialanie generatorow");
        }

        refreshRefilling();
        return false;
    }
    private void refreshRefilling() {

        cancelTask();

        List<World> worlds = plugin.getServer().getWorlds();

        for(World world : worlds) {

            List<GeneratorLocationEntity> generators = generatorManager.getAllGeneratorLocationsByWorldUUID(world.getUID());
            List<GeneratorLocationEntity> threeMinGenerators = generators.stream().filter(generatorLocation -> generatorLocation.getGenerator().getCoolDownSeconds() <=240).collect(Collectors.toList());
            List<GeneratorLocationEntity> fiveMinGenerators = new ArrayList<>(generators);
            fiveMinGenerators.removeAll(threeMinGenerators);

//            EditSession editSession = createGeneralEditSession(BukkitAdapter.adapt(world));
            int threeMinuteTaskId = scheduler.runTaskTimer(plugin, () -> {

                for(GeneratorLocationEntity generator : threeMinGenerators) {

                    if(generator == null) continue;

                    GeneratorEntity generatorType = generator.getGenerator();

                    if(generatorType == null || generatorType.getMaterials().isEmpty()) continue;

                    Location minLocation = generator.getMinLocation();
                    Location maxLocation = generator.getMaxLocation();

                    List<Material> materials = generatorType.getMaterials();

                    fillTerrain(world, minLocation, maxLocation, materials);

                    Location location = new Location(world,(minLocation.getX() + maxLocation.getX())/2, maxLocation.getY() + 2, (minLocation.getZ() + maxLocation.getZ())/2);
                    HologramManager.addHologram(location, generatorType);
                }

            }, 0, 20L * Gradzix_Core.FAST_GENERATOR_REGENERATION_TIME_SECONDS).getTaskId();

            int fiveMinuteTaskId = scheduler.runTaskTimer(plugin, () -> {

                for(GeneratorLocationEntity generator : fiveMinGenerators) {

                    if(generator == null) continue;

                    GeneratorEntity generatorType = generator.getGenerator();

                    if(generatorType == null || generatorType.getMaterials().isEmpty()) continue;

                    Location minLocation = generator.getMinLocation();
                    Location maxLocation = generator.getMaxLocation();

                    List<Material> materials = generatorType.getMaterials();

                    fillTerrain(world, minLocation, maxLocation, materials);
                    Location location = new Location(world,(minLocation.getX() + maxLocation.getX())/2, maxLocation.getY() + 2, (minLocation.getZ() + maxLocation.getZ())/2);
                    HologramManager.addHologram(location, generatorType);
                }

            }, 0, 20L * Gradzix_Core.SLOW_GENERATOR_REGENERATION_TIME_SECONDS).getTaskId();

            taskIds.add(threeMinuteTaskId);
            taskIds.add(fiveMinuteTaskId);
        }
    }
    private void cancelTask() {
        for (Integer id : taskIds) {
            scheduler.cancelTask(id);
        }
        taskIds.clear();
    }
    private void fillTerrain(World world, Location minLocation, Location maxLocation, List<Material> materials) {

        com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(world);

        EditSession editSession = worldEdit.newEditSession(adaptedWorld);

        Region region = new CuboidRegion(adaptedWorld, BlockVector3.at(minLocation.getBlockX(), minLocation.getBlockY(), minLocation.getBlockZ()), BlockVector3.at(maxLocation.getBlockX(), maxLocation.getBlockY(), maxLocation.getBlockZ()));
        editSession.setBlocks(region, createRandomPattern(materials));
        editSession.commit();
        editSession.close();
    }

    private RandomPattern createRandomPattern(List<Material> materials) {
        RandomPattern randomPattern = new RandomPattern();
        for(Material material : materials) {
            BlockType blockType = BlockTypes.get(material.name().toLowerCase());
            if(blockType == null) continue;
            randomPattern.add(blockType.getDefaultState().toBaseBlock(), 1);
        }
        return randomPattern;
    }
}
