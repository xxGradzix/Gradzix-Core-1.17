package me.xxgradzix.gradzixcore.events.listeners.keyEvent;

import me.xxgradzix.gradzixcore.events.Events;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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


}
