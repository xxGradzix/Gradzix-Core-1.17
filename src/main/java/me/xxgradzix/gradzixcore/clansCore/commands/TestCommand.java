package me.xxgradzix.gradzixcore.clansCore.commands;

import me.xxgradzix.gradzixcore.clansCore.Clans;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.managers.UserEntityManager;
import me.xxgradzix.gradzixcore.clansCore.exceptions.*;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Scoreboard scoreboard = Clans.SCOREBOARD;


        for (Team team : scoreboard.getTeams()) {
            Bukkit.broadcastMessage("Team: " + team.getName());
            Bukkit.broadcastMessage("======================");
            for (String entry : team.getEntries()) {
                Bukkit.broadcastMessage("Entry: " + entry);
            }
            Bukkit.broadcastMessage("======================");
        }



//        Player player = (Player) sender;
//
//        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//            if(onlinePlayer.equals(player)) continue;
////            TeamManager.sendDifferentPrefix(onlinePlayer, player, "redprefix ");
//        }
//        Optional<ClanEntity> tag = ClanManager.getClanEntityByTag("tag");
//
//        if(!tag.isPresent()) {
//            player.sendMessage("Clan with tag: tag not found");
//            return false;
//        }
//
//        ClanEntity clanEntity = tag.get();
//
//        try {
//            ClanManager.addMemberToClan(clanEntity.getUuid(), player.getUniqueId());
//        } catch (ThisUserAlreadyBelongsToAnotherClan e) {
//            player.sendMessage("This user already belongs to another clan");
//        } catch (ClanWithThisUUIDDoesNotExists e) {
//            player.sendMessage("Clan with this UUID does not exists");
//        }
//
//        TeamManager.updateEntities(clanEntity);

        return false;
    }
}
