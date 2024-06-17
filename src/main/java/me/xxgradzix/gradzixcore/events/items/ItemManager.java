package me.xxgradzix.gradzixcore.events.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    public static ItemStack mainReward;

    public static void init() {
        createMainReward();
    }
    private static void createMainReward() {

        ItemStack item = new ItemStack(new ItemStack(Material.NETHERITE_SCRAP));

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.DARK_PURPLE + "Odłamek z bossa");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.LIGHT_PURPLE + "Służy do wymiany na przedmioty");

        meta.setLore(lore);

        item.setItemMeta(meta);

        mainReward = item;

    }
}
