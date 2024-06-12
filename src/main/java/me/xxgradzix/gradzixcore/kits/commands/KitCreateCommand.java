package me.xxgradzix.gradzixcore.kits.commands;

import me.xxgradzix.gradzixcore.GlobalMessagesManager;
import me.xxgradzix.gradzixcore.kits.managers.KitManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class KitCreateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(GlobalMessagesManager.PLAYER_ONLY);
            return true;
        }
        Player player = (Player) commandSender;

        if(strings.length == 0) {
            player.sendMessage(GlobalMessagesManager.INVALID_ARGUMENTS);
            return true;
        }

        KitManager.createKit(player, strings[0]);
        return true;
    }
}
