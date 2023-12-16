package me.xxgradzix.gradzixcore.clansExtension;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.clansExtension.commands.WarConfigCommand;
import me.xxgradzix.gradzixcore.clansExtension.commands.DeclareWarCommand;
import me.xxgradzix.gradzixcore.clansExtension.commands.WarsCommand;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.War;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.WarEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.listeners.GuildLoseLivesEvent;
import me.xxgradzix.gradzixcore.clansExtension.listeners.addScore.AddWarScoreAfterKill;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import net.dzikoysk.funnyguilds.FunnyGuilds;

import java.sql.SQLException;

public class ClansExtension {

    private final Gradzix_Core plugin;
    private final FunnyGuilds funnyGuilds;

    private WarEntityManager warEntityManager;
    private final WarManager warManager;

    private final ConnectionSource connectionSource;
    public static boolean ARE_WARS_ACTIVE = false;

    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, War.class);
        warEntityManager = new WarEntityManager(connectionSource);
    }
    public ClansExtension(Gradzix_Core plugin, ConnectionSource connectionSource, FunnyGuilds funnyGuilds) {
        this.funnyGuilds = funnyGuilds;
        this.plugin = plugin;
        this.connectionSource = connectionSource;

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        warManager = new WarManager(warEntityManager, funnyGuilds);

    }


    public void onEnable() {

        plugin.getCommand("configWojny").setExecutor(new WarConfigCommand(funnyGuilds, warManager, plugin));
        plugin.getCommand("wypowiedzwojne").setExecutor(new DeclareWarCommand(funnyGuilds, warManager));
        plugin.getCommand("wojny").setExecutor(new WarsCommand(funnyGuilds, warManager));

        plugin.getServer().getPluginManager().registerEvents(new GuildLoseLivesEvent(funnyGuilds), plugin);
        plugin.getServer().getPluginManager().registerEvents(new AddWarScoreAfterKill(funnyGuilds, warManager), plugin);

    }

}
