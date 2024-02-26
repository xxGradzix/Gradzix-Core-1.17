package me.xxgradzix.gradzixcore.clansCore.commands;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ClanWithThisUUIDDoesNotExists;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ThisUserAlreadyBelongsToAnotherClan;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ThisUserAlreadyBelongsToThisClan;
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

public class InviteToClanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;

        if(args.length != 1) {
            sender.sendMessage(Messages.INVITE_COMMAND_USAGE);
            return false;
        }

        Player player = (Player) sender;

        UserEntity userEntity = UserManager.getOrCreateUserEntity(player);

        Optional<ClanEntity> clanEntityByClanMember = ClanManager.getClanEntityByClanMember(userEntity);

        if(!clanEntityByClanMember.isPresent()) {
            player.sendMessage(Messages.YOU_DONT_BELONG_TO_ANY_CLAN);
            return false;
        }

        String nick = args[0];
        Player invited;
        try {
            invited = Bukkit.getPlayer(nick);
        } catch (Exception e) {
            player.sendMessage(Messages.PLAYER_IS_NOT_ONLINE(nick));
            return false;
        }
        if(invited == null) {
            player.sendMessage(Messages.PLAYER_IS_NOT_ONLINE(nick));
            return false;
        }

        ClanManager.addClanInvitation(invited, clanEntityByClanMember.get().getUuid());

        return true;
    }
}
