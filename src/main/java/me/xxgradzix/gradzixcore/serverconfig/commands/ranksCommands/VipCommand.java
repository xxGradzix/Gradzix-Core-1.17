package me.xxgradzix.gradzixcore.serverconfig.commands.ranksCommands;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VipCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        player.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Przywileje rangi " + ChatColor.YELLOW + "VIP" + ChatColor.GRAY + ":");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Unikatowy prefix " + "§e§lVIP");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Kit §e§lVIP§r§7 co 24h (§b§n/kit§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/hat§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/feed§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/repair§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/ec§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/wb§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§nSTREFY VIP§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Zwiększony limit wystawiania przedmiotó na rynku (§b§n5§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Zwiększony szansa na wypadanie klucza w strefie afk (§b§ndo 30%§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Większa szansa na trafienie fragmentu perku po zabiciu gracza (§b§n20%§r§7)");
        player.sendMessage(ChatColor.DARK_GRAY + " ");
        player.sendMessage(ChatColor.DARK_GRAY + "Range zakupisz na stronie");
        player.sendMessage(ChatColor.AQUA + "https://unimc.pl/");

        return true;
    }
}
