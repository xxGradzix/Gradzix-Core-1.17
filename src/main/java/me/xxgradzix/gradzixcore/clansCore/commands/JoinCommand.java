package me.xxgradzix.gradzixcore.clansCore.commands;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ClanWithThisUUIDDoesNotExists;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ThisUserAlreadyBelongsToAnotherClan;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ThisUserAlreadyBelongsToThisClan;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
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
            sender.sendMessage("Użycie: /dolacz <tag>");
            return false;
        }

        Player player = (Player) sender;

        String tag = args[0];

        Optional<ClanEntity> clan = ClanManager.getClanEntityByTag(tag);

        if(!clan.isPresent()) {
            player.sendMessage("Klan z tagiem " + tag + " nie istnieje");
            return false;
        }

        if(!ClanManager.isPlayerInvitedToClan(player, clan.get().getUuid())) {
            player.sendMessage("Nie zostałeś zaproszony do tego klanu");
            return false;
        }

        try {
            ClanManager.addMemberToClan(clan.get().getUuid(), player);
        } catch (ThisUserAlreadyBelongsToAnotherClan e) {
            player.sendMessage("Należysz już do innego klanu");
            return false;
        } catch (ClanWithThisUUIDDoesNotExists e) {
            player.sendMessage("Nie znaleziono takiej gildi, przepraszamy");
            return false;
        } catch (ThisUserAlreadyBelongsToThisClan e) {
            player.sendMessage("Należysz już do tego klanu");
        }

        return true;
    }
}
