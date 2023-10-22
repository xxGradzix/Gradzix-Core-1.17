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

public class UlepszGuiClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        if(!(event.getPlayer() instanceof Player)) return;

        Player p = (Player) event.getPlayer();


        if (event.getInventory().getHolder() instanceof UlepszGui) {


            ArrayList<Inventory> inventories = (ArrayList<Inventory>) ((UlepszGui) event.getInventory().getHolder()).getInventories();



//            ArrayList<ItemStack[]> itemStacksList = new ArrayList<>();
            List<UpgradeEntity> upgradeEntities = new ArrayList<>();
//            Ulepsz.getUpgradeEntityManager().deleteAllUpgradeEntities();

            for(Inventory inventory : inventories) {
                for(int i = 0; i < 9; i++) {
                    if(inventory.getItem(i) == null || inventory.getItem(i+9) == null || inventory.getItem(i+18) == null) continue;

                    ItemStack currentItem = inventory.getItem(i);
                    ItemStack requiredItem = inventory.getItem(i+9);
                    ItemStack nextItem = inventory.getItem(i+18);

//                    ItemStack[] itemStacks = {currentItem, requiredItem, nextItem};
                    UpgradeEntity upgradeEntity = new UpgradeEntity(currentItem, requiredItem, nextItem);
//                    itemStacksList.add(itemStacks);
                    upgradeEntities.add(upgradeEntity);
//                    DataManager.addNewItem(upgradeEntity);

                }
            }
//            p.sendMessage(String.valueOf(upgradeEntities.size()));

//            UlepszConfigFile.setAllItems(itemStacksList);
            DataManager.setUpgradeItems(upgradeEntities);

        }
    }

}
