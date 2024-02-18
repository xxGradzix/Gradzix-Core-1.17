package me.xxgradzix.gradzixcore.afkRegion;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Random;


public class GetRewardInAfkRegion {

    private static final JavaPlugin plugin = Gradzix_Core.getInstance();
    private static final BukkitScheduler scheduler = Bukkit.getScheduler();

    private static HashMap<Player, Integer> playersInAfkRegion = new HashMap();


    public static void startCounterForPlayer(Player player) {

        cancelPlayerTask(player);

        int id = scheduler.runTaskLater(plugin, () -> {

            if(!AfkRegion.isPlayerInAfkRegion(player)) {
                return;
            }

            drawReward(player);

            if(AfkRegion.isPlayerInAfkRegion(player)) {
                playersInAfkRegion.remove(player);
                startCounterForPlayer(player);


            }
        }, 20L * Gradzix_Core.AFK_REWARD_DELAY_SECONDS).getTaskId();

        playersInAfkRegion.put(player, id);

    }

    private static void drawReward(Player player) {

        LuckPerms luckPerms = LuckPermsProvider.get();

        UserManager userManager = luckPerms.getUserManager();

        User user = userManager.getUser(player.getUniqueId());

        if(user == null) return;

        double rewardChance = getPlayerRewardChance(user);

        if(shouldDrop(rewardChance)) {
            player.getInventory().addItem(AfkRegion.getBigReward());
        }
        player.getInventory().addItem(AfkRegion.getSmallReward());
    }
    private static double getPlayerRewardChance(User player) {
        switch (player.getPrimaryGroup()) {
            case "age":
                return 0.5;
            case "svip":
                return 0.4;
            case "vip":
                return 0.3;
            default:
                return 0.2;
        }
    }

    public static void cancelPlayerTask(Player player) {
        if(playersInAfkRegion.containsKey(player)) {
            int taskId = playersInAfkRegion.get(player);
            scheduler.cancelTask(taskId);
            playersInAfkRegion.remove(player);
        }
    }
    public static boolean shouldDrop(double chance) {

        Random random = new Random();
        double result = random.nextDouble();

        return result <= chance;
    }

}
