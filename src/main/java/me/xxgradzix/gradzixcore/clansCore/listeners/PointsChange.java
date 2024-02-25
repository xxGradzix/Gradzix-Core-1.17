package me.xxgradzix.gradzixcore.clansCore.listeners;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.events.UserPointsChangeEvent;
import me.xxgradzix.gradzixcore.clansCore.managers.PointsManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PointsChange implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if(killer == null) return;
        if(victim == killer) return;
        if(victim.equals(killer)) return;

        UserEntity killerEntity = UserManager.getOrCreateUserEntity(killer);
        UserEntity victimEntity = UserManager.getOrCreateUserEntity(victim);

        int pointsChange = PointsManager.calculatePointsChange(killerEntity.getPoints(), victimEntity.getPoints());

        UserPointsChangeEvent pointsChangeEvent = new UserPointsChangeEvent(killer, victim, pointsChange, pointsChange);

        Bukkit.getPluginManager().callEvent(pointsChangeEvent);

        killerEntity = UserManager.getOrCreateUserEntity(pointsChangeEvent.getKiller());
        victimEntity = UserManager.getOrCreateUserEntity(pointsChangeEvent.getVictim());

        killerEntity.setPoints(killerEntity.getPoints() + pointsChangeEvent.getKillerPointsToGet());
        victimEntity.setPoints(victimEntity.getPoints() - pointsChangeEvent.getVictimPointsToLose());

        killerEntity.setKills(killerEntity.getKills() + 1);
        victimEntity.setDeaths(victimEntity.getDeaths() + 1);

        UserManager.updateUserEntity(killerEntity);
        UserManager.updateUserEntity(victimEntity);

        // TODO add message to killer and victim

    }

}
