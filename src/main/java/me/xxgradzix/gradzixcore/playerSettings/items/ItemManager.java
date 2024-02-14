package me.xxgradzix.gradzixcore.playerSettings.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack autoExchangeOn;
    public static ItemStack autoExchangeOff;

    public static ItemStack autoSellOn;
    public static ItemStack autoSellOff;

    public static ItemStack blackGlass;
    public static ItemStack limeGlass;
    public static ItemStack greenGlass;


    public static ItemStack nextPage;
    public static ItemStack previousPage;


    public static ItemStack price;

    public static ItemStack addOne;
    public static ItemStack addTen;
    public static ItemStack addHundred;




    public static void init() {

        crateAutoExchangeOn();
        crateAutoExchangeOff();

        createAutoSellOn();
        createAutoSellOff();

        createLimeGlass();
        createGreenGlass();
        createBlackGlass();

        createNextPage();
        createPreviousPage();

        createPrice();

        createAddOne();
        createAddTen();
        createAddHundred();

    }

    private static void crateAutoExchangeOn() {
        ItemStack item = new ItemStack(Material.FEATHER, 1);

        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(ChatColor.GRAY + "§3Automatyczna wymiana" + ChatColor.RED + "OFF");
        meta.setDisplayName(ChatColor.GRAY + "§3Automatyczna wymiana");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "§8» §7Kliknij aby §awłączyć");
        meta.setLore(lore);
        item.setItemMeta(meta);

        autoExchangeOn = item;
    }
    private static void crateAutoExchangeOff() {
        ItemStack item = new ItemStack(Material.FEATHER, 1);

        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(ChatColor.GRAY + "Automatyczną wymiana " + ChatColor.GREEN + "ON");
        meta.setDisplayName(ChatColor.GRAY + "§3Automatyczna wymiana");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "§8» §7Kliknij aby §4wyłączyć");
        meta.setLore(lore);
        item.setItemMeta(meta);

        autoExchangeOff = item;
    }

    private static void createAutoSellOn() {
        ItemStack item = new ItemStack(Material.MAP, 1);

        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(ChatColor.GRAY + "§3Automatyczna sprzedaż" + ChatColor.RED + "OFF");
        meta.setDisplayName(ChatColor.GRAY + "§3Automatyczna sprzedaż");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "§8» §7Kliknij aby §awłączyć");
        meta.setLore(lore);
        item.setItemMeta(meta);

        autoSellOn = item;
    }
    private static void createAutoSellOff() {
        ItemStack item = new ItemStack(Material.MAP, 1);

        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(ChatColor.GRAY + "Automatyczną sprzedaż " + ChatColor.GREEN + "ON");
        meta.setDisplayName(ChatColor.GRAY + "§3Automatyczna sprzedaż");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "§8» §7Kliknij aby §4wyłączyć");
        meta.setLore(lore);
        item.setItemMeta(meta);

        autoSellOff = item;
    }

    private static void createBlackGlass() {

        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        blackGlass = item;
    }
    private static void createGreenGlass() {

        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        greenGlass = item;
    }

    private static void createLimeGlass() {

        ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        limeGlass = item;
    }

    private static void createNextPage() {

        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Następna strona");
        item.setItemMeta(meta);

        nextPage = item;
    }
    private static void createPreviousPage() {

        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Poprzednia strona");
        item.setItemMeta(meta);
        previousPage = item;
    }


    private static void createPrice() {
        ItemStack item = new ItemStack(Material.SUNFLOWER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Cena: " + ChatColor.GREEN + "0$");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby zresetować cenę");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        price = item;
    }

    // add price


    private static void createAddOne() {
        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "1$");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby dodać " + ChatColor.GREEN + "1$" + ChatColor.GRAY + " do ceny przedmiotu");
        meta.setLore(lore);
        item.setItemMeta(meta);

        addOne = item;
    }
    private static void createAddTen() {
        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "10$");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby dodać " + ChatColor.GREEN + "10$" + ChatColor.GRAY + " do ceny przedmiotu");
        meta.setLore(lore);
        item.setItemMeta(meta);

        addTen = item;
    }
    private static void createAddHundred() {
        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "100$");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby dodać " + ChatColor.GREEN + "100$" + ChatColor.GRAY + " do ceny przedmiotu");
        meta.setLore(lore);

        item.setItemMeta(meta);

        addHundred = item;
    }


}
