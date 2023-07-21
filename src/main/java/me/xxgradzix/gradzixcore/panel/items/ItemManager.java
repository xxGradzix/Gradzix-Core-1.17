package me.xxgradzix.gradzixcore.panel.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack chatOn;
    public static ItemStack chatOff;

    public static ItemStack zdrapkaOn;
    public static ItemStack zdrapkaOff;


    public static ItemStack kityOn;
    public static ItemStack kityOff;
    public static ItemStack osiagnieciaOn;
    public static ItemStack osiagnieciaOff;


    public static void init() {

        createChatOn();
        createChatOff();

        createZdrapkaOn();
        createZdrapkaOff();

        createKityOn();
        createKityOff();

        createOsiagnieciaOn();
        createOsiagnieciaOff();

    }

    private static void createChatOn() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomosci na chat " + ChatColor.RED + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        chatOn = item;
    }

    private static void createChatOff() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomosci na chat " + ChatColor.GREEN + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        chatOff = item;
    }

    // zdrapka


    private static void createZdrapkaOn() {

        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Zdrapki " + ChatColor.RED + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        zdrapkaOn = item;
    }

    private static void createZdrapkaOff() {

        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Zdrapki " + ChatColor.GREEN + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        zdrapkaOff = item;
    }


    private static void createKityOn() {

        ItemStack item = new ItemStack(Material.CHEST, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Kity " + ChatColor.RED + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        kityOn = item;
    }

    private static void createKityOff() {

        ItemStack item = new ItemStack(Material.CHEST, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Kity " + ChatColor.GREEN + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        kityOff = item;
    }


    private static void createOsiagnieciaOn() {

        ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Osiagniecia " + ChatColor.RED + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        osiagnieciaOn = item;
    }

    private static void createOsiagnieciaOff() {

        ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Osiagniecia " + ChatColor.GREEN + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        osiagnieciaOff = item;
    }


}
