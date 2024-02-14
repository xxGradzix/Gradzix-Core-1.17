package me.xxgradzix.gradzixcore.afkRegion.commands;

import me.xxgradzix.gradzixcore.afkRegion.data.database.entities.RewardsEntity;
import me.xxgradzix.gradzixcore.afkRegion.data.database.managers.RewardsEntityManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SetRewardCommand implements CommandExecutor, TabCompleter {

    private final RewardsEntityManager rewardsEntityManager;

    public SetRewardCommand(RewardsEntityManager rewardsEntityManager) {
        this.rewardsEntityManager = rewardsEntityManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Komenda tylko dla graczy");
            return false;
        }
        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage("Uzycie: /setafkreward <big/small>");
            return false;
        }

        ItemStack reward = player.getInventory().getItemInMainHand();

        if(reward == null || reward.getType().equals(Material.AIR)) {
            player.sendMessage("Musisz trzymac w rece jakis przedmiot");
            return false;
        }
        RewardsEntity rewardsEntity = rewardsEntityManager.getRewardsEntity();

        if(args[0].equalsIgnoreCase("big")) {
            rewardsEntity.setBigReward(reward);
            rewardsEntityManager.updateRewardsEntity(rewardsEntity);
            player.sendMessage("Ustawiono duza nagrode za bycie afk na: " + reward.getType());
            return true;
        }
        if(args[0].equalsIgnoreCase("small")) {
            rewardsEntity.setSmallReward(reward);
            rewardsEntityManager.updateRewardsEntity(rewardsEntity);
            player.sendMessage("Ustawiono podstawowa nagrode za bycie afk na: " + reward.getType());
            return true;
        }

        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("setafkreward")) {
            if(args.length == 1) {
                return Arrays.asList("big", "small");
            }
        }
        return Collections.emptyList();
    }
}
