package me.xxgradzix.gradzixcore.clansCore.events;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ClanLivesChangeEvent extends Event {

    private final ClanEntity clan;
    private int newLives;

    public ClanLivesChangeEvent(ClanEntity clan, int newLives) {
        this.clan = clan;
        this.newLives = newLives;
    }

    public ClanEntity getClan() {
        return clan;
    }

    public int getNewLives() {
        return newLives;
    }

    public void setNewLives(int newLives) {
        this.newLives = newLives;
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
