package me.xxgradzix.gradzixcore.serverconfig.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.HashMap;
import java.util.UUID;

public class LoginCooldown implements Listener {

    private HashMap<UUID, Long> loginCooldowns = new HashMap<>();
    private final long cooldownTimeMillis = 30 * 1000; // 30 sekundy w milisekundach



    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
//        Player player = event.getPlayer();
//        UUID playerUUID = player.getUniqueId();
//
//        if (loginCooldowns.containsKey(playerUUID)) {
//            long lastLoginTime = loginCooldowns.get(playerUUID);
//            long currentTime = System.currentTimeMillis();
//            long timeSinceLastLogin = currentTime - lastLoginTime;
//
//            if (timeSinceLastLogin < cooldownTimeMillis) {
//                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Możesz łączyć się tylko raz na 30 sekund.");
//                return;
//                // Możesz tutaj zmienić powyższy komunikat na dowolny inny, który chcesz wysłać graczowi po odrzuceniu połączenia.
//            }
//        }
//
//        // Aktualizujemy czas ostatniego logowania gracza
//        loginCooldowns.put(playerUUID, System.currentTimeMillis());
    }
}
