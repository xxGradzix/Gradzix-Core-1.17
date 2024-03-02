package me.xxgradzix.gradzixcore.clansCore.listeners;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.events.UserPointsChangeEvent;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PointsChange implements Listener {
    private static final int POINTS_CHANGE = 10;

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if(killer == null) return;
        if(victim == killer) return;
        if(victim.equals(killer)) return;

        UserPointsChangeEvent pointsChangeEvent = new UserPointsChangeEvent(killer, victim, POINTS_CHANGE, POINTS_CHANGE);

        Bukkit.getPluginManager().callEvent(pointsChangeEvent);

        UserEntity killerEntity = UserManager.getOrCreateUserEntity(pointsChangeEvent.getKiller());
        UserEntity victimEntity = UserManager.getOrCreateUserEntity(pointsChangeEvent.getVictim());

        killerEntity.setPoints(killerEntity.getPoints() + pointsChangeEvent.getKillerPointsToGet());
        victimEntity.setPoints(victimEntity.getPoints() - pointsChangeEvent.getVictimPointsToLose());

        killerEntity.setKills(killerEntity.getKills() + 1);
        victimEntity.setDeaths(victimEntity.getDeaths() + 1);

        UserManager.updateUserEntity(killerEntity);
        UserManager.updateUserEntity(victimEntity);

        // TODO add message to killer and victim

    }

}
