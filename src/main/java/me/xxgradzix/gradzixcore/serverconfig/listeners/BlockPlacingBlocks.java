package me.xxgradzix.gradzixcore.serverconfig.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

public class BlockPlacingBlocks implements Listener {

    ArrayList<Material> blockTypes = new ArrayList<>();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ArrayList<Material> blockTypes = new ArrayList<>();


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
