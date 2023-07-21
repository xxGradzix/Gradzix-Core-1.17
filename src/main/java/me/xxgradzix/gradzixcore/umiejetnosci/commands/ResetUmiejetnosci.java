package me.xxgradzix.gradzixcore.umiejetnosci.commands;

import me.xxgradzix.gradzixcore.umiejetnosci.files.UmiejetnosciConfigFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ResetUmiejetnosci implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(!sender.hasPermission("umiejetnosci.resetumiejetnosci")) return false;

        UmiejetnosciConfigFile.resetLevels();

        return true;
    }
}
