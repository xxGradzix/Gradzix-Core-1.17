package me.xxgradzix.gradzixcore.serverconfig.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class ActionHouseRenameCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(args.length == 0) {
            getServer().dispatchCommand(player, "ah");
            return true;
        }

        if(args.length < 1) {
            return false;
        }

        String subCommand = args[0];

        if(subCommand.equalsIgnoreCase("wystaw")) {
            int price = 0;
            int amount = 1;
            if(args.length >= 2) {
                try {
                    price = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage("§cCena musi byc liczba!");
                    return true;
                }
            }
            if(args.length == 3) {
                try {
                    amount = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    player.sendMessage("§cIlosc musi byc liczba!");
                    return true;
                }
            }
            getServer().dispatchCommand(player, "ah sell " + price + " " + amount);
        }

        return false;

    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        ArrayList<String> list = new ArrayList<>();

        if(command.getName().equalsIgnoreCase("bazar")) {
            if(args.length == 1) {
                list.add("wystaw");
            }
        }

        return list;
    }
}
