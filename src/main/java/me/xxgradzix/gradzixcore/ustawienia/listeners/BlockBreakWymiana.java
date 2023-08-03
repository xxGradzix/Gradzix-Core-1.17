package me.xxgradzix.gradzixcore.ustawienia.listeners;

import me.xxgradzix.gradzixcore.ustawienia.files.UstawieniaOpcjeConfigFile;
import me.xxgradzix.gradzixcore.ustawienia.files.WymianaUstawieniaItemsConfigFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

public class BlockBreakWymiana implements Listener {


//    @EventHandler
//    public void onItemHeldChange(BlockBreakEvent event) {
//        Player player = event.getPlayer();
//
//        if (!UstawieniaOpcjeConfigFile.getAutoWymianaStatus(player)) return;
//
//        HashMap<ItemStack, ItemStack> mapa = (HashMap<ItemStack, ItemStack>) WymianaUstawieniaItemsConfigFile.getAllItems();
//
//
//        // Sprawdź, czy nowy przedmiot jest kluczem do wymiany
//
//        for (ItemStack keyItem : mapa.keySet()) {
//            while (player.getInventory().containsAtLeast(keyItem, keyItem.getAmount())) {
//                int freeSlots = 0;
//
//                for (ItemStack item : player.getInventory().getContents()) {
//                    if (item == null) {
//                        freeSlots++;
//                    }
//                }
//
//                if (freeSlots >= 1) {
//                    // Usuń przedmiot klucza
//                    player.getInventory().removeItem(keyItem);
//                    // Dodaj przedmiot wartości
//                    player.getInventory().addItem(mapa.get(keyItem));
//                    // Zaktualizuj ekwipunek gracza
//                    player.updateInventory();
//                }
//            }
//        }
//
//    }
@EventHandler
public void onBlockBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();

    if (!UstawieniaOpcjeConfigFile.getAutoWymianaStatus(player)) return;

//    p<ItemStack, ItemStack> mapa = (HashMap<ItemStack, ItemStack>) WymianaUstawieniaItemsConfigFile.getAllItems();

    ArrayList<ItemStack> itemKeysMap = WymianaUstawieniaItemsConfigFile.getAllItemKeys();
    ArrayList<ItemStack> itemValuesMap = WymianaUstawieniaItemsConfigFile.getAllItemValues();


    for(int i = 0; i < itemKeysMap.size(); i++) {
        ItemStack itemKey = (ItemStack) itemKeysMap.toArray()[i];
        ItemStack itemValue = (ItemStack) itemValuesMap.toArray()[i];

//        itemKey.setAmount(itemKeysMap.get(itemKey));
//        itemValue.setAmount(itemValuesMap.get(itemValue));

        int keyAmount = 0;
        for(ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null && itemStack.isSimilar(itemKey)) {
                keyAmount+=itemStack.getAmount();
            }
        }
        if (keyAmount >= itemKey.getAmount()) {
            int itemsToGive = keyAmount / itemKey.getAmount();
            for (int j = 0; j < itemsToGive; j++) {
                removeItems(player, itemKey, itemKey.getAmount());
                player.getInventory().addItem(itemValue);
            }
        }

    }

//    HashMap<ItemStack, ItemStack> mapa = (HashMap<ItemStack, ItemStack>) WymianaUstawieniaItemsConfigFile.getAllItems();
//
////
//    for (ItemStack key : mapa.keySet()) {
//        ItemStack value = mapa.get(key);
//        int keyAmount = 0;
//        for(ItemStack itemStack : player.getInventory().getContents()) {
//            if (itemStack != null && itemStack.isSimilar(key)) {
//                keyAmount+=itemStack.getAmount();
//            }
//        }
//
//
//        if (keyAmount >= key.getAmount()) {
//            int diamondsToGive = keyAmount / key.getAmount();
//            for (int i = 0; i < diamondsToGive; i++) {
//                removeItems(player, key, key.getAmount());
//                player.getInventory().addItem(value);
//            }
//        }
//    }
}
    public void removeItems(Player player, ItemStack itemStack, int amount) {
        PlayerInventory inventory = player.getInventory();
        int remainingAmount = amount;

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);

            if (item != null && item.isSimilar(itemStack)) {
                int itemAmount = item.getAmount();

                if (itemAmount <= remainingAmount) {
                    remainingAmount -= itemAmount;
                    inventory.setItem(i, null);
                } else {
                    item.setAmount(itemAmount - remainingAmount);
                    break;
                }
            }
        }
    }
}
