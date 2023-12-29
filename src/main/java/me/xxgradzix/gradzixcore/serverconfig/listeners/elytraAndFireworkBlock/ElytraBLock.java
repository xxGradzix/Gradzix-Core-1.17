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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ElytraBLock implements Listener {

    @EventHandler
    public void elytraBlockEvent(PlayerMoveEvent event) {
        

        if (event.getPlayer() instanceof Player) {
            Player player = event.getPlayer();

            if (getPlayerRegionName(event.getPlayer()) != null && getPlayerRegionName(player).equals("strefabezelytry")) {

                if (player.getInventory().getChestplate().getType() == Material.ELYTRA) {


                    player.setGliding(false);
                    event.setCancelled(true);
                }

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