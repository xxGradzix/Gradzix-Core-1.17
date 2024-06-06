package me.xxgradzix.gradzixcore.VPLNShop;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.Getter;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.VPLNShop.commands.VPLNCommand;
import me.xxgradzix.gradzixcore.VPLNShop.commands.VPLNShopCommand;
import me.xxgradzix.gradzixcore.VPLNShop.data.DataManager;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.entities.VPLNAccountEntity;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.entities.VPLNOrderEntity;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.managers.VPLNAccountsManager;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.managers.VPLNItemShopOrdersManager;
import me.xxgradzix.gradzixcore.VPLNShop.items.ItemManager;
import me.xxgradzix.gradzixcore.VPLNShop.listeners.JoinListener;
import me.xxgradzix.gradzixcore.serverconfig.listeners.BlockGrief;

import java.sql.SQLException;

public class VPLNShop {

    private final Gradzix_Core plugin;
    private final ConnectionSource connectionSource;

    private static VPLNItemShopOrdersManager vplnItemShopOrdersManager;
    private static VPLNAccountsManager VPLNAccountsManager;

    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, VPLNOrderEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, VPLNAccountEntity.class);
        vplnItemShopOrdersManager = new VPLNItemShopOrdersManager(connectionSource);
        VPLNAccountsManager = new VPLNAccountsManager(connectionSource);
    }

    private static me.xxgradzix.gradzixcore.VPLNShop.managers.VPLNShop vplnShop;

    private static DataManager dataManager;

    public VPLNShop(Gradzix_Core plugin, ConnectionSource connectionSource) {
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

        dataManager = new DataManager(vplnItemShopOrdersManager, VPLNAccountsManager);
        vplnShop = new me.xxgradzix.gradzixcore.VPLNShop.managers.VPLNShop(dataManager);

        plugin.getServer().getPluginManager().registerEvents(new JoinListener(dataManager), plugin);

        plugin.getCommand("portfel").setExecutor(new VPLNShopCommand(vplnShop));
        plugin.getCommand("dodajvpln").setExecutor(new VPLNCommand(dataManager));

    }

}
