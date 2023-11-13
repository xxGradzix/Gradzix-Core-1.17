package me.xxgradzix.gradzixcore.generators;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.generators.commands.CreateGeneratorCommand;
import me.xxgradzix.gradzixcore.generators.commands.RefillGenerators;
import me.xxgradzix.gradzixcore.generators.commands.SetGeneratorCommand;
import me.xxgradzix.gradzixcore.generators.commands.ShowGeneratorsCommand;
import me.xxgradzix.gradzixcore.generators.data.database.entities.Generator;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorLocation;
import me.xxgradzix.gradzixcore.generators.data.database.managers.GeneratorLocationManager;
import me.xxgradzix.gradzixcore.generators.data.database.managers.GeneratorManager;
import me.xxgradzix.gradzixcore.generators.items.ItemManager;

import java.sql.SQLException;

public final class Generators {

    private final Gradzix_Core plugin;
    private final ConnectionSource connectionSource;
    private final WorldEditPlugin worldEditPlugin;

    private GeneratorManager generatorManager;
    private GeneratorLocationManager generatorLocationManager;

    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, Generator.class);
        TableUtils.createTableIfNotExists(connectionSource, GeneratorLocation.class);
        generatorManager = new GeneratorManager(connectionSource);
        generatorLocationManager = new GeneratorLocationManager(connectionSource);
    }

    public Generators(Gradzix_Core plugin, WorldEditPlugin worldEditPlugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.worldEditPlugin = worldEditPlugin;
        this.connectionSource = connectionSource;
    }

    public void onEnable() {
        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ItemManager.init();
//        plugin.getServer().getPluginManager().registerEvents(new PrioritiesGuiClick(), plugin);
        plugin.getCommand("showGenerators").setExecutor(new ShowGeneratorsCommand(generatorManager, generatorLocationManager));
        plugin.getCommand("refreshGenerators").setExecutor(new RefillGenerators(plugin, worldEditPlugin, generatorLocationManager));
        plugin.getCommand("createGenerator").setExecutor(new CreateGeneratorCommand(worldEditPlugin, generatorManager));
        plugin.getCommand("setGenerator").setExecutor(new SetGeneratorCommand(worldEditPlugin, generatorManager, generatorLocationManager));
    }

    public void onDisable() {
        // Plugin shutdown logic
    }
}
