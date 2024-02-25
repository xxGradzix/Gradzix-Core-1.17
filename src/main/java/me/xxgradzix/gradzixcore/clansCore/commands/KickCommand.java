package me.xxgradzix.gradzixcore.clansCore.commands;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.exceptions.*;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
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
            sender.sendMessage("Użycie: /wyrzuc <nick>");
            return false;
        }

        Player player = (Player) sender;

        Optional<ClanEntity> leaderClan = ClanManager.getClanEntityByLeader(UserManager.getOrCreateUserEntity(player));

        if(!leaderClan.isPresent()) {
            player.sendMessage("Tylko lider może wyrzucić gracza z klanu");
            return false;
        }

        String nick = args[0];
        Optional<UserEntity> userEntityByNick = UserManager.getUserEntityByNick(nick);

        if(!userEntityByNick.isPresent()) {
            player.sendMessage("Gracz " + nick + " nie istnieje");
            return false;
        }

        try {
            ClanManager.removeMemberFromClan(leaderClan.get().getUuid(), Bukkit.getPlayer(nick));
            player.sendMessage("Gracz " + nick + " został wyrzucony z klanu");
        } catch (ThisClanDoesNotExists | ThisUserDoesNotBelongToThisClan e) {
            player.sendMessage("Ten gracz nie należy do twojego klanu");
            return false;
        }

        return true;
    }
}
