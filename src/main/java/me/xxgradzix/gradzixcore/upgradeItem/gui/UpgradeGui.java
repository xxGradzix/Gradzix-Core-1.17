package me.xxgradzix.gradzixcore.upgradeItem.gui;

import me.xxgradzix.gradzixcore.upgradeItem.data.DataManager;
import me.xxgradzix.gradzixcore.upgradeItem.data.database.entities.UpgradeEntity;
import me.xxgradzix.gradzixcore.playerSettings.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UpgradeGui implements InventoryHolder {

    private Inventory[] inventory;
    private int currentPage;
    private int maxPage = 25;
    private List<UpgradeEntity> upgradeEntityList = DataManager.getAllUpgradeEntities();

    public UpgradeGui() {
        if(upgradeEntityList == null || upgradeEntityList.isEmpty()) {
            upgradeEntityList = new ArrayList<>();
        }

        this.currentPage = 0;
        this.inventory = new Inventory[maxPage];



        int index = 0;

        for (int i = 0; i < maxPage; i++) {
            inventory[i] = Bukkit.createInventory(this, 45, "GUI - Strona " + (i + 1));

            if(i > 0) inventory[i].setItem(36, ItemManager.previousPage);
            if(i<maxPage-1) inventory[i].setItem(44, ItemManager.nextPage);

            if(i == 0) inventory[i].setItem(36, ItemManager.limeGlass);
            if(i == maxPage-1) inventory[i].setItem(44, ItemManager.limeGlass);


            inventory[i].setItem(27, ItemManager.greenGlass);
            inventory[i].setItem(28, ItemManager.limeGlass);
            inventory[i].setItem(29, ItemManager.greenGlass);
            inventory[i].setItem(30, ItemManager.limeGlass);
            inventory[i].setItem(31,ItemManager.greenGlass);
            inventory[i].setItem(32,ItemManager.limeGlass);
            inventory[i].setItem(33,ItemManager.greenGlass);
            inventory[i].setItem(34,ItemManager.limeGlass);
            inventory[i].setItem(35,ItemManager.greenGlass);

            inventory[i].setItem(37, ItemManager.greenGlass);
            inventory[i].setItem(38, ItemManager.limeGlass);
            inventory[i].setItem(39,ItemManager.greenGlass);
            inventory[i].setItem(40,ItemManager.limeGlass);
            inventory[i].setItem(41,ItemManager.greenGlass);
            inventory[i].setItem(42,ItemManager.limeGlass);
            inventory[i].setItem(43,ItemManager.greenGlass);

            for(int j = 0; j < 9; j++) {
                if(index >= upgradeEntityList.size()) break;

                if(upgradeEntityList.get(index) == null) {
                    index++;
                    continue;
                }
                if(upgradeEntityList.get(index).getCurrentItem() == null ||
                        upgradeEntityList.get(index).getItemNeeded() == null ||
                        upgradeEntityList.get(index).getNextItem()== null)  {
                    index++;
                    continue;
                }
                inventory[i].setItem(j, upgradeEntityList.get(index).getCurrentItem());
                inventory[i].setItem((j+9), upgradeEntityList.get(index).getItemNeeded());
                inventory[i].setItem((j+18), upgradeEntityList.get(index).getNextItem());
                index++;
            }


        }
    }

    public void open(Player player) {
        player.openInventory(inventory[currentPage]);
    }

    public void nextPage(Player player) {
        if (currentPage < maxPage - 1) {
            currentPage++;
            player.openInventory(inventory[currentPage]);
        }
    }

    public void previousPage(Player player) {
        if (currentPage > 0) {
            currentPage--;
            player.openInventory(inventory[currentPage]);
        }
    }


    public Inventory getInventory() {
        return inventory[currentPage];
    }
    public List<Inventory> getInventories() {
        List<Inventory> itemList = new ArrayList<>();

        Collections.addAll(itemList, inventory);
        return itemList;
    }
}



