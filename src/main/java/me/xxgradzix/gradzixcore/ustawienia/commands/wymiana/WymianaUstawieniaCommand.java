package me.xxgradzix.gradzixcore.ustawienia.commands.wymiana;

import me.xxgradzix.gradzixcore.ustawienia.files.WymianaUstawieniaItemsConfigFile;
import me.xxgradzix.gradzixcore.ustawienia.gui.wymiana.WymianaGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
            wymianaGui = new WymianaGui(WymianaUstawieniaItemsConfigFile.getAllItemKeys(), WymianaUstawieniaItemsConfigFile.getAllItemValues());



            wymianaGui.open(p);



        }

        return true;
    }

}
