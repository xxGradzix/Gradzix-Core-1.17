package me.xxgradzix.gradzixcore.incognito;


import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.incognito.commands.AddIncognitoAdminCommand;
import me.xxgradzix.gradzixcore.incognito.commands.IncognitoCommand;
import me.xxgradzix.gradzixcore.incognito.data.database.entities.IncognitoAdminEntity;
import me.xxgradzix.gradzixcore.incognito.data.database.entities.IncognitoModeEntity;
import me.xxgradzix.gradzixcore.incognito.data.database.managers.IncognitoAdminEntityManager;
import me.xxgradzix.gradzixcore.incognito.data.database.managers.IncognitoModeEntityManager;
import me.xxgradzix.gradzixcore.incognito.manages.IncognitoModeManager;

import java.sql.SQLException;

public class Incognito {


    private final Gradzix_Core plugin;

    private final ConnectionSource connectionSource;

    private IncognitoModeEntityManager incognitoModeEntityManager;
    private IncognitoAdminEntityManager incognitoAdminEntityManager;
    public Incognito(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;

    }
    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, IncognitoModeEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, IncognitoAdminEntity.class);
        incognitoModeEntityManager = new IncognitoModeEntityManager(connectionSource);
        incognitoAdminEntityManager = new IncognitoAdminEntityManager(connectionSource);
    }

    public void onEnable() {
        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        new IncognitoModeManager(incognitoModeEntityManager, incognitoAdminEntityManager);

        plugin.getCommand("incognito").setExecutor(new IncognitoCommand());
        plugin.getCommand("incognitoAdmin").setExecutor(new AddIncognitoAdminCommand(incognitoAdminEntityManager));
    }


}
