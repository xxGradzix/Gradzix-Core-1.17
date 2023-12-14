package me.xxgradzix.gradzixcore.serverconfig.commands;

import dev.triumphteam.gui.guis.StorageGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BinCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        StorageGui bin = new StorageGui(6, "§8Kosz");

        bin.open(player);

        return true;

    }
}
