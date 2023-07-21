package me.xxgradzix.gradzixcore.chatopcje.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.chatopcje.files.ChatOpcjeConfigFile;
import me.xxgradzix.gradzixcore.chatopcje.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChatCommands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (command.getName().equalsIgnoreCase("chatopcje")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                Gui gui = Gui.gui()
                        .title(Component.text(ChatColor.YELLOW + ChatColor.BOLD.toString() + "OPCJE " + ChatColor.GRAY + "(/chatopcje)"))
                        .rows(3)
                        .disableAllInteractions()
                        .create();


                // szklo

                ArrayList<Integer> czarne = new ArrayList<>();

                czarne.add(0);
                czarne.add(2);
                czarne.add(4);
                czarne.add(6);
                czarne.add(8);
                czarne.add(18);
                czarne.add(20);
                czarne.add(22);
                czarne.add(24);
                czarne.add(26);

                GuiItem blackGlass = new GuiItem(ItemManager.blackGlass);

                gui.setItem(czarne, blackGlass);


                ArrayList<Integer> zielone = new ArrayList<>();

                zielone.add(1);
                zielone.add(3);
                zielone.add(5);
                zielone.add(7);
                zielone.add(19);
                zielone.add(21);
                zielone.add(23);
                zielone.add(25);

                GuiItem greenGlass = new GuiItem(ItemManager.greenGlass);

                gui.setItem(zielone, greenGlass);

                ArrayList<Integer> lime = new ArrayList<>();

                lime.add(9);
                lime.add(11);
                lime.add(13);
                lime.add(15);
                lime.add(17);

                GuiItem limeGlass = new GuiItem(ItemManager.limeGlass);

                gui.setItem(lime, limeGlass);



                // smierc


                GuiItem deathButtonOff = ItemBuilder.from(ItemManager.deathButtonOff).asGuiItem();

                GuiItem deathButtonOn = ItemBuilder.from(ItemManager.deathButtonOn).asGuiItem();

                // TODO kolor itp wiadomosci

                deathButtonOn.setAction((action) -> {

                    ChatOpcjeConfigFile.setShowDeathMessage(p, false);

                    p.sendMessage(ChatColor.RED + "Włączyłeś wiadomości o śmierci");

                    gui.updateItem(action.getSlot(), deathButtonOff);
                });

                deathButtonOff.setAction((action) -> {

                    ChatOpcjeConfigFile.setShowDeathMessage(p, true);


                    p.sendMessage(ChatColor.RED + "Wyłączyłeś wiadomości o śmierci");

                    gui.updateItem(action.getSlot(), deathButtonOn);
                });

                if(ChatOpcjeConfigFile.getShowDeathMessageStatus(p)) {
                    gui.setItem(2, 2, deathButtonOn);
                } else {
                    gui.setItem(2, 2, deathButtonOff);
                }


                // zdrapka

                GuiItem zdrapkaButtonOff = ItemBuilder.from(ItemManager.zdrapkaButtonOff).asGuiItem();

                GuiItem zdrapkaButtonOn = ItemBuilder.from(ItemManager.zdrapkaButtonOn).asGuiItem();


                // TODO kolor itp wiadomosci

                zdrapkaButtonOn.setAction((action) -> {

                    ChatOpcjeConfigFile.setShowZdrapkaMessage(p, false);

                    p.sendMessage(ChatColor.RED + "Włączyłeś wiadomości o dropie ze zdrapki");

                    gui.updateItem(action.getSlot(), zdrapkaButtonOff);
                });

                zdrapkaButtonOff.setAction((action) -> {

                    ChatOpcjeConfigFile.setShowZdrapkaMessage(p, true);

                    p.sendMessage(ChatColor.RED + "Wyłączyłeś wiadomości o dropie ze zdrapki");

                    gui.updateItem(action.getSlot(), zdrapkaButtonOn);
                });


                if(ChatOpcjeConfigFile.getShowZdrapkaMessageStatus(p)) {
                    gui.setItem(2, 4, zdrapkaButtonOn);
                } else {
                    gui.setItem(2, 4, zdrapkaButtonOff);
                }

                // chat

                GuiItem chatButtonOff = ItemBuilder.from(ItemManager.chatButtonOff).asGuiItem();

                GuiItem chatButtonOn = ItemBuilder.from(ItemManager.chatButtonOn).asGuiItem();

                // TODO kolor itp wiadomosci

                chatButtonOn.setAction((action) -> {

                    ChatOpcjeConfigFile.setShowChatMessage(p, false);

                    p.sendMessage(ChatColor.RED + "Włączyłeś wiadomości z chatu");

                    gui.updateItem(action.getSlot(), chatButtonOff);
                });

                chatButtonOff.setAction((action) -> {

                    ChatOpcjeConfigFile.setShowChatMessage(p, true);

                    p.sendMessage(ChatColor.RED + "Wyłączyłeś wiadomości z chatu");

                    gui.updateItem(action.getSlot(), chatButtonOn);
                });


                if(ChatOpcjeConfigFile.getShowChatMessageStatus(p)) {
                    gui.setItem(2, 6, chatButtonOn);
                } else {
                    gui.setItem(2, 6, chatButtonOff);
                }

                // shop

                GuiItem shopButtonOff = ItemBuilder.from(ItemManager.shopButtonOff).asGuiItem();

                GuiItem shopButtonOn = ItemBuilder.from(ItemManager.shopButtonOn).asGuiItem();

                // TODO kolor itp wiadomosci

                shopButtonOn.setAction((action) -> {

                    ChatOpcjeConfigFile.setShowShopMessage(p, false);

                    p.sendMessage(ChatColor.RED + "Funkcjonalność chwilowo niedostępna");

                    gui.updateItem(action.getSlot(), shopButtonOff);
                });

                shopButtonOff.setAction((action) -> {

                    ChatOpcjeConfigFile.setShowShopMessage(p, true);

                    p.sendMessage(ChatColor.RED + "Funkcjonalność chwilowo niedostępna");

                    gui.updateItem(action.getSlot(), shopButtonOn);
                });


                if(ChatOpcjeConfigFile.getShowShopMessageStatus(p)) {
                    gui.setItem(2, 8, shopButtonOn);
                } else {
                    gui.setItem(2, 8, shopButtonOff);
                }


                gui.open(p);


            } else {
                sender.sendMessage(ChatColor.RED + "Ta komenda może być używana tylko przez graczy.");
            }
        }

        return true;
    }
}
