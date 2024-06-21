package me.xxgradzix.gradzixcore.eventArea.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.eventArea.EventArea;
import me.xxgradzix.gradzixcore.eventArea.items.ItemManager;
import me.xxgradzix.gradzixcore.generators.Generators;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static me.xxgradzix.gradzixcore.afkRegion.GetRewardInAfkRegion.shouldDrop;

public class EventAreaListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        Player player = event.getPlayer();

        if(isLocationInGeneratorRegion(event.getBlock().getLocation()) && event.getBlock().getType().equals(Material.AMETHYST_BLOCK)) {
            if(!event.getPlayer().getInventory().getItemInMainHand().isSimilar(ItemManager.pickaxeOfMoria)) {
                event.setCancelled(true);
                player.sendMessage("§cTen blok możesz niszczyć tylko specjlnym kilofem z Morii!");
                return;
            }
            event.setDropItems(false);

            if(shouldDrop(EventArea.getDropChance())) {
                if(event.getPlayer().getInventory().firstEmpty() != -1) {
                    event.getPlayer().getInventory().addItem(ItemManager.fragmentOfRing);
                } else {
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), ItemManager.fragmentOfRing);
                }
            }
        }
    }

    private static HashMap<UUID, Set<UUID>> killedPlayers = new HashMap<>();

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        if(event.getEntity().getKiller() == null) return;
        if(!isLocationInEventRegion(event.getEntity().getLocation())) return;

        Player dead = event.getEntity();
        Player killer = dead.getKiller();

        Set<UUID> killedPlayersSet = killedPlayers.getOrDefault(killer.getUniqueId(), new HashSet<>());

        if(killedPlayersSet.contains(dead.getUniqueId())) return;


        if(shouldDrop(EventArea.getKillDropChance())) {
            if(event.getEntity().getKiller().getInventory().firstEmpty() != -1) {
                event.getEntity().getKiller().getInventory().addItem(ItemManager.elfFragment);
            } else {
                event.getEntity().getKiller().getWorld().dropItemNaturally(event.getEntity().getLocation(), ItemManager.elfFragment);
            }
        }
        killedPlayersSet.add(dead.getUniqueId());
        killedPlayers.put(killer.getUniqueId(), killedPlayersSet);
        Bukkit.getScheduler().runTaskLaterAsynchronously(Gradzix_Core.getInstance(), () -> {
            killedPlayersSet.remove(dead.getUniqueId());
            killedPlayers.put(killer.getUniqueId(), killedPlayersSet);
        }, 20 * 60 * 5);
    }

    public boolean isLocationInGeneratorRegion(Location location) {
        World world = location.getWorld();
        if (world == null) {
            return false;
        }

        ApplicableRegionSet regionSet = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().getApplicableRegions(BukkitAdapter.adapt(location));

        for (ProtectedRegion region : regionSet) {
            if (region.getId().startsWith(Generators.GENERATOR_REGION_PREFIX)) {
                return true;
            }
        }

        return false;
    }
    public boolean isLocationInEventRegion(Location location) {
        World world = location.getWorld();
        if (world == null) {
            return false;
        }

        ApplicableRegionSet regionSet = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().getApplicableRegions(BukkitAdapter.adapt(location));

        for (ProtectedRegion region : regionSet) {
            if (region.getId().startsWith("event")) {
                return true;
            }
        }

        return false;
    }

}
