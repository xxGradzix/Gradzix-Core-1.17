package me.xxgradzix.gradzixcore.achievements.managers;

import me.xxgradzix.gradzixcore.achievements.AchievementType;
import me.xxgradzix.gradzixcore.achievements.Achievements;
import me.xxgradzix.gradzixcore.achievements.database.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class AchievementManager {

    private DataManager dataManager;

    private HashMap<UUID, HashMap<AchievementType, Integer>> playerProgress = new HashMap<>();

    public AchievementManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void saveAllAchievements() {
        for (UUID uuid : playerProgress.keySet()) {
            saveAchievements(Bukkit.getPlayer(uuid));
        }
    }

    public void insertProgress(Player player) {
        playerProgress.put(player.getUniqueId(), dataManager.getAllAchievements(player));
    }

    public void addProgress(Player player, AchievementType type, int progress) {

        int currentProgress = playerProgress.get(player.getUniqueId()).get(type);
        if(progress >= Achievements.getGoal(type)) return;

        if(playerProgress.containsKey(player)) {

            HashMap<AchievementType, Integer> playerAchievements = playerProgress.get(player.getUniqueId());

            if(playerAchievements.containsKey(type)) {
                playerAchievements.put(type, currentProgress + progress);
            } else {
                dataManager.createPlayerAchievementEntity(type, player);
                playerAchievements.put(type, progress);
            }
            playerProgress.put(player.getUniqueId(), playerAchievements);
        } else {
            insertProgress(player);
            addProgress(player, type, progress);
        }
        if(currentProgress + progress >= Achievements.getGoal(type)) {
            dataManager.completeAchievement(player, type);
        }
    }

    public int getProgress(Player player, AchievementType type) {
        HashMap<AchievementType, Integer> achievementTypeIntegerHashMap = playerProgress.getOrDefault(player.getUniqueId(), new HashMap<>());
        return achievementTypeIntegerHashMap.getOrDefault(type, 0);
    }


    public void saveAchievements(Player player) {
        HashMap<AchievementType, Integer> achievements = playerProgress.getOrDefault(player.getUniqueId(), new HashMap<>());
        dataManager.saveAchievements(player, achievements);
    }
}