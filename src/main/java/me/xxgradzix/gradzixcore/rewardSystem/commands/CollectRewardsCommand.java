package me.xxgradzix.gradzixcore.rewardSystem.commands;


import me.xxgradzix.gradzixcore.rewardSystem.database.entities.PlayerRewardsEntity;
import me.xxgradzix.gradzixcore.rewardSystem.database.managers.PlayerRewardsEntityManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CollectRewardsCommand implements CommandExecutor {

    private final PlayerRewardsEntityManager playerRewardsEntityManager;

    public CollectRewardsCommand(PlayerRewardsEntityManager playerRewardsEntityManager) {
        this.playerRewardsEntityManager = playerRewardsEntityManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        PlayerRewardsEntity playerRewardsEntity = playerRewardsEntityManager.getPlayerRewardsEntityByMinecraftId(player.getUniqueId());

        if(playerRewardsEntity == null) {
            player.sendMessage("Nie masz zadnych nagród do odebrania");
            return false;
        }

        HashMap<String, Integer> rewards = playerRewardsEntity.getRewards();

        if(rewards == null || rewards.isEmpty()) {
            player.sendMessage("Nie masz zadnych nagród do odebrania");
            return false;
        }

        player.sendMessage("Twoje nagrody:");
        for(String reward : rewards.keySet()) {
            player.sendMessage(reward + ": " + rewards.get(reward));
        }

        return true;
    }
}
