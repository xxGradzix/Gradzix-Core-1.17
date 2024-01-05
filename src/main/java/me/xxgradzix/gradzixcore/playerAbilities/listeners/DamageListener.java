package me.xxgradzix.gradzixcore.playerAbilities.listeners;

import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();
        double damage = event.getDamage();

        int damagerLevel = DataManager.getPlayerAbilityLevel(Ability.STRENGTH, player);

        double multiplier = 1.0;

        if (damagerLevel > 0 ) multiplier = DataManager.getAbilityModifier(Ability.STRENGTH, damagerLevel);

        double increasedDamage = damage * multiplier;

        event.setDamage(increasedDamage);

    }
}
