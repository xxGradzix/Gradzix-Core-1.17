package me.xxgradzix.gradzixcore.playerAbilities;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.playerAbilities.commands.GiveFragmentCommand;
import me.xxgradzix.gradzixcore.playerAbilities.commands.AbilitiesModifierCommand;
import me.xxgradzix.gradzixcore.playerAbilities.commands.ResetUmiejetnosci;
import me.xxgradzix.gradzixcore.playerAbilities.commands.UmiejetnosciCommand;
import me.xxgradzix.gradzixcore.playerAbilities.data.configfiles.AbilitiesModifiersConfigFile;
import me.xxgradzix.gradzixcore.playerAbilities.data.configfiles.UmiejetnosciConfigFile;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.AbilityModifierEntity;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.PlayerAbilitiesEntity;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.managers.AbilitiesModifiersEntityManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.managers.PlayerAbilitiesEntityManager;
import me.xxgradzix.gradzixcore.playerAbilities.items.ItemManager;
import me.xxgradzix.gradzixcore.playerAbilities.listeners.DamageListener;
import me.xxgradzix.gradzixcore.playerAbilities.listeners.OnBlockBreak;
import me.xxgradzix.gradzixcore.playerAbilities.listeners.PlayerKillRankingIncrease;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerAbilities {

    private final Gradzix_Core plugin;

    // db change
    private final ConnectionSource connectionSource;
    private static PlayerAbilitiesEntityManager playerAbilitiesEntityManager;

    private static AbilitiesModifiersEntityManager abilitiesModifiersEntityManager;

    public static AbilitiesModifiersEntityManager getAbilitiesModifiersEntityManager() {
        return abilitiesModifiersEntityManager;
    }
    public static PlayerAbilitiesEntityManager getPlayerAbilitiesEntityManager() {
        return playerAbilitiesEntityManager;
    }
    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, PlayerAbilitiesEntity.class);
        playerAbilitiesEntityManager = new PlayerAbilitiesEntityManager(connectionSource);

        TableUtils.createTableIfNotExists(connectionSource, AbilityModifierEntity.class);
        abilitiesModifiersEntityManager = new AbilitiesModifiersEntityManager(connectionSource);

    }
    ////////////

    public PlayerAbilities(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        plugin.getCommand("umiejetnosci").setExecutor(new UmiejetnosciCommand());
        plugin.getCommand("modyfikatoryumiejetnosci").setExecutor(new AbilitiesModifierCommand());
        plugin.getCommand("giveodlamek").setExecutor(new GiveFragmentCommand());

        plugin.getCommand("resetumiejetnosci").setExecutor(new ResetUmiejetnosci());

        plugin.getServer().getPluginManager().registerEvents(new DamageListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnBlockBreak(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerKillRankingIncrease(), plugin);

        UmiejetnosciConfigFile.setup();
        UmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci", new ArrayList<String>());
        UmiejetnosciConfigFile.getCustomFile().options().copyDefaults(true);
        UmiejetnosciConfigFile.save();


        AbilitiesModifiersConfigFile.setup();

        AbilitiesModifiersConfigFile.getCustomFile().addDefault("umiejetnosci.sila.1", 1.1);
        AbilitiesModifiersConfigFile.getCustomFile().addDefault("umiejetnosci.sila.2", 1.2);
        AbilitiesModifiersConfigFile.getCustomFile().addDefault("umiejetnosci.sila.3", 1.3);
        AbilitiesModifiersConfigFile.getCustomFile().addDefault("umiejetnosci.sila.4", 1.4);


        AbilitiesModifiersConfigFile.getCustomFile().addDefault("umiejetnosci.drop.1", 1.5);
        AbilitiesModifiersConfigFile.getCustomFile().addDefault("umiejetnosci.drop.2", 2.0);
        AbilitiesModifiersConfigFile.getCustomFile().addDefault("umiejetnosci.drop.3", 2.5);
        AbilitiesModifiersConfigFile.getCustomFile().addDefault("umiejetnosci.drop.4", 3.0);

        AbilitiesModifiersConfigFile.getCustomFile().addDefault("umiejetnosci.rank.1", 1.2);
        AbilitiesModifiersConfigFile.getCustomFile().addDefault("umiejetnosci.rank.2", 1.4);
        AbilitiesModifiersConfigFile.getCustomFile().addDefault("umiejetnosci.rank.3", 1.6);
        AbilitiesModifiersConfigFile.getCustomFile().addDefault("umiejetnosci.rank.4", 1.8);

        AbilitiesModifiersConfigFile.getCustomFile().options().copyDefaults(true);
        AbilitiesModifiersConfigFile.save();

        ItemManager.init();
    }

}
