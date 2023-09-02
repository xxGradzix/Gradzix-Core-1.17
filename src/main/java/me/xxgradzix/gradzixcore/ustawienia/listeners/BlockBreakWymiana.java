package me.xxgradzix.gradzixcore.ustawienia.listeners;

import me.xxgradzix.gradzixcore.ustawienia.data.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

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


        if (!DataManager.getAutoExchangeStatus(player)) return;


        Map<ItemStack, ItemStack> itemMap = DataManager.getAutoExchangeItems();


        for(ItemStack item : itemMap.keySet()) {

            ItemStack itemValue = itemMap.get(item);

            int keyAmount = 0;

            for(ItemStack itemStack : player.getInventory().getContents()) {
                if (itemStack != null && itemStack.isSimilar(item)) {
                    keyAmount += itemStack.getAmount();
                }
            }
            final int rewardLoops = (keyAmount / item.getAmount());

            final int priceToPay = (rewardLoops * item.getAmount());

            final int valueAmount = (rewardLoops * itemValue.getAmount());


            removeItems(player, item, priceToPay);
            ItemStack reward = itemValue.clone();
            reward.setAmount(valueAmount);
            player.getInventory().addItem(reward);

        }
    }
    public void removeItems(Player player, ItemStack itemStack, int amount) {
//        PlayerInventory inventory = player.getInventory();

        int remainingAmount = amount;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.isSimilar(itemStack)) {
                int itemAmount = item.getAmount();

                if (itemAmount <= remainingAmount) {
                    remainingAmount -= itemAmount;
                    item.setAmount(0);
                    player.updateInventory();
                } else {
                    item.setAmount(itemAmount - remainingAmount);
                    remainingAmount -= itemAmount;
                }
            }
        }
    }
}
