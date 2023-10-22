package me.xxgradzix.gradzixcore.adminPanel.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack chatOn;
    public static ItemStack chatOff;

    public static ItemStack scratchCardOn;
    public static ItemStack scratchCardOff;


    public static ItemStack kitsOn;
    public static ItemStack kitsOff;
    public static ItemStack achievementsOn;
    public static ItemStack achievementsOff;


    public static void init() {

        createChatOn();
        createChatOff();

        createScratchCardOn();
        createScratchCardOff();

        createKitsOn();
        createKitsOff();

        createAchievementsOn();
        createAchievementsOff();

    }

    // chat

    private static void createChatOn() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości na chat " + ChatColor.RED + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        chatOn = item;
    }

    private static void createChatOff() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości na chat " + ChatColor.GREEN + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        chatOff = item;
    }

    // scratch card

    private static void createScratchCardOn() {

        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Zdrapki " + ChatColor.RED + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        scratchCardOn = item;
    }

    private static void createScratchCardOff() {

        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Zdrapki " + ChatColor.GREEN + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        scratchCardOff = item;
    }


    private static void createKitsOn() {

        ItemStack item = new ItemStack(Material.CHEST, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Kity " + ChatColor.RED + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        kitsOn = item;
    }

    private static void createKitsOff() {

        ItemStack item = new ItemStack(Material.CHEST, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Kity " + ChatColor.GREEN + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        kitsOff = item;
    }


    private static void createAchievementsOn() {

        ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Osiągniecia " + ChatColor.RED + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        achievementsOn = item;
    }

    private static void createAchievementsOff() {

        ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Osiagniecia " + ChatColor.GREEN + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        achievementsOff = item;
    }


}
