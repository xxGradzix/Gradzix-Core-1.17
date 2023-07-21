package me.xxgradzix.gradzixcore.ulepsz.gui;

import me.xxgradzix.gradzixcore.ulepsz.files.UlepszConfigFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class UlepszGuiClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        if(!(event.getPlayer() instanceof Player)) return;

        Player p = (Player) event.getPlayer();


        if (event.getInventory().getHolder() instanceof UlepszGui) {


            ArrayList<Inventory> inventories = (ArrayList<Inventory>) ((UlepszGui) event.getInventory().getHolder()).getInventories();



            ArrayList<ItemStack[]> itemStacksList = new ArrayList<>();

            for(Inventory inventory : inventories) {
                for(int i = 0; i < 9; i++) {
                    if(inventory.getItem(i) == null || inventory.getItem(i+9) == null || inventory.getItem(i+18) == null) continue;

                    ItemStack currentItem = inventory.getItem(i);
                    ItemStack requiredItem = inventory.getItem(i+9);
                    ItemStack nextItem = inventory.getItem(i+18);

                    ItemStack[] itemStacks = {currentItem, requiredItem, nextItem};
                    itemStacksList.add(itemStacks);
                }
            }
            UlepszConfigFile.setAllItems(itemStacksList);

        }
    }

}
