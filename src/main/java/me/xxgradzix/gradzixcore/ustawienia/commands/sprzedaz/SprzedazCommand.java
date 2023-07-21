package me.xxgradzix.gradzixcore.ustawienia.commands.sprzedaz;

import me.xxgradzix.gradzixcore.ustawienia.files.WymianaUstawieniaItemsConfigFile;
import me.xxgradzix.gradzixcore.ustawienia.gui.sprzedaz.SprzedazGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SprzedazCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

//        if(!sender.hasPermission("ustawienia.sprzedazustawienia")) return false;

        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;


        SprzedazGui sprzedazGui;

        sprzedazGui = new SprzedazGui(WymianaUstawieniaItemsConfigFile.getAllItemsToSell());


        sprzedazGui.open(p);


        return true;
    }
}
