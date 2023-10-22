package me.xxgradzix.gradzixcore.magicFirework.commands;

import me.xxgradzix.gradzixcore.magicFirework.items.ItemMenager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FireworkCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("Only Players can use that command");
            return true;
        }
        Player player = (Player) sender;
//        if(!player.hasPermission("magicznafajerwerka.givefajerwerka")) return false;

        if(cmd.getName().equalsIgnoreCase("givefirework")) {
            player.getInventory().addItem(ItemMenager.firework);
        }

        return true;
    }
}
