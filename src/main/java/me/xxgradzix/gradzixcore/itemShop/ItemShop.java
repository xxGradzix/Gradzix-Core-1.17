package me.xxgradzix.gradzixcore.itemShop;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.itemShop.command.ItemShopBalanceCommand;
import me.xxgradzix.gradzixcore.itemShop.command.ItemShopCommand;
import me.xxgradzix.gradzixcore.itemShop.command.ItemShopConfigCommand;
import me.xxgradzix.gradzixcore.itemShop.data.DataManager;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopCategoryEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopPlayerBalanceEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopProductEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.managers.ItemShopCategoryEntityManager;
import me.xxgradzix.gradzixcore.itemShop.data.database.managers.ItemShopPlayerBalanceEntityManager;
import me.xxgradzix.gradzixcore.itemShop.data.database.managers.ItemShopProductEntityManager;
import me.xxgradzix.gradzixcore.itemShop.items.ItemManager;
import me.xxgradzix.gradzixcore.itemShop.listeners.OnPlayerJoinCreateBalanceEntity;
import me.xxgradzix.gradzixcore.itemShop.listeners.OnPlayerKillAddPoint;

import java.sql.SQLException;

public class ItemShop {


    public static final int SHOP_SIZE = 3;
    private final Gradzix_Core plugin;

    private final ConnectionSource connectionSource;

    private DataManager dataManager;

    private ItemShopCategoryEntityManager itemShopCategoryEntityManager;
    private ItemShopProductEntityManager itemShopProductEntityManager;
    private ItemShopPlayerBalanceEntityManager itemShopPlayerBalanceEntityManager;
    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, ItemShopProductEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, ItemShopCategoryEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, ItemShopPlayerBalanceEntity.class);
        itemShopCategoryEntityManager= new ItemShopCategoryEntityManager(connectionSource);
        itemShopProductEntityManager = new ItemShopProductEntityManager(connectionSource);
        itemShopPlayerBalanceEntityManager = new ItemShopPlayerBalanceEntityManager(connectionSource);
    }

    public ItemShop(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public void onEnable() {

        ItemManager.init();
        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dataManager = new DataManager(itemShopProductEntityManager, itemShopPlayerBalanceEntityManager);

        plugin.getCommand("sklep").setExecutor(new ItemShopCommand(dataManager));
        plugin.getCommand("itemShopConfig").setExecutor(new ItemShopConfigCommand(dataManager));
        plugin.getCommand("stankonta").setExecutor(new ItemShopBalanceCommand(dataManager));

        plugin.getServer().getPluginManager().registerEvents(new OnPlayerKillAddPoint(dataManager), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnPlayerJoinCreateBalanceEntity(dataManager, plugin), plugin);

    }



}
