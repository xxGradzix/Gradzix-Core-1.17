package me.xxgradzix.gradzixcore.ustawienia.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack autoWymianaOn;
    public static ItemStack autoWymianaOff;

    public static ItemStack autoSprzedazOn;
    public static ItemStack autoSprzedazOff;

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

        createAutoWymianaOn();
        createAutoWymianaOff();

        createAutoSprzedazOn();
        createAutoSprzedazOff();

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

    private static void createAutoWymianaOn() {
        ItemStack item = new ItemStack(Material.END_CRYSTAL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Automatyczną wymiana " + ChatColor.RED + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        autoWymianaOn = item;
    }
    private static void createAutoWymianaOff() {
        ItemStack item = new ItemStack(Material.END_CRYSTAL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Automatyczną wymiana " + ChatColor.GREEN + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        autoWymianaOff = item;
    }

    private static void createAutoSprzedazOn() {
        ItemStack item = new ItemStack(Material.SUNFLOWER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Automatyczną sprzedaż " + ChatColor.RED + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        autoSprzedazOn = item;
    }
    private static void createAutoSprzedazOff() {
        ItemStack item = new ItemStack(Material.SUNFLOWER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Automatyczną sprzedaż " + ChatColor.GREEN + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        autoSprzedazOff = item;
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
        meta.setDisplayName(ChatColor.YELLOW + "Nastepna strona");
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
        lore.add(ChatColor.GRAY + "Kliknij aby zresetować cene");
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
        lore.add(ChatColor.GRAY + "Kliknij aby dodac " + ChatColor.GREEN + "1$" + ChatColor.GRAY + " do ceny przedmiotu");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        addOne = item;
    }
    private static void createAddTen() {
        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "10$");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby dodac " + ChatColor.GREEN + "10$" + ChatColor.GRAY + " do ceny przedmiotu");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        addTen = item;
    }
    private static void createAddHundred() {
        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "100$");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby dodac " + ChatColor.GREEN + "100$" + ChatColor.GRAY + " do ceny przedmiotu");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        addHundred = item;
    }


}
