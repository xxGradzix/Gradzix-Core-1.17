package me.xxgradzix.gradzixcore.afkRegion;

import com.j256.ormlite.support.ConnectionSource;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.afkRegion.listeners.EnterRegionListener;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AfkRegion {

    private final Gradzix_Core plugin;
    private final ConnectionSource connectionSource;
    public static final String afkRegionName = "afk_region";

    public AfkRegion(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public static ItemStack getSmallReward() {
        return null;
    }
    public static ItemStack getBigReward() {
        return null;
    }

    public void onEnable() {


        plugin.getServer().getPluginManager().registerEvents(new EnterRegionListener(), plugin);

//        plugin.getCommand("gamma").setExecutor(new GammaCommand());

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
