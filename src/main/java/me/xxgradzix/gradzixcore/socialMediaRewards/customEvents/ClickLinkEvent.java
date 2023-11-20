package me.xxgradzix.gradzixcore.socialMediaRewards.customEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ClickLinkEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    Player player;

    String link;
    public ClickLinkEvent(Player player, String link) {
        this.player = player;
        this.link = link;
    }

    public Player getPlayer() {
        return player;
    }

    public String getLink() {
        return link;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
