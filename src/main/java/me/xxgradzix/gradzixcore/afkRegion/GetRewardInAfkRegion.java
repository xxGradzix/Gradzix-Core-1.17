package me.xxgradzix.gradzixcore.afkRegion;

import me.xxgradzix.gradzixcore.GlobalMessagesManager;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.globalStatic.EconomyManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class GetRewardInAfkRegion {

    private static final JavaPlugin plugin = Gradzix_Core.getInstance();
    private static final BukkitScheduler scheduler = Bukkit.getScheduler();

//    private static HashMap<Player, BukkitTask> playersInAfkRegion = new HashMap();

    private static Set<Player> playersInAfkRegion = new HashSet<>();

//    private static HashMap<Player, BukkitTask> playersInAfkRegionBigReward = new HashMap();

    public static String getBigRewardTitle(int secondsLeft) {
        return "§6⌚ §8| §f§lKlucz za §e§l" + GlobalMessagesManager.secondsToTimeFormat(secondsLeft);
    }
    public static String getSmallRewardTitle(int secondsLeft) {
        return "§3⌚ §8| §f§lPodstawowa nagroda §3" + AfkRegion.getSmallRewardMoney() + "$§f§l za §b" + GlobalMessagesManager.secondsToTimeFormat(secondsLeft);
    }


//    public static void startCounterForPlayer(Player player) {
//        BossBar smallRewardBossBar = Bukkit.createBossBar(getSmallRewardTitle((int) Gradzix_Core.SMALL_AFK_REWARD_DELAY_SECONDS), BarColor.BLUE, org.bukkit.boss.BarStyle.SOLID);
//        smallRewardBossBar.setProgress(1.0);
//        cancelPlayerTask(player);
//
//        smallRewardBossBar.addPlayer(player);
//
//        BukkitTask task = new BukkitRunnable() {
//            int secs = 0;
//
//            @Override
//            public void run() {
//                secs++;
//
//                int smallRewardTimeLeft = (int) Gradzix_Core.SMALL_AFK_REWARD_DELAY_SECONDS - (secs%((int) Gradzix_Core.SMALL_AFK_REWARD_DELAY_SECONDS));
//                smallRewardBossBar.setTitle(getSmallRewardTitle(smallRewardTimeLeft));
//                smallRewardBossBar.setProgress((double) smallRewardTimeLeft / Gradzix_Core.SMALL_AFK_REWARD_DELAY_SECONDS);
//
//                if (secs%Gradzix_Core.SMALL_AFK_REWARD_DELAY_SECONDS == 0) {
//                    if(!AfkRegion.isPlayerInAfkRegion(player)) {
//                        return;
//                    }
//                    drawSmallReward(player);
//                    if(AfkRegion.isPlayerInAfkRegion(player)) {
//                        playersInAfkRegion.remove(player);
//                        startCounterForPlayer(player);
//                    }
//                    this.cancel();
//                }
//                if (secs%Gradzix_Core.BIG_AFK_REWARD_DELAY_SECONDS == 0) {
//                    if(!AfkRegion.isPlayerInAfkRegion(player)) {
//                        this.cancel();
//                        player.sendMessage("Nie jestes juz w afk regionie");
//                        return;
//                    }
//                    drawBigReward(player);
//                    if(AfkRegion.isPlayerInAfkRegion(player)) {
//                        playersInAfkRegion.remove(player);
//                        startCounterForPlayer(player);
//                    }
//                    this.cancel();
//                }
//            }
//        }.runTaskTimer(Gradzix_Core.getInstance(), 0L, 20L);
//
//        playersInAfkRegion.put(player, task);
//
//    }

    private static void addPlayerToBossBar(Player player) {
        smallRewardBossBar.addPlayer(player);
        bigRewardBossBar.addPlayer(player);
    }
    private static void removePlayerFromBossBar(Player player) {
        smallRewardBossBar.removePlayer(player);
        bigRewardBossBar.removePlayer(player);
    }


    public static void playerEnterAfkRegion(Player player) {
        playersInAfkRegion.add(player);
        addPlayerToBossBar(player);
    }

    private static BossBar smallRewardBossBar = Bukkit.createBossBar(getSmallRewardTitle((int) Gradzix_Core.SMALL_AFK_REWARD_DELAY_SECONDS), BarColor.BLUE, org.bukkit.boss.BarStyle.SOLID);
    private static BossBar bigRewardBossBar = Bukkit.createBossBar(getBigRewardTitle((int) Gradzix_Core.SMALL_AFK_REWARD_DELAY_SECONDS), BarColor.BLUE, org.bukkit.boss.BarStyle.SOLID);

    public static void playerLeaveAfkRegion(Player player) {
        playersInAfkRegion.remove(player);
        removePlayerFromBossBar(player);
    }

    public static void startCounterForAllPlayers() {

         smallRewardBossBar.setProgress(1.0);

        smallRewardBossBar.setProgress(1.0);


        BukkitTask task = new BukkitRunnable() {
            int secs = 0;

            @Override
            public void run() {
                secs++;

                int smallRewardTimeLeft = (int) Gradzix_Core.SMALL_AFK_REWARD_DELAY_SECONDS - (secs%((int) Gradzix_Core.SMALL_AFK_REWARD_DELAY_SECONDS));
                smallRewardBossBar.setTitle(getSmallRewardTitle(smallRewardTimeLeft));
                smallRewardBossBar.setProgress((double) smallRewardTimeLeft / Gradzix_Core.SMALL_AFK_REWARD_DELAY_SECONDS);

                int bigRewardTimeLeft = (int) Gradzix_Core.BIG_AFK_REWARD_DELAY_SECONDS - (secs%((int) Gradzix_Core.BIG_AFK_REWARD_DELAY_SECONDS));
                bigRewardBossBar.setTitle(getBigRewardTitle(bigRewardTimeLeft));
                bigRewardBossBar.setProgress((double) bigRewardTimeLeft / Gradzix_Core.BIG_AFK_REWARD_DELAY_SECONDS);

                if (secs%Gradzix_Core.SMALL_AFK_REWARD_DELAY_SECONDS == 0) {
                    for (Player player : playersInAfkRegion) {
                        drawSmallReward(player);
                    }
                }
                if (secs%Gradzix_Core.BIG_AFK_REWARD_DELAY_SECONDS == 0) {
                    for (Player player : playersInAfkRegion) {
                        drawBigReward(player);
                    }
                }
            }
        }.runTaskTimer(Gradzix_Core.getInstance(), 0L, 20L);


    }

    private static void drawSmallReward(Player player) {
//        player.getInventory().addItem(AfkRegion.getSmallReward());
        EconomyManager.depositMoney(player, AfkRegion.getSmallRewardMoney());
    }
    private static void drawBigReward(Player player) {
        LuckPerms luckPerms = LuckPermsProvider.get();

        UserManager userManager = luckPerms.getUserManager();

        User user = userManager.getUser(player.getUniqueId());

        if(user == null) return;

        double rewardChance = getPlayerRewardChance(user);

        if(shouldDrop(rewardChance)) {
            player.getInventory().addItem(AfkRegion.getBigReward());
        }
    }
    private static double getPlayerRewardChance(User player) {
        switch (player.getPrimaryGroup()) {
            case "uni":
                return 0.5;
            case "svip":
                return 0.4;
            case "vip":
                return 0.3;
            default:
                return 0.2;
        }
    }

//    public static void cancelPlayerTask(Player player) {
//        if(playersInAfkRegion.containsKey(player)) {
//            BukkitTask taskId = playersInAfkRegion.get(player);
//            taskId.cancel();
//            playersInAfkRegion.remove(player);
//        }
//    }
    public static boolean shouldDrop(double chance) {
        Random random = new Random();
        double result = random.nextDouble();
        return result <= chance;
    }

}
