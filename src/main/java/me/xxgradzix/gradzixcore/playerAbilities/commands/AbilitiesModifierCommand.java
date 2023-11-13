package me.xxgradzix.gradzixcore.playerAbilities.commands;

import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;
import me.xxgradzix.gradzixcore.playerAbilities.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AbilitiesModifierCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 3) {
            String type = args[0].toLowerCase();
            int level;
            try {
                level = Integer.parseInt(args[1]);
            } catch (Exception e) {
                // Todo must be number MEssage
                return false;
            }
            double multiplier;
            try {
                multiplier = Double.parseDouble(args[2]);
            } catch (Exception e) {
                // Todo must be number MEssage
                return false;
            }
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

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("modyfikatoryumiejetnosci")) {
            if (args.length == 1) {
                List<String> completions = new ArrayList<>();
                completions.add("DROP");
                completions.add("RANK");
                completions.add("SILA");
                return completions;
            }
        }
        return null;
    }
}





