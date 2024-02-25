package me.xxgradzix.gradzixcore.clansCore.commands.deprecatedCommands;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ClanWithThisUUIDDoesNotExists;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ThisUserAlreadyBelongsToAnotherClan;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ThisUserAlreadyBelongsToThisClan;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.messages.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class JoinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;

        if(args.length != 1) {
            sender.sendMessage(Messages.JOIN_COMMAND_USAGE);
            return false;
        }

        Player player = (Player) sender;

        String tag = args[0];

        Optional<ClanEntity> clan = ClanManager.getClanEntityByTag(tag);

        if(!clan.isPresent()) {
            player.sendMessage(Messages.CLAN_WITH_THIS_TAG_DOES_NOT_EXISTS);
            return false;
        }

        if(!ClanManager.isPlayerInvitedToClan(player, clan.get().getUuid())) {
            player.sendMessage(Messages.YOU_WERE_NOT_INVITED_TO_THIS_CLAN);
            return false;
        }

        try {
            ClanManager.addMemberToClan(clan.get().getUuid(), player);
        } catch (ThisUserAlreadyBelongsToAnotherClan e) {
            player.sendMessage(Messages.YOU_BELONG_TO_ANOTHER_CLAN);
            return false;
        } catch (ClanWithThisUUIDDoesNotExists e) {
            player.sendMessage(Messages.CLAN_WITH_THIS_TAG_DOES_NOT_EXISTS);
            return false;
        } catch (ThisUserAlreadyBelongsToThisClan e) {
            player.sendMessage(Messages.YOU_BELONG_TO_THIS_CLAN);
        }

        return true;
    }
}
