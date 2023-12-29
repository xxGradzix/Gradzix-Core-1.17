package me.xxgradzix.gradzixcore.itemPickupPriorities;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.Getter;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.itemPickupPriorities.PriorytetyGui.PrioritiesGuiClick;
import me.xxgradzix.gradzixcore.itemPickupPriorities.PriorytetyGui.PrioritiesGuiClose;
import me.xxgradzix.gradzixcore.itemPickupPriorities.commands.SetPrioritiesCommand;
import me.xxgradzix.gradzixcore.itemPickupPriorities.data.database.entities.PickupPrioritiesEntity;
import me.xxgradzix.gradzixcore.itemPickupPriorities.data.database.managers.PickupPrioritiesEntityManager;
import me.xxgradzix.gradzixcore.itemPickupPriorities.listeners.GiveArmorBackEvent;
import me.xxgradzix.gradzixcore.itemPickupPriorities.listeners.GiveItemsBackWithPriorities;

import java.sql.SQLException;

public final class ItemPickupPriorities {

    private final Gradzix_Core plugin;

    private final ConnectionSource connectionSource;

    @Getter
    private static PickupPrioritiesEntityManager pickupPrioritiesEntityManager;

    public void configureDB() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, PickupPrioritiesEntity.class);
        pickupPrioritiesEntityManager = new PickupPrioritiesEntityManager(connectionSource);
    }

    public ItemPickupPriorities(Gradzix_Core plugin, ConnectionSource connectionSource) {
        this.plugin = plugin;
        this.connectionSource = connectionSource;
    }


    public void onEnable() {

        try {
            configureDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        plugin.getServer().getPluginManager().registerEvents(new PrioritiesGuiClick(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PrioritiesGuiClose(), plugin);
//        plugin.getServer().getPluginManager().registerEvents(new GiveArmorBackEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GiveItemsBackWithPriorities(), plugin);


        plugin.getCommand("setpriorities").setExecutor(new SetPrioritiesCommand());


    }

    public void onDisable() {
        // Plugin shutdown logic
    }
}
