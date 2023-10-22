package me.xxgradzix.gradzixcore.upgradeItem.commands;

import me.xxgradzix.gradzixcore.upgradeItem.gui.UpgradeGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UpgradeConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {


        if(sender instanceof Player) {

            Player p = (Player) sender;

            UpgradeGui upgradeGui = new UpgradeGui();

            upgradeGui.open(p);

        }

        return true;

    }
}
