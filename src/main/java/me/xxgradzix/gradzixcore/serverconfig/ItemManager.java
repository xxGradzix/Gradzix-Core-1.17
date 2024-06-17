package me.xxgradzix.gradzixcore.serverconfig;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack vipItem;
    public static ItemStack svipItem;
    public static ItemStack uniItem;
    public static ItemStack discordItem;

    public static void init() {
        createVipItem();
        createSvipItem();
        createUniItem();
        createDiscordItem();
    }
    private static void createDiscordItem() {
        ItemStack item = new ItemStack(Material.LIGHT_BLUE_DYE);

        ItemMeta discordMeta = item.getItemMeta();

        discordMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Discord");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_AQUA + "Jak odebrac nagrode?");
        lore.add(ChatColor.DARK_GRAY + "1. " + ChatColor.AQUA + "Dolacz na dc (DC.UNIMC.PL - Kliknij aby uzyskac link)");
        lore.add(ChatColor.DARK_GRAY + "2. " + ChatColor.AQUA + "Znajdz kategorie nagrody (#boxpvp)");
        lore.add(ChatColor.DARK_GRAY + "2. " + ChatColor.AQUA + "Kliknij odbierz, wpisz swoj nick");
        lore.add(ChatColor.DARK_AQUA + "Nagroda" + ChatColor.DARK_GRAY + ":" + ChatColor.AQUA + " VIP NA CALA EDYCJE");

        discordMeta.setLore(lore);
        item.setItemMeta(discordMeta);

        discordItem = item;
    }

    public static void createVipItem() {
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta meta = chestplate.getItemMeta();

        meta.setDisplayName(ChatColor.YELLOW + "§lVIP");

        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "Przywileje rangi " + ChatColor.YELLOW + "VIP" + ChatColor.GRAY + ":");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Unikatowy prefix " + "§e§lVIP");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Kit §e§lVIP§r§7 co 24h (§b§n/kit§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/hat§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/feed§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/repair§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/ec§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/wb§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§nSTREFY VIP§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Zwiększony limit wystawiania przedmiotó na rynku (§b§n5§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Zwiększony szansa na wypadanie klucza w strefie afk (§b§ndo 30%§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Większa szansa na trafienie fragmentu perku po zabiciu gracza (§b§n20%§r§7)");
        lore.add(ChatColor.DARK_GRAY + " ");
        lore.add(ChatColor.DARK_GRAY + "Range zakupisz na stronie");
        lore.add(ChatColor.AQUA + "https://unimc.pl/");

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setLore(lore);
        chestplate.setItemMeta(meta);

        vipItem = chestplate;
    }

    public static void createSvipItem() {
        ItemStack chestplate = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemMeta meta = chestplate.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "§lSVIP");


        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Unikatowy prefix " + "§6§lSVIP");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Kit §6§lSVIP§r§7 co 24h (§b§n/kit§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/hat§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/feed§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/repair§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/ec§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/wb§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§nSTREFY SVIP§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Zwiększony limit wystawiania przedmiotó na rynku (§b§n10§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Zwiększony szansa na wypadanie klucza w strefie afk (§b§ndo 40%§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Większa szansa na trafienie fragmentu perku po zabiciu gracza (§b§n50%§r§7)");
        lore.add(ChatColor.DARK_GRAY + " ");
        lore.add(ChatColor.DARK_GRAY + "Range zakupisz na stronie");
        lore.add(ChatColor.AQUA + "https://unimc.pl/");

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setLore(lore);
        chestplate.setItemMeta(meta);
        svipItem = chestplate;

    }

    public static void createUniItem() {
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = chestplate.getItemMeta();

        meta.setDisplayName(ChatColor.AQUA + "§lUNI");

        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "Przywileje rangi " + ChatColor.AQUA + "UNI" + ChatColor.GRAY + ":");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Unikatowy prefix " + "§b§lUNI");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Kit §b§lUNI§r§7 co 24h (§b§n/kit§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/hat§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/feed§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/repair§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/ec§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§n/wb§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Dostęp do (§b§nSTREFY UNI§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Zwiększony limit wystawiania przedmiotó na rynku (§b§n15§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Zwiększony szansa na wypadanie klucza w strefie afk (§b§ndo 50%§r§7)");
        lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Większa szansa na trafienie fragmentu perku po zabiciu gracza (§b§n50%§r§7)");
        lore.add(ChatColor.DARK_GRAY + " ");
        lore.add(ChatColor.DARK_GRAY + "Range zakupisz na stronie");
        lore.add(ChatColor.AQUA + "https://unimc.pl/");

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        chestplate.setItemMeta(meta);
        uniItem = chestplate;

    }
}
