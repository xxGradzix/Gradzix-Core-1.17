package me.xxgradzix.gradzixcore.clansExtension;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.clansExtension.commands.ClanUpgradesCommand;
import me.xxgradzix.gradzixcore.clansExtension.commands.DeclareWarCommand;
import me.xxgradzix.gradzixcore.clansExtension.commands.WarConfigCommand;
import me.xxgradzix.gradzixcore.clansExtension.commands.WarsCommand;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.*;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.*;
import me.xxgradzix.gradzixcore.clansExtension.items.ItemManager;
import me.xxgradzix.gradzixcore.clansExtension.listeners.GuildLoseLivesEvent;
import me.xxgradzix.gradzixcore.clansExtension.listeners.GuildRemoveEvent;
import me.xxgradzix.gradzixcore.clansExtension.listeners.addScore.AddWarScoreAfterKill;
import me.xxgradzix.gradzixcore.clansExtension.listeners.perks.rankPerk.PointChangeEvent;
import me.xxgradzix.gradzixcore.clansExtension.managers.ClanPerksManager;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarTimeManager;

import java.sql.SQLException;

public class ClansExtension {

    private final Gradzix_Core plugin;

    private WarEntityManager warEntityManager;
    private WarRecordEntityManager warRecordEntityManager;
    private ClanPerksEntityManager clanPerksEntityManager;
    private PerkModifierEntityManager perkModifierEntityManager;
    private WarScheduleEntityManager warScheduleEntityManager;

    private final WarManager warManager;
    private final ClanPerksManager clanPerksManager;
    private final WarTimeManager warTimeManager;

    private final ConnectionSource connectionSource;
    public static boolean ARE_WARS_ACTIVE = false;

    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, WarEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, WarRecordEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, PerkModifierEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, ClanPerksEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, WarScheduleEntity.class);

        warEntityManager = new WarEntityManager(connectionSource);
        warRecordEntityManager = new WarRecordEntityManager(connectionSource);
        clanPerksEntityManager = new ClanPerksEntityManager(connectionSource);
        perkModifierEntityManager = new PerkModifierEntityManager(connectionSource);
        warScheduleEntityManager = new WarScheduleEntityManager(connectionSource);
    }
    public ClansExtension(Gradzix_Core plugin, ConnectionSource connectionSource) {

        ItemManager.init();
        this.plugin = plugin;
        this.connectionSource = connectionSource;

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        warManager = new WarManager(warEntityManager, warRecordEntityManager, clanPerksEntityManager);
        clanPerksManager = new ClanPerksManager(clanPerksEntityManager);
        warTimeManager = new WarTimeManager(warScheduleEntityManager, warManager);

        warTimeManager.scheduleOnEnable();

    }


    public void onEnable() {

        plugin.getCommand("configWojny").setExecutor(new WarConfigCommand(warTimeManager, warScheduleEntityManager));
        plugin.getCommand("wypowiedzwojne").setExecutor(new DeclareWarCommand(warManager));
        plugin.getCommand("wojny").setExecutor(new WarsCommand(warManager));
        plugin.getCommand("ulepszeniaklanu").setExecutor(new ClanUpgradesCommand(clanPerksManager, clanPerksEntityManager));

        plugin.getServer().getPluginManager().registerEvents(new GuildLoseLivesEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new AddWarScoreAfterKill(warManager), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GuildRemoveEvent(warManager), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PointChangeEvent(clanPerksEntityManager), plugin);

    }

}
