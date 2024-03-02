package me.xxgradzix.gradzixcore.clansCore.events;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ClanPointsChangeEvent extends Event {

    private final ClanEntity clan;
    private int pointsToChange;

    public ClanPointsChangeEvent(ClanEntity clan, int pointsToChange) {
        this.clan = clan;
        this.pointsToChange = pointsToChange;
    }

    public ClanEntity getClan() {
        return clan;
    }

    public int getPointsToChange() {
        return pointsToChange;
    }

    public void setPointsToChange(int pointsToChange) {
        this.pointsToChange = pointsToChange;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
