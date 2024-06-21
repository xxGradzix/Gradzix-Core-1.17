package me.xxgradzix.gradzixcore.events.managers;

import me.xxgradzix.gradzixcore.GlobalMessagesManager;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class BossBarManager {

    public static void createBossBarCountDown(int seconds, String title, BarColor color) {

        BossBar countDownBossBar = Bukkit.createBossBar(title, color, org.bukkit.boss.BarStyle.SOLID);

        countDownBossBar.setVisible(true);
        for (Player player : Bukkit.getOnlinePlayers()) {
            countDownBossBar.addPlayer(player);
        }
        countDownBossBar.setProgress(1.0);

        BukkitTask task = new BukkitRunnable() {
            int rep = 0;

            @Override
            public void run() {
                rep++;

                if(rep > seconds) {
                    countDownBossBar.removeAll();
                    countDownBossBar.setVisible(false);
                    this.cancel();
                }
                double progress = 1 - (double) rep / seconds;

                countDownBossBar.setTitle(title + GlobalMessagesManager.secondsToTimeFormat(seconds - rep));
                countDownBossBar.setProgress(progress);

//                if (rep == seconds) {
//                    countDownBossBar.removeAll();
//                    countDownBossBar.setVisible(false);
//                    this.cancel();
//                }
            }
        }.runTaskTimer(Gradzix_Core.getInstance(), 0L, 20L);


    }

}
