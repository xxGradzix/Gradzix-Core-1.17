package me.xxgradzix.gradzixcore;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GlobalItemManager {

    public static ItemStack FILLER_GLASS_PANE;
    public static ItemStack DARK_GLASS_PANE;
    public static ItemStack LIGHT_GLASS_PANE;

    public static void init() {
        createBlackGlass();
        createDarkGlass();
        createLightGlass();
    }

    private static void createBlackGlass() {

        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        FILLER_GLASS_PANE = item;
    }
    private static void createDarkGlass() {

        ItemStack item = new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        DARK_GLASS_PANE = item;
    }

    private static void createLightGlass() {

        ItemStack item = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        LIGHT_GLASS_PANE = item;
    }

}
