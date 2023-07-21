package me.xxgradzix.gradzixcore.serverconfig.listeners;

import me.xxgradzix.gradzixcore.serverconfig.files.ConfigServera;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvent implements Listener {


    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {

            Player player = (Player) event.getDamager();



            event.setDamage(event.getDamage() * ConfigServera.getDamageMultiplier());

        }
    }

}
