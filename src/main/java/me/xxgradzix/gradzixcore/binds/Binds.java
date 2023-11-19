package me.xxgradzix.gradzixcore.binds;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.binds.commands.PlayBindsCommand;
import me.xxgradzix.gradzixcore.binds.items.ItemManager;

public class Binds {


    private Gradzix_Core plugin;

    public Binds(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
        ItemManager.init();

        plugin.getCommand("binds").setExecutor(new PlayBindsCommand());

    }

}
