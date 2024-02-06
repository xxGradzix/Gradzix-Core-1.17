package me.xxgradzix.gradzixcore.playerPerks;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.playerPerks.commands.SetPerkLevelCommand;
import me.xxgradzix.gradzixcore.playerPerks.commands.UpgradePerksCommand;
import me.xxgradzix.gradzixcore.playerPerks.commands.PerksCommand;
import me.xxgradzix.gradzixcore.playerPerks.commands.GivePerkBooksCommand;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import me.xxgradzix.gradzixcore.playerPerks.items.ItemManager;
import me.xxgradzix.gradzixcore.playerPerks.listeners.BookClickIncreaseLevel;
import me.xxgradzix.gradzixcore.playerPerks.listeners.PotionEffectAfflictionOnDamage;
import me.xxgradzix.gradzixcore.playerPerks.listeners.SetHeartsOnJoin;
import me.xxgradzix.gradzixcore.playerPerks.listeners.StrengthPerkIncreaseDamage;

import java.sql.SQLException;

public class PlayerPerks {

    private final Gradzix_Core plugin;

    // db change
    private final ConnectionSource connectionSource;

    private PlayerPerkEntityManager playerPerksEntityManager;
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
        plugin.getServer().getPluginManager().registerEvents(new PotionEffectAfflictionOnDamage(playerPerksEntityManager), plugin);
        plugin.getServer().getPluginManager().registerEvents(new StrengthPerkIncreaseDamage(playerPerksEntityManager), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SetHeartsOnJoin(playerPerksEntityManager), plugin);

        plugin.getCommand("givePerkBook").setExecutor(new GivePerkBooksCommand());
        plugin.getCommand("ustawPerk").setExecutor(new SetPerkLevelCommand(playerPerksEntityManager));
        plugin.getCommand("perki").setExecutor(new PerksCommand(playerPerksEntityManager));
        plugin.getCommand("umiejetnosci").setExecutor(new UpgradePerksCommand(plugin));

        ItemManager.init();
    }
}
