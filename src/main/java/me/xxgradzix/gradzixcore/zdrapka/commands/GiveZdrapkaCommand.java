package me.xxgradzix.gradzixcore.zdrapka.commands;

import me.xxgradzix.gradzixcore.zdrapka.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveZdrapkaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player) sender;

//            if(!p.hasPermission("zdrapka.givezdrapka")) return false;

            String targetPlayerName = null;
            int amount = 1;

            if (args.length >= 1) {
                targetPlayerName = args[0];
            }

            if (args.length >= 2) {
                try {
                    amount = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    p.sendMessage(ChatColor.RED + "Nieprawidłowa ilość.");
                    return true;
                }
            }

            Player targetPlayer = p;

            if (targetPlayerName != null) {
                targetPlayer = p.getServer().getPlayer(targetPlayerName);

                if (targetPlayer == null) {
                    p.sendMessage(ChatColor.RED + "Nie znaleziono gracza o podanej nazwie.");
                    return true;
                }
            }

            ItemStack zdrapka = ItemManager.zdrapka;
            zdrapka.setAmount(amount);


            

            targetPlayer.getInventory().addItem(zdrapka);
            p.sendMessage(ChatColor.GREEN + "Dano " + amount + " zdrapek graczowi " + targetPlayer.getName() + ".");

            return true;




        }


        return true;
    }
}
