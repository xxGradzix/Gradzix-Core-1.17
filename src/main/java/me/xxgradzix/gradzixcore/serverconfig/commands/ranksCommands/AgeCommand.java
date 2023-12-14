package me.xxgradzix.gradzixcore.serverconfig.commands.ranksCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AgeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        player.sendMessage(ChatColor.GRAY + "Przywileje rangi " + ChatColor.GREEN + "AGE" + ChatColor.GRAY + ":");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Unikatowy prefix przed nickiem " + "§8[§aAGE§8]");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Kit AGE co 24h (§e/kit§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do (§e/hat§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do (§e/feed§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do (§e/repair§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do (§e/repair all§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do (§e/ec§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do (§e/wb§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do §a§lSTREFY AGE");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Zwiększony limit wystawiania przedmiotó na rynku (§c15)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Zwiększony szansa na wypadanie klucza w strefie afk do §e50%");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + "§e50%" + ChatColor.WHITE + " szans na trafienie fragmentu perku po zabiciu gracza");
        player.sendMessage(ChatColor.DARK_GRAY + " ");
        player.sendMessage(ChatColor.GREEN + "Range zakupisz na stronie");
        player.sendMessage(ChatColor.DARK_GREEN + "https://ageplay.pl/");

        return true;
    }
}
