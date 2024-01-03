package me.xxgradzix.gradzixcore.events;

import com.j256.ormlite.support.ConnectionSource;
import lombok.Getter;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.events.commands.StartEvent;
import me.xxgradzix.gradzixcore.events.items.ItemManager;
import me.xxgradzix.gradzixcore.events.listeners.bossEvent.BossDamageUpdateBossBar;
import me.xxgradzix.gradzixcore.events.listeners.bossEvent.GlowEffectListener;
import me.xxgradzix.gradzixcore.events.listeners.keyEvent.OnBlockBreak;
import me.xxgradzix.gradzixcore.events.managers.BossManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class Events {

    private Gradzix_Core plugin;

    public static void setIsGeneratorEventEnabled(boolean isGeneratorEventEnabled) {
        Events.isGeneratorEventEnabled = isGeneratorEventEnabled;
    }

    public static void setGeneratorEventMultiplier(double generatorEventMultiplier) {
        Events.generatorEventMultiplier = generatorEventMultiplier;
    }

    public static void setIsKeyEventEnabled(boolean isKeyEventEnabled) {
        Events.isKeyEventEnabled = isKeyEventEnabled;
    }

    public static void setKeyDropChance(double keyDropChance) {
        Events.keyDropChance = keyDropChance;
    }

    public static void setKeyRewardItem(ItemStack keyRewardItem) {
        Events.keyRewardItem = keyRewardItem;
    }
    public static void setKeyRewardItemAmount(int keyRewardItemAmount) {
        Events.keyRewardItemAmount = keyRewardItemAmount;
    }

    public static void setIsMagicPondEventEnabled(boolean isMagicPondEventEnabled) {
        Events.isMagicPondEventEnabled = isMagicPondEventEnabled;
    }

    public static void setIsBossSpawned(boolean isBossSpawned) {
        Events.isBossSpawned = isBossSpawned;
    }
    @Getter
    private static boolean isGeneratorEventEnabled;
    @Getter
    private static double generatorEventMultiplier;


    @Getter
    private static boolean isKeyEventEnabled;
    @Getter
    private static double keyDropChance;
    @Getter
    private static ItemStack keyRewardItem;
    @Getter
    private static int keyRewardItemAmount;


    @Getter
    private static boolean isMagicPondEventEnabled;

    @Getter
    private static boolean isBossSpawned;


    public Events(Gradzix_Core plugin, ConnectionSource connectionSource) {
        isGeneratorEventEnabled = false;
        generatorEventMultiplier = 2;

        isKeyEventEnabled = false;
        keyDropChance = 0.01;
        keyRewardItem = new ItemStack(Material.DIAMOND, 1);
        keyRewardItemAmount = 1;

        this.plugin = plugin;

    }

    public void onEnable() {

        ItemManager.init();

        plugin.getCommand("events").setExecutor(new StartEvent());

        plugin.getServer().getPluginManager().registerEvents(new OnBlockBreak(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BossDamageUpdateBossBar(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GlowEffectListener(), plugin);

    }
}
