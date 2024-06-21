package me.xxgradzix.gradzixcore.serverconfig.listeners;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BlockPlacingBlocks extends BukkitRunnable implements Listener {

    ArrayList<Material> blockTypes = new ArrayList<>();
    private static Set<Location> placedBlocks = new HashSet<>();

    private final Gradzix_Core plugin;

    public BlockPlacingBlocks(Gradzix_Core plugin) {
        this.plugin = plugin;


    }
    @Override
    public void run() {
        removeBlocks();
        Bukkit.broadcastMessage(ChatColor.GREEN + "Usunięto bloki z mapy");
    }
    public static void removeBlocks() {
        for (Location location : placedBlocks) {
            Block block = location.getBlock();
            if (block.getType() != org.bukkit.Material.AIR && placedBlocks.contains(location)) {
                block.setType(org.bukkit.Material.AIR);

            }
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;

        if(event.getBlock().getType().equals(Material.PLAYER_HEAD)
        || event.getBlock().getType().equals(Material.PLAYER_WALL_HEAD)
        || event.getBlock().getType().equals(Material.CHEST)
        || event.getBlock().getType().equals(Material.TRAPPED_CHEST)
        || event.getBlock().getType().equals(Material.CRAFTING_TABLE)
        || event.getBlock().getType().equals(Material.OAK_SIGN)
        || event.getBlock().getType().equals(Material.OAK_WALL_SIGN)
        || event.getBlock().getType().equals(Material.OAK_DOOR)
        || event.getBlock().getType().equals(Material.OAK_TRAPDOOR)
        || event.getBlock().getType().equals(Material.OAK_FENCE)
        || event.getBlock().getType().equals(Material.OAK_FENCE_GATE)
        || event.getBlock().getType().equals(Material.OAK_STAIRS)
        || event.getBlock().getType().equals(Material.OAK_SLAB)
        || event.getBlock().getType().equals(Material.OAK_PRESSURE_PLATE)
        || event.getBlock().getType().equals(Material.STRIPPED_OAK_LOG)) {

            event.setCancelled(true);
            return;
        }


//        ArrayList<Material> blockTypes = new ArrayList<>();
        if(event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            placedBlocks.add(event.getBlock().getLocation());
        }

//        blockTypes.add(Material.DIAMOND_BLOCK);
//        blockTypes.add(Material.DIAMOND_ORE);
//        blockTypes.add(Material.EMERALD_BLOCK);
//        blockTypes.add(Material.EMERALD_ORE);
//        blockTypes.add(Material.GOLD_ORE);
//        blockTypes.add(Material.GOLD_BLOCK);
//        blockTypes.add(Material.REDSTONE_BLOCK);
//        blockTypes.add(Material.REDSTONE_ORE);
//        blockTypes.add(Material.IRON_BLOCK);
//        blockTypes.add(Material.IRON_ORE);
//        blockTypes.add(Material.NETHERITE_BLOCK);
//        blockTypes.add(Material.ANCIENT_DEBRIS);
//        blockTypes.add(Material.COAL_BLOCK);
//        blockTypes.add(Material.COAL_ORE);
//        blockTypes.add(Material.LAPIS_BLOCK);
//        blockTypes.add(Material.LAPIS_ORE);
//        blockTypes.add(Material.LIME_WOOL);
//        blockTypes.add(Material.PINK_WOOL);
//        blockTypes.add(Material.TRIPWIRE);
//
//        if(blockTypes.contains(event.getBlock().getType())) {
//            event.getPlayer().sendMessage("Możesz kłaść tylko drewno i pajęczyny");
//            event.setCancelled(true);
//        }
    }

}
