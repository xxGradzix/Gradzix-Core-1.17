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

        player.sendMessage(ChatColor.GRAY + "Przywileje rangi " + ChatColor.GOLD + "SVIP" + ChatColor.GRAY + ":");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Unikatowy prefix przed nickiem " + "§8[§6SVIP§8]");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Kit SVIP co 24h (§e/kit§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do (§e/hat§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do (§e/feed§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do (§e/repair§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do (§e/ec§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do (§e/wb§f)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Dostęp do §6§lSTREFY SVIP");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Zwiększony limit wystawiania przedmiotó na rynku (§c10)");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.WHITE + "Zwiększony szansa na wypadanie klucza w strefie afk do §e40%");
        player.sendMessage(ChatColor.DARK_GRAY + " - " + "§e50%" + ChatColor.WHITE + " szans na trafienie fragmentu perku po zabiciu gracza");
        player.sendMessage(ChatColor.DARK_GRAY + " ");
        player.sendMessage(ChatColor.AQUA + "Range zakupisz na stronie");
        player.sendMessage(ChatColor.DARK_AQUA + "https://unimc.pl/");

        return true;
    }
}
