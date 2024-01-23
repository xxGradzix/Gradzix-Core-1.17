package me.xxgradzix.gradzixcore.clansExtension.listeners;

import me.xxgradzix.gradzixcore.clansExtension.ClansExtension;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.event.guild.GuildDeleteEvent;
import net.dzikoysk.funnyguilds.guild.Guild;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import panda.std.Option;

import java.util.UUID;

public class GuildRemoveEvent implements Listener {

    private final WarManager warManager;
    private final FunnyGuilds funnyGuilds;

    public GuildRemoveEvent(WarManager warManager, FunnyGuilds funnyGuilds) {
        this.warManager = warManager;
        this.funnyGuilds = funnyGuilds;
    }

    @EventHandler
    public void onGuildRemove(GuildDeleteEvent event) {

        Guild guild = event.getGuild();

        warManager.getNonEndedGuildWars(guild.getUUID()).forEach(warEntity -> {
            int enemyPoints = guild.getUUID().equals(warEntity.getInvaderGuildId()) ? warEntity.getInvadedScore() : warEntity.getInvaderScore();

            if(enemyPoints <= 0) enemyPoints = 1;

            if(guild.getUUID().equals(warEntity.getInvaderGuildId())) {
                warEntity.setInvaderScore(0);
                warEntity.setInvadedScore(enemyPoints);
            } else {
                warEntity.setInvadedScore(0);
                warEntity.setInvaderScore(enemyPoints);
            }

            UUID enemyGuildId = guild.getUUID().equals(warEntity.getInvaderGuildId()) ? warEntity.getInvadedGuildId() : warEntity.getInvaderGuildId();

            Option<Guild> guildOption = funnyGuilds.getGuildManager().findByUuid(enemyGuildId);
            if(guildOption.isPresent()) {
                Guild enemyGuild = guildOption.get();
                
                if(ClansExtension.ARE_WARS_ACTIVE) {
                    enemyGuild.getMembers().forEach(member -> {
                        member.sendMessage(Messages.WAR_ENDED_VIA_GUILD_REMOVAL(guild.getTag()));
                    });
                } else {
                    enemyGuild.getMembers().forEach(member -> {
                        member.sendMessage(Messages.WAR_CANCELED_VIA_GUILD_REMOVAL(guild.getTag()));
                    });
                }

            }

            warManager.endWar(warEntity);
        });


    }

}
