package me.xxgradzix.gradzixcore.playerSettings.gui.wymiana;

import me.xxgradzix.gradzixcore.playerSettings.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ExchangeGuiClick implements Listener {



    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof ExchangeGui)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        int clickedSlot = event.getSlot();

        if (clickedSlot > 17 && clickedSlot < 36 && event.getClickedInventory().getHolder() instanceof ExchangeGui) {
            event.setCancelled(true);
        }

        ExchangeGui gui = (ExchangeGui) event.getInventory().getHolder();


        if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.previousPage)) {
            gui.previousPage(player);
        } else if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.nextPage)) {
            gui.nextPage(player);
        }
    }
}
