package me.xxgradzix.gradzixcore.itemShop.listeners;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.itemShop.data.DataManager;
import me.xxgradzix.gradzixcore.itemShop.data.database.enums.ShopType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class OnPlayerJoinCreateBalanceEntity implements Listener {

    private final DataManager dataManager;
    private final BukkitScheduler scheduler = Bukkit.getScheduler();
    private final Gradzix_Core plugin;

    public OnPlayerJoinCreateBalanceEntity(DataManager dataManager, Gradzix_Core plugin) {
        this.dataManager = dataManager;
        this.plugin = plugin;
        startAddingPoints();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        dataManager.createPlayerBalanceEntityIfNotExists(player);

    }
    private void startAddingPoints() {

            scheduler.runTaskTimer(plugin, () -> {

                for(Player player : Bukkit.getOnlinePlayers()) {
                    dataManager.addMoneyToPlayer(player, ShopType.TIME, 1);
                }

            }, 0, 20L * 60L * 15); // 15 minutes duration

    }
}
