package me.xxgradzix.gradzixcore.playerSettings;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.playerSettings.listeners.OnJoin;
import me.xxgradzix.gradzixcore.playerSettings.commands.UstawieniaCommand;
import me.xxgradzix.gradzixcore.playerSettings.commands.sprzedaz.SprzedazCommand;
import me.xxgradzix.gradzixcore.playerSettings.commands.wymiana.WymianaUstawieniaCommand;
import me.xxgradzix.gradzixcore.playerSettings.data.database.entities.SettingsEntity;
import me.xxgradzix.gradzixcore.playerSettings.data.database.entities.SettingsItemsEntity;
import me.xxgradzix.gradzixcore.playerSettings.data.database.managers.SettingItemsEntityManager;
import me.xxgradzix.gradzixcore.playerSettings.data.database.managers.SettingOptionsEntityManager;
import me.xxgradzix.gradzixcore.playerSettings.data.configfiles.UstawieniaOpcjeConfigFile;
import me.xxgradzix.gradzixcore.playerSettings.data.configfiles.WymianaUstawieniaItemsConfigFile;
import me.xxgradzix.gradzixcore.playerSettings.gui.sprzedaz.SellGuiClick;
import me.xxgradzix.gradzixcore.playerSettings.gui.sprzedaz.SprzedazGuiClose;
import me.xxgradzix.gradzixcore.playerSettings.gui.wymiana.ExchangeGuiClick;
import me.xxgradzix.gradzixcore.playerSettings.gui.wymiana.ExchangeGuiClose;
import me.xxgradzix.gradzixcore.playerSettings.items.ItemManager;
import me.xxgradzix.gradzixcore.playerSettings.listeners.BlockBreakSell;
import me.xxgradzix.gradzixcore.playerSettings.listeners.BlockBreakExchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerSettings {

    private final Gradzix_Core plugin;

    private final ConnectionSource connectionSource;

    private static SettingOptionsEntityManager settingOptionsEntityManager;
    private static SettingItemsEntityManager settingItemsEntityManager;

    public static SettingOptionsEntityManager getSettingOptionsEntityManager() {
        return settingOptionsEntityManager;
    }
    public static SettingItemsEntityManager getSettingItemsEntityManager() {
        return settingItemsEntityManager;
    }

    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, SettingsEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, SettingsItemsEntity.class);
        settingOptionsEntityManager= new SettingOptionsEntityManager(connectionSource);
        settingItemsEntityManager = new SettingItemsEntityManager(connectionSource);

    }

    public PlayerSettings(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public void onEnable() {
        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<String> defaultPlayers = new ArrayList<>();
        UstawieniaOpcjeConfigFile.setup();
        UstawieniaOpcjeConfigFile.getCustomFile().addDefault("players", defaultPlayers);
        UstawieniaOpcjeConfigFile.getCustomFile().options().copyDefaults(true);
        UstawieniaOpcjeConfigFile.save();

        WymianaUstawieniaItemsConfigFile.setup();
        WymianaUstawieniaItemsConfigFile.getCustomFile().options().copyDefaults(true);
        WymianaUstawieniaItemsConfigFile.save();
        ItemManager.init();

        plugin.getCommand("ustawienia").setExecutor(new UstawieniaCommand());
        plugin.getCommand("wymianaustawienia").setExecutor(new WymianaUstawieniaCommand());
        plugin.getCommand("sprzedazustawienia").setExecutor(new SprzedazCommand());

        plugin.getServer().getPluginManager().registerEvents(new BlockBreakExchange(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BlockBreakSell(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ExchangeGuiClose(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ExchangeGuiClick(), plugin);

        plugin.getServer().getPluginManager().registerEvents(new SprzedazGuiClose(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SellGuiClick(), plugin);

        plugin.getServer().getPluginManager().registerEvents(new OnJoin(), plugin);

    }



}
