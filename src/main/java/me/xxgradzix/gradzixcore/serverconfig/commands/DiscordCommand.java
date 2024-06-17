package me.xxgradzix.gradzixcore.serverconfig.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.serverconfig.ItemManager;
import me.xxgradzix.gradzixcore.socialMediaRewards.data.database.DataManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof org.bukkit.entity.Player)) {
            return true;
        }

        Player player = (Player) commandSender;

        Gui gui = Gui.gui()
                .disableAllInteractions()
                .title(Component.text(ChatColor.DARK_AQUA + "Discord"))
                .create();



        GuiItem guiItem = new GuiItem(ItemManager.discordItem);

        guiItem.setAction(event -> {
            player.sendMessage(ChatColor.DARK_AQUA + "https://discord.gg/g2Gzu2e4");
            player.closeInventory();
        });

        gui.setItem(1, 5, guiItem);

        gui.open(player);


        return false;
    }
}
