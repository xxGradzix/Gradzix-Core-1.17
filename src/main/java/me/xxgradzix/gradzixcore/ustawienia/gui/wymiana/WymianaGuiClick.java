package me.xxgradzix.gradzixcore.ustawienia.gui.wymiana;

import me.xxgradzix.gradzixcore.ustawienia.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WymianaGuiClick implements Listener {



    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof WymianaGui)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        int clickedSlot = event.getSlot();//.getRawSlot();



        if (clickedSlot > 17 && clickedSlot < 36 && event.getClickedInventory().getHolder() instanceof WymianaGui) {

            event.setCancelled(true); // Zablokuj zabieranie przedmiotów z górnego rzędu
            // Dodaj swoją logikę obsługi kliknięcia w górny rząd GUI
        }

        WymianaGui gui = (WymianaGui) event.getInventory().getHolder();


        if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.previousPage)) {
            gui.previousPage(player);
        } else if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.nextPage)) {
            gui.nextPage(player);
        }

        // Obsługa kliknięcia innych przedmiotów w GUI, jeśli potrzebna
    }


}
