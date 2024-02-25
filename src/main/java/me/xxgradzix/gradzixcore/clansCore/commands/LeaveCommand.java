package me.xxgradzix.gradzixcore.clansCore.commands;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.exceptions.*;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class LeaveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;

        if(args.length != 0) {
            sender.sendMessage("Użycie: /opusc");
            return false;
        }

        Player player = (Player) sender;
        UserEntity userEntity = UserManager.getOrCreateUserEntity(player);

        Optional<ClanEntity> clanEntityOfMember = ClanManager.getClanEntityOfMember(userEntity);
        if(!clanEntityOfMember.isPresent()) {
            player.sendMessage("Nie należysz do żadnego klanu");
            return false;
        }

        boolean isLeader = ClanManager.getClanEntityByLeader(userEntity).isPresent();
        if(isLeader) {
            player.sendMessage("Jesteś liderem klanu, nie możesz opuścić klanu");
            return false;
        }

        ClanManager.removeMemberFromClan(player);

        return true;
    }
}
