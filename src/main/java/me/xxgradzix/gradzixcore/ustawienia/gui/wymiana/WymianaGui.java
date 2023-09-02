package me.xxgradzix.gradzixcore.ustawienia.gui.wymiana;


import me.xxgradzix.gradzixcore.ustawienia.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WymianaGui implements InventoryHolder {

    private Inventory[] inventory;
    private int currentPage;
    private int maxPage = 25;
    private Map<ItemStack, ItemStack> itemMap;
    ArrayList<ItemStack> itemKeysList = null;
//    private ArrayList<ItemStack> itemListKeys;
//    private ArrayList<ItemStack> itemListValues;

    public WymianaGui(Map<ItemStack, ItemStack> items) {
//    public WymianaGui(ArrayList<ItemStack> itemListKeys, ArrayList<ItemStack> itemListValues) {

//        ItemStack[] itemKeys;

        if(items == null || items.isEmpty()) {
            this.itemMap = new HashMap<>();
//            itemKeys = new ItemStack[9*maxPage];
        } else {
            this.itemMap = items;
//            itemKeys = itemMap.keySet().toArray(new ItemStack[9*maxPage]);
        }
        this.itemKeysList = new ArrayList<>(itemMap.keySet());

//        ItemStack[] itemKeys;
//        ItemStack[] itemValues;
//        if(itemListKeys == null || itemListValues.isEmpty() ||
//        itemListValues == null || itemListValues.isEmpty()) {
//            this.itemListKeys = new ArrayList<>();
//            this.itemListValues = new ArrayList<>();
////            itemKeys = new ItemStack[9*maxPage];
////            itemValues = new ItemStack[9*maxPage];
//        } else {
//            this.itemListKeys = itemListKeys;
//            this.itemListValues = itemListValues;
////            itemKeys = itemListKeys.toArray(new ItemStack[9 * maxPage]);
////            itemValues = itemListValues.keySet().toArray(new ItemStack[9 * maxPage]);
//        }


        this.currentPage = 0;
        this.inventory = new Inventory[maxPage];

        // Inicjalizacja inventories dla każdej strony


        int key = 0;
        for (int i = 0; i < maxPage; i++) {
            inventory[i] = Bukkit.createInventory(this, 36, "GUI - Strona " + (i + 1));

            if(i > 0) inventory[i].setItem(27, ItemManager.previousPage);
            if(i<maxPage-1) inventory[i].setItem(35, ItemManager.nextPage);

            if(i == 0) inventory[i].setItem(27, ItemManager.greenGlass);
            if(i == maxPage-1) inventory[i].setItem(35, ItemManager.greenGlass);

            inventory[i].setItem(18, ItemManager.limeGlass);
//            inventory[i].setItem(19, ItemManager.blackGlass);
            inventory[i].setItem(28, ItemManager.limeGlass);

            inventory[i].setItem(26, ItemManager.limeGlass);
//            inventory[i].setItem(25, ItemManager.blackGlass);
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
//                || itemListValues.get(key)  == null) {
                    key++;
                    continue;
                }

//                ItemStack keyItem = itemListKeys.get(key);
//                ItemStack valueItem = itemListValues.get(key);

                ItemStack keyItem = itemKeysList.get(key);
                ItemStack valueItem = itemMap.get(keyItem);

//                keyItem.setAmount(itemListKeys.get(keyItem));
//                valueItem.setAmount(itemListValues.get(valueItem));


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



