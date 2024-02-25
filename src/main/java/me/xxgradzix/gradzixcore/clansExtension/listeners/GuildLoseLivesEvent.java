package me.xxgradzix.gradzixcore.clansExtension.listeners;

import me.xxgradzix.gradzixcore.clansCore.events.ClanLivesChangeEvent;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GuildLoseLivesEvent implements Listener {



    @EventHandler
    public void onLoseLives(ClanLivesChangeEvent event) {

        if(event.getNewLives() == 0) {

            ClanManager.removeClan(event.getClan());

        }

    }

}
