package me.xxgradzix.gradzixcore;

import me.xxgradzix.gradzixcore.chatopcje.Chatopcje;
import me.xxgradzix.gradzixcore.magicznafajerwerka.Magicznafajerwerka;
import me.xxgradzix.gradzixcore.panel.Panel;
import me.xxgradzix.gradzixcore.serverconfig.ServerConfig;
import me.xxgradzix.gradzixcore.ulepsz.Ulepsz;
import me.xxgradzix.gradzixcore.umiejetnosci.Umiejetnosci;
import me.xxgradzix.gradzixcore.ustawienia.Ustawienia;
import me.xxgradzix.gradzixcore.zdrapka.Zdrapka;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Gradzix_Core extends JavaPlugin {


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



    @Override public void onEnable()  {
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
//          getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (chatopcje == null) {
            chatopcje = new Chatopcje(this);
            chatopcje.onEnable();
        }
        if (zdrapkaPlugin == null) {
            zdrapkaPlugin = new Zdrapka(this);
            zdrapkaPlugin.onEnable();
        }

        if (magicznafajerwerka == null) {
            magicznafajerwerka = new Magicznafajerwerka(this);
            magicznafajerwerka.onEnable();
        }
        if (ustawienia == null) {
            ustawienia = new Ustawienia(this);
            ustawienia.onEnable();
        }
        if (panel == null) {
            panel = new Panel(this);
            panel.onEnable();
        }
        if (umiejetnosci == null) {
            umiejetnosci = new Umiejetnosci(this);
            umiejetnosci.onEnable();
        }
        if (ulepsz == null) {
            ulepsz = new Ulepsz(this);
            ulepsz.onEnable();
        }
        if (serverConfig == null) {
            serverConfig = new ServerConfig(this);
            serverConfig.onEnable();
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
