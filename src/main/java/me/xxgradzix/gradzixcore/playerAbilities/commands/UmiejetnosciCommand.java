package me.xxgradzix.gradzixcore.playerAbilities.commands;

import dev.triumphteam.gui.guis.Gui;
import me.xxgradzix.gradzixcore.playerAbilities.AbilitiesGuiManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UmiejetnosciCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            AbilitiesGuiManager.openAbilitiesGui(p);

        }
        return true;
    }
}