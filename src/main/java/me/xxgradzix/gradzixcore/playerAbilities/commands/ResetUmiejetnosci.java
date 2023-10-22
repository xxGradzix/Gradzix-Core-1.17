package me.xxgradzix.gradzixcore.playerAbilities.commands;

import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ResetUmiejetnosci implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        
        DataManager.resetAllAbilities();

        return true;
    }
}
