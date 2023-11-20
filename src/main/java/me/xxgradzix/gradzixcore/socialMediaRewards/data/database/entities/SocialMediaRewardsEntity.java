package me.xxgradzix.gradzixcore.socialMediaRewards.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_social_media_rewards")
@Data
@NoArgsConstructor
public class SocialMediaRewardsEntity {

    @DatabaseField(id = true, unique = true)
    private UUID playerUUID;

    @DatabaseField
    private boolean isYoutubeRewardCollected;

    @DatabaseField
    private boolean isDiscordRewardCollected;

    @DatabaseField
    private boolean isFacebookRewardCollected;

    @DatabaseField
    private boolean isTiktokRewardCollected;

    public SocialMediaRewardsEntity(UUID playerUUID, boolean isYoutubeRewardCollected, boolean isDiscordRewardCollected, boolean isFacebookRewardCollected, boolean isTiktokRewardCollected) {
        this.playerUUID = playerUUID;
        this.isYoutubeRewardCollected = isYoutubeRewardCollected;
        this.isDiscordRewardCollected = isDiscordRewardCollected;
        this.isFacebookRewardCollected = isFacebookRewardCollected;
        this.isTiktokRewardCollected = isTiktokRewardCollected;
    }
}
