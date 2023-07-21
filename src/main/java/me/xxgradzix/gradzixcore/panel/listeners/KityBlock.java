package me.xxgradzix.gradzixcore.panel.listeners;

import me.xxgradzix.gradzixcore.panel.files.PanelAdminConfigFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class KityBlock implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage();

        Player p = event.getPlayer();

        if(PanelAdminConfigFile.getKityStatus()) return;

        if(!PanelAdminConfigFile.getKityStatus()) {
            if (message.startsWith("/kit")) {

                event.setCancelled(true);

                p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");

            }
        }

    }

}
