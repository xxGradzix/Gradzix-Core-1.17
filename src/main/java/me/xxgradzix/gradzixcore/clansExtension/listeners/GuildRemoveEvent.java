package me.xxgradzix.gradzixcore.clansExtension.listeners;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.events.ClanDeleteEvent;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import me.xxgradzix.gradzixcore.clansExtension.ClansExtension;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Optional;
import java.util.UUID;

public class GuildRemoveEvent implements Listener {

    private final WarManager warManager;

    public GuildRemoveEvent(WarManager warManager) {
        this.warManager = warManager;
    }

    @EventHandler
    public void onGuildRemove(ClanDeleteEvent event) {

        ClanEntity clan = event.getClan();

        warManager.getNonEndedGuildWars(clan.getUuid()).forEach(warEntity -> {
            int enemyPoints = clan.getUuid().equals(warEntity.getInvaderGuildId()) ? warEntity.getInvadedScore() : warEntity.getInvaderScore();

            if(enemyPoints <= 0) enemyPoints = 1;

            if(clan.getUuid().equals(warEntity.getInvaderGuildId())) {
                warEntity.setInvaderScore(0);
                warEntity.setInvadedScore(enemyPoints);
            } else {
                warEntity.setInvadedScore(0);
                warEntity.setInvaderScore(enemyPoints);
            }

            UUID enemyGuildId = clan.getUuid().equals(warEntity.getInvaderGuildId()) ? warEntity.getInvadedGuildId() : warEntity.getInvaderGuildId();

            Optional<ClanEntity> clanEntityOptional = ClanManager.getClanEntityByUUID(enemyGuildId);
            if(clanEntityOptional.isPresent()) {
                ClanEntity enemyGuild = clanEntityOptional.get();

                if(ClansExtension.ARE_WARS_ACTIVE) {
                    enemyGuild.getMembersUUIDs().forEach(member -> {
                        Player player = Bukkit.getPlayer(member);
                        if(player != null) player.sendMessage(Messages.WAR_ENDED_VIA_GUILD_REMOVAL(clan.getTag()));
                    });
                } else {
                    enemyGuild.getMembersUUIDs().forEach(member -> {
                        Player player = Bukkit.getPlayer(member);
                        if(player != null) player.sendMessage(Messages.WAR_CANCELED_VIA_GUILD_REMOVAL(clan.getTag()));
                    });
                }

            }

            warManager.endWar(warEntity);
        });


    }

}
