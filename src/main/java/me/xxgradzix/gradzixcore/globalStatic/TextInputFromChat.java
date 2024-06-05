package me.xxgradzix.gradzixcore.globalStatic;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class TextInputFromChat implements Listener {

    private static final HashMap<UUID, CompletableFuture<String>> playerUuidInputMap = new HashMap<>();

    public static CompletableFuture<String> getPlayerInput(UUID uuid) {
        CompletableFuture<String> future = new CompletableFuture<>();
        playerUuidInputMap.put(uuid, future);
        return future;
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        UUID playerUUIF = event.getPlayer().getUniqueId();
        if (playerUuidInputMap.containsKey(playerUUIF)) {
            playerUuidInputMap.get(playerUUIF).complete(event.getMessage());
            playerUuidInputMap.remove(playerUUIF);
            event.setCancelled(true); // Prevent the message from being sent to chat
        }
    }

}
