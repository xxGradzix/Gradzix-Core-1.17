package me.xxgradzix.gradzixcore.VPLNShop.listeners;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.VPLNShop.data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final DataManager dataManager;

    public JoinListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(Gradzix_Core.getInstance(), () -> {
            if(player != null) {
                player.sendMessage("§aOtrzymales nowe zamowienie VPLN! Sprawdz dostepne zamowienia w sklepie!");
            }
            dataManager.collectAllPendingOrders(player);
        }, 20L * 3);


    }

}
