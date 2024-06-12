package me.xxgradzix.gradzixcore.generators;

import com.fastasyncworldedit.core.Fawe;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.generators.commands.CreateGeneratorCommand;
import me.xxgradzix.gradzixcore.generators.commands.RefillGenerators;
import me.xxgradzix.gradzixcore.generators.commands.SetGeneratorCommand;
import me.xxgradzix.gradzixcore.generators.commands.ShowGeneratorsCommand;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorEntity;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorLocationEntity;
import me.xxgradzix.gradzixcore.generators.data.database.managers.GeneratorLocationEntityManager;
import me.xxgradzix.gradzixcore.generators.data.database.managers.GeneratorEntityManager;
import me.xxgradzix.gradzixcore.generators.items.ItemManager;
import me.xxgradzix.gradzixcore.generators.managers.GeneratorManager;

import java.sql.SQLException;

public final class Generators {

    public static final String GENERATOR_REGION_PREFIX = "generator_region_";


    private final Gradzix_Core plugin;
    private final ConnectionSource connectionSource;
    public WorldEdit worldEdit = Fawe.instance().getWorldEdit();
    private final WorldGuardPlugin worldGuardPlugin;

    private GeneratorEntityManager generatorEntityManager;
    private GeneratorLocationEntityManager generatorLocationEntityManager;

    private GeneratorManager generatorManager;
    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, GeneratorEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, GeneratorLocationEntity.class);
        generatorEntityManager = new GeneratorEntityManager(connectionSource);
        generatorLocationEntityManager = new GeneratorLocationEntityManager(connectionSource);
    }

    public Generators(Gradzix_Core plugin, WorldGuardPlugin worldGuardPlugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
        this.worldGuardPlugin = worldGuardPlugin;
    }

    public void onEnable() {
        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        generatorManager = new GeneratorManager(generatorLocationEntityManager, generatorEntityManager, worldGuardPlugin);

        ItemManager.init();
//        plugin.getServer().getPluginManager().registerEvents(new PrioritiesGuiClick(), plugin);
        plugin.getCommand("showGenerators").setExecutor(new ShowGeneratorsCommand(generatorManager));
        plugin.getCommand("refreshGenerators").setExecutor(new RefillGenerators(plugin, worldEdit, generatorManager));
        plugin.getCommand("createGenerator").setExecutor(new CreateGeneratorCommand(generatorManager));
        plugin.getCommand("setGenerator").setExecutor(new SetGeneratorCommand(generatorManager));
    }

    public void onDisable() {
        // Plugin shutdown logic
    }
}
