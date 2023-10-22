package me.xxgradzix.gradzixcore.playerSettings.commands.sprzedaz;

import me.xxgradzix.gradzixcore.playerSettings.data.DataManager;
import me.xxgradzix.gradzixcore.playerSettings.gui.sprzedaz.SellGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class SprzedazCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        SellGui sellGui;

        HashMap<ItemStack, Integer> map = (HashMap<ItemStack, Integer>) DataManager.getAutoSellItems();

        sellGui = new SellGui(map);

        sellGui.open(p);

        return true;
    }
}
