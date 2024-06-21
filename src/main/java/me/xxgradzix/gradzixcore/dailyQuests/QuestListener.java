package me.xxgradzix.gradzixcore.dailyQuests;

import me.xxgradzix.gradzixcore.dailyQuests.managers.QuestsManagers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class QuestListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;
        QuestsManagers.onQuestProgress(event.getPlayer().getUniqueId(), QuestType.BLOCK_BREAKED);
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(event.isCancelled()) return;
        QuestsManagers.onQuestProgress(event.getPlayer().getUniqueId(), QuestType.BLOCK_PLACED);
    }
    @EventHandler
    public void onTotemOfUndyingUse(EntityResurrectEvent event) {
        if(event.isCancelled()) return;
        if(!(event.getEntity() instanceof Player)) return;
        if(event.getEntity().getKiller() == null) return;
        QuestsManagers.onQuestProgress(event.getEntity().getKiller().getUniqueId(), QuestType.TOTEM_OF_UNDYING_USED);
    }
    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        QuestsManagers.onQuestProgress(event.getEntity().getUniqueId(), QuestType.PLAYER_KILLS);
    }


}
