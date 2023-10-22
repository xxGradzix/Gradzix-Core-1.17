package me.xxgradzix.gradzixcore.playerSettings.gui.wymiana;


import me.xxgradzix.gradzixcore.playerSettings.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeGui implements InventoryHolder {

    private Inventory[] inventory;
    private int currentPage;
    private final int maxPage = 25;
    private Map<ItemStack, ItemStack> itemMap;
    private ArrayList<ItemStack> itemKeysList = null;

    public ExchangeGui(Map<ItemStack, ItemStack> items) {

        if(items == null || items.isEmpty()) {
            this.itemMap = new HashMap<>();
        } else {
            this.itemMap = items;
        }
        this.itemKeysList = new ArrayList<>(itemMap.keySet());


        this.currentPage = 0;
        this.inventory = new Inventory[maxPage];

        int key = 0;
        for (int i = 0; i < maxPage; i++) {
            inventory[i] = Bukkit.createInventory(this, 36, "GUI - Strona " + (i + 1));

            if(i > 0) inventory[i].setItem(27, ItemManager.previousPage);
            if(i<maxPage-1) inventory[i].setItem(35, ItemManager.nextPage);

            if(i == 0) inventory[i].setItem(27, ItemManager.greenGlass);
            if(i == maxPage-1) inventory[i].setItem(35, ItemManager.greenGlass);

            inventory[i].setItem(18, ItemManager.limeGlass);
            inventory[i].setItem(28, ItemManager.limeGlass);

            inventory[i].setItem(26, ItemManager.limeGlass);
            inventory[i].setItem(34, ItemManager.limeGlass);

            inventory[i].setItem(19,ItemManager.greenGlass);
            inventory[i].setItem(20,ItemManager.limeGlass);
            inventory[i].setItem(21,ItemManager.greenGlass);
            inventory[i].setItem(22,ItemManager.limeGlass);
            inventory[i].setItem(23,ItemManager.greenGlass);
            inventory[i].setItem(24,ItemManager.limeGlass);
            inventory[i].setItem(25,ItemManager.greenGlass);

            inventory[i].setItem(29,ItemManager.greenGlass);
            inventory[i].setItem(30,ItemManager.limeGlass);
            inventory[i].setItem(31,ItemManager.greenGlass);
            inventory[i].setItem(32,ItemManager.limeGlass);
            inventory[i].setItem(33,ItemManager.greenGlass);

            for(int j = 0; j < 9; j++) {


                if(itemKeysList == null) break;
                if(itemKeysList.size() <= key) break;

                if(itemKeysList.get(key) == null) {
                    key++;
                    continue;
                }

                ItemStack keyItem = itemKeysList.get(key);
                ItemStack valueItem = itemMap.get(keyItem);

                inventory[i].setItem(j, keyItem);
                inventory[i].setItem((j+9), valueItem);
                key++;
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



