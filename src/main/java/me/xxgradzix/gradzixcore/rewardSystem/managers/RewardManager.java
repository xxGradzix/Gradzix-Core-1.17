package me.xxgradzix.gradzixcore.rewardSystem.managers;

import me.xxgradzix.gradzixcore.rewardSystem.database.entities.PlayerRewardsEntity;
import me.xxgradzix.gradzixcore.rewardSystem.database.managers.PlayerRewardsEntityManager;

import java.util.HashMap;
import java.util.UUID;

public class RewardManager {

    private final PlayerRewardsEntityManager playerRewardsEntityManager;

    public RewardManager(PlayerRewardsEntityManager playerRewardsEntityManager) {
        this.playerRewardsEntityManager = playerRewardsEntityManager;

    }


    public boolean addRewardToPlayer(UUID playerUUID, PlayerRewardsEntity.Reward reward, int quantity) {
        PlayerRewardsEntity playerRewardsEntity = playerRewardsEntityManager.getPlayerRewardsEntityByMinecraftId(playerUUID);
        if(playerRewardsEntity == null) {
            playerRewardsEntity = new PlayerRewardsEntity(playerUUID, new HashMap<>());
        }
        HashMap<String, Integer> rewards = playerRewardsEntity.getRewards();
        int currentRewards = rewards.getOrDefault(reward.name(), 0);
        currentRewards += quantity;
        if(currentRewards < 0) currentRewards = 0;
        rewards.put(reward.name(), currentRewards);
        playerRewardsEntity.setRewards(rewards);
        playerRewardsEntityManager.createOrUpdatePlayerRewardsEntity(playerRewardsEntity);
        return true;
    }
    public boolean removeRewardToPlayer(UUID playerUUID, PlayerRewardsEntity.Reward reward, int quantity) {
        PlayerRewardsEntity playerRewardsEntity = playerRewardsEntityManager.getPlayerRewardsEntityByMinecraftId(playerUUID);
        if(playerRewardsEntity == null) {
            return false;
        }
        HashMap<String, Integer> rewards = playerRewardsEntity.getRewards();

        int currentRewards = rewards.getOrDefault(reward.name(), 0);

        if((currentRewards - quantity) < 0) {
            return false;
        }
        currentRewards -= quantity;
        rewards.put(reward.name(), currentRewards);
        playerRewardsEntity.setRewards(rewards);
        playerRewardsEntityManager.createOrUpdatePlayerRewardsEntity(playerRewardsEntity);
        System.out.println(" quantity na " + currentRewards);
        return true;
    }
    public int getRewardOfPlayer(UUID playerUUID, PlayerRewardsEntity.Reward reward) {
        PlayerRewardsEntity playerRewardsEntity = playerRewardsEntityManager.getPlayerRewardsEntityByMinecraftId(playerUUID);
        if(playerRewardsEntity == null) {
            return 0;
        }
        HashMap<String, Integer> rewards = playerRewardsEntity.getRewards();
        return rewards.getOrDefault(reward.name(), 0);
    }

}
