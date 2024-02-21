package me.xxgradzix.gradzixcore.playerPerks.listeners;

import me.xxgradzix.gradzixcore.playerPerks.data.database.DataManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.xxgradzix.gradzixcore.playerPerks.PerkType.ADDITIONAL_HEARTS;

public class SetHeartsOnJoin implements Listener {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20 + DataManager.getPerkEntity(player).getPerkTypeLevel(ADDITIONAL_HEARTS));


    }

}
