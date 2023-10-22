package me.xxgradzix.gradzixcore;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.adminPanel.Panel;
import me.xxgradzix.gradzixcore.chatOptions.ChatOptions;
import me.xxgradzix.gradzixcore.itemPickupPriorities.ItemPickupPriorities;
import me.xxgradzix.gradzixcore.magicFirework.MagicFirework;
import me.xxgradzix.gradzixcore.playerAbilities.PlayerAbilities;
import me.xxgradzix.gradzixcore.playerSettings.PlayerSettings;
import me.xxgradzix.gradzixcore.rewardSystem.RewardSystem;
import me.xxgradzix.gradzixcore.scratchCard.Zdrapka;
import me.xxgradzix.gradzixcore.serverconfig.ServerConfig;
import me.xxgradzix.gradzixcore.upgradeItem.Ulepsz;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.logging.Logger;

public final class Gradzix_Core extends JavaPlugin {


    public static final boolean USEDB = true;
    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;

    private Zdrapka zdrapkaPlugin;
    private ChatOptions chatOptions;
    private MagicFirework magicFirework;

    private PlayerSettings playerSettings;
    private Panel panel;
    private PlayerAbilities playerAbilities;
    private ServerConfig serverConfig;
    private Ulepsz ulepsz;
    private ItemPickupPriorities itemPickupPriorities;
    private RewardSystem rewardSystem;

    private ConnectionSource connectionSource;

    public static Properties loadConfig() throws IOException {
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream("application.properties");
        prop.load(input);
        input.close();
        return prop;
    }

    public void configureDB() throws SQLException, IOException {

        Properties config = loadConfig();

        String databaseUrl = config.getProperty("db.url");
        String user = config.getProperty("db.username");
        String password = config.getProperty("db.password");

        this.connectionSource = new JdbcConnectionSource(databaseUrl, user, password);

    }


    @Override
    public void onEnable()  {
        if (!LocalDate.now().isBefore(LocalDate.of(2023, 12, 30))) {
            System.out.println("jeżeli wyświetliła się ta wiadomosc to skontaktuj sie z xxGradzix");
            return;
        }
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            return;
        }
        try {
            configureDB();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        if (chatOptions == null) {
            chatOptions = new ChatOptions(this, connectionSource);
            chatOptions.onEnable();
        }
        if (zdrapkaPlugin == null) {
            zdrapkaPlugin = new Zdrapka(this, connectionSource);
            zdrapkaPlugin.onEnable();
        }

        if (magicFirework == null) {
            magicFirework = new MagicFirework(this);
            magicFirework.onEnable();
        }
        if (playerSettings == null) {
            playerSettings = new PlayerSettings(this, connectionSource);
            playerSettings.onEnable();
        }
        if (panel == null) {
            panel = new Panel(this, connectionSource);
            panel.onEnable();
        }
        if (playerAbilities == null) {
            playerAbilities = new PlayerAbilities(this, connectionSource);
            playerAbilities.onEnable();
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
        if (rewardSystem == null) {
            rewardSystem = new RewardSystem(this, connectionSource);
            rewardSystem.onEnable();
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

    }
}
