package me.xxgradzix.gradzixcore.magicPond;


import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.magicPond.commands.MagicPondConfig;
import me.xxgradzix.gradzixcore.magicPond.commands.MagicPondRewards;
import me.xxgradzix.gradzixcore.magicPond.data.DataManager;
import me.xxgradzix.gradzixcore.magicPond.data.database.entities.MagicPondEntity;
import me.xxgradzix.gradzixcore.magicPond.data.database.managers.MagicPondEntityManager;
import me.xxgradzix.gradzixcore.magicPond.items.ItemManager;
import me.xxgradzix.gradzixcore.magicPond.listeners.OnPlayerFish;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.HashMap;

public final class MagicPond {

    private Gradzix_Core plugin;

    private ConnectionSource connectionSource;

    private MagicPondEntityManager magicPondEntityManager;
    private static DataManager dataManager;

    private static HashMap<ItemStack, Integer> magicPondRewards = new HashMap<>();

    public static void updateMagicPondRewards() {
        magicPondRewards = dataManager.getMagicPondEntityRewards();
    }

    public static HashMap<ItemStack, Integer> getMagicPondRewards() {
        return magicPondRewards;
    }

    public MagicPondEntityManager getScratchCardEntityManager() {
        return magicPondEntityManager;
    }
    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, MagicPondEntity.class);
        magicPondEntityManager = new MagicPondEntityManager(connectionSource);
    }


    public MagicPond(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }


    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dataManager = new DataManager(magicPondEntityManager);
        ItemManager.init();

        updateMagicPondRewards();

        plugin.getCommand("jeziorkoconfig").setExecutor(new MagicPondConfig(dataManager));
        plugin.getCommand("jeziorko").setExecutor(new MagicPondRewards(dataManager));

        plugin.getServer().getPluginManager().registerEvents(new OnPlayerFish(dataManager), plugin);

    }

    public void onDisable() {
    }


}
