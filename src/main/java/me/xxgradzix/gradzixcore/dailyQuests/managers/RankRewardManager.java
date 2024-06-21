package me.xxgradzix.gradzixcore.dailyQuests.managers;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RankRewardManager {

    public static void getRewardAccordingToRank(Player player) {

        if(LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()) != null) {
            User user = LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), getRewardCommandAccordingToRank(user));
        }

    }

    private static String getRewardCommandAccordingToRank(User player) {
        switch (player.getPrimaryGroup()) {
            case "uni":
                return "excellentcrates key give " + player.getUsername() + " magiczna 4";
            case "svip":
                return "excellentcrates key give " + player.getUsername() + " magiczna 3";
            case "vip":
                return "excellentcrates key give " + player.getUsername() + " magiczna 2";
            default:
                return "excellentcrates key give " + player.getUsername() + " magiczna 1";
        }
    }

}