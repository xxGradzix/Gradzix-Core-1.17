package me.xxgradzix.gradzixcore.magicznafajerwerka;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.magicznafajerwerka.commands.FireworkCommand;
import me.xxgradzix.gradzixcore.magicznafajerwerka.items.ItemMenager;
import me.xxgradzix.gradzixcore.magicznafajerwerka.listeners.OnPlayerDamage;
import me.xxgradzix.gradzixcore.magicznafajerwerka.listeners.PlayerFireworkListener;

public final class Magicznafajerwerka {

    private Gradzix_Core plugin;

    public Magicznafajerwerka(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
        ItemMenager.init();
        plugin.getCommand("givefirework").setExecutor(new FireworkCommand());

        plugin.getServer().getPluginManager().registerEvents(new PlayerFireworkListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnPlayerDamage(), plugin);

    }

    public void onDisable() {
        // Plugin shutdown logic
    }
}
