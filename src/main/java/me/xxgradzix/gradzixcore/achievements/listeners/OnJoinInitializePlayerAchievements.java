package me.xxgradzix.gradzixcore.achievements.listeners;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.achievements.database.DataManager;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementsProgressEntity;
import me.xxgradzix.gradzixcore.achievements.managers.AchievementManager;
import me.xxgradzix.gradzixcore.dailyQuests.managers.QuestsManagers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinInitializePlayerAchievements implements Listener {

    private DataManager dataManager;

    private AchievementManager achievementManager;

    public OnJoinInitializePlayerAchievements(DataManager dataManager, AchievementManager achievementManager) {
        this.dataManager = dataManager;
        this.achievementManager = achievementManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        achievementManager.insertProgress(player);
    }

}