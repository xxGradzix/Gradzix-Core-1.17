package me.xxgradzix.gradzixcore.afkRegion.listeners;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.afkRegion.AfkRegion;
import me.xxgradzix.gradzixcore.afkRegion.data.database.managers.RewardsEntityManager;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import net.raidstone.wgevents.events.RegionLeftEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EnterRegionListener implements Listener {

    private final JavaPlugin plugin = Gradzix_Core.getInstance();

    @EventHandler
    public void enterRegionListener(RegionEnteredEvent event) {
        Player player = event.getPlayer();

        if(!event.getRegionName().startsWith(AfkRegion.afkRegionName)) return;
        showPlayersInAfkRegion(player);
        GetRewardInAfkRegion.startCounterForPlayer(player);
    }
    @EventHandler
    public void onPlayerLeftAfkRegion(RegionLeftEvent event) {

        Player player = event.getPlayer();

        if(!event.getRegionName().startsWith(AfkRegion.afkRegionName)) return;

        showPlayerOutsideAfkRegion(player);
        GetRewardInAfkRegion.cancelPlayerTask(player);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(AfkRegion.isPlayerInAfkRegion(player)) {
            showPlayersInAfkRegion(player);
        } else {
            showPlayerOutsideAfkRegion(player);
        }

    }
    private void showPlayerOutsideAfkRegion(Player player) {

        for (Player onlinePlayer : player.getWorld().getPlayers()) {

            if(onlinePlayer == player) continue;

            if(AfkRegion.isPlayerInAfkRegion(onlinePlayer)) {
                onlinePlayer.hidePlayer(plugin, player);
                player.hidePlayer(plugin, onlinePlayer);
            } else {
                onlinePlayer.showPlayer(plugin, player);
                player.showPlayer(plugin, onlinePlayer);
            }
        }
    }
    private void showPlayersInAfkRegion(Player player) {
        for (Player onlinePlayer : player.getWorld().getPlayers()) {

            if(onlinePlayer == player) continue;

            if(AfkRegion.isPlayerInAfkRegion(onlinePlayer)) {
                onlinePlayer.showPlayer(plugin, player);
                player.showPlayer(plugin, onlinePlayer);
            } else {
                onlinePlayer.hidePlayer(plugin, player);
                player.hidePlayer(plugin, onlinePlayer);
            }
        }
    }



}
