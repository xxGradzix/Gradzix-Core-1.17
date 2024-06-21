package me.xxgradzix.gradzixcore.dailyQuests.commands;

import me.xxgradzix.gradzixcore.dailyQuests.managers.QuestsManagers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DailyQuestsResetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        QuestsManagers.resetDailyQuests();

        return false;
    }
}
