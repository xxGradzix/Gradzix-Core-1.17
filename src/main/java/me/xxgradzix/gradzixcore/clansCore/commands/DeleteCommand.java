package me.xxgradzix.gradzixcore.clansCore.commands;

import me.xxgradzix.gradzixcore.clansCore.exceptions.*;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length != 0) {
            player.sendMessage("Użycie: /usun");
            return false;
        }


        try {
            ClanManager.removeClan(player);
        } catch (ThisUserIsNotALeader e) {
            player.sendMessage("Musisz być liderem klanu, aby móc go usunąć");
            return false;
        }

        player.sendMessage("Usunąłeś klan");

        return true;
    }
}
