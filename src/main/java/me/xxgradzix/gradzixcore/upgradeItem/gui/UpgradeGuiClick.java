package me.xxgradzix.gradzixcore.upgradeItem.gui;

import me.xxgradzix.gradzixcore.playerSettings.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class UpgradeGuiClick implements Listener {



    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof UpgradeGui)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        int clickedSlot = event.getSlot();



        if (clickedSlot > 26 && clickedSlot < 45 && event.getClickedInventory().getHolder() instanceof UpgradeGui) {

            event.setCancelled(true);
        }

        UpgradeGui gui = (UpgradeGui) event.getInventory().getHolder();


        if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.previousPage)) {
            gui.previousPage(player);
        } else if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.nextPage)) {
            gui.nextPage(player);
        }
    }
}
