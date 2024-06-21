package me.xxgradzix.gradzixcore.dailyQuests.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.GlobalItemManager;
import me.xxgradzix.gradzixcore.dailyQuests.QuestType;
import me.xxgradzix.gradzixcore.dailyQuests.items.ItemManager;
import me.xxgradzix.gradzixcore.dailyQuests.managers.QuestsManagers;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DailyQuestsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("Komenda dostępna tylko dla graczy!");
            return true;
        }

        Player player = (Player) commandSender;

        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Codzienne misje"))
                .rows(5)
                .disableAllInteractions()
                .create();
        gui.getFiller().fillBetweenPoints(1, 3, 1, 4, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
        gui.getFiller().fillBetweenPoints(1, 6, 1, 7, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
        gui.getFiller().fillBetweenPoints(5, 3, 5, 4, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
        gui.getFiller().fillBetweenPoints(5, 6, 5, 7, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);


        gui.setItem(3, 1, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
        gui.setItem(3, 9, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);

        gui.setItem(1, 2, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);
        gui.setItem(5, 2, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);

        gui.setItem(1, 8, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);
        gui.setItem(5, 8, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);

        gui.setItem(2, 1, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);
        gui.setItem(2, 9, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);

        gui.setItem(4, 1, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);
        gui.setItem(4, 9, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);

        gui.setItem(1, 1, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
        gui.setItem(5, 1, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);

        gui.setItem(1, 9, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
        gui.setItem(5, 9, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);

        GuiItem blockBreakQuestItem = new GuiItem(ItemManager.createProgressQuestItem(QuestType.BLOCK_BREAKED, QuestsManagers.getQuestProgress(player.getUniqueId(), QuestType.BLOCK_BREAKED), QuestsManagers.isClaimed(player.getUniqueId(), QuestType.BLOCK_BREAKED)));
        blockBreakQuestItem.setAction((inventoryClickEvent -> {
            if(QuestsManagers.canCollect(player.getUniqueId(), QuestType.BLOCK_BREAKED)) {
                QuestsManagers.setCollectStatus(player.getUniqueId(), QuestType.BLOCK_BREAKED);
                player.sendMessage(ChatColor.GREEN + "Odebrano nagrodę za misję!");
            } else if(QuestsManagers.isClaimed(player.getUniqueId(), QuestType.BLOCK_BREAKED)) {
                player.sendMessage(ChatColor.RED + "Nagroda za misje została już odebrana!");
            } else {
                player.sendMessage(ChatColor.RED + "Misja nie została ukończona!");
            }
        }));

        GuiItem blockPlaceQuestItem = new GuiItem(ItemManager.createProgressQuestItem(QuestType.BLOCK_PLACED, QuestsManagers.getQuestProgress(player.getUniqueId(), QuestType.BLOCK_PLACED), QuestsManagers.isClaimed(player.getUniqueId(), QuestType.BLOCK_PLACED)));
        blockPlaceQuestItem.setAction((inventoryClickEvent -> {
            if(QuestsManagers.canCollect(player.getUniqueId(), QuestType.BLOCK_PLACED)) {
                QuestsManagers.setCollectStatus(player.getUniqueId(), QuestType.BLOCK_PLACED);
                player.sendMessage(ChatColor.GREEN + "Odebrano nagrodę za misję!");
            } else if(QuestsManagers.isClaimed(player.getUniqueId(), QuestType.BLOCK_PLACED)) {
                player.sendMessage(ChatColor.RED + "Nagroda za misje została już odebrana!");
            } else {
                player.sendMessage(ChatColor.RED + "Misja nie została ukończona!");
            }
        }));

        GuiItem totemOfUndyingQuestItem = new GuiItem(ItemManager.createProgressQuestItem(QuestType.TOTEM_OF_UNDYING_USED, QuestsManagers.getQuestProgress(player.getUniqueId(), QuestType.TOTEM_OF_UNDYING_USED), QuestsManagers.isClaimed(player.getUniqueId(), QuestType.TOTEM_OF_UNDYING_USED)));
        totemOfUndyingQuestItem.setAction((inventoryClickEvent -> {
            if(QuestsManagers.canCollect(player.getUniqueId(), QuestType.TOTEM_OF_UNDYING_USED)) {
                QuestsManagers.setCollectStatus(player.getUniqueId(), QuestType.TOTEM_OF_UNDYING_USED);
                player.sendMessage(ChatColor.GREEN + "Odebrano nagrodę za misję!");
            } else if(QuestsManagers.isClaimed(player.getUniqueId(), QuestType.TOTEM_OF_UNDYING_USED)) {
                player.sendMessage(ChatColor.RED + "Nagroda za misje została już odebrana!");
            } else {
                player.sendMessage(ChatColor.RED + "Misja nie została ukończona!");
            }
        }));

        GuiItem playerKillsQuestItem = new GuiItem(ItemManager.createProgressQuestItem(QuestType.PLAYER_KILLS, QuestsManagers.getQuestProgress(player.getUniqueId(), QuestType.PLAYER_KILLS), QuestsManagers.isClaimed(player.getUniqueId(), QuestType.PLAYER_KILLS)));
        playerKillsQuestItem.setAction((inventoryClickEvent -> {
            if(QuestsManagers.canCollect(player.getUniqueId(), QuestType.PLAYER_KILLS)) {
                QuestsManagers.setCollectStatus(player.getUniqueId(), QuestType.PLAYER_KILLS);
                player.sendMessage(ChatColor.GREEN + "Odebrano nagrodę za misję!");
            } else if(QuestsManagers.isClaimed(player.getUniqueId(), QuestType.PLAYER_KILLS)) {
                player.sendMessage(ChatColor.RED + "Nagroda za misje została już odebrana!");
            } else {
                player.sendMessage(ChatColor.RED + "Misja nie została ukończona!");
            }
        }));

        gui.setItem(2, 2, blockBreakQuestItem);
        gui.setItem(2, 4, blockPlaceQuestItem);
        gui.setItem(2, 6, totemOfUndyingQuestItem);
        gui.setItem(2, 8, playerKillsQuestItem);

        gui.setDefaultClickAction(inventoryClickEvent -> gui.open(player));

        gui.open(player);


        return true;
    }

}
