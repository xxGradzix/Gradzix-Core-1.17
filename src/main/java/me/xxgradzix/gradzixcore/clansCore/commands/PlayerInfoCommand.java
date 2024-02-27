package me.xxgradzix.gradzixcore.clansCore.commands;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import me.xxgradzix.gradzixcore.clansCore.messages.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class PlayerInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(args.length != 1) {
            player.sendMessage(Messages.PLAYER_INFO_COMMAND_USAGE);
            return false;
        }

        String nick = args[0];
        Optional<UserEntity> target = UserManager.getUserEntityByNick(nick);
        if(!target.isPresent()) {
            player.sendMessage(Messages.PLAYER_DOES_NOT_EXISTS(nick));
            return false;
        }

        UserEntity user = UserManager.getOrCreateUserEntity(player);

        ClanEntity targetClan = ClanManager.getClanEntityByClanMember(target.get()).orElse(null);

        player.sendMessage(Messages.PLAYER_INFO(user, targetClan));

        return true;

    }
}
