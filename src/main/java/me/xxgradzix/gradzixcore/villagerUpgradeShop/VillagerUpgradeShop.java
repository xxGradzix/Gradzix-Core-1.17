package me.xxgradzix.gradzixcore.villagerUpgradeShop;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.Getter;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.serverconfig.commands.*;
import me.xxgradzix.gradzixcore.serverconfig.commands.ranksCommands.AgeCommand;
import me.xxgradzix.gradzixcore.serverconfig.commands.ranksCommands.SVipCommand;
import me.xxgradzix.gradzixcore.serverconfig.commands.ranksCommands.VipCommand;
import me.xxgradzix.gradzixcore.serverconfig.data.configfiles.ConfigServera;
import me.xxgradzix.gradzixcore.serverconfig.data.database.entities.ServerConfigEntity;
import me.xxgradzix.gradzixcore.serverconfig.data.database.managers.ServerConfigEntityManager;
import me.xxgradzix.gradzixcore.serverconfig.listeners.BlockGrief;
import me.xxgradzix.gradzixcore.serverconfig.listeners.BlockPlacingBlocks;
import me.xxgradzix.gradzixcore.serverconfig.listeners.FortuneSheers;
import me.xxgradzix.gradzixcore.serverconfig.listeners.VanishingPotionBottle;
import me.xxgradzix.gradzixcore.serverconfig.listeners.elytraAndFireworkBlock.ElytraAndFallDamageDecrease;
import me.xxgradzix.gradzixcore.serverconfig.listeners.elytraAndFireworkBlock.OnTotemBreakBlockFirework;
import me.xxgradzix.gradzixcore.serverconfig.listeners.protectionEnchantRework.DamageEvent;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.commands.UpgradeShopCommand;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.DataManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopEntity;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopProductEntity;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.managers.VillagerUpgradeShopEntityManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.managers.VillagerUpgradeShopProductEntityManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.items.ItemManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class VillagerUpgradeShop {

    private final Gradzix_Core plugin;
    private final ConnectionSource connectionSource;

    private static VillagerUpgradeShopEntityManager villagerUpgradeShopEntityManager;
    private static VillagerUpgradeShopProductEntityManager villagerUpgradeShopProductEntityManager;

    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, VillagerUpgradeShopEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, VillagerUpgradeShopProductEntity.class);
        villagerUpgradeShopEntityManager = new VillagerUpgradeShopEntityManager(connectionSource);
        villagerUpgradeShopProductEntityManager = new VillagerUpgradeShopProductEntityManager(connectionSource);
    }
    public VillagerUpgradeShop(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    private static DataManager dataManager;

    public void onEnable() {

        ItemManager.init();

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dataManager = new DataManager(villagerUpgradeShopEntityManager, villagerUpgradeShopProductEntityManager);

//        plugin.getServer().getPluginManager().registerEvents(new BlockGrief(), plugin);

        plugin.getCommand("upgradeshop").setExecutor(new UpgradeShopCommand(dataManager));

    }

}
