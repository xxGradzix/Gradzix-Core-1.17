package me.xxgradzix.gradzixcore.serverconfig.listeners.elytraAndFireworkBlock;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class OnTotemBreakBlockFirework implements Listener {

    @EventHandler
    public void onTotemBreakBlockFirework(EntityResurrectEvent event) {
        LivingEntity entity = event.getEntity();
        if(!(entity instanceof Player)) return;
        Player player = (Player) entity;

        player.setCooldown(Material.FIREWORK_ROCKET, 80);
    }

    @EventHandler
    public void onFireWorkUse(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if(player.getInventory().getItemInMainHand().getType() == Material.FIREWORK_ROCKET ||
                player.getInventory().getItemInOffHand().getType() == Material.FIREWORK_ROCKET) {

            if(player.getCooldown(Material.FIREWORK_ROCKET) > 0) {
                event.setCancelled(true);
            }
        }

    }

}
