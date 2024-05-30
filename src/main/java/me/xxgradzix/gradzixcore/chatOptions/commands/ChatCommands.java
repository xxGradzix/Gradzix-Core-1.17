package me.xxgradzix.gradzixcore.chatOptions.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.GlobalItemManager;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.chatOptions.ChatOptions;
import me.xxgradzix.gradzixcore.chatOptions.data.database.entities.ChatOptionsEntity;
import me.xxgradzix.gradzixcore.chatOptions.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ChatCommands implements CommandExecutor {

    private static final JavaPlugin plugin = Gradzix_Core.getInstance();

    private static Set<UUID> deathMessageButtonCooldown = new HashSet<>();
    private static Set<UUID> shopMessageButtonCooldown = new HashSet<>();
    private static Set<UUID> chatMessageButtonCooldown = new HashSet<>();
    private static Set<UUID> scratchCardMessageButtonCooldown = new HashSet<>();

    private enum ButtonType {
        DEATH,
        SHOP,
        CHAT,
        SCRATCH_CARD
    }

    private static void addCooldown(UUID uplayerUuid, ButtonType buttonType) {

        switch (buttonType) {
            case DEATH:
                deathMessageButtonCooldown.add(uplayerUuid);
                plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                    deathMessageButtonCooldown.remove(uplayerUuid);
                }, 20 * Gradzix_Core.MESSAGE_BUTTON_COOLDOWN_SECONDS);
                break;
            case SHOP:
                shopMessageButtonCooldown.add(uplayerUuid);
                plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                    shopMessageButtonCooldown.remove(uplayerUuid);
                }, 20 * Gradzix_Core.MESSAGE_BUTTON_COOLDOWN_SECONDS);
                break;
            case CHAT:
                chatMessageButtonCooldown.add(uplayerUuid);
                plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                    chatMessageButtonCooldown.remove(uplayerUuid);
                }, 20 * Gradzix_Core.MESSAGE_BUTTON_COOLDOWN_SECONDS);
                break;
            case SCRATCH_CARD:
                scratchCardMessageButtonCooldown.add(uplayerUuid);
                plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                    scratchCardMessageButtonCooldown.remove(uplayerUuid);
                }, 20 * Gradzix_Core.MESSAGE_BUTTON_COOLDOWN_SECONDS);
                break;
        }
    }
    private static boolean isOnCooldown(UUID uplayerUuid, ButtonType buttonType) {
        switch (buttonType) {
            case DEATH:
                return deathMessageButtonCooldown.contains(uplayerUuid);
            case SHOP:
                return shopMessageButtonCooldown.contains(uplayerUuid);
            case CHAT:
                return chatMessageButtonCooldown.contains(uplayerUuid);
            case SCRATCH_CARD:
                return scratchCardMessageButtonCooldown.contains(uplayerUuid);
        }
        return false;
    }

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
                GuiItem blackGlass = new GuiItem(GlobalItemManager.FILLER_GLASS_PANE);
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
                GuiItem greenGlass = new GuiItem(GlobalItemManager.DARK_GLASS_PANE);
                gui.setItem(green, greenGlass);
                ArrayList<Integer> lime = new ArrayList<>();
                lime.add(9);
                lime.add(11);
                lime.add(13);
                lime.add(15);
                lime.add(17);
                GuiItem limeGlass = new GuiItem(GlobalItemManager.LIGHT_GLASS_PANE);
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
                    if(isOnCooldown(p.getUniqueId(), ButtonType.DEATH)) {
                        p.sendMessage(ChatColor.RED + "Poczekaj chwilę przed ponownym użyciem przycisku.");
                        return;
                    }
                    addCooldown(p.getUniqueId(), ButtonType.DEATH);
                    finalChatOptionsEntity.setShowDeathMessages(true);
                    p.sendMessage(ChatColor.RED + "Włączyłeś wiadomości o śmierci");
                    gui.updateItem(action.getSlot(), deathButtonOff);
                });

                deathButtonOff.setAction((action) -> {
                    if(isOnCooldown(p.getUniqueId(), ButtonType.DEATH)) {
                        p.sendMessage(ChatColor.RED + "Poczekaj chwilę przed ponownym użyciem przycisku.");
                        return;
                    }
                    addCooldown(p.getUniqueId(), ButtonType.DEATH);
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
                    if (isOnCooldown(p.getUniqueId(), ButtonType.CHAT)) {
                        p.sendMessage(ChatColor.RED + "Poczekaj chwilę przed ponownym użyciem przycisku.");
                        return;
                    }
                    addCooldown(p.getUniqueId(), ButtonType.CHAT);
                    finalChatOptionsEntity.setShowChatMessages(true);
                    p.sendMessage(ChatColor.RED + "Włączyłeś wiadomości z chatu");
                    gui.updateItem(action.getSlot(), chatButtonOff);
                });

                chatButtonOff.setAction((action) -> {
                    if (isOnCooldown(p.getUniqueId(), ButtonType.CHAT)) {
                        p.sendMessage(ChatColor.RED + "Poczekaj chwilę przed ponownym użyciem przycisku.");
                        return;
                    }
                    addCooldown(p.getUniqueId(), ButtonType.CHAT);
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
                    if (isOnCooldown(p.getUniqueId(), ButtonType.SHOP)) {
                        p.sendMessage(ChatColor.RED + "Poczekaj chwilę przed ponownym użyciem przycisku.");
                        return;
                    }
                    addCooldown(p.getUniqueId(), ButtonType.SHOP);
                    finalChatOptionsEntity.setShowShopMessages(true);
                    p.sendMessage(ChatColor.RED + "Funkcjonalność chwilowo niedostępna");
                    gui.updateItem(action.getSlot(), shopButtonOff);
                });

                shopButtonOff.setAction((action) -> {
                    if (isOnCooldown(p.getUniqueId(), ButtonType.SHOP)) {
                        p.sendMessage(ChatColor.RED + "Poczekaj chwilę przed ponownym użyciem przycisku.");
                        return;
                    }
                    addCooldown(p.getUniqueId(), ButtonType.SHOP);
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
