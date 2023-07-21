package me.xxgradzix.gradzixcore.serverconfig.listeners;

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
            if(getPlayerRegionName(event.getPlayer()) == "strefabezelytry" || getPlayerRegionName(event.getPlayer()).equals("strefabezelytry")) {
                event.setCancelled(true);
//                event.setUseItemInHand(Event.Result.DENY);
//                event.setUseInteractedBlock(Event.Result.DENY);
//                player.setWalkSpeed(0);
//                player.setFlySpeed(0);
                event.setUseItemInHand(Event.Result.DENY);
                // Dodatkowo możesz poinformować gracza, że używanie fajerwerków jest zabronione
//                if (event.getClickedBlock() != null) {
//                    // Anuluj event, aby zablokować zużycie fajerwerki
//
//                    event.setCancelled(true);
//                    // Możesz również wysłać wiadomość do gracza informującą, że klikanie fajerwerką w blok jest zabronione
//                    event.getPlayer().sendMessage("Klikanie fajerwerką w blok jest zabronione na tym serwerze.");
//                }

            }
            // Anuluj event


        }
//        Player p = event.getPlayer();
//        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK) ||
//                event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
//            p.sendMessage("test right/left click");
//        if(p.getInventory().getItemInOffHand().getType().equals(Material.FIREWORK_ROCKET) || p.getInventory().getItemInMainHand().getType().equals(Material.FIREWORK_ROCKET) ||
//                p.getInventory().getItemInOffHand().getType() == Material.FIREWORK_ROCKET || p.getInventory().getItemInMainHand().getType() == Material.FIREWORK_ROCKET) {
//            p.sendMessage("test item in hand");
//
//            if(getPlayerRegionName(event.getPlayer()) == "strefabezelytry" || getPlayerRegionName(event.getPlayer()).equals("strefabezelytry")) {
//                if(event.getItem().getType().equals(Material.FIREWORK_ROCKET) || event.getItem().getType() == Material.FIREWORK_ROCKET) {
//
//                    p.sendMessage("test strefa bez elytr");
//                    event.setCancelled(true);
//
//                }
//
//                }
//
//            }
//
//
//        }


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
