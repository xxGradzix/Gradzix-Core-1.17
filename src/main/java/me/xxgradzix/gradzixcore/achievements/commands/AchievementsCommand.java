package me.xxgradzix.gradzixcore.achievements.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.GlobalItemManager;
import me.xxgradzix.gradzixcore.achievements.AchievementType;
import me.xxgradzix.gradzixcore.achievements.database.DataManager;
import me.xxgradzix.gradzixcore.achievements.managers.AchievementManager;
import me.xxgradzix.gradzixcore.achievements.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AchievementsCommand implements CommandExecutor {

    public AchievementsCommand(AchievementManager achievementManager, DataManager dataManager) {
        this.achievementManager = achievementManager;
        this.dataManager = dataManager;
    }

    private AchievementManager achievementManager;
    private DataManager dataManager;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("Komenda dostępna tylko dla graczy!");
            return true;
        }

        Player player = (Player) commandSender;

        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Osiągnięcia"))
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

        GuiItem blockBreakQuestItem = new GuiItem(
                ItemManager.createProgressQuestItem(AchievementType.BLOCKS_BROKEN, achievementManager.getProgress(player, AchievementType.BLOCKS_BROKEN), dataManager.canClaimAchievement(player, AchievementType.BLOCKS_BROKEN))
        );
        blockBreakQuestItem.setAction((inventoryClickEvent -> {
            if(dataManager.canClaimAchievement(player, AchievementType.BLOCKS_BROKEN)) {
                dataManager.setAchievementClaimed(player, AchievementType.BLOCKS_BROKEN);
                player.sendMessage(ChatColor.GREEN + "Odebrano nagrodę za misję!");
            } else if(dataManager.isAchievementClaimed(player, AchievementType.BLOCKS_BROKEN)) {
                player.sendMessage(ChatColor.RED + "Nagroda za misje została już odebrana!");
            } else {
                player.sendMessage(ChatColor.RED + "Misja nie została ukończona!");
            }
        }));

        GuiItem blockPlaceQuestItem = new GuiItem(                ItemManager.createProgressQuestItem(AchievementType.BLOCKS_PLACED, achievementManager.getProgress(player, AchievementType.BLOCKS_PLACED), dataManager.canClaimAchievement(player, AchievementType.BLOCKS_PLACED))
        );
        blockPlaceQuestItem.setAction((inventoryClickEvent -> {
            if(dataManager.canClaimAchievement(player, AchievementType.BLOCKS_PLACED)) {
                dataManager.setAchievementClaimed(player, AchievementType.BLOCKS_PLACED);
                player.sendMessage(ChatColor.GREEN + "Odebrano nagrodę za misję!");
            } else if(dataManager.isAchievementClaimed(player, AchievementType.BLOCKS_PLACED)) {
                player.sendMessage(ChatColor.RED + "Nagroda za misje została już odebrana!");
            } else {
                player.sendMessage(ChatColor.RED + "Misja nie została ukończona!");
            }
        }));

        GuiItem totemOfUndyingQuestItem = new GuiItem(ItemManager.createProgressQuestItem(AchievementType.TOTEM_OF_UNDYING, achievementManager.getProgress(player, AchievementType.TOTEM_OF_UNDYING), dataManager.canClaimAchievement(player, AchievementType.TOTEM_OF_UNDYING)));
        totemOfUndyingQuestItem.setAction((inventoryClickEvent -> {
            if(dataManager.canClaimAchievement(player, AchievementType.TOTEM_OF_UNDYING)) {
                dataManager.setAchievementClaimed(player, AchievementType.TOTEM_OF_UNDYING);
                player.sendMessage(ChatColor.GREEN + "Odebrano nagrodę za misję!");
            } else if(dataManager.isAchievementClaimed(player, AchievementType.TOTEM_OF_UNDYING)) {
                player.sendMessage(ChatColor.RED + "Nagroda za misje została już odebrana!");
            } else {
                player.sendMessage(ChatColor.RED + "Misja nie została ukończona!");
            }
        }));

        GuiItem playerKillsQuestItem = new GuiItem(ItemManager.createProgressQuestItem(AchievementType.PLAYERS_KILLED, achievementManager.getProgress(player, AchievementType.PLAYERS_KILLED), dataManager.canClaimAchievement(player, AchievementType.PLAYERS_KILLED)));
        playerKillsQuestItem.setAction((inventoryClickEvent -> {
            if(dataManager.canClaimAchievement(player, AchievementType.PLAYERS_KILLED)) {
                dataManager.setAchievementClaimed(player, AchievementType.PLAYERS_KILLED);
                player.sendMessage(ChatColor.GREEN + "Odebrano nagrodę za misję!");
            } else if(dataManager.isAchievementClaimed(player, AchievementType.PLAYERS_KILLED)) {
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