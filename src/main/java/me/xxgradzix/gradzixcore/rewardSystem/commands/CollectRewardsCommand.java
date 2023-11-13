package me.xxgradzix.gradzixcore.rewardSystem.commands;


import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
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

    private final PlayerRewardsEntityManager playerRewardsEntityManager;
    private final RewardManager rewardManager;

    public CollectRewardsCommand(PlayerRewardsEntityManager playerRewardsEntityManager, RewardManager rewardManager) {
        this.playerRewardsEntityManager = playerRewardsEntityManager;
        this.rewardManager = rewardManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        PlayerRewardsEntity playerRewardsEntity = playerRewardsEntityManager.getPlayerRewardsEntityByMinecraftId(player.getUniqueId());
        HashMap<String, Integer> rewards;

        if(playerRewardsEntity == null) {
            rewards = new HashMap<>();
        } else {
            rewards = playerRewardsEntity.getRewards();
        }

//        if(rewards == null) {
//
////            player.sendMessage("Nie masz żadnych nagród do odebrania");
//            rewards = new HashMap<>();
////            return false;
//        }

        player.sendMessage("Twoje nagrody:");
        for(String reward : rewards.keySet()) {
            player.sendMessage(reward + ": " + rewards.get(reward));
        }

        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.GREEN + "Twoje nagrody!"))
                .rows(5)
                .disableAllInteractions()
                .create();

        GuiItem vipReward = ItemBuilder.from(ItemManager.createRewardCollectButton(Material.COPPER_INGOT, ChatColor.YELLOW + "VIP", rewards.getOrDefault("vip", 0))).asGuiItem();
        GuiItem svipReward = ItemBuilder.from(ItemManager.createRewardCollectButton(Material.GOLD_INGOT, ChatColor.GOLD + "SVIP", rewards.getOrDefault("svip", 0))).asGuiItem();
        GuiItem ageReward = ItemBuilder.from(ItemManager.createRewardCollectButton(Material.EMERALD, ChatColor.GREEN + "AGE", rewards.getOrDefault("age", 0))).asGuiItem();
        GuiItem bogaczReward = ItemBuilder.from(ItemManager.createRewardCollectButton(Material.DIAMOND, ChatColor.BLUE + "Zestaw Bogacz", rewards.getOrDefault("bogacz", 0))).asGuiItem();
        GuiItem jaskiniowcaReward = ItemBuilder.from(ItemManager.createRewardCollectButton(Material.GOLD_INGOT, ChatColor.DARK_GREEN + "Zestaw Jaskiniowca", rewards.getOrDefault("jaskiniowca", 0))).asGuiItem();
//        GuiItem ageReward = ItemBuilder.from(ItemManager.createVipCollectButton(rewards.get("age"))).asGuiItem();
        // TODO keys

        gui.setItem(2, 3, vipReward);
        gui.setItem(2, 5, svipReward);
        gui.setItem(2, 7, ageReward);

        gui.setItem(4, 4, bogaczReward);
        gui.setItem(4, 6, jaskiniowcaReward);

        gui.open(player);

        vipReward.setAction((action) -> {

            if(action.isLeftClick()) {
                if(rewardManager.removeRewardToPlayer(player.getUniqueId(), PlayerRewardsEntity.Reward.vip, 1)) {

                    getServer().dispatchCommand(getConsoleSender(), "lp user " + player.getName() + " parent addtemp vip 30d");
                    Bukkit.broadcastMessage("§7Gracz §2" + player.getName() + " §7zakupił §eRanga VIP");
                    Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");

                } else {
                    player.sendMessage(ChatColor.GRAY + "Nie mozesz odebrac tej nagrody");
                }
                gui.close(player);
            }
        });
        svipReward.setAction((action) -> {
            if(action.isLeftClick()) {
                if(rewardManager.removeRewardToPlayer(player.getUniqueId(), PlayerRewardsEntity.Reward.svip, 1)) {

                    getServer().dispatchCommand(getConsoleSender(), "lp user " + player.getName() + " parent addtemp svip 30d");
                    Bukkit.broadcastMessage("§7Gracz §2" + player.getName() + " §7zakupił §6Ranga SVIP");
                    Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");

                } else {
                    player.sendMessage(ChatColor.GRAY + "Nie mozesz odebrac tej nagrody");
                }
                gui.close(player);
            }
        });
        ageReward.setAction((action) -> {
            if(action.isLeftClick()) {
                if(rewardManager.removeRewardToPlayer(player.getUniqueId(), PlayerRewardsEntity.Reward.age, 1)) {

                    getServer().dispatchCommand(getConsoleSender(), "lp user " + player.getName() + " parent addtemp age 30d");
                    Bukkit.broadcastMessage("§7Gracz §2" + player.getName() + " §7zakupił §9Ranga AGE");
                    Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");

                } else {
                    player.sendMessage(ChatColor.GRAY + "Nie mozesz odebrac tej nagrody");
                }
                gui.close(player);
            }
        });
        bogaczReward.setAction((action) -> {

            if(action.isLeftClick()) {
                if(rewardManager.removeRewardToPlayer(player.getUniqueId(), PlayerRewardsEntity.Reward.bogacz, 1)) {

                    getServer().dispatchCommand(getConsoleSender(), "lp user " + player.getName() + " parent addtemp age 30d");
                    Bukkit.broadcastMessage("§7Gracz §2" + player.getName() + " §7zakupił §4Zestaw Bogacz");
                    Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                    ItemStack itemToGive = new ItemStack(me.xxgradzix.gradzixcore.scratchCard.items.ItemManager.zdrapka);
                    itemToGive.setAmount(3);
                    player.getInventory().addItem(itemToGive);
                    getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " +
                            player.getName() +
                            " jaskiniowca 10");

                } else {
                    player.sendMessage(ChatColor.GRAY + "Nie mozesz odebrac tej nagrody");
                }
                gui.close(player);
            }
        });
        jaskiniowcaReward.setAction((action) -> {

            if(action.isLeftClick()) {

                if(rewardManager.removeRewardToPlayer(player.getUniqueId(), PlayerRewardsEntity.Reward.jaskiniowca, 1)) {

                    getServer().dispatchCommand(getConsoleSender(), "lp user " + player.getName() + " parent addtemp svip 30d");
                    Bukkit.broadcastMessage("§7Gracz §2" + player.getName() + " §7zakupił §5Zestaw Jaskiniowca");
                    Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                    player.getInventory().addItem(me.xxgradzix.gradzixcore.scratchCard.items.ItemManager.zdrapka);
                    getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " +
                            player.getName() +
                            " magiczna 32");

                } else {
                    player.sendMessage(ChatColor.GRAY + "Nie mozesz odebrac tej nagrody");
                }
                gui.close(player);
            }
        });

        return true;
    }
}
