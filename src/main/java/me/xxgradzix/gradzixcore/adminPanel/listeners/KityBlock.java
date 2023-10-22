package me.xxgradzix.gradzixcore.adminPanel.listeners;

import me.xxgradzix.gradzixcore.adminPanel.data.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class KityBlock implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().toLowerCase();

        Player p = event.getPlayer();

        if(DataManager.getKitStatus()) return;

        if(!DataManager.getKitStatus()) {

            if (message.startsWith("/kit")) {
                    event.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
            }
            if (message.startsWith("/essentials:kits")) {
                event.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
            }
            if (message.startsWith("/essentials:ekits")) {
                event.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
            }
            if (message.startsWith("/essentials:kit")) {
                event.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
            }
            if (message.startsWith("/essentials:ekit")) {
                event.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
            }
            if (message.startsWith("/ekit")) {
                if(!message.equals("/ekit gracz")) {
                    event.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
                }
            }
        }
    }
}
