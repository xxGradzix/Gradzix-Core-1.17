package me.xxgradzix.gradzixcore.playerAbilities.listeners;

import me.xxgradzix.gradzixcore.chatOptions.Chatopcje;
import me.xxgradzix.gradzixcore.chatOptions.data.database.entities.ChatOptionsEntity;
import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;

import net.dzikoysk.funnyguilds.event.rank.CombatPointsChangeEvent;
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
    public void onPlayerKill(CombatPointsChangeEvent event) {

        int killerPoints = event.getAttackerPointsChange();
        int preyPoints = event.getAttackerPointsChange();

        Player p = Bukkit.getPlayer(event.getAttacker().getUUID());

        int playerRankLevel = DataManager.getPlayerAbilityLevel(Ability.RANK, p);


        double multiplier = DataManager.getAbilityModifier(Ability.RANK, playerRankLevel);

        if(multiplier > 1) {
            killerPoints *= multiplier;
        }

        event.setAttackerPointsChange(killerPoints);


//        List<String> blockedMessagePlayerUUIDs = ChatOpcjeConfigFile.getShowDeathMessageStatusUUIDsList(true);
        List<ChatOptionsEntity> chatOptionsEntityList = Chatopcje.getChatOptionsEntityManager().getChatOptionsEntitiesWhereShowDeathMessageIs(false);

        List<UUID> blockedMessagePlayerUUIDs = chatOptionsEntityList.stream().map((ChatOptionsEntity::getUuid)).collect(Collectors.toList());


        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!blockedMessagePlayerUUIDs.contains(player.getUniqueId())) {
                player.sendMessage(ChatColor.DARK_RED + "⚔ " + ChatColor.GRAY + "| " + ChatColor.RED +  event.getAttacker().getName() + ChatColor.GRAY + " (" + ChatColor.GREEN +"+" + ChatColor.GRAY + killerPoints +  ") zabił " + ChatColor.RED + event.getVictim().getName() + ChatColor.GRAY +" (" + ChatColor.RED + "-" + ChatColor.GRAY + preyPoints + ")");
            }
        }

    }


}