package me.xxgradzix.gradzixcore.umiejetnosci.commands;

import me.xxgradzix.gradzixcore.umiejetnosci.files.ModyfikatoryUmiejetnosciConfigFile;
import me.xxgradzix.gradzixcore.umiejetnosci.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ModyfikatoryUmiejetnosciCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

//        if(!sender.hasPermission("umiejetnosci.modyfikatoryumiejetnosci")) return false;

        if (args.length == 3) {
            String type = args[0];
            int level = Integer.parseInt(args[1]);
            double multiplier = Double.parseDouble(args[2]);

            if(level < 1 || level > 4) {
                sender.sendMessage(ChatColor.RED + "nie mozesz ustawic takiego poziomu");
                return false;
            }
            if(multiplier < 0) {
                sender.sendMessage(ChatColor.RED + "mnoznik nie moze byc mniejszy od zera");
                return false;
            }

            switch (type) {

                case "sila":
                    ModyfikatoryUmiejetnosciConfigFile.setSilaLevel(level, multiplier);
                    break;

                case "drop":
                    ModyfikatoryUmiejetnosciConfigFile.setDropLevel(level, multiplier);
                    break;
                case "rank":
                    ModyfikatoryUmiejetnosciConfigFile.setRankLevel(level, multiplier);

                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "nieznany typ");
                    sender.sendMessage(ChatColor.RED + "znane typy to: sila, drop, rank");
                    break;
            }

            sender.sendMessage(ChatColor.RED + "Modyfikator umiejętności ustawiony.");
            ItemManager.init();

            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Nieprawidłowe użycie komendy. Użyj /modyfikatoryumiejetnosci [typ] [level] [multiplier]");

            return false;
        }
    }
}





