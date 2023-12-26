package me.xxgradzix.gradzixcore.shulker;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.shulker.listeners.OpenShulkerListener;

public class ShulkerRework {

    private final Gradzix_Core plugin;

    public ShulkerRework(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
        plugin.getServer().getPluginManager().registerEvents(new OpenShulkerListener(plugin), plugin);
    }
}
