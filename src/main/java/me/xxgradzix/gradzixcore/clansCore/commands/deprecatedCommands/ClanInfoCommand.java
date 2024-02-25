package me.xxgradzix.gradzixcore.clansCore.commands.deprecatedCommands;

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

public class ClanInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(args.length != 1) {
            player.sendMessage(Messages.CLAN_INFO_COMMAND_USAGE);
            return false;
        }

        String tag = args[0];
        Optional<ClanEntity> clan = ClanManager.getClanEntityByTag(tag);

        if(!clan.isPresent()) {
            player.sendMessage(Messages.CLAN_WITH_THIS_TAG_DOES_NOT_EXISTS);
            return false;
        }

        ClanEntity targetClan = clan.get();

        player.sendMessage(Messages.CLAN_INFO(targetClan));

        return true;

    }
}
