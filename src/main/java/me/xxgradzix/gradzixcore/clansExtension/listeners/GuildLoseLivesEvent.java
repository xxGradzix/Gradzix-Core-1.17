package me.xxgradzix.gradzixcore.clansExtension.listeners;

import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.event.guild.GuildLivesChangeEvent;
import net.dzikoysk.funnyguilds.guild.GuildManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GuildLoseLivesEvent implements Listener {

    private final FunnyGuilds funnyGuilds;

    public GuildLoseLivesEvent(FunnyGuilds funnyGuilds) {
        this.funnyGuilds = funnyGuilds;
    }

    @EventHandler
    public void onLoseLives(GuildLivesChangeEvent event) {

        if(event.getNewLives() == 0) {

            GuildManager guildManager = funnyGuilds.getGuildManager();

            guildManager.deleteGuild(funnyGuilds, event.getGuild());

        }

    }

}
