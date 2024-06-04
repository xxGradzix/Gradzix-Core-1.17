package me.xxgradzix.gradzixcore.serverconfig.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.xxgradzix.gradzixcore.generators.Generators;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashSet;
import java.util.Set;

public class BlockGrief implements Listener {

    private static Set<Material> allowedBlocks = new HashSet<>();

    public BlockGrief() {
//        allowedBlocks.add(Material.EMERALD_BLOCK);
//        allowedBlocks.add(Material.EMERALD_ORE);
//        allowedBlocks.add(Material.DIAMOND_BLOCK);
//        allowedBlocks.add(Material.DIAMOND_ORE);
//        allowedBlocks.add(Material.GOLD_BLOCK);
//        allowedBlocks.add(Material.GOLD_ORE);
//        allowedBlocks.add(Material.IRON_BLOCK);
//        allowedBlocks.add(Material.IRON_ORE);
//        allowedBlocks.add(Material.COAL_BLOCK);
//        allowedBlocks.add(Material.COAL_ORE);
        allowedBlocks.add(Material.OAK_PLANKS);
        allowedBlocks.add(Material.OAK_WOOD);
        allowedBlocks.add(Material.COBWEB);
//        allowedBlocks.add(Material.LIGHT_GRAY_WOOL);
//        allowedBlocks.add(Material.GRAY_WOOL);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        if(isLocationInGeneratorRegion(event.getBlock().getLocation())) return;

        if(!allowedBlocks.contains(event.getBlock().getType())) {
            event.setCancelled(true);
        }
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

}
