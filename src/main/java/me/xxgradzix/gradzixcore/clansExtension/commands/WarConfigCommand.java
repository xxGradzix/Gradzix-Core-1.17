package me.xxgradzix.gradzixcore.clansExtension.commands;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.clansExtension.ClansExtension;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class WarConfigCommand implements CommandExecutor {
    private final FunnyGuilds funnyGuilds;
    private final WarManager warManager;
    private final Gradzix_Core plugin;
    public WarConfigCommand(FunnyGuilds funnyGuilds, WarManager warManager, Gradzix_Core plugin) {
        this.funnyGuilds = funnyGuilds;
        this.warManager = warManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        warManager.startWars();

        Bukkit.broadcastMessage(Messages.WARS_ARE_ACTIVE);
        ClansExtension.ARE_WARS_ACTIVE = true;

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            ClansExtension.ARE_WARS_ACTIVE = false;
            warManager.endWars();
            Bukkit.broadcastMessage(Messages.WARS_ENDED);

        }, 20 * 30);

        return true;

    }
}
