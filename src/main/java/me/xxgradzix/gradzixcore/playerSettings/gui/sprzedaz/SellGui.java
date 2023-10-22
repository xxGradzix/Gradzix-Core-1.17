package me.xxgradzix.gradzixcore.playerSettings.gui.sprzedaz;

//public class UpgradeGui implements InventoryHolder, Listener {
//
//    private Inventory inventory;
//    private int currentPage;
//    private int maxPage;
//
//    public UpgradeGui() {
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

import me.xxgradzix.gradzixcore.playerSettings.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellGui implements InventoryHolder {

    private Inventory[] inventory;
    private int currentPage;
    private final int maxPage = 25;
    private Map<ItemStack, Integer> itemMap;

    public SellGui(Map<ItemStack, Integer> itemMap) {

        ItemStack[] itemKeys;
        if(itemMap == null || itemMap.isEmpty()) {
            this.itemMap = new HashMap<>();
            itemKeys = new ItemStack[9*maxPage];
        } else {
            this.itemMap = itemMap;
            itemKeys = itemMap.keySet().toArray(new ItemStack[9*maxPage]);
        }


        this.currentPage = 0;
        this.inventory = new Inventory[maxPage];

        int key = 0;
        for (int i = 0; i < maxPage; i++) {
            inventory[i] = Bukkit.createInventory(this, 54, "PlayerSettings sprzedaży - Strona " + (i + 1));

            if(i > 0) inventory[i].setItem(45, ItemManager.previousPage);
            if(i<maxPage-1) inventory[i].setItem(53, ItemManager.nextPage);

            if(i == 0) inventory[i].setItem(45, ItemManager.greenGlass);
            if(i == maxPage-1) inventory[i].setItem(53, ItemManager.greenGlass);

            for(int slot = 9; slot < 18;  slot++) {
                inventory[i].setItem(slot, ItemManager.price);
            }
            for(int slot = 18; slot < 27;  slot++) {
                inventory[i].setItem(slot, ItemManager.addOne);
            }
            for(int slot = 27; slot < 36;  slot++) {
                inventory[i].setItem(slot, ItemManager.addTen);
            }
            for(int slot = 36; slot < 45;  slot++) {
                inventory[i].setItem(slot, ItemManager.addHundred);
            }

            inventory[i].setItem(46, ItemManager.limeGlass);
            inventory[i].setItem(47,ItemManager.greenGlass);
            inventory[i].setItem(48,ItemManager.limeGlass);
            inventory[i].setItem(49,ItemManager.greenGlass);
            inventory[i].setItem(50,ItemManager.limeGlass);
            inventory[i].setItem(51,ItemManager.greenGlass);
            inventory[i].setItem(52, ItemManager.limeGlass);

            for(int j = 0; j < 9; j++) {
                if(itemKeys[key] == null) {
                    key++;
                    continue;
                }

                inventory[i].setItem(j, itemKeys[key]);

                ItemStack item = ItemManager.price.clone();

                ItemMeta meta = item.getItemMeta();

                meta.setDisplayName(ChatColor.GRAY + "Cena: " + ChatColor.GREEN + itemMap.get(itemKeys[key]) + "$");
                item.setItemMeta(meta);

                inventory[i].setItem(j+9, item);

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



