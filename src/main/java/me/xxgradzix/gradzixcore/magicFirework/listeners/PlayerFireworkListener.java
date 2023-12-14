package me.xxgradzix.gradzixcore.magicFirework.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.xxgradzix.gradzixcore.magicFirework.items.ItemManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

public class PlayerFireworkListener implements Listener {

    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if(!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;

        if(event.getItem() == null) return;
        if(!ItemManager.firework.isSimilar(event.getItem())) return;

        if(event.getPlayer().getCooldown(Material.FIREWORK_ROCKET) > 0) return;

        event.setCancelled(true);

        ItemStack fireworkItem = event.getItem();
        FireworkMeta fireworkMeta = (FireworkMeta) fireworkItem.getItemMeta();

        if (fireworkMeta == null) return;

        Firework firework = event.getPlayer().launchProjectile(Firework.class);
        firework.setFireworkMeta(fireworkMeta);
        Vector direction = event.getPlayer().getLocation().getDirection();
        firework.setVelocity(direction);

    }

}
