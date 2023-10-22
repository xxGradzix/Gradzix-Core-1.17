package me.xxgradzix.gradzixcore.chatOptions.listeners;

import me.xxgradzix.gradzixcore.chatOptions.Chatopcje;
import me.xxgradzix.gradzixcore.chatOptions.data.database.entities.ChatOptionsEntity;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OnPlayerChat implements Listener {


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        UUID playerUUID = event.getPlayer().getUniqueId();

//        List<String> blockedPlayersUUIDs = ChatOpcjeConfigFile.getShowChatMessageStatusUUIDsList(true);
        List<ChatOptionsEntity> chatOptionsEntityList = Chatopcje.getChatOptionsEntityManager().getChatOptionsEntitiesWhereShowChatMessageIs(false);

        List<UUID> blockedPlayersUUIDs = chatOptionsEntityList.stream().map(ChatOptionsEntity::getUuid).collect(Collectors.toList());

        if (blockedPlayersUUIDs.contains(playerUUID)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "Nie możesz wysyłać wiadomości.");
        }

        event.getRecipients().removeIf(recipient -> blockedPlayersUUIDs.contains(recipient.getUniqueId()));

    }



}


