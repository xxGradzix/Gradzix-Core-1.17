package me.xxgradzix.gradzixcore.ustawienia;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.umiejetnosci.listeners.OnJoin;
import me.xxgradzix.gradzixcore.ustawienia.commands.UstawieniaCommand;
import me.xxgradzix.gradzixcore.ustawienia.commands.sprzedaz.SprzedazCommand;
import me.xxgradzix.gradzixcore.ustawienia.commands.wymiana.WymianaUstawieniaCommand;
import me.xxgradzix.gradzixcore.ustawienia.data.database.entities.SettingsEntity;
import me.xxgradzix.gradzixcore.ustawienia.data.database.entities.SettingsItemsEntity;
import me.xxgradzix.gradzixcore.ustawienia.data.database.managers.SettingItemsEntityManager;
import me.xxgradzix.gradzixcore.ustawienia.data.database.managers.SettingOptionsEntityManager;
import me.xxgradzix.gradzixcore.ustawienia.data.configfiles.UstawieniaOpcjeConfigFile;
import me.xxgradzix.gradzixcore.ustawienia.data.configfiles.WymianaUstawieniaItemsConfigFile;
import me.xxgradzix.gradzixcore.ustawienia.gui.sprzedaz.SprzedazGuiClick;
import me.xxgradzix.gradzixcore.ustawienia.gui.sprzedaz.SprzedazGuiClose;
import me.xxgradzix.gradzixcore.ustawienia.gui.wymiana.WymianaGuiClick;
import me.xxgradzix.gradzixcore.ustawienia.gui.wymiana.WymianaGuiClose;
import me.xxgradzix.gradzixcore.ustawienia.items.ItemManager;
import me.xxgradzix.gradzixcore.ustawienia.listeners.BlockBreakSprzedaz;
import me.xxgradzix.gradzixcore.ustawienia.listeners.BlockBreakWymiana;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ustawienia {

    private Gradzix_Core plugin;

    // db change
    private ConnectionSource connectionSource;

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
    ////////////

    public Ustawienia(Gradzix_Core plugin, ConnectionSource connectionSource) {
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

        plugin.getServer().getPluginManager().registerEvents(new BlockBreakWymiana(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BlockBreakSprzedaz(), plugin);

        plugin.getServer().getPluginManager().registerEvents(new WymianaGuiClose(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new WymianaGuiClick(), plugin);

        plugin.getServer().getPluginManager().registerEvents(new SprzedazGuiClose(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SprzedazGuiClick(), plugin);

        plugin.getServer().getPluginManager().registerEvents(new OnJoin(), plugin);



    }



}
