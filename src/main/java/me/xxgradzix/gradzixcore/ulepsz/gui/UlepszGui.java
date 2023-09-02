package me.xxgradzix.gradzixcore.ulepsz.gui;

//public class UlepszGui implements InventoryHolder, Listener {
//
//    private Inventory inventory;
//    private int currentPage;
//    private int maxPage;
//
//    public UlepszGui() {
//        inventory = Bukkit.createInventory(this, 36, "Itemy do wymiany");
//
//    }
//
//    public void open(Player player) {
//
//        player.openInventory(inventory);
//
//    }
//
//    @Override
//    public Inventory getInventory() {
//        return inventory;
//    }
//
//
//
//}

import me.xxgradzix.gradzixcore.ulepsz.data.DataManager;
import me.xxgradzix.gradzixcore.ulepsz.data.database.entities.UpgradeEntity;
import me.xxgradzix.gradzixcore.ustawienia.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.ArrayList;
import java.util.List;

public class UlepszGui implements InventoryHolder {

    private Inventory[] inventory;
    private int currentPage;
    private int maxPage = 25;
//    private ArrayList<ItemStack[]> itemStacksList;
    private List<UpgradeEntity> upgradeEntityList = DataManager.getAllUpgradeEntities();

    public UlepszGui() {
        if(upgradeEntityList == null || upgradeEntityList.isEmpty()) {
            upgradeEntityList = new ArrayList<>();
        }

//        ItemStack[] itemKeys;
//        if(itemStacksList.isEmpty() || itemStacksList == null) {
//            this.itemStacksList = new ArrayList<>();
////            index = new ItemStack[9*maxPage];
//        } else {
//            this.itemStacksList = itemStacksList;
////            index = itemMap.keySet().toArray(new ItemStack[9*maxPage]);
//        }

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

        for (Inventory inventory : inventory) {
            itemList.add(inventory);
        }
        return itemList;
    }
}



