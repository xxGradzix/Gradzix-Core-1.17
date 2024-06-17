package me.xxgradzix.gradzixcore.serverconfig.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ElytraSwaperBlock implements Listener {


    @EventHandler
    public void elytraSwapBlock(PlayerInteractEvent event) {

        ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
        ItemStack itemInOffHand = event.getPlayer().getInventory().getItemInOffHand();

        if(itemInMainHand.getType().equals(Material.ELYTRA) || itemInOffHand.getType().equals(Material.ELYTRA)
        || itemInOffHand.getType().equals(Material.NETHERITE_CHESTPLATE) || itemInMainHand.getType().equals(Material.NETHERITE_CHESTPLATE)) {
            if(event.getAction().equals(org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(org.bukkit.event.block.Action.RIGHT_CLICK_AIR)) {
                if(event.getPlayer().getInventory().getChestplate() != null) event.setCancelled(true);
            }
        }
    }

}
