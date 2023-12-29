package me.xxgradzix.gradzixcore.serverconfig.listeners.elytraAndFireworkBlock;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerFireworkListener implements Listener {

    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.FIREWORK_ROCKET ||
        player.getInventory().getItemInMainHand().getType() == Material.FIREWORK_ROCKET ||
        player.getInventory().getItemInOffHand().getType() == Material.FIREWORK_ROCKET
        ) {


            if(getPlayerRegionName(event.getPlayer()) == null) return;
            if(getPlayerRegionName(event.getPlayer()).equals("strefabezelytry")) {
                event.setCancelled(true);
                event.setUseItemInHand(Event.Result.DENY);
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
