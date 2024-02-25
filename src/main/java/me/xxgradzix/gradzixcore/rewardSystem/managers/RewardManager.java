package me.xxgradzix.gradzixcore.rewardSystem.managers;

import me.xxgradzix.gradzixcore.rewardSystem.RewardSystem;
import me.xxgradzix.gradzixcore.rewardSystem.database.entities.PlayerRewardsEntity;
import me.xxgradzix.gradzixcore.rewardSystem.database.managers.PlayerRewardsEntityManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class RewardManager {

    private  final PlayerRewardsEntityManager playerRewardsEntityManager;

    public RewardManager(PlayerRewardsEntityManager playerRewardsEntityManager) {
        this.playerRewardsEntityManager = playerRewardsEntityManager;

    }

    public boolean hasAnyRewards(Player player) {
        PlayerRewardsEntity playerRewardsEntity = playerRewardsEntityManager.getPlayerRewardsEntityByMinecraftId(player.getUniqueId());
        if(playerRewardsEntity == null) return false;
        HashMap<String, Integer> rewards = playerRewardsEntity.getRewards();
        if(rewards.isEmpty()) return false;
        if(rewards.values().stream().mapToInt(Integer::intValue).sum() == 0) return false;
        return true;
    }


    public void addRewardToPlayer(UUID playerUUID, PlayerRewardsEntity.Reward reward, int amount) throws IllegalArgumentException {

        PlayerRewardsEntity playerRewardsEntity = playerRewardsEntityManager.getPlayerRewardsEntityByMinecraftId(playerUUID);
        if(playerRewardsEntity == null) playerRewardsEntity = new PlayerRewardsEntity(playerUUID, new HashMap<>());

        playerRewardsEntity.addReward(reward, amount);
        playerRewardsEntityManager.createOrUpdatePlayerRewardsEntity(playerRewardsEntity);
    }

    public boolean canCollectReward(UUID playerUUID, PlayerRewardsEntity.Reward reward, int amount) {
        PlayerRewardsEntity playerRewardsEntity = playerRewardsEntityManager.getPlayerRewardsEntityByMinecraftId(playerUUID);
        if(playerRewardsEntity == null) return false;
        HashMap<String, Integer> rewards = playerRewardsEntity.getRewards();
        return rewards.getOrDefault(reward.name(), 0) >= amount;
    }
    public void removeRewardFromPlayer(UUID playerUUID, PlayerRewardsEntity.Reward reward, int quantity) throws IllegalArgumentException {
        PlayerRewardsEntity playerRewardsEntity = playerRewardsEntityManager.getPlayerRewardsEntityByMinecraftId(playerUUID);
        if(playerRewardsEntity == null) playerRewardsEntity = new PlayerRewardsEntity(playerUUID, new HashMap<>());
        playerRewardsEntity.removeReward(reward, quantity);
        playerRewardsEntityManager.createOrUpdatePlayerRewardsEntity(playerRewardsEntity);
    }

    public int getRewardOfPlayer(UUID playerUUID, PlayerRewardsEntity.Reward reward) {
        PlayerRewardsEntity playerRewardsEntity = playerRewardsEntityManager.getPlayerRewardsEntityByMinecraftId(playerUUID);
        if(playerRewardsEntity == null) return 0;
        HashMap<String, Integer> rewards = playerRewardsEntity.getRewards();
        return rewards.getOrDefault(reward.name(), 0);
    }

}
