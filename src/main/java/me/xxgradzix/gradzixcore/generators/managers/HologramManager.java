package me.xxgradzix.gradzixcore.generators.managers;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.generators.Countdown;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class HologramManager {

    private static final JavaPlugin plugin = Gradzix_Core.getInstance();

    public static void addHologram(Location location, GeneratorEntity generator) {

        Countdown countdown = new Countdown(plugin, generator.getCoolDownSeconds(), location);

        countdown.scheduleTimer();

    }

}
