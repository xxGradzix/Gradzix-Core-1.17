package me.xxgradzix.gradzixcore.serverconfig;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.serverconfig.PriorytetyGui.PrioritiesGuiClick;
import me.xxgradzix.gradzixcore.serverconfig.PriorytetyGui.PrioritiesGuiClose;
import me.xxgradzix.gradzixcore.serverconfig.commands.AgePlayItemShopCommand;
import me.xxgradzix.gradzixcore.serverconfig.commands.BazarWystawCommand;
import me.xxgradzix.gradzixcore.serverconfig.commands.SetDamageCommand;
import me.xxgradzix.gradzixcore.serverconfig.commands.SetPrioritiesCommand;
import me.xxgradzix.gradzixcore.serverconfig.data.configfiles.ConfigServera;
import me.xxgradzix.gradzixcore.serverconfig.data.database.entities.ServerConfigEntity;
import me.xxgradzix.gradzixcore.serverconfig.data.database.managers.ServerConfigEntityManager;
import me.xxgradzix.gradzixcore.serverconfig.listeners.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServerConfig {

    private Gradzix_Core plugin;

    // db change
    private ConnectionSource connectionSource;

    private static ServerConfigEntityManager serverConfigEntityManager;

    public static ServerConfigEntityManager getServerConfigEntityManager() {
        return serverConfigEntityManager;
    }
    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, ServerConfigEntity.class);
        serverConfigEntityManager = new ServerConfigEntityManager(connectionSource);
    }
    ////////////
    public ServerConfig(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        plugin.getServer().getPluginManager().registerEvents(new DamageEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ElytraBLock(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerFireworkListener(), plugin);
//        plugin.getServer().getPluginManager().registerEvents(new PickUpPriority(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new VanishingPotionBottle(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PrioritiesGuiClick(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PrioritiesGuiClose(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GiveArmorBackEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new StrefaAFK(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new LoginCooldown(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new FortuneSheers(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnPlayerJoinStrefaAfk(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BlockPlacingBlocks(), plugin);
        plugin.getCommand("setserverdamagemultiplier").setExecutor(new SetDamageCommand());
//        plugin.getCommand("bazar").setExecutor(new BazarWystawCommand());
        plugin.getCommand("bazarwystaw").setExecutor(new BazarWystawCommand());
        plugin.getCommand("setpriorities").setExecutor(new SetPrioritiesCommand());
        plugin.getCommand("ais").setExecutor(new AgePlayItemShopCommand());

        ConfigServera.setup();
        ConfigServera.getCustomFile().set("damageMultiplier", 1);
        ConfigServera.getCustomFile().set("itemPriorities", new ArrayList<>());
        ConfigServera.getCustomFile().options().copyDefaults(true);

        ConfigServera.save();

    }

}
