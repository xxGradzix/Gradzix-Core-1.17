package me.xxgradzix.gradzixcore.clansCore.commands.deprecatedCommands;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.exceptions.*;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import me.xxgradzix.gradzixcore.clansCore.messages.Messages;
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
            sender.sendMessage(Messages.LEAVE_COMMAND_USAGE);
            return false;
        }

        Player player = (Player) sender;
        UserEntity userEntity = UserManager.getOrCreateUserEntity(player);

        Optional<ClanEntity> clanEntityOfMember = ClanManager.getClanEntityOfMember(userEntity);
        if(!clanEntityOfMember.isPresent()) {
            player.sendMessage(Messages.YOU_DONT_BELONG_TO_ANY_CLAN);
            return false;
        }

        boolean isLeader = ClanManager.getClanEntityByLeader(userEntity).isPresent();
        if(isLeader) {
            player.sendMessage(Messages.YOU_CANT_LEAVE_CLAN_BECAUSE_YOU_ARE_LEADER);
            return false;
        }

        ClanManager.removeMemberFromClan(player);


        return true;
    }
}
