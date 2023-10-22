package me.xxgradzix.gradzixcore.playerAbilities.commands;

import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;
import me.xxgradzix.gradzixcore.playerAbilities.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AbilitiesModifierCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 3) {
            String type = args[0];
            int level = Integer.parseInt(args[1]);
            double multiplier = Double.parseDouble(args[2]);

            if(level < 1 || level > 4) {
                sender.sendMessage(ChatColor.RED + "nie możesz ustawić takiego poziomu");
                return false;
            }
            if(multiplier < 0) {
                sender.sendMessage(ChatColor.RED + "mnożnik nie może być mniejszy od zera");
                return false;
            }

            switch (type) {

                case "sila":
                    DataManager.setAbilityModifier(Ability.STRENGTH, level, multiplier);
                    break;

                case "drop":
                    DataManager.setAbilityModifier(Ability.DROP, level, multiplier);
                    break;
                case "rank":
                    DataManager.setAbilityModifier(Ability.RANK, level, multiplier);
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "nieznany typ");
                    sender.sendMessage(ChatColor.RED + "znane typy to: sila, drop, rank");
                    break;
            }

            sender.sendMessage(ChatColor.RED + "Modyfikator umiejętności został ustawiony.");
            ItemManager.init();

            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Nieprawidłowe użycie komendy. Użyj /modyfikatoryumiejetnosci [typ] [level] [multiplier]");
            return false;
        }
    }
}





