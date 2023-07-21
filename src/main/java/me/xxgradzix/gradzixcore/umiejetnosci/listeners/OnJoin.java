package me.xxgradzix.gradzixcore.umiejetnosci.listeners;

import me.xxgradzix.gradzixcore.ustawienia.files.UstawieniaOpcjeConfigFile;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    @EventHandler
    public void onPlayerJoinMessage(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        String wymiana = UstawieniaOpcjeConfigFile.getAutoWymianaStatus(p) ? "ON" : "OFF";
        String sprzedaz = UstawieniaOpcjeConfigFile.getAutoSprzedazStatus(p) ? "ON" : "OFF";


        String message = "§7wymiana: §2" + wymiana + "§8« §7(§2/ustawienia§7) §8» §7sprzedaż: §2" + sprzedaz;
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));

    }

}
