package me.xxgradzix.gradzixcore.achievements.listeners;

import me.xxgradzix.gradzixcore.achievements.database.DataManager;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementsProgressEntity;
import me.xxgradzix.gradzixcore.achievements.managers.AchievementManager;
import me.xxgradzix.gradzixcore.dailyQuests.managers.QuestsManagers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnQuitSave implements Listener {

    private DataManager dataManager;
    private AchievementManager achievementManager;

    public OnQuitSave(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        achievementManager.saveAchievements(player);
    }
}
