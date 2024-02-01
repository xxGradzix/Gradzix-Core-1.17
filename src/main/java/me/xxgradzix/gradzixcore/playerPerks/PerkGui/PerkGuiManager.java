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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PerkGuiManager {


    private static final Gradzix_Core plugin = Gradzix_Core.getInstance();

//    public static void drawPerk(Player player) {
//        drawPerk(plugin, perkBooks, player);
//    }
    public static void drawPerk(Gradzix_Core plugin, Player player) {

        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.LIGHT_PURPLE + "PERKI"))
                .disableAllInteractions()
                .rows(3)
                .create();

        gui.getFiller().fill(ItemBuilder.from(ItemManager.blackGlass).asGuiItem());
        gui.getFiller().fillBetweenPoints(2, 1, 2, 9, ItemBuilder.from(ItemManager.limeGlass).asGuiItem());


        gui.open(player);

        List<ItemStack> perkBooks = new ArrayList<>();
        perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.poisonPerkBook);
        perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.strengthPerkBook);
        perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.slownessPerkBook);
        perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.resistancePerkBook);
        perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.lifeStealPerkBook);
        perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.sicknessPerkBook);
        perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.poisonPerkBook);
        perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.strengthPerkBook);
        perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.slownessPerkBook);
        perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.resistancePerkBook);
        perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.lifeStealPerkBook);
        perkBooks.add(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.sicknessPerkBook);

        ScrollItems scrollItems;
        scrollItems = new ScrollItems(perkBooks, player, gui, 2);
        scrollItems.runTaskTimer(plugin, 0, 3);

    }
}
