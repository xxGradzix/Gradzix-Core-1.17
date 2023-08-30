package me.xxgradzix.gradzixcore.serverconfig.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class StrefaAFK implements Listener {


    private Gradzix_Core plugin;

    public StrefaAFK(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
//        if (getPlayerRegionName(event.getPlayer()) != null && getPlayerRegionName(event.getPlayer()).equals("strefaafk")) {
//
//            for (org.bukkit.entity.Player onlinePlayer : event.getPlayer().getWorld().getPlayers()) {
//                if (onlinePlayer != event.getPlayer()) {
//                    event.getPlayer().hidePlayer(plugin, onlinePlayer);
//                }
//            }
//        } else {
//            for (org.bukkit.entity.Player onlinePlayer : event.getPlayer().getWorld().getPlayers()) {
//                if (onlinePlayer != event.getPlayer()) {
//                    event.getPlayer().showPlayer(plugin, onlinePlayer);
//                }
//            }
//        }
        if (getPlayerRegionName(event.getPlayer()) != null && getPlayerRegionName(event.getPlayer()).equals("strefaafk")) {
            for (org.bukkit.entity.Player onlinePlayer : event.getPlayer().getWorld().getPlayers()) {
                if (onlinePlayer != event.getPlayer()) {
                    onlinePlayer.hidePlayer(plugin, event.getPlayer());
                }
            }
        } else {
            for (org.bukkit.entity.Player onlinePlayer : event.getPlayer().getWorld().getPlayers()) {
                if (onlinePlayer != event.getPlayer()) {
                    onlinePlayer.showPlayer(plugin, event.getPlayer());
                }
            }
        }
    }

    public String getPlayerRegionName(Player player) {
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        Location location = player.getLocation();

        ApplicableRegionSet regionSet = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().getApplicableRegions(BukkitAdapter.adapt(location));

        for (ProtectedRegion region : regionSet) {
            if (region.contains(location.getBlockX(), location.getBlockY(), location.getBlockZ())) {
                return region.getId();
            }
        }

        return null;
    }


}
