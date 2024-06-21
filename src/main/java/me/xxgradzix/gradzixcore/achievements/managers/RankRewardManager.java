package me.xxgradzix.gradzixcore.achievements.managers;

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
                return "excellentcrates key give " + player.getUsername() + " unibox 8";
            case "svip":
                return "excellentcrates key give " + player.getUsername() + " unibox 6";
            case "vip":
                return "excellentcrates key give " + player.getUsername() + " unibox 4";
            default:
                return "excellentcrates key give " + player.getUsername() + " unibox 2";
        }
    }

}
