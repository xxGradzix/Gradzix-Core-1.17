package me.xxgradzix.gradzixcore.magicPond.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.magicPond.data.DataManager;
import me.xxgradzix.gradzixcore.magicPond.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class MagicPondRewards implements CommandExecutor {

    private final DataManager dataManager;

    public MagicPondRewards(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;

        Player p = (Player) sender;

        showRewards(p);
        return false;
    }
    private void showRewards(Player p) {
        Gui rewardsGui = Gui.gui()
                .title(Component.text(ChatColor.BLUE + "Nagrody z jeziorka"))
                .rows(1)
                .disableAllInteractions()
                .create();

        HashMap<ItemStack, Integer> magicPondRewards = dataManager.getMagicPondEntityRewards();

        for (ItemStack item : magicPondRewards.keySet()) {
            GuiItem guiItem = new GuiItem(ItemManager.createItemRewardButton(item, magicPondRewards.get(item)));
            rewardsGui.addItem(guiItem);
        }
        rewardsGui.open(p);

    }
}
