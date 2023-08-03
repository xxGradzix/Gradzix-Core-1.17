package me.xxgradzix.gradzixcore.panel.listeners;

import me.xxgradzix.gradzixcore.panel.files.PanelAdminConfigFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OsiagnieciaBlock implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().toLowerCase();

        Player p = event.getPlayer();

        if(PanelAdminConfigFile.getOsiagnieciaStatus()) return;

        if(!PanelAdminConfigFile.getOsiagnieciaStatus()) {

            if (message.startsWith("/osiagniecia")) {

                event.setCancelled(true);

                p.sendMessage(ChatColor.RED + "Osiagniecia są aktualnie wyłączone");

            }
            if (message.startsWith("/boxpvp-ageplay:osiagniecia")) {

                event.setCancelled(true);

                p.sendMessage(ChatColor.RED + "Osiagniecia są aktualnie wyłączone");

            }
        }

    }

}
