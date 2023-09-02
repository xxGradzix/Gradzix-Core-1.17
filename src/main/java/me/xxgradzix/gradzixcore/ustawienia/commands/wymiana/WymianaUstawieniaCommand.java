package me.xxgradzix.gradzixcore.ustawienia.commands.wymiana;

import me.xxgradzix.gradzixcore.ustawienia.data.DataManager;
import me.xxgradzix.gradzixcore.ustawienia.gui.wymiana.WymianaGui;
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


//        if(!p.hasPermission("ustawienia.wymianaustawieniaitems")) return false;

        if(sender instanceof Player) {
            Player p = (Player) sender;
//            if(!p.getName().equals("xxGradzix") ||
//            !p.getName().equals("MajkiToJa")) {
//                p.sendMessage("tylko xxGradzix i MajkiToJa moze uzyc tej komendy w tym momencie");
//                return true;
//            }


            WymianaGui wymianaGui;


//            wymianaGui = new WymianaGui(WymianaUstawieniaItemsConfigFile.getAllItems());
            HashMap<ItemStack, ItemStack> map = (HashMap<ItemStack, ItemStack>) DataManager.getAutoExchangeItems();
            wymianaGui = new WymianaGui(map);



            wymianaGui.open(p);



        }

        return true;
    }

}
