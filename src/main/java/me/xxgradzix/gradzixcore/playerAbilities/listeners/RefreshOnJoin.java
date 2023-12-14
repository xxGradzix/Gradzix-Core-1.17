package me.xxgradzix.gradzixcore.playerAbilities.listeners;

import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RefreshOnJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        DataManager.refreshAbilities(player);
    }

}
