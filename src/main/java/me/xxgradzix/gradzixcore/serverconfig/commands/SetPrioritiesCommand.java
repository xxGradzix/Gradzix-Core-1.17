package me.xxgradzix.gradzixcore.serverconfig.commands;

import me.xxgradzix.gradzixcore.serverconfig.PriorytetyGui.PrioritiesGui;
import me.xxgradzix.gradzixcore.serverconfig.data.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SetPrioritiesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;


        PrioritiesGui prioritiesGui;

        prioritiesGui = new PrioritiesGui((ArrayList<ItemStack>) DataManager.getItemPriorities());


        prioritiesGui.open(p);


        return true;
    }
}
