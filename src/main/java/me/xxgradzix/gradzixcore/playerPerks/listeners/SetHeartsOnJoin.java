package me.xxgradzix.gradzixcore.playerPerks.listeners;

import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.xxgradzix.gradzixcore.playerPerks.PerkType.ADDITIONAL_HEARTS;

public class SetHeartsOnJoin implements Listener {

    private final PlayerPerkEntityManager playerPerkEntityManager;

    public SetHeartsOnJoin(PlayerPerkEntityManager playerPerkEntityManager) {
        this.playerPerkEntityManager = playerPerkEntityManager;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        PlayerPerksEntity playerPerksEntity = playerPerkEntityManager.getPlayerPerksEntityById(player.getUniqueId());

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20 + playerPerksEntity.getPerkTypeLevel(ADDITIONAL_HEARTS));


    }

}
