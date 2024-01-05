package me.xxgradzix.gradzixcore.playerPerks.listeners;

import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class StrengthPerkIncreaseDamage implements Listener {

    private final PlayerPerkEntityManager playerPerkEntityManager;

    public StrengthPerkIncreaseDamage(PlayerPerkEntityManager playerPerkEntityManager) {
        this.playerPerkEntityManager = playerPerkEntityManager;
    }

    @EventHandler
    public void onStrengthPerkIncreaseDamage(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        Player damager = (Player) event.getDamager();

        PlayerPerksEntity playerPerksEntity = playerPerkEntityManager.getPlayerPerksEntityById(damager.getUniqueId());

        double damage = event.getDamage();
        double damageIncrease = damage * (playerPerksEntity.getPerkTypeLevel(PerkType.STRENGTH) / 100.0);

        event.setDamage(damage + damageIncrease);

    }

}
