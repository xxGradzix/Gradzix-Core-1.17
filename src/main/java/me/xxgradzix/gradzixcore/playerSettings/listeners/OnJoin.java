package me.xxgradzix.gradzixcore.playerSettings.listeners;

import me.xxgradzix.gradzixcore.playerSettings.data.DataManager;
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

        String exchange = DataManager.getAutoExchangeStatus(p) ? "ON" : "OFF";
        String sell = DataManager.getAutoSellStatus(p) ? "ON" : "OFF";

        String message = "§7wymiana: §2" + exchange + "§8« §7(§2/ustawienia§7) §8» §7sprzedaż: §2" + sell;
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));

    }

}
