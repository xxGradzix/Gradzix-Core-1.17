package me.xxgradzix.gradzixcore.rewardSystem.database.entities;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import me.xxgradzix.gradzixcore.rewardSystem.database.persisters.HashMapItemStackIntegerType;

import java.util.HashMap;
import java.util.UUID;

@DatabaseTable(tableName = "players_rewards")
public class PlayerRewardsEntity {

    public enum Reward {
        caveman_key, magic_key, scratchcard
    }

    @DatabaseField(unique = true, id = true)
    private UUID id;

    @DatabaseField(persisterClass = HashMapItemStackIntegerType.class, columnDefinition = "BLOB")
    private HashMap<String, Integer> rewards;

    public PlayerRewardsEntity(UUID id, HashMap<String, Integer> rewards) {
        this.id = id;
        this.rewards = rewards;
    }

    public PlayerRewardsEntity() {
        rewards = new HashMap<>();
    }

    public UUID getId() {
        return id;
    }

    public HashMap<String, Integer> getRewards() {
        return new HashMap<>(rewards);
    }

    public void addReward(Reward reward, int amount) throws IllegalArgumentException {
        if(amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        rewards.put(reward.name().toLowerCase(), rewards.getOrDefault(reward.name().toLowerCase(), 0) + amount);
    }
    public int getRewardAmount(Reward reward) {
        return rewards.getOrDefault(reward.name().toLowerCase(), 0);
    }
    public void removeReward(Reward reward, int amount) throws IllegalArgumentException {
        if(amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        rewards.put(reward.name().toLowerCase(), rewards.getOrDefault(reward.name().toLowerCase(), 0) - amount);
        if(rewards.get(reward.name().toLowerCase()) < 0) rewards.put(reward.name().toLowerCase(), 0);
    }
}
