package me.xxgradzix.gradzixcore.playerSettings.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.chatOptions.commands.ChatCommands;
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
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UstawieniaCommand implements CommandExecutor {

    private static Gradzix_Core plugin = Gradzix_Core.getInstance();

    private static Set<UUID> sellButtonCooldown = new HashSet<>();
    private static Set<UUID> exchangeButtonCooldown = new HashSet<>();

    private enum ButtonType {
        SELL,
        EXCHANGE
    }

    private static void addCooldown(UUID uplayerUuid, ButtonType buttonType) {

        switch (buttonType) {
            case SELL:
                sellButtonCooldown.add(uplayerUuid);
                plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                    sellButtonCooldown.remove(uplayerUuid);
                }, 20 * Gradzix_Core.SETTINGS_BUTTON_COOLDOWN_SECONDS);
                break;
            case EXCHANGE:
                exchangeButtonCooldown.add(uplayerUuid);
                plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                    exchangeButtonCooldown.remove(uplayerUuid);
                }, 20 * Gradzix_Core.SETTINGS_BUTTON_COOLDOWN_SECONDS);
                break;
        }
    }
    private static boolean isOnCooldown(UUID uplayerUuid,ButtonType buttonType) {
        switch (buttonType) {
            case SELL:
                return sellButtonCooldown.contains(uplayerUuid);
            case EXCHANGE:
                return exchangeButtonCooldown.contains(uplayerUuid);
        }
        return false;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

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
                if(isOnCooldown(player.getUniqueId(),ButtonType.EXCHANGE)) {
                    player.sendMessage(ChatColor.RED + "Poczekaj chwilę przed ponownym użyciem przycisku.");
                    return;
                }
                addCooldown(player.getUniqueId(),ButtonType.EXCHANGE);
                DataManager.setAutoExchangeStatus(player, true);
                gui.updateItem(action.getSlot(), exchangeButtonOff);
            });
            exchangeButtonOff.setAction((action) -> {
                if(isOnCooldown(player.getUniqueId(),ButtonType.EXCHANGE)) {
                    player.sendMessage(ChatColor.RED + "Poczekaj chwilę przed ponownym użyciem przycisku.");
                    return;
                }
                addCooldown(player.getUniqueId(),ButtonType.EXCHANGE);
                DataManager.setAutoExchangeStatus(player, false);
                gui.updateItem(action.getSlot(), exchangeButtonOn);
            });
            if(DataManager.getAutoExchangeStatus(player)) {
                gui.setItem(2, 4, exchangeButtonOff);
            } else {
                gui.setItem(2, 4, exchangeButtonOn);
            }
            // auto sell
            GuiItem sellButtonOff = ItemBuilder.from(ItemManager.autoSellOff).asGuiItem();
            GuiItem sellButtonOn = ItemBuilder.from(ItemManager.autoSellOn).asGuiItem();
            sellButtonOn.setAction((action) -> {
                if(isOnCooldown(player.getUniqueId(),ButtonType.SELL)) {
                    player.sendMessage(ChatColor.RED + "Poczekaj chwilę przed ponownym użyciem przycisku.");
                    return;
                }
                addCooldown(player.getUniqueId(),ButtonType.SELL);
                DataManager.setAutoSellStatus(player, true);
                gui.updateItem(action.getSlot(), sellButtonOff);
            });
            sellButtonOff.setAction((action) -> {
                if(isOnCooldown(player.getUniqueId(),ButtonType.SELL)) {
                    player.sendMessage(ChatColor.RED + "Poczekaj chwilę przed ponownym użyciem przycisku.");
                    return;
                }
                addCooldown(player.getUniqueId(),ButtonType.SELL);
                DataManager.setAutoSellStatus(player, false);
                gui.updateItem(action.getSlot(), sellButtonOn);
            });
            if(DataManager.getAutoSellStatus(player)) {
                gui.setItem(2, 6, sellButtonOff);
            } else {
                gui.setItem(2, 6, sellButtonOn);
            }
            gui.open(player);
        }
        return true;
    }
}
