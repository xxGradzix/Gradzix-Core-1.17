package me.xxgradzix.gradzixcore.serverconfig;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.Getter;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.serverconfig.commands.AgePlayItemShopCommand;
import me.xxgradzix.gradzixcore.serverconfig.commands.BazarWystawCommand;
import me.xxgradzix.gradzixcore.serverconfig.commands.SetArmorAttributeOnItem;
import me.xxgradzix.gradzixcore.serverconfig.commands.GammaCommand;
import me.xxgradzix.gradzixcore.serverconfig.commands.SetDamageCommand;
import me.xxgradzix.gradzixcore.serverconfig.data.configfiles.ConfigServera;
import me.xxgradzix.gradzixcore.serverconfig.data.database.entities.ServerConfigEntity;
import me.xxgradzix.gradzixcore.serverconfig.data.database.managers.ServerConfigEntityManager;
import me.xxgradzix.gradzixcore.serverconfig.listeners.BlockPlacingBlocks;
import me.xxgradzix.gradzixcore.serverconfig.listeners.elytraAndFireworkBlock.ElytraAndFallDamageDecrease;
import me.xxgradzix.gradzixcore.serverconfig.listeners.elytraAndFireworkBlock.OnTotemBreakBlockFirework;
import me.xxgradzix.gradzixcore.serverconfig.listeners.protectionEnchantRework.DamageEvent;
import me.xxgradzix.gradzixcore.serverconfig.listeners.FortuneSheers;
import me.xxgradzix.gradzixcore.serverconfig.listeners.VanishingPotionBottle;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServerConfig {

    private final Gradzix_Core plugin;
    private final ConnectionSource connectionSource;

    @Getter
    private static ServerConfigEntityManager serverConfigEntityManager;

    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, ServerConfigEntity.class);
        serverConfigEntityManager = new ServerConfigEntityManager(connectionSource);
    }
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

        plugin.getServer().getPluginManager().registerEvents(new VanishingPotionBottle(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnTotemBreakBlockFirework(), plugin);

        plugin.getServer().getPluginManager().registerEvents(new ElytraAndFallDamageDecrease(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new FortuneSheers(), plugin);

        BlockPlacingBlocks blockPlacingBlocks = new BlockPlacingBlocks(plugin);
        blockPlacingBlocks.runTaskTimer(plugin, 0L, 20L * 5);
        plugin.getServer().getPluginManager().registerEvents(blockPlacingBlocks, plugin);

        plugin.getCommand("setserverdamagemultiplier").setExecutor(new SetDamageCommand());
        plugin.getCommand("bazarwystaw").setExecutor(new BazarWystawCommand());
        plugin.getCommand("ais").setExecutor(new AgePlayItemShopCommand());
        plugin.getCommand("setarmorattribute").setExecutor(new SetArmorAttributeOnItem());
        plugin.getCommand("gamma").setExecutor(new GammaCommand());

        ConfigServera.setup();
        ConfigServera.getCustomFile().set("damageMultiplier", 1);
        ConfigServera.getCustomFile().set("itemPriorities", new ArrayList<>());
        ConfigServera.getCustomFile().options().copyDefaults(true);

        ConfigServera.save();

    }

}
