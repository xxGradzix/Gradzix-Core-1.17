package me.xxgradzix.gradzixcore.panel;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.panel.commands.PanelCommand;
import me.xxgradzix.gradzixcore.panel.files.PanelAdminConfigFile;
import me.xxgradzix.gradzixcore.panel.items.ItemManager;
import me.xxgradzix.gradzixcore.panel.listeners.ChatEvent;
import me.xxgradzix.gradzixcore.panel.listeners.KityBlock;
import me.xxgradzix.gradzixcore.panel.listeners.OsiagnieciaBlock;

public class Panel {

    private Gradzix_Core plugin;

    public Panel(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {


        ItemManager.init();

        plugin.getServer().getPluginManager().registerEvents(new ChatEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new KityBlock(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OsiagnieciaBlock(), plugin);
        plugin.getCommand("panel").setExecutor(new PanelCommand());

        PanelAdminConfigFile.setup();
        PanelAdminConfigFile.getCustomFile().addDefault("isChatEnabled", true);
        PanelAdminConfigFile.getCustomFile().addDefault("isZdrapkaEnabled", true);
        PanelAdminConfigFile.getCustomFile().addDefault("isKityEnabled", true);
        PanelAdminConfigFile.getCustomFile().addDefault("isOsiagnieciaEnabled", true);
        PanelAdminConfigFile.getCustomFile().options().copyDefaults(true);
        PanelAdminConfigFile.save();

    }

}
