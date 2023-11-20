package me.xxgradzix.gradzixcore.socialMediaRewards.listeners;

import me.xxgradzix.gradzixcore.socialMediaRewards.SocialMediaRewards;
import me.xxgradzix.gradzixcore.socialMediaRewards.customEvents.ClickLinkEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LinkClickEvent implements Listener {


    @EventHandler
    public void onLinkClick(ClickLinkEvent event) {

        Player player = event.getPlayer();
        String link = event.getLink();

        switch (link) {
            case SocialMediaRewards.YOUTUBE_LINK:
                player.sendMessage("Kliknął yt");
                break;
            case SocialMediaRewards.DISCORD_LINK:
                player.sendMessage("Kliknął dc");
                break;
            case SocialMediaRewards.FACEBOOK_LINK:
                player.sendMessage("Kliknął fb");
                break;

            case SocialMediaRewards.TIKTOK_LINK:
                player.sendMessage("Kliknął tt");
                break;

        }

    }

}
