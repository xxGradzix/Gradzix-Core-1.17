package me.xxgradzix.gradzixcore;

import dev.triumphteam.gui.guis.GuiItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GlobalItemManager {

    public static GuiItem HOPPER_GUI_ITEM;
    private static ItemStack FILLER_GLASS_PANE;
    private static ItemStack DARK_GLASS_PANE;
    private static ItemStack LIGHT_GLASS_PANE;
    private static ItemStack HOPPER;

    public static ItemStack NEXT_PAGE_ITEM;
    public static ItemStack PREVIOUS_PAGE_ITEM;

    public static GuiItem FILLER_GLASS_PANE_GUI_ITEM;
    public static GuiItem DARK_GLASS_PANE_GUI_ITEM;
    public static GuiItem LIGHT_GLASS_PANE_GUI_ITEM;


    public static void init() {
        createBlackGlass();
        createDarkGlass();
        createLightGlass();
        createHopper();

        createNextPage();
        createPreviousPage();

        FILLER_GLASS_PANE_GUI_ITEM = new GuiItem(FILLER_GLASS_PANE);
        DARK_GLASS_PANE_GUI_ITEM = new GuiItem(DARK_GLASS_PANE);
        LIGHT_GLASS_PANE_GUI_ITEM = new GuiItem(LIGHT_GLASS_PANE);
        HOPPER_GUI_ITEM = new GuiItem(HOPPER);
    }

    private static void createBlackGlass() {

        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        FILLER_GLASS_PANE = item;
    }
    private static void createHopper() {

        ItemStack item = new ItemStack(Material.HOPPER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        HOPPER = item;
    }
    private static void createDarkGlass() {

        ItemStack item = new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        DARK_GLASS_PANE = item;
    }

    private static void createLightGlass() {

        ItemStack item = new ItemStack(Material.CYAN_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        LIGHT_GLASS_PANE = item;
    }
    private static void createNextPage() {

        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Następna strona");
        item.setItemMeta(meta);

        NEXT_PAGE_ITEM = item;
    }
    private static void createPreviousPage() {

        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Poprzednia strona");
        item.setItemMeta(meta);
        PREVIOUS_PAGE_ITEM = item;
    }

}
