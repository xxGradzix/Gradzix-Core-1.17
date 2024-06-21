package me.xxgradzix.gradzixcore.achievements.listeners;

import me.xxgradzix.gradzixcore.achievements.AchievementType;
import me.xxgradzix.gradzixcore.achievements.managers.AchievementManager;
import me.xxgradzix.gradzixcore.dailyQuests.QuestType;
import me.xxgradzix.gradzixcore.dailyQuests.managers.QuestsManagers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import static me.xxgradzix.gradzixcore.dailyQuests.managers.QuestsManagers.onQuestProgress;

public class AchievementListener implements Listener {

    private AchievementManager achievementManager;

    public AchievementListener(AchievementManager achievementManager) {
        this.achievementManager = achievementManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;
        achievementManager.addProgress(event.getPlayer(), AchievementType.BLOCKS_BROKEN, 1);
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.isCancelled()) return;
        achievementManager.addProgress(event.getPlayer(), AchievementType.BLOCKS_PLACED, 1);
    }
    @EventHandler
    public void onTotemOfUndyingUse(EntityResurrectEvent event) {
        if(event.isCancelled()) return;
        if(!(event.getEntity() instanceof Player)) return;
        if(event.getEntity().getKiller() == null) return;
        achievementManager.addProgress(((Player) event.getEntity()).getKiller(), AchievementType.TOTEM_OF_UNDYING, 1);
    }
    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        if(event.getEntity().getKiller() == null) return;
        achievementManager.addProgress(event.getEntity().getKiller(), AchievementType.PLAYERS_KILLED, 1);
    }


}
