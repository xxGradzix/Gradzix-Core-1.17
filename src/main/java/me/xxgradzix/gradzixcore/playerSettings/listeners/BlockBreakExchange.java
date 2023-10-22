package me.xxgradzix.gradzixcore.playerSettings.listeners;

import me.xxgradzix.gradzixcore.playerSettings.data.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class BlockBreakExchange implements Listener {

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
