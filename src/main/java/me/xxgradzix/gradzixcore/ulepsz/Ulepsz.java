package me.xxgradzix.gradzixcore.ulepsz;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.ulepsz.commands.UlepszCommand;
import me.xxgradzix.gradzixcore.ulepsz.commands.UlepszConfigCommand;
import me.xxgradzix.gradzixcore.ulepsz.files.UlepszConfigFile;
import me.xxgradzix.gradzixcore.ulepsz.gui.UlepszGuiClick;
import me.xxgradzix.gradzixcore.ulepsz.gui.UlepszGuiClose;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Ulepsz {


    private Gradzix_Core plugin;

    public Ulepsz(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {




        plugin.getCommand("ulepsz").setExecutor(new UlepszCommand());
        plugin.getCommand("ulepszconfig").setExecutor(new UlepszConfigCommand());


        plugin.getServer().getPluginManager().registerEvents(new UlepszGuiClick(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new UlepszGuiClose(), plugin);

        UlepszConfigFile.setup();

        int slot0 = 0;
        ItemStack nextItem = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack currentItem = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack itemRequired = new ItemStack(Material.DIAMOND, 64);



//        UlepszConfigFile.getCustomFile().addDefault("items." + slot0 + ".currentItem", currentItem);
//        UlepszConfigFile.getCustomFile().addDefault("items." + slot0 + ".nextItem", nextItem);
//        UlepszConfigFile.getCustomFile().addDefault("items." + slot0 + ".itemRequired", itemRequired);
//
//        UlepszConfigFile.getCustomFile().addDefault("items." + slot1 + ".currentItem", currentItem2);
//        UlepszConfigFile.getCustomFile().addDefault("items." + slot1 + ".nextItem", nextItem2);
//        UlepszConfigFile.getCustomFile().addDefault("items." + slot1 + ".itemRequired", itemRequired2);



        UlepszConfigFile.getCustomFile().options().copyDefaults(true);
        UlepszConfigFile.save();

    }

}
