package me.xxgradzix.gradzixcore.umiejetnosci.listeners;

import me.xxgradzix.gradzixcore.chatopcje.files.ChatOpcjeConfigFile;
import me.xxgradzix.gradzixcore.umiejetnosci.files.ModyfikatoryUmiejetnosciConfigFile;
import me.xxgradzix.gradzixcore.umiejetnosci.files.UmiejetnosciConfigFile;
import net.dzikoysk.funnyguilds.event.rank.CombatPointsChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class PlayerKillRankingIncrease implements Listener {

    @EventHandler
    public void onPlayerKill(CombatPointsChangeEvent event) {

        int killerPoints = event.getAttackerPointsChange();
        int preyPoints = event.getAttackerPointsChange();

        Player p = Bukkit.getPlayer(event.getAttacker().getUUID());

        double multiplier = ModyfikatoryUmiejetnosciConfigFile.getRankMultiplier(UmiejetnosciConfigFile.getRankLevel(p));

        if(multiplier > 1) {
            killerPoints *= multiplier;
        }

        event.setAttackerPointsChange(killerPoints);


        List<String> blockedMessagePlayerUUIDs = ChatOpcjeConfigFile.getShowDeathMessageStatusUUIDsList(true);


        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!blockedMessagePlayerUUIDs.contains(player.getUniqueId().toString())) {
                player.sendMessage(ChatColor.DARK_RED + "⚔ " + ChatColor.GRAY + "| " + ChatColor.RED +  event.getAttacker().getName() + ChatColor.GRAY + " (" + ChatColor.GREEN +"+" + ChatColor.GRAY + killerPoints +  ") zabił " + ChatColor.RED + event.getVictim().getName() + ChatColor.GRAY +" (" + ChatColor.RED + "-" + ChatColor.GRAY + preyPoints + ")");
            }
        }

    }


}