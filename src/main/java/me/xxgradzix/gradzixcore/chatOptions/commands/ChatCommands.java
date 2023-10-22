package me.xxgradzix.gradzixcore.chatOptions.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.chatOptions.ChatOptions;
import me.xxgradzix.gradzixcore.chatOptions.data.database.entities.ChatOptionsEntity;
import me.xxgradzix.gradzixcore.chatOptions.items.ItemManager;
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

                // glass

                ArrayList<Integer> black = new ArrayList<>();

                black.add(0);
                black.add(2);
                black.add(4);
                black.add(6);
                black.add(8);
                black.add(18);
                black.add(20);
                black.add(22);
                black.add(24);
                black.add(26);

                GuiItem blackGlass = new GuiItem(ItemManager.blackGlass);

                gui.setItem(black, blackGlass);

                ArrayList<Integer> green = new ArrayList<>();

                green.add(1);
                green.add(3);
                green.add(5);
                green.add(7);
                green.add(19);
                green.add(21);
                green.add(23);
                green.add(25);

                GuiItem greenGlass = new GuiItem(ItemManager.greenGlass);

                gui.setItem(green, greenGlass);

                ArrayList<Integer> lime = new ArrayList<>();

                lime.add(9);
                lime.add(11);
                lime.add(13);
                lime.add(15);
                lime.add(17);

                GuiItem limeGlass = new GuiItem(ItemManager.limeGlass);

                gui.setItem(lime, limeGlass);

                ChatOptionsEntity chatOptionsEntity = ChatOptions.getChatOptionsEntityManager().getChatOptionsEntityById(p.getUniqueId());

                if(chatOptionsEntity == null) {
                    chatOptionsEntity = new ChatOptionsEntity(p.getUniqueId(),
                            p.getName(),
                            true,
                            true,
                            true,
                            true);
                    ChatOptions.getChatOptionsEntityManager().createChatOptionsEntity(chatOptionsEntity);
                }

                ChatOptionsEntity finalChatOptionsEntity = chatOptionsEntity;

                // death

                GuiItem deathButtonOff = ItemBuilder.from(ItemManager.deathButtonOff).asGuiItem();

                GuiItem deathButtonOn = ItemBuilder.from(ItemManager.deathButtonOn).asGuiItem();

                deathButtonOn.setAction((action) -> {
                    finalChatOptionsEntity.setShowDeathMessages(true);
                    p.sendMessage(ChatColor.RED + "Włączyłeś wiadomości o śmierci");
                    gui.updateItem(action.getSlot(), deathButtonOff);
                });

                deathButtonOff.setAction((action) -> {
                    finalChatOptionsEntity.setShowDeathMessages(false);
                    p.sendMessage(ChatColor.RED + "Wyłączyłeś wiadomości o śmierci");
                    gui.updateItem(action.getSlot(), deathButtonOn);
                });

                if(chatOptionsEntity.isShowDeathMessages()) {
                    gui.setItem(2, 2, deathButtonOff);
                } else {
                    gui.setItem(2, 2, deathButtonOn);
                }

                // scratch card

                GuiItem scratchCardOff = ItemBuilder.from(ItemManager.scratchCardButtonOff).asGuiItem();

                GuiItem scratchCardOn = ItemBuilder.from(ItemManager.scratchCardButtonOn).asGuiItem();

                scratchCardOn.setAction((action) -> {
                    finalChatOptionsEntity.setShowScratchCardMessages(true);
                    p.sendMessage(ChatColor.RED + "Włączyłeś wiadomości o dropie ze zdrapki");
                    gui.updateItem(action.getSlot(), scratchCardOff);
                });

                scratchCardOff.setAction((action) -> {
                    finalChatOptionsEntity.setShowScratchCardMessages(false);
                    p.sendMessage(ChatColor.RED + "Wyłączyłeś wiadomości o dropie ze zdrapki");
                    gui.updateItem(action.getSlot(), scratchCardOn);
                });

                if(chatOptionsEntity.isShowScratchCardMessages()) {
                    gui.setItem(2, 4, scratchCardOff);
                } else {
                    gui.setItem(2, 4, scratchCardOn);
                }

                // chat

                GuiItem chatButtonOff = ItemBuilder.from(ItemManager.chatButtonOff).asGuiItem();

                GuiItem chatButtonOn = ItemBuilder.from(ItemManager.chatButtonOn).asGuiItem();

                chatButtonOn.setAction((action) -> {
                    finalChatOptionsEntity.setShowChatMessages(true);
                    p.sendMessage(ChatColor.RED + "Włączyłeś wiadomości z chatu");
                    gui.updateItem(action.getSlot(), chatButtonOff);
                });

                chatButtonOff.setAction((action) -> {
                    finalChatOptionsEntity.setShowChatMessages(false);
                    p.sendMessage(ChatColor.RED + "Wyłączyłeś wiadomości z chatu");
                    gui.updateItem(action.getSlot(), chatButtonOn);
                });

                if(chatOptionsEntity.isShowChatMessages()) {
                    gui.setItem(2, 6, chatButtonOff);
                } else {
                    gui.setItem(2, 6, chatButtonOn);
                }

                // shop

                GuiItem shopButtonOff = ItemBuilder.from(ItemManager.shopButtonOff).asGuiItem();

                GuiItem shopButtonOn = ItemBuilder.from(ItemManager.shopButtonOn).asGuiItem();

                shopButtonOn.setAction((action) -> {
                    finalChatOptionsEntity.setShowShopMessages(true);
                    p.sendMessage(ChatColor.RED + "Funkcjonalność chwilowo niedostępna");
                    gui.updateItem(action.getSlot(), shopButtonOff);
                });

                shopButtonOff.setAction((action) -> {
                    finalChatOptionsEntity.setShowShopMessages(false);
                    p.sendMessage(ChatColor.RED + "Funkcjonalność chwilowo niedostępna");
                    gui.updateItem(action.getSlot(), shopButtonOn);
                });

                if(chatOptionsEntity.isShowShopMessages()) {
                    gui.setItem(2, 8, shopButtonOff);
                } else {
                    gui.setItem(2, 8, shopButtonOn);
                }

                gui.open(p);

                gui.setCloseGuiAction((action) -> {
                    ChatOptions.getChatOptionsEntityManager().createOrUpdateChatOptionsEntity(finalChatOptionsEntity);
                });
            } else {
                sender.sendMessage(ChatColor.RED + "Ta komenda może być używana tylko przez graczy.");
            }
        }
        return true;
    }
}
