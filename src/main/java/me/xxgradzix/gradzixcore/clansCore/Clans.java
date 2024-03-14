package me.xxgradzix.gradzixcore.clansCore;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.clansCore.commands.*;
import me.xxgradzix.gradzixcore.clansCore.commands.deprecatedCommands.*;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.managers.ClanEntityManager;
import me.xxgradzix.gradzixcore.clansCore.data.database.managers.UserEntityManager;
import me.xxgradzix.gradzixcore.clansCore.listeners.OnJoinCreateUser;
import me.xxgradzix.gradzixcore.clansCore.managers.TeamManager;
import me.xxgradzix.gradzixcore.clansCore.placeholders.PlayerPointsPlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

import java.sql.SQLException;

public class Clans {

    public static final int DEFAULT_POINTS = 1000;
    public static final Integer DEFAULT_LIVES = 3;

    private final Gradzix_Core plugin;

    private static ClanEntityManager clanEntityManager;
    private static UserEntityManager userEntityManager;

    public static ClanEntityManager getClanEntityManager() {
        return clanEntityManager;
    }
    public static UserEntityManager getUserEntityManager() {
        return userEntityManager;
    }

    public static final Scoreboard SCOREBOARD = Bukkit.getScoreboardManager().getNewScoreboard();

    private final ConnectionSource connectionSource;

    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, ClanEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, UserEntity.class);
        clanEntityManager = new ClanEntityManager(connectionSource);
        userEntityManager = new UserEntityManager(connectionSource);
    }
    public Clans(Gradzix_Core plugin, ConnectionSource connectionSource) {

        this.plugin = plugin;
        this.connectionSource = connectionSource;

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void onEnable() {

        TeamManager.changePrefixes();
        TeamManager.refreshAllTeams();

        new PlayerPointsPlaceholder().register();


        plugin.getCommand("klan").setExecutor(new ClanCommand());
        plugin.getCommand("gracz").setExecutor(new PlayerInfoCommand());

        plugin.getCommand("test").setExecutor(new TestCommand());

        plugin.getServer().getPluginManager().registerEvents(new OnJoinCreateUser(), plugin);

    }

}
