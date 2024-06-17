package me.xxgradzix.gradzixcore.shulker.listeners;

import dev.triumphteam.gui.guis.StorageGui;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class OpenShulkerListener implements Listener {

    private final Gradzix_Core plugin;

    public OpenShulkerListener(Gradzix_Core plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.SHULKER_BOX && event.getAction().name().contains("RIGHT")) {
            openShulkerBox(player, item);
        }
    }
    @EventHandler
    public void onShulkerPlaceEvent(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if (item != null && item.getType() == Material.SHULKER_BOX) {
            event.setCancelled(true);
        }
    }

    private void openShulkerBox(Player player, ItemStack shulkerItem) {
        BlockStateMeta blockStateMeta = (BlockStateMeta) shulkerItem.getItemMeta();
        if (blockStateMeta != null && blockStateMeta.getBlockState() instanceof ShulkerBox) {
            ShulkerBox shulkerBox = (ShulkerBox) blockStateMeta.getBlockState();

            StorageGui gui = new StorageGui(3, "Shulker");

            gui.getInventory().setContents(shulkerBox.getInventory().getContents());
            gui.setCloseGuiAction(event -> {
                shulkerBox.getInventory().setContents(gui.getInventory().getContents());
                blockStateMeta.setBlockState(shulkerBox);
                shulkerItem.setItemMeta(blockStateMeta);
            });

            gui.setPlayerInventoryAction(event -> {
               if(shulkerItem.equals(event.getCurrentItem())) {
                   player.sendMessage("Nie możesz tego zrobić!");
                   event.setCancelled(true);
               }
            });
            gui.open(player);

        }
    }

}
