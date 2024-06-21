package me.xxgradzix.gradzixcore.eventArea;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.dailyQuests.commands.SetDropChance;
import me.xxgradzix.gradzixcore.dailyQuests.commands.SetKillDropChance;
import me.xxgradzix.gradzixcore.eventArea.commands.GiveEventItems;
import me.xxgradzix.gradzixcore.eventArea.items.ItemManager;
import me.xxgradzix.gradzixcore.eventArea.listeners.EventAreaListener;

public class EventArea {

    private final Gradzix_Core plugin;

    public EventArea(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    private static double dropChance = 0.01;
    private static double killDropChance = 0.1;

    public static double getDropChance() {
        return dropChance;
    }
    public static double getKillDropChance() {
        return killDropChance;
    }
    public static void setDropChance(double dropChance) {
        EventArea.dropChance = dropChance;
    }
    public static void setKillDropChance(double killDropChance) {
        EventArea.killDropChance = killDropChance;
    }

    public void onEnable() {

        ItemManager.init();

        plugin.getCommand("geteventitems").setExecutor(new GiveEventItems());
        plugin.getCommand("setdropchance").setExecutor(new SetDropChance());
        plugin.getCommand("setkilldropchance").setExecutor(new SetKillDropChance());

        plugin.getServer().getPluginManager().registerEvents(new EventAreaListener(), plugin);

    }

}
