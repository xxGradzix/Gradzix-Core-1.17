package me.xxgradzix.gradzixcore.umiejetnosci.listeners;

import me.xxgradzix.gradzixcore.umiejetnosci.data.DataManager;
import me.xxgradzix.gradzixcore.umiejetnosci.data.database.entities.enums.Ability;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL) ||
                event.getCause().equals(EntityDamageEvent.DamageCause.FLY_INTO_WALL)) {

            double oldDamage = event.getDamage();

            double newDamage = oldDamage / 30;


            event.setDamage(newDamage);

        }
        if (event.getDamager() instanceof Player) {

            Player player = (Player) event.getDamager();
            double damage = event.getDamage();

            int damagerLevel = DataManager.getPlayerAbilityLevel(Ability.STRENGTH, player);

            double multiplier = 1.0;

            if (damagerLevel > 0 ) multiplier = DataManager.getAbilityModifier(Ability.STRENGTH, damagerLevel);

            double increasedDamage = damage * multiplier;

            event.setDamage(increasedDamage);
        }
    }
}
