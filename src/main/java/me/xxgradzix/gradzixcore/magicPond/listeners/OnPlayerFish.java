package me.xxgradzix.gradzixcore.magicPond.listeners;


import me.xxgradzix.gradzixcore.events.Events;
import me.xxgradzix.gradzixcore.magicPond.MagicPond;
import me.xxgradzix.gradzixcore.magicPond.data.DataManager;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OnPlayerFish implements Listener {

    private final DataManager dataManager;

    public OnPlayerFish(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Player p = event.getPlayer();
        if(!Events.isMagicPondEventEnabled()) {
            event.setCancelled(true);
            p.sendMessage("Event magiczne jeziorko jest w tym momencie wyłączony");
        }

        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;




        Item item = (Item) event.getCaught();


//        HashMap<ItemStack, Integer> rewards = dataManager.getMagicPondEntityRewards();
        HashMap<ItemStack, Integer> rewards = MagicPond.getMagicPondRewards();

        ItemStack reward = chooseRandomReward(rewards);

        if(reward == null) {
            item.setItemStack(new ItemStack(Material.DIAMOND, 1));
        } else {
            item.setItemStack(reward);
        }
    }
    private static ItemStack chooseRandomReward(Map<ItemStack, Integer> rewards) {
        if(rewards == null || rewards.isEmpty()) return null;
        int totalPercentage = 0;
        for (Integer percentage : rewards.values()) {
            totalPercentage += percentage;
        }
        int randomPercentage = new Random().nextInt(totalPercentage);
        int cumulativePercentage = 0;
        for (Map.Entry<ItemStack, Integer> entry : rewards.entrySet()) {
            cumulativePercentage += entry.getValue();
            if (randomPercentage < cumulativePercentage) {
                return entry.getKey();
            }
        }
        return null;
    }
}
