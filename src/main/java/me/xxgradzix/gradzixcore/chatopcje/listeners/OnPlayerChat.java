package me.xxgradzix.gradzixcore.chatopcje.listeners;

import me.xxgradzix.gradzixcore.chatopcje.files.ChatOpcjeConfigFile;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class OnPlayerChat implements Listener {


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        String playerUUID = event.getPlayer().getUniqueId().toString();

        List<String> blockedPlayersUUIDs = ChatOpcjeConfigFile.getShowChatMessageStatusUUIDsList(true);

        if (blockedPlayersUUIDs.contains(playerUUID)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "Nie możesz wysyłać wiadomości.");
        }

        event.getRecipients().removeIf(recipient -> blockedPlayersUUIDs.contains(recipient.getUniqueId().toString()));

    }



}


