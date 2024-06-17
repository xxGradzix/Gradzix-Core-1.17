package me.xxgradzix.gradzixcore.playerPerks;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.playerPerks.PerkGui.PerkGuiManager;
import me.xxgradzix.gradzixcore.playerPerks.commands.SetPerkLevelCommand;
import me.xxgradzix.gradzixcore.playerPerks.commands.UpgradePerksCommand;
import me.xxgradzix.gradzixcore.playerPerks.commands.PerksCommand;
import me.xxgradzix.gradzixcore.playerPerks.commands.GivePerkBooksCommand;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import me.xxgradzix.gradzixcore.playerPerks.items.ItemManager;
import me.xxgradzix.gradzixcore.playerPerks.listeners.*;

import java.sql.SQLException;

public class PlayerPerks {

    public static final int STRENGTH_MAX_LEVEL = 20;
    public static final int POISON_MAX_LEVEL = 10;
    public static final int RESISTANCE_MAX_LEVEL = 20;
    public static final int LIFE_STEAL_MAX_LEVEL = 10;
    public static final int ADDITIONAL_HEARTS_MAX_LEVEL = 4;
    public static final int WEAKNESS_MAX_LEVEL = 10;
    public static final int SLOWNESS_MAX_LEVEL = 10;
    public static final int PERK_FRAGMENT_DROP_MAX_LEVEL = 20;

    private final Gradzix_Core plugin;

    public static final int FRAGMENT_COST = 100;



    // db change
    private final ConnectionSource connectionSource;

    private final PerkGuiManager perkGuiManager = new PerkGuiManager();

    private static PlayerPerkEntityManager playerPerksEntityManager;

    public static PlayerPerkEntityManager getPlayerPerkEntityManager() {
        return playerPerksEntityManager;
    }

    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, PlayerPerksEntity.class);
        playerPerksEntityManager = new PlayerPerkEntityManager(connectionSource);
    }

    public PlayerPerks(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        plugin.getServer().getPluginManager().registerEvents(new BookClickIncreaseLevel(playerPerksEntityManager), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PotionEffectAfflictionOnDamage(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ResistanceEffectOnDamage(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new StrengthPerkIncreaseDamage(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SetHeartsOnJoin(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new DropFragmentOnKill(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new WeaknessEffectAfflictionOnDamage(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new JoinListener(), plugin);

        plugin.getCommand("givePerkBook").setExecutor(new GivePerkBooksCommand());
        plugin.getCommand("ustawPerk").setExecutor(new SetPerkLevelCommand(playerPerksEntityManager));
        plugin.getCommand("perki").setExecutor(new PerksCommand(playerPerksEntityManager));
        plugin.getCommand("umiejetnosci").setExecutor(new UpgradePerksCommand(plugin, playerPerksEntityManager, perkGuiManager));

        ItemManager.init();
    }
}
