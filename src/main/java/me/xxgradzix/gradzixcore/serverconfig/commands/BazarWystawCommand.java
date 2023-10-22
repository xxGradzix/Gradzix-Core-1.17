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

                if(args.length == 1) {
                    int price = Integer.parseInt(args[0]);
                    getServer().dispatchCommand(player, "bazar sell " + price + " " + player.getInventory().getItemInOffHand());
                    return true;

                } else if(args.length == 2) {
                    int price = Integer.parseInt(args[0]);
                    int ilosc = Integer.parseInt(args[0]);
                    getServer().dispatchCommand(player, "bazar sell " + price + " " + ilosc);
                    return true;
                } else {
                    sender.sendMessage("Poprawne uzycie to");
                    sender.sendMessage("/bazarwwystaw [CENA] [ILOSC]");
                    return true;
                }
            }
        }
        return false;
    }

}
