package me.xxgradzix.gradzixcore.magicFirework.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.xxgradzix.gradzixcore.magicFirework.items.ItemManager;
import org.bukkit.Location;
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

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getItem() != null && event.getItem().isSimilar(ItemManager.firework)) {
                event.setCancelled(true);


                if(getPlayerRegionName(event.getPlayer()) != null &&
                        (getPlayerRegionName(event.getPlayer()).equals("strefabezelytry") || getPlayerRegionName(event.getPlayer()).equals("strefabezelytry"))) {
                    event.setCancelled(true);
//
                    event.setUseItemInHand(Event.Result.DENY);
                    return;
                }


                    if (!getPlayerRegionName(event.getPlayer()).equals("strefabezelytry")) {

                    ItemStack fireworkItem = event.getItem();

                    FireworkMeta fireworkMeta = (FireworkMeta) fireworkItem.getItemMeta();

                    Firework firework = event.getPlayer().launchProjectile(Firework.class);
                    firework.setFireworkMeta(fireworkMeta);

                    Vector direction = event.getPlayer().getLocation().getDirection();
                    firework.setVelocity(direction);
                }
                event.setCancelled(true);
            }
        }


    }
    public String getPlayerRegionName(Player player) {
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        Location location = player.getLocation();

        ApplicableRegionSet regionSet = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().getApplicableRegions(BukkitAdapter.adapt(location));

        for (ProtectedRegion region : regionSet) {
            if (region.contains(location.getBlockX(), location.getBlockY(), location.getBlockZ())) {
                return region.getId();
            }
        }

        return null;
    }
}
