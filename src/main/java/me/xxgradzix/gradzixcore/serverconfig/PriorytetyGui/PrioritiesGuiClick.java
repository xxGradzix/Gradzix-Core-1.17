package me.xxgradzix.gradzixcore.serverconfig.PriorytetyGui;

import me.xxgradzix.gradzixcore.ustawienia.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PrioritiesGuiClick implements Listener {


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof PrioritiesGui)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        int clickedSlot = event.getSlot();//.getRawSlot();



        if (clickedSlot > 44 && event.getClickedInventory().getHolder() instanceof PrioritiesGui) {

            event.setCancelled(true); // Zablokuj zabieranie przedmiotów z górnego rzędu

        }

        PrioritiesGui gui = (PrioritiesGui) event.getInventory().getHolder();


        if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.previousPage)) {
            gui.previousPage(player);
        } else if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.nextPage)) {
            gui.nextPage(player);
        }



    }


}
