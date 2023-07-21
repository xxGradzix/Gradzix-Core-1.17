package me.xxgradzix.gradzixcore.chatopcje;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.chatopcje.commands.ChatCommands;
import me.xxgradzix.gradzixcore.chatopcje.files.ChatOpcjeConfigFile;
import me.xxgradzix.gradzixcore.chatopcje.items.ItemManager;
import me.xxgradzix.gradzixcore.chatopcje.listeners.OnPlayerChat;
import me.xxgradzix.gradzixcore.chatopcje.listeners.PlayerDeathMessage;

import java.util.ArrayList;
import java.util.List;

public final class Chatopcje {

    private Gradzix_Core plugin;

    public Chatopcje(Gradzix_Core plugin) {
        this.plugin = plugin;
    }


    public void onEnable() {

        ItemManager.init();

        plugin.getCommand("chatopcje").setExecutor(new ChatCommands());

        plugin.getServer().getPluginManager().registerEvents(new PlayerDeathMessage(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnPlayerChat(), plugin);


        List<String> list = new ArrayList<>();

        ChatOpcjeConfigFile.setup();
        ChatOpcjeConfigFile.getCustomFile().addDefault("players", list);
        ChatOpcjeConfigFile.getCustomFile().options().copyDefaults(true);
        ChatOpcjeConfigFile.save();

    }

    public void onDisable() {
        // Plugin shutdown logic
    }
}
