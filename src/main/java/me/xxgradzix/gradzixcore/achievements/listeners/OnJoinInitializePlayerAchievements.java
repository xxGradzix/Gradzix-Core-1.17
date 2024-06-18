package me.xxgradzix.gradzixcore.achievements.listeners;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.achievements.database.DataManager;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementsProgressEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinInitializePlayerAchievements implements Listener {

    private DataManager dataManager;

    public OnJoinInitializePlayerAchievements(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        dataManager.createPlayerAchievementsIfNotExists(player);

    }

}
