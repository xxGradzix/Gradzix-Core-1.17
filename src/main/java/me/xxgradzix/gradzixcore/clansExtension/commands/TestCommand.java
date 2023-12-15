package me.xxgradzix.gradzixcore.clansExtension.commands;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.clansExtension.ClansExtension;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {
    private final FunnyGuilds funnyGuilds;
    private final WarManager warManager;
    private final Gradzix_Core plugin;
    public TestCommand(FunnyGuilds funnyGuilds, WarManager warManager, Gradzix_Core plugin) {
        this.funnyGuilds = funnyGuilds;
        this.warManager = warManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        warManager.startWars();

        Bukkit.broadcastMessage("zaczely sie wojny");
        ClansExtension.ARE_WARS_ACTIVE = true;
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            ClansExtension.ARE_WARS_ACTIVE = false;
            warManager.endWars();
            Bukkit.broadcastMessage("skonczyly sie wojny");

        }, 20 * 30);


        return true;

    }
}
