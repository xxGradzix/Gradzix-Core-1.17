package me.xxgradzix.gradzixcore.serverconfig.commands.ranksCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SVipCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        player.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Przywileje rangi " + ChatColor.GOLD + "SVIP" + ChatColor.GRAY + ":");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Unikatowy prefix " + "§6§lSVIP");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Kit §6§lSVIP§r§7 co 24h (§b§n/kit§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/hat§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/feed§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/repair§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/ec§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/wb§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§nSTREFY SVIP§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Zwiększony limit wystawiania przedmiotó na rynku (§b§n10§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Zwiększony szansa na wypadanie klucza w strefie afk (§b§ndo 40%§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Większa szansa na trafienie fragmentu perku po zabiciu gracza (§b§n50%§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " ");
        player.sendMessage(ChatColor.DARK_GRAY + "Range zakupisz na stronie");
        player.sendMessage(ChatColor.AQUA + "https://unimc.pl/");

        return true;
    }
}
