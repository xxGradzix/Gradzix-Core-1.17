package me.xxgradzix.gradzixcore.incognito.commands;


import me.xxgradzix.gradzixcore.incognito.manages.IncognitoModeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IncognitoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Komenda dostępna tylko dla graczy!");
            return true;
        }
        Player player = (Player) sender;
//
//        if(args.length > 0) {
//            Bukkit.broadcastMessage("§cUżycie: /incognito restore");
//            IncognitoModeManager.toggleIncognitoMode(player);
//            return false;
//        }

//        Bukkit.broadcastMessage("§cUżycie: /incognito false");
            IncognitoModeManager.toggleIncognitoMode(player);


        return true;
    }



}
