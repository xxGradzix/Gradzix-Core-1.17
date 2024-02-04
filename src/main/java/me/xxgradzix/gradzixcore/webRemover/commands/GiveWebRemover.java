package me.xxgradzix.gradzixcore.webRemover.commands;

import me.xxgradzix.gradzixcore.webRemover.itemManager.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveWebRemover implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Komenda tylko dla graczy");
            return true;
        }

        Player player = (Player) sender;


        player.getInventory().addItem(ItemManager.webRemover);
        player.sendMessage("Dodano przedmiot do ekwipunku");

        return false;
    }
}
