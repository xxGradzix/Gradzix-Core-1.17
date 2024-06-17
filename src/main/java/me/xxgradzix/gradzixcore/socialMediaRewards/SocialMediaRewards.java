package me.xxgradzix.gradzixcore.socialMediaRewards;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.adminPanel.items.ItemManager;
import me.xxgradzix.gradzixcore.socialMediaRewards.commands.NagrodaCommand;
import me.xxgradzix.gradzixcore.socialMediaRewards.data.database.DataManager;
import me.xxgradzix.gradzixcore.socialMediaRewards.data.database.entities.SocialMediaRewardsEntity;
import me.xxgradzix.gradzixcore.socialMediaRewards.data.database.managers.SocialMediaRewardsEntityManager;
import me.xxgradzix.gradzixcore.serverconfig.listeners.BlockGrief;
import me.xxgradzix.gradzixcore.socialMediaRewards.listeners.LinkClickEvent;

import java.sql.SQLException;

public class SocialMediaRewards {

    public static final String TIKTOK_LINK = "https://www.tiktok.com/@majkitoja_yt/video/7185189244334247174?is_from_webapp=1&sender_device=pc&web_id=7137431783154697733";
    public static final String YOUTUBE_LINK = "https://www.youtube.com/@UniMcPL";
    public static final String FACEBOOK_LINK = "";
    public static final String DISCORD_LINK = "https://discord.gg/548tXhxg";
    private final Gradzix_Core plugin;
    // db change
    private final ConnectionSource connectionSource;

    private SocialMediaRewardsEntityManager socialMediaRewardsEntityManager;

    private DataManager dataManager;
    public void configureDB() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, SocialMediaRewardsEntity.class);
        socialMediaRewardsEntityManager= new SocialMediaRewardsEntityManager(connectionSource);
    }

    public SocialMediaRewards(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }

    public void onEnable() {

        try {
            configureDB();
            dataManager = new DataManager(socialMediaRewardsEntityManager);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ItemManager.init();

        plugin.getServer().getPluginManager().registerEvents(new LinkClickEvent(), plugin);
//        plugin.getCommand("nagroda").setExecutor(new NagrodaCommand(dataManager, plugin));


    }

}
