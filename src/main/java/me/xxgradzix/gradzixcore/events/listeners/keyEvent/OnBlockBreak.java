package me.xxgradzix.gradzixcore.events.listeners.keyEvent;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.xxgradzix.gradzixcore.events.Events;
import me.xxgradzix.gradzixcore.generators.Generators;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class OnBlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if(!Events.isKeyEventEnabled()) return;

        Player player = event.getPlayer();

        if(player.getGameMode().equals(GameMode.CREATIVE)) return;

        if(!isLocationInGeneratorRegion(event.getBlock().getLocation())) return;

        double chance = Events.getKeyDropChance();


        if(shouldDrop(chance)) {

            ItemStack reward = Events.getKeyRewardItem();
            reward.setAmount(Events.getKeyRewardItemAmount());

            if (player.getInventory().firstEmpty() != -1) {
                player.getInventory().addItem(reward);
            } else {
                event.getBlock().getWorld().dropItemNaturally(player.getLocation(), reward);
            }
        }

    }
    public static boolean shouldDrop(double chance) {

        Random random = new Random();
        double result = random.nextDouble();

        return result <= chance;
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
