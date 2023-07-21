package me.xxgradzix.gradzixcore.umiejetnosci.listeners;

import me.xxgradzix.gradzixcore.umiejetnosci.files.ModyfikatoryUmiejetnosciConfigFile;
import me.xxgradzix.gradzixcore.umiejetnosci.files.UmiejetnosciConfigFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {

            Player player = (Player) event.getDamager();
            double damage = event.getDamage();

            int damagerLevel = UmiejetnosciConfigFile.getSilaLevel(player);

            double multiplier = 1.0;

            if (damagerLevel > 0 ) multiplier = ModyfikatoryUmiejetnosciConfigFile.getSilaMultiplier(damagerLevel);

            double increasedDamage = damage * multiplier;

            event.setDamage(increasedDamage);
        }
    }
}
