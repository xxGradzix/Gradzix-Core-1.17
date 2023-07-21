package me.xxgradzix.gradzixcore.serverconfig.commands;

import me.xxgradzix.gradzixcore.serverconfig.files.ConfigServera;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetDamageCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] args) {


        if(sender instanceof Player) {

            Player p = (Player) sender;

//            if(!p.hasPermission("serverConfig.damageConfig")) return false;

            if (args.length == 1) {
                try {
                    double multiplier = Double.parseDouble(args[0]);

                    ConfigServera.setDamageMultiplier(multiplier);

                    sender.sendMessage("Mnożnik obrażeń ustawiony na: " + multiplier);
                } catch (NumberFormatException e) {
                    sender.sendMessage("Nieprawidłowy format mnożnika. Użyj liczby.");
                }
            } else {
                sender.sendMessage("Nieprawidłowe użycie komendy. Użyj: /setserverdamagemultiplier [multiplier]");
            }


        }
        return true;
    }
}
