package me.xxgradzix.gradzixcore.incognito.commands;


import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.chatOptions.commands.ChatCommands;
import me.xxgradzix.gradzixcore.incognito.manages.IncognitoModeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class IncognitoCommand implements CommandExecutor {

    private static final JavaPlugin plugin = Gradzix_Core.getInstance();

    private static Set<UUID> cooldown = new HashSet<>();


    private static void addCooldown(UUID uplayerUuid) {

        cooldown.add(uplayerUuid);
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> cooldown.remove(uplayerUuid), 20 * Gradzix_Core.MESSAGE_BUTTON_COOLDOWN_SECONDS);

    }
    private static boolean isOnCooldown(UUID uplayerUuid) {
        return cooldown.contains(uplayerUuid);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Komenda dostępna tylko dla graczy!");
            return true;
        }
        Player player = (Player) sender;

        if (isOnCooldown(player.getUniqueId())) {
            player.sendMessage("§cPoczekaj chwilę przed ponownym użyciem tej komendy!");
            return true;
        }
        addCooldown(player.getUniqueId());
//        IncognitoModeManager.toggleIncognitoMode(player);


        return true;
    }



}
