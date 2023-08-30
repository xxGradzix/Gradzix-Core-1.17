package me.xxgradzix.gradzixcore.panel.listeners;

import me.xxgradzix.gradzixcore.panel.data.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {



    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!DataManager.getChatStatus()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "Czat jest obecnie wyłączony.");
        }
    }
}