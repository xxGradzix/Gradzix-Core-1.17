package me.xxgradzix.gradzixcore.dailyQuests;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

public class QuestListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        UUID playerUUID = event.getPlayer().getUniqueId();
        int blocks = blocksBreaked.getOrDefault(playerUUID, 0);
        blocks++;
        blocksBreaked.put(playerUUID, blocks);
    }

}
