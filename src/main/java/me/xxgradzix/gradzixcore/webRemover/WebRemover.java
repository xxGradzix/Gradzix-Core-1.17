package me.xxgradzix.gradzixcore.webRemover;

import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.serverconfig.commands.SetDamageCommand;
import me.xxgradzix.gradzixcore.serverconfig.listeners.protectionEnchantRework.DamageEvent;
import me.xxgradzix.gradzixcore.webRemover.commands.GiveWebRemover;
import me.xxgradzix.gradzixcore.webRemover.itemManager.ItemManager;
import me.xxgradzix.gradzixcore.webRemover.listeners.DeleteWebListener;

public class WebRemover {

    private final Gradzix_Core plugin;

    public WebRemover(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
    }

    public void onEnable() {

        ItemManager.init();

        plugin.getCommand("giveusuwacz").setExecutor(new GiveWebRemover());

        plugin.getServer().getPluginManager().registerEvents(new DeleteWebListener(), plugin);
    }


}
