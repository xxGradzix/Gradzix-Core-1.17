package me.xxgradzix.gradzixcore.playerSettings.commands.sprzedaz;

import me.xxgradzix.gradzixcore.playerSettings.data.DataManager;
import me.xxgradzix.gradzixcore.playerSettings.gui.sprzedaz.SprzedazGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class SprzedazCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

//        if(!sender.hasPermission("ustawienia.sprzedazustawienia")) return false;

        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;


        SprzedazGui sprzedazGui;

        HashMap<ItemStack, Integer> map = (HashMap<ItemStack, Integer>) DataManager.getAutoSellItems();

        sprzedazGui = new SprzedazGui(map);


        sprzedazGui.open(p);


        return true;
    }
}
