package me.xxgradzix.gradzixcore.warps.listeners;

import com.comphenix.protocol.wrappers.WrappedChatComponent;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.awt.*;

public class WarpCommandListener implements Listener {

    @EventHandler
    public void onWarpCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage();

        if(!message.equalsIgnoreCase("/warp") && !message.equalsIgnoreCase("/warps")) return;



    }

    private void openGui(Player player) {

        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.AQUA + "" + ChatColor.BOLD + "Warp " + ChatColor.RESET + ChatColor.GRAY + "(/warp)"))
                .rows(6)
                .create();


        GuiItem blackGlass = new GuiItem(me.xxgradzix.gradzixcore.chatOptions.items.ItemManager.blackGlass);

        gui.getFiller().fillBetweenPoints(1, 2, 1, 8, blackGlass);
        gui.getFiller().fillBetweenPoints(3, 2, 3, 8, blackGlass);

        gui.setItem(2, 1, blackGlass);
        gui.setItem(2, 9, blackGlass);

        GuiItem limeGlass = new GuiItem(me.xxgradzix.gradzixcore.chatOptions.items.ItemManager.limeGlass);

        gui.setItem(1, 1, limeGlass);
        gui.setItem(1, 9, limeGlass);
        gui.setItem(3, 1, limeGlass);
        gui.setItem(3, 9, limeGlass);

    }


}
