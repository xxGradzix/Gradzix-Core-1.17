package me.xxgradzix.gradzixcore.clansCore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class UserPointsChangeEvent extends Event {

    private final Player killer;
    private final Player victim;
    private int killerPointsToGet;
    private int victimPointsToLose;

    public UserPointsChangeEvent(Player killer, Player victim, int killerPointsToGet, int victimPointsToLose) {
        this.killer = killer;
        this.victim = victim;
        this.killerPointsToGet = killerPointsToGet;
        this.victimPointsToLose = victimPointsToLose;
    }

    public Player getKiller() {
        return killer;
    }

    public Player getVictim() {
        return victim;
    }

    public int getKillerPointsToGet() {
        return killerPointsToGet;
    }

    public void setKillerPointsToGet(int killerPointsToGet) {
        this.killerPointsToGet = killerPointsToGet;
    }

    public int getVictimPointsToLose() {
        return victimPointsToLose;
    }

    public void setVictimPointsToLose(int victimPointsToLose) {
        this.victimPointsToLose = victimPointsToLose;
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
