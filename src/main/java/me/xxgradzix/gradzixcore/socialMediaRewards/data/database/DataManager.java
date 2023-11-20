package me.xxgradzix.gradzixcore.socialMediaRewards.data.database;

import me.xxgradzix.gradzixcore.socialMediaRewards.data.database.entities.SocialMediaRewardsEntity;
import me.xxgradzix.gradzixcore.socialMediaRewards.data.database.managers.SocialMediaRewardsEntityManager;
import org.bukkit.entity.Player;

import java.util.Optional;

public class DataManager {

    public enum Reward {
        YOUTUBE, DISCORD, FACEBOOK, TIKTOK
    }
    private final SocialMediaRewardsEntityManager entityManager;

    public DataManager(SocialMediaRewardsEntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void setRewardCollectStatus(Player player, boolean value, Reward reward) {
        Optional<SocialMediaRewardsEntity> optionalEntity = entityManager.getSocialMediaRewardsEntity(player.getUniqueId());
        SocialMediaRewardsEntity entity;
        if(optionalEntity.isPresent()) {
            entity = optionalEntity.get();

        } else {
            entity = new SocialMediaRewardsEntity(
                    player.getUniqueId(),
                    false,
                    false,
                    false,
                    false);
        }
        switch (reward) {
            case TIKTOK: entity.setTiktokRewardCollected(value); break;
            case YOUTUBE: entity.setYoutubeRewardCollected(value); break;
            case DISCORD: entity.setDiscordRewardCollected(value); break;
            case FACEBOOK: entity.setFacebookRewardCollected(value); break;
        }
        entityManager.createOrUpdateSocialMediaRewardsEntity(entity);
    }

    public boolean isRewardCollected(Player player, Reward reward) {
        Optional<SocialMediaRewardsEntity> entity = entityManager.getSocialMediaRewardsEntity(player.getUniqueId());
        if(!entity.isPresent()) return false;
        switch (reward) {
            case TIKTOK: return entity.get().isTiktokRewardCollected();
            case YOUTUBE: return entity.get().isYoutubeRewardCollected();
            case DISCORD: return entity.get().isDiscordRewardCollected();
            case FACEBOOK: return entity.get().isFacebookRewardCollected();
        }
        throw new EnumConstantNotPresentException(Reward.class, "Reward " + reward + " does not exists");
    }


}
