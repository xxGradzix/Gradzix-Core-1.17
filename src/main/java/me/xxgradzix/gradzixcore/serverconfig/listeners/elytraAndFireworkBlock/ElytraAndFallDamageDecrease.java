package me.xxgradzix.gradzixcore.serverconfig.listeners.elytraAndFireworkBlock;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class ElytraAndFallDamageDecrease implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL) ||
                event.getCause().equals(EntityDamageEvent.DamageCause.FLY_INTO_WALL)) {
            double oldDamage = event.getDamage();
            double newDamage = oldDamage / 30;
            event.setDamage(newDamage);
        }
    }
}
