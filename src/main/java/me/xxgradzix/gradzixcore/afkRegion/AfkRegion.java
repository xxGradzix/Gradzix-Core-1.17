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
import org.bukkit.Material;
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

    private static ItemStack smallReward = new ItemStack(Material.DIAMOND);
    private static ItemStack bigReward = new ItemStack(Material.DIAMOND);
    private static Double smallRewardMoney = 1.0;

    public static ItemStack getSmallReward() {
        return smallReward;
    }
    public static Double getSmallRewardMoney() {
        return smallRewardMoney;
    }
    public static ItemStack getBigReward() {
        return bigReward;
    }
    public static void updateRewards() {
        smallReward = rewardsEntityManager.getRewardsEntity().getSmallReward();
        bigReward = rewardsEntityManager.getRewardsEntity().getBigReward();
        smallRewardMoney = rewardsEntityManager.getRewardsEntity().getSmallRewardMoney();
    }

    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        updateRewards();
        GetRewardInAfkRegion.startCounterForAllPlayers();

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
