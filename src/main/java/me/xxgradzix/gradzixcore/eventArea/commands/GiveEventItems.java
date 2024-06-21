package me.xxgradzix.gradzixcore.eventArea.commands;

import me.xxgradzix.gradzixcore.eventArea.items.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveEventItems implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) return true;

        Player player = (Player) commandSender;

        player.getInventory().addItem(ItemManager.fragmentOfRing);
        player.getInventory().addItem(ItemManager.elfFragment);
        player.getInventory().addItem(ItemManager.dwarfFragment);
        player.getInventory().addItem(ItemManager.onlyRing);
        player.getInventory().addItem(ItemManager.pickaxeOfMoria);

        return true;
    }
}
