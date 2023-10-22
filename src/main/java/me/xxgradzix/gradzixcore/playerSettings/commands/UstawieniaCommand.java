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
                    .rows(5)
                    .disableAllInteractions()
                    .create();

            // glass

            ArrayList<Integer> black = new ArrayList<>();

            black.add(2);
            black.add(3);
            black.add(4);
            black.add(5);
            black.add(6);

            black.add(18);
            black.add(26);

            black.add(38);
            black.add(39);
            black.add(40);
            black.add(41);
            black.add(42);

            GuiItem blackGlass = new GuiItem(me.xxgradzix.gradzixcore.chatOptions.items.ItemManager.blackGlass);

            gui.setItem(black, blackGlass);

            ArrayList<Integer> lime = new ArrayList<>();

            lime.add(0);
            lime.add(1);
            lime.add(7);
            lime.add(8);
            lime.add(9);
            lime.add(17);

            lime.add(27);
            lime.add(35);
            lime.add(36);
            lime.add(37);
            lime.add(43);
            lime.add(44);

            GuiItem limeGlass = new GuiItem(me.xxgradzix.gradzixcore.chatOptions.items.ItemManager.limeGlass);

            gui.setItem(lime, limeGlass);


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
                gui.setItem(3, 4, exchangeButtonOff);
            } else {
                gui.setItem(3, 4, exchangeButtonOn);
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
                gui.setItem(3, 6, sellButtonOff);
            } else {
                gui.setItem(3, 6, sellButtonOn);
            }
            gui.open(p);
        }
        return true;
    }
}
