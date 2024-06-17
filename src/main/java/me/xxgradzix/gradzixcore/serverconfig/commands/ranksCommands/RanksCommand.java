package me.xxgradzix.gradzixcore.serverconfig.commands.ranksCommands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.GlobalItemManager;
import me.xxgradzix.gradzixcore.serverconfig.ItemManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RanksCommand implements CommandExecutor {



    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            return true;
        }

        Player player = (Player) commandSender;

        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.DARK_AQUA + "Rangi"))
                .rows(3)
                .disableAllInteractions()
                .create();

        gui.getFiller().fillBorder(GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
        gui.setItem(1, 1, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
        gui.setItem(1, 9, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
        gui.setItem(3, 1, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
        gui.setItem(3, 9, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);

        GuiItem vipItem = new GuiItem(ItemManager.vipItem);
        GuiItem svipItem = new GuiItem(ItemManager.svipItem);
        GuiItem uniItem = new GuiItem(ItemManager.uniItem);

        gui.setItem(2, 3, vipItem);
        gui.setItem(2, 5, svipItem);
        gui.setItem(2, 7, uniItem);

        gui.open(player);

        return false;
    }
}
