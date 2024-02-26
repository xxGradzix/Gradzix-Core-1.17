package me.xxgradzix.gradzixcore.clansCore.commands;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.exceptions.*;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import me.xxgradzix.gradzixcore.clansCore.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class KickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;

        if(args.length != 1) {
            sender.sendMessage(Messages.KICK_COMMAND_USAGE);
            return false;
        }

        Player player = (Player) sender;

        Optional<ClanEntity> leaderClan = ClanManager.getClanEntityByLeader(UserManager.getOrCreateUserEntity(player));

        if(!leaderClan.isPresent()) {
            player.sendMessage(Messages.YOU_DONT_HAVE_PERMISSION_TO_DO_THIS);
            return false;
        }

        String nick = args[0];
        Optional<UserEntity> userEntityByNick = UserManager.getUserEntityByNick(nick);

        if(!userEntityByNick.isPresent()) {
            player.sendMessage(Messages.PLAYER_DOES_NOT_EXISTS(nick));
            return false;
        }

        try {
            ClanManager.removeMemberFromClan(leaderClan.get().getUuid(), Bukkit.getPlayer(nick));
            player.sendMessage(Messages.YOU_KICKED_PLAYER(nick));
        } catch (ThisClanDoesNotExists | ThisUserDoesNotBelongToThisClan e) {
            player.sendMessage(Messages.PLAYER_DOES_BELONG_TO_YOUR_CLAN(nick));
            return false;
        }

        return true;
    }
}
