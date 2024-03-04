package me.xxgradzix.gradzixcore.playerAbilities.listeners;

import me.xxgradzix.gradzixcore.chatOptions.ChatOptions;
import me.xxgradzix.gradzixcore.chatOptions.data.database.entities.ChatOptionsEntity;
import me.xxgradzix.gradzixcore.clansCore.events.UserPointsChangeEvent;
import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlayerKillRankingIncrease implements Listener {

    @EventHandler
    public void onPlayerKill(UserPointsChangeEvent event) {

        int killerPoints = event.getKillerPointsToGet();
        int preyPoints = event.getVictimPointsToLose();

        Player p = event.getKiller();

        double multiplier = DataManager.getRankAbilityModifier(p);

        if(multiplier > 1) {
            killerPoints *= multiplier;
        }

        event.setKillerPointsToGet(killerPoints);

        List<ChatOptionsEntity> chatOptionsEntityList = ChatOptions.getChatOptionsEntityManager().getChatOptionsEntitiesWhereShowDeathMessageIs(false);

        List<UUID> blockedMessagePlayerUUIDs = chatOptionsEntityList.stream().map((ChatOptionsEntity::getUuid)).collect(Collectors.toList());


        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!blockedMessagePlayerUUIDs.contains(player.getUniqueId())) {
                player.sendMessage(ChatColor.DARK_RED + "⚔ " + ChatColor.GRAY + "| " + ChatColor.RED +  event.getKiller().getName() + ChatColor.GRAY + " (" + ChatColor.GREEN +"+" + ChatColor.GRAY + killerPoints +  ") zabił " + ChatColor.RED + event.getVictim().getName() + ChatColor.GRAY +" (" + ChatColor.RED + "-" + ChatColor.GRAY + preyPoints + ")");
            }
        }

    }


}