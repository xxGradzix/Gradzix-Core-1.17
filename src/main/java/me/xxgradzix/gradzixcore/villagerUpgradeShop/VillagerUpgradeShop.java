package me.xxgradzix.gradzixcore.villagerUpgradeShop;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.playerSettings.EconomyManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.commands.UpgradeShopCommand;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.commands.UpgradeShopEditorCommand;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.DataManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopEntity;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopProductEntity;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.managers.VillagerUpgradeShopEntityManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.managers.VillagerUpgradeShopProductEntityManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.items.ItemManager;

import java.sql.SQLException;

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

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dataManager = new DataManager(villagerUpgradeShopEntityManager, villagerUpgradeShopProductEntityManager);
        new ItemManager(dataManager);
//        plugin.getServer().getPluginManager().registerEvents(new BlockGrief(), plugin);

        plugin.getCommand("upgradeshop").setExecutor(new UpgradeShopCommand(dataManager));
        plugin.getCommand("upgradeshopeditor").setExecutor(new UpgradeShopEditorCommand(dataManager));

    }

}
