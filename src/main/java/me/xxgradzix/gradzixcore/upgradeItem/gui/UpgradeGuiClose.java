package me.xxgradzix.gradzixcore.upgradeItem.gui;

import me.xxgradzix.gradzixcore.upgradeItem.data.DataManager;
import me.xxgradzix.gradzixcore.upgradeItem.data.database.entities.UpgradeEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class UpgradeGuiClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        if(!(event.getPlayer() instanceof Player)) return;

        Player p = (Player) event.getPlayer();


        if (event.getInventory().getHolder() instanceof UpgradeGui) {


            ArrayList<Inventory> inventories = (ArrayList<Inventory>) ((UpgradeGui) event.getInventory().getHolder()).getInventories();

            List<UpgradeEntity> upgradeEntities = new ArrayList<>();

            for(Inventory inventory : inventories) {
                for(int i = 0; i < 9; i++) {
                    if(inventory.getItem(i) == null || inventory.getItem(i+9) == null || inventory.getItem(i+18) == null) continue;

                    ItemStack currentItem = inventory.getItem(i);
                    ItemStack requiredItem = inventory.getItem(i+9);
                    ItemStack nextItem = inventory.getItem(i+18);

                    UpgradeEntity upgradeEntity = new UpgradeEntity(currentItem, requiredItem, nextItem);

                    upgradeEntities.add(upgradeEntity);
                }
            }
            DataManager.setUpgradeItems(upgradeEntities);
        }
    }
}
