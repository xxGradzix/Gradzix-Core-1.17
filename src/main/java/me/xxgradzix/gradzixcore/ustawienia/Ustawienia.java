package me.xxgradzix.gradzixcore.ustawienia;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.umiejetnosci.listeners.OnJoin;
import me.xxgradzix.gradzixcore.ustawienia.commands.UstawieniaCommand;
import me.xxgradzix.gradzixcore.ustawienia.commands.sprzedaz.SprzedazCommand;
import me.xxgradzix.gradzixcore.ustawienia.commands.wymiana.WymianaUstawieniaCommand;
import me.xxgradzix.gradzixcore.ustawienia.files.UstawieniaOpcjeConfigFile;
import me.xxgradzix.gradzixcore.ustawienia.files.WymianaUstawieniaItemsConfigFile;
import me.xxgradzix.gradzixcore.ustawienia.gui.sprzedaz.SprzedazGuiClick;
import me.xxgradzix.gradzixcore.ustawienia.gui.sprzedaz.SprzedazGuiClose;
import me.xxgradzix.gradzixcore.ustawienia.gui.wymiana.WymianaGuiClick;
import me.xxgradzix.gradzixcore.ustawienia.gui.wymiana.WymianaGuiClose;
import me.xxgradzix.gradzixcore.ustawienia.items.ItemManager;
import me.xxgradzix.gradzixcore.ustawienia.listeners.BlockBreakSprzedaz;
import me.xxgradzix.gradzixcore.ustawienia.listeners.BlockBreakWymiana;

import java.util.ArrayList;
import java.util.List;

public class Ustawienia {

    private Gradzix_Core plugin;


    public Ustawienia(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
        List<String> defaultPlayers = new ArrayList<>();

        UstawieniaOpcjeConfigFile.setup();
        UstawieniaOpcjeConfigFile.getCustomFile().addDefault("players", defaultPlayers);
        UstawieniaOpcjeConfigFile.getCustomFile().options().copyDefaults(true);
        UstawieniaOpcjeConfigFile.save();


        WymianaUstawieniaItemsConfigFile.setup();
        WymianaUstawieniaItemsConfigFile.getCustomFile().options().copyDefaults(true);
        WymianaUstawieniaItemsConfigFile.save();

        ItemManager.init();

        plugin.getCommand("ustawienia").setExecutor(new UstawieniaCommand());
        plugin.getCommand("wymianaustawienia").setExecutor(new WymianaUstawieniaCommand());
        plugin.getCommand("sprzedazustawienia").setExecutor(new SprzedazCommand());

        plugin.getServer().getPluginManager().registerEvents(new BlockBreakWymiana(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BlockBreakSprzedaz(), plugin);

        plugin.getServer().getPluginManager().registerEvents(new WymianaGuiClose(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new WymianaGuiClick(), plugin);

        plugin.getServer().getPluginManager().registerEvents(new SprzedazGuiClose(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SprzedazGuiClick(), plugin);

        plugin.getServer().getPluginManager().registerEvents(new OnJoin(), plugin);



    }



}
