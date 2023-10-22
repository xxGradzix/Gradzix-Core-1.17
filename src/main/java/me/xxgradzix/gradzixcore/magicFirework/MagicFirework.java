package me.xxgradzix.gradzixcore.magicFirework;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.magicFirework.commands.FireworkCommand;
import me.xxgradzix.gradzixcore.magicFirework.items.ItemManager;
import me.xxgradzix.gradzixcore.magicFirework.listeners.OnPlayerDamage;
import me.xxgradzix.gradzixcore.magicFirework.listeners.PlayerFireworkListener;

public final class MagicFirework {

    private final Gradzix_Core plugin;

    public MagicFirework(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
        ItemManager.init();
        plugin.getCommand("givefirework").setExecutor(new FireworkCommand());

        plugin.getServer().getPluginManager().registerEvents(new PlayerFireworkListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnPlayerDamage(), plugin);

    }

    public void onDisable() {

    }
}
