package me.xxgradzix.gradzixcore.kits.commands;

import me.xxgradzix.gradzixcore.GlobalMessagesManager;
import me.xxgradzix.gradzixcore.kits.managers.KitManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class KitCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalMessagesManager.PLAYER_ONLY);
            return true;
        }
        Player player = (Player) sender;

        KitManager.openKitSelector(player);
        return true;
    }
}
