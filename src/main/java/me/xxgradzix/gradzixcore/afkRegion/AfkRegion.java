package me.xxgradzix.gradzixcore.afkRegion;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.afkRegion.commands.SetRewardCommand;
import me.xxgradzix.gradzixcore.afkRegion.data.database.entities.RewardsEntity;
import me.xxgradzix.gradzixcore.afkRegion.data.database.managers.RewardsEntityManager;
import me.xxgradzix.gradzixcore.afkRegion.listeners.EnterRegionListener;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class AfkRegion {

    private final Gradzix_Core plugin;
    private final ConnectionSource connectionSource;
    public static final String afkRegionName = "afk_region";

    private static RewardsEntityManager rewardsEntityManager;

    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, RewardsEntity.class);
        rewardsEntityManager = new RewardsEntityManager(connectionSource);
    }

    public AfkRegion(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public static ItemStack getSmallReward() {
        return rewardsEntityManager.getRewardsEntity().getSmallReward();
    }
    public static ItemStack getBigReward() {
        return rewardsEntityManager.getRewardsEntity().getBigReward();
    }

    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        plugin.getServer().getPluginManager().registerEvents(new EnterRegionListener(), plugin);

        plugin.getCommand("setAfkReward").setExecutor(new SetRewardCommand(rewardsEntityManager));

    }
    public static boolean isPlayerInAfkRegion(Player player) {

        Location location = player.getLocation();
        ApplicableRegionSet regionSet = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().getApplicableRegions(BukkitAdapter.adapt(location));

        for(ProtectedRegion region : regionSet) {
            if(region.getId().startsWith(afkRegionName)) return true;
        }
        return false;
    }
}
