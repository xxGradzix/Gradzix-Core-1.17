package me.xxgradzix.gradzixcore.ustawienia.gui.wymiana;

import me.xxgradzix.gradzixcore.ustawienia.files.WymianaUstawieniaItemsConfigFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class WymianaGuiClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        if(!(event.getPlayer() instanceof Player)) return;

        Player p = (Player) event.getPlayer();


        if (event.getInventory().getHolder() instanceof WymianaGui) {


            ArrayList<Inventory> inventories = (ArrayList<Inventory>) ((WymianaGui) event.getInventory().getHolder()).getInventories();

            HashMap<ItemStack, ItemStack> itemMap = new HashMap<>();

//            HashMap<ItemStack, Integer> itemKeysMap = new HashMap<>();
//            HashMap<ItemStack, Integer> itemValuesMap = new HashMap<>();

            ArrayList<ItemStack> itemKeys = new ArrayList<>();
            ArrayList<ItemStack> itemValues = new ArrayList<>();
            ArrayList<Integer> itemValuesAmounts = new ArrayList<>();


            for(Inventory inventory : inventories) {
                for(int i = 0; i < 9; i++) {
                    if(inventory.getItem(i) == null || inventory.getItem(i+9) == null) continue;
                    ItemStack key = inventory.getItem(i);
                    ItemStack value = inventory.getItem(i+9);

                    int keyAmount = key.getAmount();
                    int valueAmount = value.getAmount();

//                    itemKeysMap.put(key, keyAmount);
//                    itemValuesMap.put(value, valueAmount);

//                    itemMap.put(key, value);
                    itemKeys.add(key);
                    itemValues.add(value);
                    itemValuesAmounts.add(value.getAmount());
                }
            }

//            WymianaUstawieniaItemsConfigFile.setItems(itemMap);
            WymianaUstawieniaItemsConfigFile.setItems(itemKeys, itemValues, itemValuesAmounts);
//            WymianaUstawieniaItemsConfigFile.setItems(itemKeysMap, itemValuesMap);



        }
    }

}
