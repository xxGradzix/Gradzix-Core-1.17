package me.xxgradzix.gradzixcore.dailyQuests.commands;

import me.xxgradzix.gradzixcore.eventArea.EventArea;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetKillDropChance implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) return true;

        if(strings.length == 0) {
            commandSender.sendMessage("Podaj wartość szansy na drop!");
            return true;
        }

        try {
            Double dropChance = Double.parseDouble(strings[0]);
            EventArea.setKillDropChance(dropChance);
        } catch (NumberFormatException e) {
            commandSender.sendMessage("Podaj poprawną wartość szansy na drop!");
            return true;
        }

        return true;
    }

}
