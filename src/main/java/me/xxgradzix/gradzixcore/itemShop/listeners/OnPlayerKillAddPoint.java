package me.xxgradzix.gradzixcore.itemShop.listeners;

import me.xxgradzix.gradzixcore.itemShop.data.DataManager;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopPlayerBalanceEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.enums.ShopType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerKillAddPoint implements Listener {

    private final DataManager dataManager;

    public OnPlayerKillAddPoint(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if(killer == null) return;
        dataManager.addMoneyToPlayer(killer, ShopType.KILLS, 1);
    }

}
