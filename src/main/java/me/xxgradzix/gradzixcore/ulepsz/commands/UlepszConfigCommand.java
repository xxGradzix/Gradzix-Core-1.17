package me.xxgradzix.gradzixcore.ulepsz.commands;

import me.xxgradzix.gradzixcore.ulepsz.files.UlepszConfigFile;
import me.xxgradzix.gradzixcore.ulepsz.gui.UlepszGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UlepszConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {


        if(sender instanceof Player) {

            Player p = (Player) sender;
//            if(!p.hasPermission("ulepsz.ulepszconfig")) return false;

            ArrayList<ItemStack[]> itemStacksList = (ArrayList<ItemStack[]>) UlepszConfigFile.getAllItems();


            UlepszGui ulepszGui = new UlepszGui(itemStacksList);


            ulepszGui.open(p);



        }

        return true;

    }
}
