package me.xxgradzix.gradzixcore.serverconfig.PriorytetyGui;

import me.xxgradzix.gradzixcore.serverconfig.data.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PrioritiesGuiClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        if(!(event.getPlayer() instanceof Player)) return;

        Player p = (Player) event.getPlayer();

        if (event.getInventory().getHolder() instanceof PrioritiesGui) {

            ArrayList<Inventory> inventories = (ArrayList<Inventory>) ((PrioritiesGui) event.getInventory().getHolder()).getInventories();

            ArrayList<ItemStack> itemList = new ArrayList<>();

            for(Inventory inventory : inventories) {
                for(int i = 0; i < 45; i++) {

                    if(inventory.getItem(i) == null) continue;

                    ItemStack item = inventory.getItem(i);



                    itemList.add(item);
                }
            }

            DataManager.setItemPriorities(itemList);



        }
    }

}
