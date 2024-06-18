package me.xxgradzix.gradzixcore.achievements.listeners;

import me.xxgradzix.gradzixcore.achievements.database.DataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.UUID;

public class BlockBreakListener implements Listener {

    private DataManager dataManager;

    private static HashMap<UUID, Integer> blocksBreaked = new HashMap<>();



    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        UUID playerUUID = event.getPlayer().getUniqueId();
        int blocks = blocksBreaked.getOrDefault(playerUUID, 0);
        blocks++;
        blocksBreaked.put(playerUUID, blocks);
    }

    // create method that will update all player achievements
    public void updatePlayerAchievements() {
        // iterate over all players
        for(UUID playerUUID : blocksBreaked.keySet()) {
            // get player's blocks breaked
            int blocks = blocksBreaked.get(playerUUID);
            // update player's achievements
            // ...
            dataManager.getAchievements(playerUUID);
        }
    }


}
