package me.xxgradzix.gradzixcore.generators;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;

public class Countdown implements Runnable {

    // Main class for bukkit scheduling
    private final JavaPlugin plugin;

    // Our scheduled task's assigned id, needed for canceling
    private Integer assignedTaskId;

    // Seconds and shiz
    private final int seconds;
    private int secondsLeft;

    private final Hologram hologram;
    // Actions to perform while counting down, before and after
//    private final Consumer<Countdown> everySecond;
//    private final Runnable beforeTimer;
//    private final Runnable afterTimer;

    // Construct a timer, you could create multiple so for example if
    // you do not want these "actions"
    public Countdown(final JavaPlugin plugin, final int seconds, Location location) {

        this.plugin = plugin;
        this.seconds = seconds;
        this.secondsLeft = seconds;

        String hologramName = "generator_" + location.getBlockX() + location.getBlockY() + location.getBlockZ();

        Hologram tempHol = DHAPI.getHologram(hologramName);
        DHAPI.removeHologram(hologramName);
        if(tempHol != null) tempHol.unregister();


        this.hologram = DHAPI.createHologram(hologramName, location, Arrays.asList(" "));
        hologram.register();

        hologram.showAll();


//        this.beforeTimer = beforeTimer;
//        this.afterTimer = afterTimer;
//        this.everySecond = everySecond;
    }

    public static String format(final long mills) {
        long seconds = mills / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        seconds %= 60;
        minutes %= 60;
        hours %= 24;

        StringBuilder formattedTime = new StringBuilder();
        if (days > 0) {
            formattedTime.append(days).append("d ");
        }
        if (hours > 0) {
            formattedTime.append(hours).append("h ");
        }
        if (minutes > 0) {
            formattedTime.append(minutes).append("m ");
        }
        if (seconds > 0 || (mills > 0 && formattedTime.length() == 0)) {
            formattedTime.append(seconds).append("s");
        }

        return formattedTime.toString().trim();
    }

    @Override
    public void run() {

        // Is the timer up?
        if (this.secondsLeft < 1) {

            this.scheduleTimer();

            // Cancel timer
            if (this.assignedTaskId != null) {
                Bukkit.getScheduler().cancelTask(this.assignedTaskId);

            }

            return;
        }

        DHAPI.setHologramLine(this.hologram, 0, ChatColor.GREEN+ "Ten generator odnowi sie za: " + format(this.secondsLeft * 1000));
        hologram.updateAll();

        // Are we just starting?
//        if (this.secondsLeft == this.seconds)
//            this.beforeTimer.run();
        // Do what's supposed to happen every second
//        this.everySecond.accept(this);

        --this.secondsLeft;
    }

    /**
     * Gets the total seconds this timer was set to run for
     *
     * @return Total seconds timer should run
     */
    public int getTotalSeconds() {
        return this.seconds;
    }

    /**
     * Gets the seconds left this timer should run
     *
     * @return Seconds left timer should run
     */
    public int getSecondsLeft() {
        return this.secondsLeft;
    }

    public Integer getTaskId() {
        return this.assignedTaskId;
    }

    public void killTask() {
        Bukkit.getScheduler().cancelTask(this.assignedTaskId);
    }

    /**
     * Schedules this instance to "run" every second
     */
    public void scheduleTimer() {
        // Initialize our assigned task's id, for later use so we can cancel
        this.assignedTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, this, 0L, 20L);
    }
}