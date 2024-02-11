package me.xxgradzix.gradzixcore.webRemover.listeners;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.function.mask.SolidBlockMask;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.function.pattern.RandomPattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import me.xxgradzix.gradzixcore.webRemover.itemManager.ItemManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.nio.Buffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteWebListener implements Listener {
    
    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {

        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getAction().equals(Action.RIGHT_CLICK_AIR) ) return;
        if(!ItemManager.webRemover.isSimilar(event.getItem())) return;
        Player player = event.getPlayer();

        if(player.getCooldown(Material.BONE) > 0) return;

        Location location = player.getLocation();

        World world = location.getWorld();

        Location corner1 = location.clone();
        corner1.add(-1, 0, -1);

        Location corner2 = location.clone();
        corner2.add(1, 2, 1);

        fillTerrain(createGeneralEditSession(BukkitAdapter.adapt(world)), world, corner1, corner2);

        player.getInventory().removeItem(ItemManager.webRemover);
        player.sendMessage(ChatColor.GREEN + "Usunąłeś pajęczyny");
        player.setCooldown(Material.BONE, 20*15);
    }
    private void fillTerrain(EditSession editSession, World world, Location minLocation, Location maxLocation) {
        com.sk89q.worldedit.world.World weWorld = BukkitAdapter.adapt(world);
        Region region = new CuboidRegion(weWorld, BlockVector3.at(minLocation.getBlockX(), minLocation.getBlockY(), minLocation.getBlockZ()), BlockVector3.at(maxLocation.getBlockX(), maxLocation.getBlockY(), maxLocation.getBlockZ()));
        Pattern pattern = createAirPattern();

        try {
            BlockType from = BlockTypes.COBWEB;
            BlockType to = BlockTypes.AIR;

            Set<BaseBlock> cobweb = new HashSet<>();
            cobweb.add(from.getDefaultState().toBaseBlock());

            editSession.replaceBlocks(region, cobweb, pattern);
            editSession.commit();
            editSession.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private EditSession createGeneralEditSession(com.sk89q.worldedit.world.World world) {
        return WorldEdit.getInstance().newEditSession(world);
    }
    private RandomPattern createAirPattern() {
        RandomPattern randomPattern = new RandomPattern();

        BlockType blockType = BlockTypes.AIR;

        randomPattern.add(blockType.getDefaultState().toBaseBlock(), 1);

        return randomPattern;
    }
}
