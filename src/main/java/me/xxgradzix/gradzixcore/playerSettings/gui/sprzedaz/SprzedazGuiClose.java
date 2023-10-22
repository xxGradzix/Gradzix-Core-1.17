package me.xxgradzix.gradzixcore.playerSettings.gui.sprzedaz;

import me.xxgradzix.gradzixcore.playerSettings.data.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class SprzedazGuiClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        if(!(event.getPlayer() instanceof Player)) return;

        Player p = (Player) event.getPlayer();

        if (event.getInventory().getHolder() instanceof SellGui) {

            ArrayList<Inventory> inventories = (ArrayList<Inventory>) ((SellGui) event.getInventory().getHolder()).getInventories();

            HashMap<ItemStack, Integer> itemMap = new HashMap<>();

            for(Inventory inventory : inventories) {
                for(int i = 0; i < 9; i++) {

                    if(inventory.getItem(i) == null) continue;

                    ItemStack key = inventory.getItem(i);


                    // cena

                    ItemStack item =  inventory.getItem( i+9);

                    ItemMeta meta = item.getItemMeta();

                    String price = meta.getDisplayName().substring(6, meta.getDisplayName().length() - 1).substring(4);

                    int priceInt = Integer.parseInt(price);

                    itemMap.put(key, priceInt);
                }
            }

//            WymianaUstawieniaItemsConfigFile.setItemsToSell(itemMap);
            DataManager.setAutoSellItems(itemMap);



        }
    }

}
