package me.xxgradzix.gradzixcore.umiejetnosci;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.umiejetnosci.commands.GiveOdlamekCommand;
import me.xxgradzix.gradzixcore.umiejetnosci.commands.ModyfikatoryUmiejetnosciCommand;
import me.xxgradzix.gradzixcore.umiejetnosci.commands.ResetUmiejetnosci;
import me.xxgradzix.gradzixcore.umiejetnosci.commands.UmiejetnosciCommand;
import me.xxgradzix.gradzixcore.umiejetnosci.files.ModyfikatoryUmiejetnosciConfigFile;
import me.xxgradzix.gradzixcore.umiejetnosci.files.UmiejetnosciConfigFile;
import me.xxgradzix.gradzixcore.umiejetnosci.items.ItemManager;
import me.xxgradzix.gradzixcore.umiejetnosci.listeners.DamageListener;
import me.xxgradzix.gradzixcore.umiejetnosci.listeners.OnBlockBreak;
import me.xxgradzix.gradzixcore.umiejetnosci.listeners.PlayerKillRankingIncrease;

import java.util.ArrayList;

public class Umiejetnosci {

    private Gradzix_Core plugin;

    public Umiejetnosci(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {



        plugin.getCommand("umiejetnosci").setExecutor(new UmiejetnosciCommand());
        plugin.getCommand("modyfikatoryumiejetnosci").setExecutor(new ModyfikatoryUmiejetnosciCommand());
        plugin.getCommand("giveodlamek").setExecutor(new GiveOdlamekCommand());

        plugin.getCommand("resetumiejetnosci").setExecutor(new ResetUmiejetnosci());

        plugin.getServer().getPluginManager().registerEvents(new DamageListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new OnBlockBreak(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerKillRankingIncrease(), plugin);

        UmiejetnosciConfigFile.setup();
        UmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci", new ArrayList<String>());
        UmiejetnosciConfigFile.getCustomFile().options().copyDefaults(true);
        UmiejetnosciConfigFile.save();


        ModyfikatoryUmiejetnosciConfigFile.setup();

        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci.sila.1", 1.1);
        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci.sila.2", 1.2);
        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci.sila.3", 1.3);
        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci.sila.4", 1.4);


        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci.drop.1", 1.5);
        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci.drop.2", 2.0);
        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci.drop.3", 2.5);
        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci.drop.4", 3.0);

        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci.rank.1", 1.2);
        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci.rank.2", 1.4);
        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci.rank.3", 1.6);
        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().addDefault("umiejetnosci.rank.4", 1.8);

        ModyfikatoryUmiejetnosciConfigFile.getCustomFile().options().copyDefaults(true);
        ModyfikatoryUmiejetnosciConfigFile.save();

        ItemManager.init();
    }

}
