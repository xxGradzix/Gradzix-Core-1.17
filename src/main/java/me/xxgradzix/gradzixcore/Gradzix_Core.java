package me.xxgradzix.gradzixcore;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import lombok.Getter;
import me.xxgradzix.gradzixcore.VPLNShop.VPLNShop;
import me.xxgradzix.gradzixcore.achievements.Achievements;
import me.xxgradzix.gradzixcore.achievements.managers.AchievementManager;
import me.xxgradzix.gradzixcore.adminPanel.Panel;
import me.xxgradzix.gradzixcore.afkRegion.AfkRegion;
import me.xxgradzix.gradzixcore.autodropsell.AutoDropSell;
import me.xxgradzix.gradzixcore.binds.Binds;
import me.xxgradzix.gradzixcore.chatOptions.ChatOptions;
import me.xxgradzix.gradzixcore.clansCore.Clans;
import me.xxgradzix.gradzixcore.clansExtension.ClansExtension;
import me.xxgradzix.gradzixcore.dailyQuests.DailyQuests;
import me.xxgradzix.gradzixcore.eventArea.EventArea;
import me.xxgradzix.gradzixcore.events.Events;
import me.xxgradzix.gradzixcore.generators.Generators;
import me.xxgradzix.gradzixcore.globalStatic.EconomyManager;
import me.xxgradzix.gradzixcore.globalStatic.TextInputFromChat;
import me.xxgradzix.gradzixcore.incognito.Incognito;
import me.xxgradzix.gradzixcore.itemPickupPriorities.ItemPickupPriorities;
import me.xxgradzix.gradzixcore.itemShop.ItemShop;
import me.xxgradzix.gradzixcore.kits.Kits;
import me.xxgradzix.gradzixcore.magicFirework.MagicFirework;
import me.xxgradzix.gradzixcore.magicPond.MagicPond;
import me.xxgradzix.gradzixcore.playerAbilities.PlayerAbilities;
import me.xxgradzix.gradzixcore.playerPerks.PlayerPerks;
import me.xxgradzix.gradzixcore.playerSettings.PlayerSettings;
import me.xxgradzix.gradzixcore.rewardSystem.RewardSystem;
import me.xxgradzix.gradzixcore.scratchCard.Zdrapka;
import me.xxgradzix.gradzixcore.serverconfig.ServerConfig;
import me.xxgradzix.gradzixcore.shulker.ShulkerRework;
import me.xxgradzix.gradzixcore.socialMediaRewards.SocialMediaRewards;
import me.xxgradzix.gradzixcore.upgradeItem.Ulepsz;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.VillagerUpgradeShop;
import me.xxgradzix.gradzixcore.warps.Warps;
import me.xxgradzix.gradzixcore.webRemover.WebRemover;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public final class Gradzix_Core extends JavaPlugin {

    public static final boolean USE_DB = true;

    public static final boolean USE_AUTO_DROP = true;

    public static final boolean GUILD_WARS = false;

    public static final boolean USE_CUSTOM_CLANS = false;

    public static final int WEAKNESS_EFFECT_DURATION_TIME_SECONDS = 3;
    public static final long BIG_AFK_REWARD_DELAY_SECONDS = 15L * 60;
    public static final long SMALL_AFK_REWARD_DELAY_SECONDS = (1 * 60L);
    public static final long BOSS_SPAWN_DELAY_SECONDS = 60;
    public static final long REMOVE_BLOCKS_INTERVAL_SECONDS = 300;
    public static final long FAST_GENERATOR_REGENERATION_TIME_SECONDS = 180;
    public static final long SLOW_GENERATOR_REGENERATION_TIME_SECONDS = 300;
    public static final int MESSAGE_BUTTON_COOLDOWN_SECONDS = 3;
    public static final int SETTINGS_BUTTON_COOLDOWN_SECONDS = 5;

    private static final Logger log = Logger.getLogger("Minecraft");

    private static Economy econ = null;

    @Getter
    private static Gradzix_Core instance;

    private Zdrapka scratchCardPlugin;
    private ChatOptions chatOptions;
    private MagicFirework magicFirework;
    private PlayerSettings playerSettings;
    private AutoDropSell autoDropSell;
    private Panel panel;
    private PlayerAbilities playerAbilities;
    private ServerConfig serverConfig;
    private Ulepsz ulepsz;
    private ItemPickupPriorities itemPickupPriorities;
    private RewardSystem rewardSystem;
    private Generators generators;
    private Events events;
    private Binds binds;
    private MagicPond magicPond;
//    private ShulkerRework shulkerRework;
    private SocialMediaRewards socialMediaRewards;
    private ItemShop itemShop;
    private Incognito incognito;
    private PlayerPerks playerPerks;
    private WebRemover webRemover;
    private AfkRegion afkRegion;
    private Warps warps;
    private VPLNShop vplnShop;
    private ClansExtension clansExtension;
    private VillagerUpgradeShop villagerUpgradeShop;
    private Kits kits;
    private DailyQuests dailyQuests;
    private Achievements achievements;
    private EventArea eventArea;

    private ConnectionSource connectionSource;


    private Clans clans;

    Properties loadConfig() {
        Properties prop = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");

        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
        instance = this;
        EconomyManager.setupEconomy();
        Bukkit.getPluginManager().registerEvents(new TextInputFromChat(), this);

        GlobalItemManager.init();

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            return;
        }
        if (Bukkit.getPluginManager().getPlugin("WorldEdit") != null) {
        } else {
            getLogger().severe("WorldEdit is not available.");
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
        if (scratchCardPlugin == null) {
            scratchCardPlugin = new Zdrapka(this, connectionSource);
            scratchCardPlugin.onEnable();
        }

        if (magicFirework == null) {
            magicFirework = new MagicFirework(this);
            magicFirework.onEnable();
        }

        if(USE_AUTO_DROP) {
            if (autoDropSell == null) {
                autoDropSell = new AutoDropSell(this, connectionSource);
                autoDropSell.onEnable();
            }
        } else {
            if (playerSettings == null) {
                playerSettings = new PlayerSettings(this, connectionSource);
                playerSettings.onEnable();
            }
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
        if (rewardSystem == null) {
            rewardSystem = new RewardSystem(this, connectionSource);
            rewardSystem.onEnable();
        }
        if (serverConfig == null) {
            serverConfig = new ServerConfig(this, connectionSource);
            serverConfig.onEnable();
        }
        if (itemPickupPriorities == null) {
            itemPickupPriorities = new ItemPickupPriorities(this, connectionSource);
            itemPickupPriorities.onEnable();
        }

        if (generators == null) {
            if (generators == null) {
                generators = new Generators(Gradzix_Core.getInstance(), getWorldGuard(), connectionSource);
                generators.onEnable();
            }
        }
        if (events == null) {
            events = new Events(this, connectionSource);
            events.onEnable();
        }
        if (binds == null) {
            binds = new Binds(this);
            binds.onEnable();
        }

        if (magicPond == null) {
            magicPond = new MagicPond(this, connectionSource);
            magicPond.onEnable();
        }

        if (socialMediaRewards == null) {
            socialMediaRewards = new SocialMediaRewards(this, connectionSource);
            socialMediaRewards.onEnable();
        }
        if (dailyQuests == null) {
            dailyQuests = new DailyQuests(this);
            dailyQuests.onEnable();
        }
//        if (shulkerRework == null) {
//            shulkerRework = new ShulkerRework(this);
//            shulkerRework.onEnable();
//        }
//        if (incognito == null) {
//            incognito = new Incognito(this, connectionSource);
//            incognito.onEnable();
//        }
        if (playerPerks == null) {
            playerPerks = new PlayerPerks(this, connectionSource);
            playerPerks.onEnable();
        }
        if (webRemover == null) {
            webRemover = new WebRemover(this, connectionSource);
            webRemover.onEnable();
        }
        if (afkRegion == null) {
            afkRegion = new AfkRegion(this, connectionSource);
            afkRegion.onEnable();
        }
        if (warps == null) {
            warps = new Warps(this, connectionSource);
            warps.onEnable();
        }
        if (vplnShop == null) {
            vplnShop = new VPLNShop(this, connectionSource);
            vplnShop.onEnable();
        }
        if (villagerUpgradeShop == null) {
            villagerUpgradeShop = new VillagerUpgradeShop(this, connectionSource);
            villagerUpgradeShop.onEnable();
        }
        if (kits == null) {
            kits = new Kits(this, connectionSource);
            kits.onEnable();
        }

        if (itemShop == null) {
            itemShop = new ItemShop(this, connectionSource);
            itemShop.onEnable();
        }
        if (achievements == null) {
            achievements = new Achievements(this, connectionSource);
            achievements.onEnable();
        }
        if (eventArea == null) {
            eventArea = new EventArea(this);
            eventArea.onEnable();
        }

        if(USE_CUSTOM_CLANS) {
            if(GUILD_WARS) {
                if (clansExtension == null) {
                    clansExtension = new ClansExtension(this, connectionSource);
                    clansExtension.onEnable();
                }
            }
            if (clans == null) {
                clans = new Clans(this, connectionSource);
                clans.onEnable();
            }
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
    public static WorldGuardPlugin getWorldGuard() {
        Plugin p = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        if (p instanceof WorldGuardPlugin) return (WorldGuardPlugin) p;
        else return null;
    }

    @Override
    public void onDisable() {
//        if (shulkerRework != null) {
//            shulkerRework.onDisable();
//        }
        if (serverConfig != null) {
            serverConfig.onDisable();
        }
        if (achievements != null) {
            achievements.onDisable();
        }
    }
}
