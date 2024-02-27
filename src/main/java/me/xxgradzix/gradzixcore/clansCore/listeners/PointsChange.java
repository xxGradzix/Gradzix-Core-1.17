package me.xxgradzix.gradzixcore.clansCore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public class PointsChange extends Event implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if(killer == null) return;
        if(victim == killer) return;

        // call event
        Bukkit.getPluginManager().callEvent(new PointsChange(killer, 1));

    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
