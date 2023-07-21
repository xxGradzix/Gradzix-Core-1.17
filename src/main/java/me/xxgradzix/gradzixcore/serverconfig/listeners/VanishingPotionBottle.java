package me.xxgradzix.gradzixcore.serverconfig.listeners;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class VanishingPotionBottle implements Listener {

    private Gradzix_Core plugin;
    public VanishingPotionBottle(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDrink(PlayerItemConsumeEvent e) {
        final Player player = e.getPlayer();

        if (e.getItem().getType() == Material.POTION) {
            Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
                public void run() {
//                    player.setItemInHand(new ItemStack(Material.AIR));
                    removeItems(player, new ItemStack(Material.GLASS_BOTTLE), 1);
                }
            }, 1L);
        }
    }
    public void removeItems(Player player, ItemStack itemStack, int amount) {
        PlayerInventory inventory = player.getInventory();
        int remainingAmount = amount;

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);

            if (item != null && item.isSimilar(itemStack)) {
                int itemAmount = item.getAmount();

//                if (itemAmount <= remainingAmount) {
                    remainingAmount -= itemAmount;
                    inventory.setItem(i, null);
//                } else {
//                    item.setAmount(itemAmount - remainingAmount);
//                    break;
//                }
            }
        }
    }

}
