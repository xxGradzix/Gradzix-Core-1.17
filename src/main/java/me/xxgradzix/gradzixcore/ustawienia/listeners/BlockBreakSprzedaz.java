package me.xxgradzix.gradzixcore.ustawienia.listeners;

import me.xxgradzix.gradzixcore.ustawienia.EconomyManager;
import me.xxgradzix.gradzixcore.ustawienia.data.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;

public class BlockBreakSprzedaz implements Listener {


    @EventHandler
    public void onItemHeldChange(BlockBreakEvent event) {
        Player player = event.getPlayer();


        if (!DataManager.getAutoSellStatus(player)) return;

//        HashMap<ItemStack, Integer> map = (HashMap<ItemStack, Integer>) WymianaUstawieniaItemsConfigFile.getAllItemsToSell();
        HashMap<ItemStack, Integer> map = (HashMap<ItemStack, Integer>) DataManager.getAutoSellItems();
//        DataManager.getAutoSellItems();

        for (ItemStack keyItem : map.keySet()) {


            int itemPrice = map.get(keyItem);

            while (player.getInventory().containsAtLeast(keyItem, keyItem.getAmount())) {


                    // Usuń przedmiot klucza
                    player.getInventory().removeItem(keyItem);



                    EconomyManager economy = new EconomyManager();
                    economy.depositMoney(player, itemPrice);
                    if(keyItem.getItemMeta().hasDisplayName()) {
                        player.sendMessage("§7Sprzedałeś przedmiot " + keyItem.getItemMeta().getDisplayName() + " za §2" + itemPrice + "$");
                    } else {
                        player.sendMessage("§7Sprzedałeś przedmiot " + keyItem.getType().toString() + " za §2" + itemPrice + "$");
                    }

                    player.updateInventory();
                }
            }
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
