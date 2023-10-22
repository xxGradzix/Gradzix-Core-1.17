package me.xxgradzix.gradzixcore.playerAbilities.commands;

import me.xxgradzix.gradzixcore.playerAbilities.items.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveOdlamekCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(sender instanceof Player) {

            Player p = (Player) sender;

//            if(!p.hasPermission("umiejetnosci.giveodlamek")) return false;

            p.getInventory().addItem(ItemManager.odlamek);

        }

        return true;

    }
}
