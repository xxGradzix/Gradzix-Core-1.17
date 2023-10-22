package me.xxgradzix.gradzixcore;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.chatOptions.Chatopcje;
import me.xxgradzix.gradzixcore.itemPickupPriorities.ItemPickupPriorities;
import me.xxgradzix.gradzixcore.magicFirework.Magicznafajerwerka;
import me.xxgradzix.gradzixcore.adminPanel.Panel;
import me.xxgradzix.gradzixcore.serverconfig.ServerConfig;
import me.xxgradzix.gradzixcore.upgradeItem.Ulepsz;
import me.xxgradzix.gradzixcore.playerAbilities.Umiejetnosci;
import me.xxgradzix.gradzixcore.playerSettings.Ustawienia;
import me.xxgradzix.gradzixcore.scratchCard.Zdrapka;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

public final class Gradzix_Core extends JavaPlugin {


    public static final boolean USEDB = true;
    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;

    private Zdrapka zdrapkaPlugin;
    private Chatopcje chatopcje;
    private Magicznafajerwerka magicznafajerwerka;

    private Ustawienia ustawienia;
    private Panel panel;
    private Umiejetnosci umiejetnosci;
    private ServerConfig serverConfig;
    private Ulepsz ulepsz;
    private ItemPickupPriorities itemPickupPriorities;

    // database variables
    private String databaseUrl = "jdbc:mysql://localhost:3306/gradzixcore";
    private ConnectionSource connectionSource;
    public void configureDB() throws SQLException {

        // DATABASE AGEPLAY
//        this.connectionSource = new JdbcConnectionSource(databaseUrl, "u286_f8T7gXXzU1", "a65qmwbgH8Y@cg3dXm^qgSm6");

        // DATABASE LOCAL
        this.connectionSource = new JdbcConnectionSource(databaseUrl, "root", "");

    }

    //////////////////

    @Override
    public void onEnable()  {
        if (!LocalDate.now().isBefore(LocalDate.of(2023, 12, 30))) {
            System.out.println("jezeli wyswietliła sie ta wiadomosc to skontaktuj sie z xxGradzix");
            return;
        }
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
//          getServer().getPluginManager().disablePlugin(this);
            return;
        }
        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (chatopcje == null) {
            chatopcje = new Chatopcje(this, connectionSource);
            chatopcje.onEnable();
        }
        if (zdrapkaPlugin == null) {
            zdrapkaPlugin = new Zdrapka(this, connectionSource);
            zdrapkaPlugin.onEnable();
        }

        if (magicznafajerwerka == null) {
            magicznafajerwerka = new Magicznafajerwerka(this);
            magicznafajerwerka.onEnable();
        }
        if (ustawienia == null) {
            ustawienia = new Ustawienia(this, connectionSource);
            ustawienia.onEnable();
        }
        if (panel == null) {
            panel = new Panel(this, connectionSource);
            panel.onEnable();
        }
        if (umiejetnosci == null) {
            umiejetnosci = new Umiejetnosci(this, connectionSource);
            umiejetnosci.onEnable();
        }
        if (ulepsz == null) {
            ulepsz = new Ulepsz(this, connectionSource);
            ulepsz.onEnable();
        }
        if (serverConfig == null) {
            serverConfig = new ServerConfig(this, connectionSource);
            serverConfig.onEnable();
        }
        if (serverConfig == null) {
            serverConfig = new ServerConfig(this, connectionSource);
            serverConfig.onEnable();
        }
        if (itemPickupPriorities == null) {
            itemPickupPriorities = new ItemPickupPriorities(this, connectionSource);
            itemPickupPriorities.onEnable();
        }
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    public static Economy getEconomy() {
        return econ;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
