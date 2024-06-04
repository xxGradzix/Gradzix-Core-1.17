package me.xxgradzix.gradzixcore.autodropsell.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.PaginatedGui;
import dev.triumphteam.gui.guis.ScrollingGui;
import me.xxgradzix.gradzixcore.GlobalItemManager;
import me.xxgradzix.gradzixcore.GlobalMessagesManager;
import me.xxgradzix.gradzixcore.autodropsell.items.ItemManager;
import me.xxgradzix.gradzixcore.playerSettings.data.database.entities.AutoSellEntity;
import me.xxgradzix.gradzixcore.playerSettings.data.database.managers.AutoSellEntityManager;
import net.kyori.adventure.text.Component;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class MinerCommand implements CommandExecutor {

    public static AutoSellEntityManager autoSellEntityManager;

    public MinerCommand(AutoSellEntityManager autoSellEntityManager) {
        this.autoSellEntityManager = autoSellEntityManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalMessagesManager.PLAYER_ONLY);
            return true;
        }

        PaginatedGui gui = Gui.paginated()
                .title(Component.text(ChatColor.YELLOW + "" + ChatColor.BOLD + "Górnik " + ChatColor.RESET + ChatColor.GRAY + "(/gornik)"))
                .rows(3)
                .disableAllInteractions()
                .create();

        gui.setItem(3, 1, ItemBuilder.from(GlobalItemManager.PREVIOUS_PAGE_ITEM).asGuiItem(event -> gui.previous()));
        gui.setItem(3, 9, ItemBuilder.from(GlobalItemManager.NEXT_PAGE_ITEM).asGuiItem(event -> gui.next()));

        gui.setItem(1, 1, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
        gui.setItem(1, 9, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);

        gui.setItem(2, 1, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
        gui.setItem(2, 9, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);

        gui.getFiller().fillBetweenPoints(1, 2, 1, 8, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
        gui.getFiller().fillBetweenPoints(3, 2, 3, 8, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);

        AutoSellEntity autoSellEntity = autoSellEntityManager.getAutoSellEntity();

        HashMap<Material, Double> itemsToSell = autoSellEntity.getItemsToSell();

        itemsToSell.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .forEach(entry -> {
                    gui.addItem(ItemManager.createAutoSellItem(entry.getKey(), entry.getValue()));
                });

        gui.open((Player) sender);

        return true;
    }
}
