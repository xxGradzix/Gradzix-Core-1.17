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

            // szklo

            ArrayList<Integer> czarne = new ArrayList<>();


            czarne.add(2);
            czarne.add(3);
            czarne.add(4);
            czarne.add(5);
            czarne.add(6);

            czarne.add(18);
            czarne.add(26);

            czarne.add(38);
            czarne.add(39);
            czarne.add(40);
            czarne.add(41);
            czarne.add(42);

            GuiItem blackGlass = new GuiItem(me.xxgradzix.gradzixcore.chatOptions.items.ItemManager.blackGlass);

            gui.setItem(czarne, blackGlass);

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


            // wymiana

            GuiItem wymianaButtonOff = ItemBuilder.from(ItemManager.autoWymianaOff).asGuiItem();

            GuiItem wymianaButtonOn = ItemBuilder.from(ItemManager.autoWymianaOn).asGuiItem();



            wymianaButtonOn.setAction((action) -> {

                DataManager.setAutoExchangeStatus(p, true);

                gui.updateItem(action.getSlot(), wymianaButtonOff);
            });

            wymianaButtonOff.setAction((action) -> {

                DataManager.setAutoExchangeStatus(p, false);

                gui.updateItem(action.getSlot(), wymianaButtonOn);
            });


            if(DataManager.getAutoExchangeStatus(p)) {
                gui.setItem(3, 4, wymianaButtonOff);
            } else {
                gui.setItem(3, 4, wymianaButtonOn);
            }


            // auto sprzedaz

            GuiItem sprzedazButtonOff = ItemBuilder.from(ItemManager.autoSprzedazOff).asGuiItem();

            GuiItem sprzedazButtonOn = ItemBuilder.from(ItemManager.autoSprzedazOn).asGuiItem();



            sprzedazButtonOn.setAction((action) -> {

                DataManager.setAutoSellStatus(p, true);

                gui.updateItem(action.getSlot(), sprzedazButtonOff);
            });

            sprzedazButtonOff.setAction((action) -> {

                DataManager.setAutoSellStatus(p, false);

                gui.updateItem(action.getSlot(), sprzedazButtonOn);
            });


            
            if(DataManager.getAutoSellStatus(p)) {
                gui.setItem(3, 6, sprzedazButtonOff);
            } else {
                gui.setItem(3, 6, sprzedazButtonOn);
            }


            gui.open(p);

        }

        return true;
    }
}
