package me.xxgradzix.gradzixcore.serverconfig.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class BazarWystawCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("bazarwystaw")) {
                // Wywołaj komendę "/bazar sell" z innego pluginu
                getServer().dispatchCommand(player, "bazar sell");
                return true;
            }
        }
        return false;
    }

}
