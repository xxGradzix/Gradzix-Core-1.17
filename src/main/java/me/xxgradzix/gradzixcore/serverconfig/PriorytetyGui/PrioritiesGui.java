package me.xxgradzix.gradzixcore.serverconfig.PriorytetyGui;


import me.xxgradzix.gradzixcore.ustawienia.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PrioritiesGui implements InventoryHolder {

    private Inventory[] inventory;
    private int currentPage;
    private int maxPage = 6;
    private ArrayList<ItemStack> itemList;

    public PrioritiesGui(ArrayList<ItemStack> itemList) {

//        ItemStack[] itemKeys;
        if(itemList.isEmpty() || itemList == null) {
            this.itemList = new ArrayList<>();
//            itemKeys = new ItemStack[9*maxPage];
        } else {
            this.itemList = itemList;
//            itemKeys = itemMap.keySet().toArray(new ItemStack[9*maxPage]);
        }


        this.currentPage = 0;
        this.inventory = new Inventory[maxPage];

        // Inicjalizacja inventories dla każdej strony


        int itemNum = 0;
        for (int i = 0; i < maxPage; i++) {
            inventory[i] = Bukkit.createInventory(this, 54, "Ustawienia sprzedazy - Strona " + (i + 1));

            if(i > 0) inventory[i].setItem(45, ItemManager.previousPage);
            if(i<maxPage-1) inventory[i].setItem(53, ItemManager.nextPage);

            if(i == 0) inventory[i].setItem(45, ItemManager.greenGlass);
            if(i == maxPage-1) inventory[i].setItem(53, ItemManager.greenGlass);

            inventory[i].setItem(46, ItemManager.limeGlass);
            inventory[i].setItem(47,ItemManager.greenGlass);
            inventory[i].setItem(48,ItemManager.limeGlass);
            inventory[i].setItem(49,ItemManager.greenGlass);
            inventory[i].setItem(50,ItemManager.limeGlass);
            inventory[i].setItem(51,ItemManager.greenGlass);
            inventory[i].setItem(52, ItemManager.limeGlass);

            for(int j = 0; j < 45; j++) {
                if(itemNum >= itemList.size()) {
                    continue;
                }

                inventory[i].setItem(j, itemList.get(itemNum));

                itemNum++;
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



