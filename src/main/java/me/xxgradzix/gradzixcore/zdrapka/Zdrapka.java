package me.xxgradzix.gradzixcore.zdrapka;


import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.zdrapka.commands.GiveZdrapkaCommand;
import me.xxgradzix.gradzixcore.zdrapka.commands.ZdrapkaCommand;
import me.xxgradzix.gradzixcore.zdrapka.files.ZdrapkaConfigFile;
import me.xxgradzix.gradzixcore.zdrapka.items.ItemManager;
import me.xxgradzix.gradzixcore.zdrapka.listeners.OnLeftClick;
import me.xxgradzix.gradzixcore.zdrapka.listeners.OnRightClick;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class Zdrapka {

    private Gradzix_Core plugin;

    public Zdrapka(Gradzix_Core plugin) {
        this.plugin = plugin;
    }


    public void onEnable() {

        ItemManager.init();

        plugin.getCommand("givezdrapka").setExecutor(new GiveZdrapkaCommand());
        plugin.getCommand("zdrapka").setExecutor(new ZdrapkaCommand());

        plugin.getServer().getPluginManager().registerEvents(new OnLeftClick(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnRightClick(), plugin);

        ZdrapkaConfigFile.setup();

        List<ItemStack> list = new ArrayList<ItemStack>();

        ZdrapkaConfigFile.getCustomFile().addDefault("items", list);

        ZdrapkaConfigFile.getCustomFile().options().copyDefaults(true);
        ZdrapkaConfigFile.save();
    }


    public void onDisable() {
        // Plugin shutdown logic
    }


}
