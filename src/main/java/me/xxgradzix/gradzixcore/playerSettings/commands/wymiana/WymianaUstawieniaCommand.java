package me.xxgradzix.gradzixcore.playerSettings.commands.wymiana;

import me.xxgradzix.gradzixcore.playerSettings.data.DataManager;
import me.xxgradzix.gradzixcore.playerSettings.gui.wymiana.ExchangeGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class WymianaUstawieniaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            ExchangeGui exchangeGui;
            HashMap<ItemStack, ItemStack> map = (HashMap<ItemStack, ItemStack>) DataManager.getAutoExchangeItems();
            exchangeGui = new ExchangeGui(map);
            exchangeGui.open(p);
        }
        return true;
    }
}
