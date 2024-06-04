package me.xxgradzix.gradzixcore.playerPerks.data.database;

import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;
import me.xxgradzix.gradzixcore.playerPerks.PlayerPerks;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private static final PlayerPerkEntityManager playerPerkEntityManager = PlayerPerks.getPlayerPerkEntityManager();


    private static final Map<UUID, PlayerPerksEntity> playerPerksCache = new HashMap<>();

    public static PlayerPerksEntity getPerkEntity(Player player) {
        if (!playerPerksCache.containsKey(player.getUniqueId())) {
            refreshPerkEntity(player);
        }
        return playerPerksCache.get(player.getUniqueId());
    }
    public static void refreshPerkEntity(Player player) {
        playerPerksCache.put(player.getUniqueId(), playerPerkEntityManager.getPlayerPerksEntityById(player.getUniqueId()));
    }


}
