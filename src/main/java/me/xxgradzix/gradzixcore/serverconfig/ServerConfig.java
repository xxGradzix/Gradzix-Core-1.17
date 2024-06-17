package me.xxgradzix.gradzixcore.serverconfig;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.Getter;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.serverconfig.commands.*;
import me.xxgradzix.gradzixcore.serverconfig.commands.ranksCommands.RanksCommand;
import me.xxgradzix.gradzixcore.serverconfig.commands.ranksCommands.UniCommand;
import me.xxgradzix.gradzixcore.serverconfig.commands.ranksCommands.SVipCommand;
import me.xxgradzix.gradzixcore.serverconfig.commands.ranksCommands.VipCommand;
import me.xxgradzix.gradzixcore.serverconfig.data.configfiles.ConfigServera;
import me.xxgradzix.gradzixcore.serverconfig.data.database.entities.ServerConfigEntity;
import me.xxgradzix.gradzixcore.serverconfig.data.database.managers.ServerConfigEntityManager;
import me.xxgradzix.gradzixcore.serverconfig.listeners.*;
import me.xxgradzix.gradzixcore.serverconfig.listeners.elytraAndFireworkBlock.ElytraAndFallDamageDecrease;
import me.xxgradzix.gradzixcore.serverconfig.listeners.elytraAndFireworkBlock.OnTotemBreakBlockFirework;
import me.xxgradzix.gradzixcore.serverconfig.listeners.protectionEnchantRework.DamageEvent;
import org.bukkit.command.defaults.HelpCommand;

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
        ItemManager.init();

        plugin.getServer().getPluginManager().registerEvents(new DamageEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ElytraSwaperBlock(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new VanishingPotionBottle(plugin), plugin);
//        plugin.getServer().getPluginManager().registerEvents(new OnTotemBreakBlockFirework(), plugin);

        plugin.getServer().getPluginManager().registerEvents(new ElytraAndFallDamageDecrease(), plugin);
//        plugin.getServer().getPluginManager().registerEvents(new FortuneSheers(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new AnvilClick(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BlockGrief(), plugin);
        BlockPlacingBlocks blockPlacingBlocks = new BlockPlacingBlocks(plugin);
        blockPlacingBlocks.runTaskTimer(plugin, 0L, 20L * Gradzix_Core.REMOVE_BLOCKS_INTERVAL_SECONDS);

        plugin.getServer().getPluginManager().registerEvents(blockPlacingBlocks, plugin);


        plugin.getCommand("setserverdamagemultiplier").setExecutor(new SetDamageCommand());
        plugin.getCommand("bazar").setExecutor(new ActionHouseRenameCommand());
//        plugin.getCommand("ais").setExecutor(new AgePlayItemShopCommand());
        plugin.getCommand("setarmorattribute").setExecutor(new SetArmorAttributeOnItem());
        plugin.getCommand("gamma").setExecutor(new GammaCommand());

        plugin.getCommand("kosz").setExecutor(new BinCommand());
        plugin.getCommand("discord").setExecutor(new DiscordCommand());
        plugin.getCommand("rangi").setExecutor(new RanksCommand());


        plugin.getCommand("vip").setExecutor(new VipCommand());
        plugin.getCommand("svip").setExecutor(new SVipCommand());
        plugin.getCommand("pomoc").setExecutor(new NewHelpCommand());
        plugin.getCommand("uni").setExecutor(new UniCommand());

        ConfigServera.setup();
        ConfigServera.getCustomFile().set("damageMultiplier", 1);
        ConfigServera.getCustomFile().set("itemPriorities", new ArrayList<>());
        ConfigServera.getCustomFile().options().copyDefaults(true);

        ConfigServera.save();

    }
    public void onDisable() {
        BlockPlacingBlocks.removeBlocks();
    }

}
