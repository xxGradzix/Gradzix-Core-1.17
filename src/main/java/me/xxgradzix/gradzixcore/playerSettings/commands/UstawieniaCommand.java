package me.xxgradzix.gradzixcore.playerSettings.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.playerSettings.data.DataManager;
import me.xxgradzix.gradzixcore.playerSettings.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UstawieniaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            Gui gui = Gui.gui()
                    .title(Component.text(ChatColor.GREEN + ChatColor.BOLD.toString() + "USTAWIENIA " + ChatColor.GRAY + "(/ustawienia)"))
                    .rows(3)
                    .disableAllInteractions()
                    .create();

            // glass

            GuiItem blackGlass = new GuiItem(me.xxgradzix.gradzixcore.chatOptions.items.ItemManager.blackGlass);

            gui.getFiller().fillBetweenPoints(1, 2, 1, 8, blackGlass);
            gui.getFiller().fillBetweenPoints(3, 2, 3, 8, blackGlass);

            gui.setItem(2, 1, blackGlass);
            gui.setItem(2, 9, blackGlass);

            GuiItem limeGlass = new GuiItem(me.xxgradzix.gradzixcore.chatOptions.items.ItemManager.limeGlass);

            gui.setItem(1, 1, limeGlass);
            gui.setItem(1, 9, limeGlass);
            gui.setItem(3, 1, limeGlass);
            gui.setItem(3, 9, limeGlass);


            // exchange

            GuiItem exchangeButtonOff = ItemBuilder.from(ItemManager.autoExchangeOff).asGuiItem();
            GuiItem exchangeButtonOn = ItemBuilder.from(ItemManager.autoExchangeOn).asGuiItem();

            exchangeButtonOn.setAction((action) -> {
                DataManager.setAutoExchangeStatus(p, true);
                gui.updateItem(action.getSlot(), exchangeButtonOff);
            });

            exchangeButtonOff.setAction((action) -> {
                DataManager.setAutoExchangeStatus(p, false);
                gui.updateItem(action.getSlot(), exchangeButtonOn);
            });


            if(DataManager.getAutoExchangeStatus(p)) {
                gui.setItem(2, 4, exchangeButtonOff);
            } else {
                gui.setItem(2, 4, exchangeButtonOn);
            }


            // auto sell

            GuiItem sellButtonOff = ItemBuilder.from(ItemManager.autoSellOff).asGuiItem();

            GuiItem sellButtonOn = ItemBuilder.from(ItemManager.autoSellOn).asGuiItem();



            sellButtonOn.setAction((action) -> {
                DataManager.setAutoSellStatus(p, true);
                gui.updateItem(action.getSlot(), sellButtonOff);
            });

            sellButtonOff.setAction((action) -> {
                DataManager.setAutoSellStatus(p, false);
                gui.updateItem(action.getSlot(), sellButtonOn);
            });

            if(DataManager.getAutoSellStatus(p)) {
                gui.setItem(2, 6, sellButtonOff);
            } else {
                gui.setItem(2, 6, sellButtonOn);
            }
            gui.open(p);
        }
        return true;
    }
}
