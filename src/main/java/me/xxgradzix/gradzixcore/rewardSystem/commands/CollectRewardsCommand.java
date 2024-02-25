package me.xxgradzix.gradzixcore.rewardSystem.commands;


import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.rewardSystem.RewardSystem;
import me.xxgradzix.gradzixcore.rewardSystem.database.entities.PlayerRewardsEntity;
import me.xxgradzix.gradzixcore.rewardSystem.database.managers.PlayerRewardsEntityManager;
import me.xxgradzix.gradzixcore.rewardSystem.items.ItemManager;
import me.xxgradzix.gradzixcore.rewardSystem.managers.RewardManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static org.bukkit.Bukkit.getConsoleSender;
import static org.bukkit.Bukkit.getServer;

public class CollectRewardsCommand implements CommandExecutor {

//    private final PlayerRewardsEntityManager playerRewardsEntityManager;
    private final RewardManager rewardManager = RewardSystem.rewardManager;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(!rewardManager.hasAnyRewards(player)) {
            player.sendMessage(ChatColor.GRAY + "Nie masz zadnych nagrod do odebrania");
            return true;
        }

        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.GREEN + "Twoje nagrody!"))
                .rows(5)
                .disableAllInteractions()
                .create();

        GuiItem limeGlass = ItemBuilder.from(Material.LIME_STAINED_GLASS_PANE).setName(" ").asGuiItem();
        gui.setItem(1, 1, limeGlass);
        gui.setItem(1, 9, limeGlass);
        gui.setItem(5, 1, limeGlass);
        gui.setItem(5, 9, limeGlass);

        GuiItem blackGlass = ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem();
        gui.getFiller().fillBetweenPoints(1, 2, 1, 8, blackGlass);
        gui.getFiller().fillBetweenPoints(5, 2, 5, 8, blackGlass);

        gui.getFiller().fillBetweenPoints(2, 1, 4, 1, blackGlass);
        gui.getFiller().fillBetweenPoints(2, 9, 4, 9, blackGlass);

        setUpdatableButtonOnSlot(player, gui, 3, 3, PlayerRewardsEntity.Reward.magic_key);
        setUpdatableButtonOnSlot(player, gui, 3, 4, PlayerRewardsEntity.Reward.caveman_key);
        setUpdatableButtonOnSlot(player, gui, 3, 5, PlayerRewardsEntity.Reward.scratchcard);

        gui.open(player);
        return true;
    }
    private void setUpdatableButtonOnSlot(Player player, Gui gui, int x, int y, PlayerRewardsEntity.Reward reward) {
        int rewardOfPlayer = rewardManager.getRewardOfPlayer(player.getUniqueId(), reward);
        if(rewardOfPlayer <= 0) {
            gui.removeItem(x, y);
            return;
        }
        GuiItem item = new GuiItem(ItemManager.createRewardCollectButton(reward, rewardOfPlayer));
        item.setAction((action) -> {
            if(action.isLeftClick()) {
                collectReward(player, reward);
                setUpdatableButtonOnSlot(player, gui, x, y, reward);
            }
        });
        gui.setItem(x, y, item);
        gui.update();
    }

    private void collectReward(Player player, PlayerRewardsEntity.Reward reward) {

        if(player.getInventory().firstEmpty() == -1) {
            player.sendMessage(ChatColor.GRAY + "Nie masz miejsca w ekwipunku na te nagrode");
            return;
        }

        int amount = rewardManager.getRewardOfPlayer(player.getUniqueId(), reward);
        if(amount <= 0) {
            player.sendMessage(ChatColor.GRAY + "Nie masz tej nagrody");
            return;
        }

        if(amount > 64) amount = 64;

        rewardManager.removeRewardFromPlayer(player.getUniqueId(), reward, amount);

        switch (reward) {
            case magic_key: {
                getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " +
                            player.getName() +
                            " magiczna " + amount);
            }
            break;
            case caveman_key: {
                getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " +
                        player.getName() +
                        " jaskiniowca " + amount);
            }
            break;
            case scratchcard: {
                for (int i = 0; i < amount; i++) {
                    player.getInventory().addItem(me.xxgradzix.gradzixcore.scratchCard.items.ItemManager.zdrapka);
                }
            }
            break;
        }


    }



}
