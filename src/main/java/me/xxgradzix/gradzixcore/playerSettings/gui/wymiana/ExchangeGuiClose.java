package me.xxgradzix.gradzixcore.playerSettings.gui.wymiana;

import me.xxgradzix.gradzixcore.playerSettings.data.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class ExchangeGuiClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        if(!(event.getPlayer() instanceof Player)) return;

        Player p = (Player) event.getPlayer();


        if (event.getInventory().getHolder() instanceof ExchangeGui) {


            ArrayList<Inventory> inventories = (ArrayList<Inventory>) ((ExchangeGui) event.getInventory().getHolder()).getInventories();

            HashMap<ItemStack, ItemStack> itemMap = new HashMap<>();

            for(Inventory inventory : inventories) {
                for(int i = 0; i < 9; i++) {
                    if(inventory.getItem(i) == null || inventory.getItem(i+9) == null) continue;
                    ItemStack key = inventory.getItem(i);
                    ItemStack value = inventory.getItem(i+9);

                    int keyAmount = key.getAmount();
                    int valueAmount = value.getAmount();

                    itemMap.put(key, value);
                }
            }

            DataManager.setAutoExchangeItems(itemMap);
        }
    }

}
