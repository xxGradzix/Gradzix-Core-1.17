package me.xxgradzix.gradzixcore.serverconfig.commands;

import me.xxgradzix.gradzixcore.serverconfig.PriorytetyGui.PrioritiesGui;
import me.xxgradzix.gradzixcore.serverconfig.files.ConfigServera;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetPrioritiesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;


        PrioritiesGui prioritiesGui;

        prioritiesGui = new PrioritiesGui(ConfigServera.getItemPriorities());


        prioritiesGui.open(p);


        return true;
    }
}
