package me.xxgradzix.gradzixcore.ustawienia.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.ustawienia.files.UstawieniaOpcjeConfigFile;
import me.xxgradzix.gradzixcore.ustawienia.items.ItemManager;
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
                    .title(Component.text(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Ustawienia " + ChatColor.GRAY + "(/ustawienia)"))
                    .rows(3)
                    .disableAllInteractions()
                    .create();

            // szklo

            ArrayList<Integer> czarne = new ArrayList<>();

            czarne.add(0);
            czarne.add(1);
            czarne.add(2);
            czarne.add(3);
            czarne.add(4);
            czarne.add(5);
            czarne.add(6);
            czarne.add(7);
            czarne.add(8);

            czarne.add(18);
            czarne.add(19);
            czarne.add(20);
            czarne.add(21);
            czarne.add(22);
            czarne.add(23);
            czarne.add(24);
            czarne.add(25);
            czarne.add(26);

            GuiItem blackGlass = new GuiItem(me.xxgradzix.gradzixcore.chatopcje.items.ItemManager.blackGlass);

            gui.setItem(czarne, blackGlass);


            ArrayList<Integer> zielone = new ArrayList<>();

            zielone.add(9);
            zielone.add(10);
            zielone.add(11);
            zielone.add(15);
            zielone.add(16);
            zielone.add(17);

            GuiItem greenGlass = new GuiItem(me.xxgradzix.gradzixcore.chatopcje.items.ItemManager.greenGlass);

            gui.setItem(zielone, greenGlass);

            ArrayList<Integer> lime = new ArrayList<>();

            lime.add(13);

            GuiItem limeGlass = new GuiItem(me.xxgradzix.gradzixcore.chatopcje.items.ItemManager.limeGlass);

            gui.setItem(lime, limeGlass);


            // wymiana

            GuiItem wymianaButtonOff = ItemBuilder.from(ItemManager.autoWymianaOff).asGuiItem();

            GuiItem wymianaButtonOn = ItemBuilder.from(ItemManager.autoWymianaOn).asGuiItem();



            wymianaButtonOn.setAction((action) -> {

                UstawieniaOpcjeConfigFile.setAutoWymianaStatus(p, true);

                gui.updateItem(action.getSlot(), wymianaButtonOff);
            });

            wymianaButtonOff.setAction((action) -> {

                UstawieniaOpcjeConfigFile.setAutoWymianaStatus(p, false);

                gui.updateItem(action.getSlot(), wymianaButtonOn);
            });


            if(UstawieniaOpcjeConfigFile.getAutoWymianaStatus(p)) {
                gui.setItem(2, 4, wymianaButtonOff);
            } else {
                gui.setItem(2, 4, wymianaButtonOn);
            }


            // auto sprzedaz

            GuiItem sprzedazButtonOff = ItemBuilder.from(ItemManager.autoSprzedazOff).asGuiItem();

            GuiItem sprzedazButtonOn = ItemBuilder.from(ItemManager.autoSprzedazOn).asGuiItem();



            sprzedazButtonOn.setAction((action) -> {

                UstawieniaOpcjeConfigFile.setAutoSprzedazStatus(p, true);

                gui.updateItem(action.getSlot(), sprzedazButtonOff);
            });

            sprzedazButtonOff.setAction((action) -> {

                UstawieniaOpcjeConfigFile.setAutoSprzedazStatus(p, false);

                gui.updateItem(action.getSlot(), sprzedazButtonOn);
            });


            if(UstawieniaOpcjeConfigFile.getAutoSprzedazStatus(p)) {
                gui.setItem(2, 6, sprzedazButtonOff);
            } else {
                gui.setItem(2, 6, sprzedazButtonOn);
            }


            gui.open(p);

        }

        return true;
    }
}
