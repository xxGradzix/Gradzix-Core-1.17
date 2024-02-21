package me.xxgradzix.gradzixcore.playerPerks.PerkGui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.chatOptions.items.ItemManager;
import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PerkGuiManager {


    private static final Gradzix_Core plugin = Gradzix_Core.getInstance();

    public void drawPerk(Gradzix_Core plugin, Player player) {

        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.LIGHT_PURPLE + "PERKI"))
                .disableAllInteractions()
                .rows(3)
                .create();

        gui.getFiller().fill(ItemBuilder.from(ItemManager.blackGlass).asGuiItem());
        gui.getFiller().fillBetweenPoints(2, 1, 2, 9, ItemBuilder.from(ItemManager.limeGlass).asGuiItem());


        gui.open(player);

        List<ItemStack> perkBooks = new ArrayList<>();
        for(PerkType perkType : PerkType.values()) {
            perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.getPerkBook(perkType));
        }
        ScrollItems scrollItems;
        scrollItems = new ScrollItems(perkBooks, player, gui, 2);

        scrollItems.runTaskTimer(plugin, 0, 3);

    }
}
