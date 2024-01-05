package me.xxgradzix.gradzixcore.playerPerks.items;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;
import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemManager {

    // strength
    public static ItemStack strengthPerkBook;
    public static ItemStack poisonPerkBook;
    public static ItemStack resistancePerkBook;
    public static ItemStack lifeStealPerkBook;
    public static ItemStack sicknessPerkBook;
//    public static ItemStack additionalHeartsPerkBook;
    public static ItemStack slownessPerkBook;

    public static ItemStack perkFragment;
    public static void init() {

        createStrengthPerkBook();
        createPoisonPerkBook();
        createResistancePerkBook();
        createLifeStealPerkBook();
        createSicknessPerkBook();
//        createAdditionalHeartsPerkBook();
        createSlownessPerkBook();
        createPerkFragment();

    }

    // strength
    private static void createStrengthPerkBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Księga Siły");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Użycie tej księgi zwiększy");
        lore.add(ChatColor.GRAY + "twoje obrażenia od 1 do 3%");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Kliknij PPM aby użyć");
        lore.add(ChatColor.GRAY + " ");

        meta.setLore(lore);

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);

        strengthPerkBook = item;
    }
    private static void createPoisonPerkBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Księga Trucizny");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Użycie tej księgi zwiększy");
        lore.add(ChatColor.GRAY + "twoją szanse na nałożenie");
        lore.add(ChatColor.GRAY + "efektu trucizny na wroga od 1 do 3%");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Kliknij PPM aby użyć");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        poisonPerkBook = item;
    }
    private static void createResistancePerkBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Księga Odporności");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Użycie tej księgi zwiększy");
        lore.add(ChatColor.GRAY + "twoją szanse na nałożenie");
        lore.add(ChatColor.GRAY + "efektu odporności na siebie od 1 do 3%");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Kliknij PPM aby użyć");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        resistancePerkBook = item;
    }
    private static void createLifeStealPerkBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Księga Kradzieży Zdrowia");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Użycie tej księgi zwiększy");
        lore.add(ChatColor.GRAY + "twoją szanse na to ze po ataku");
        lore.add(ChatColor.GRAY + "odzyskasz część zdrowia od 1 do 3%");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Kliknij PPM aby użyć");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        lifeStealPerkBook = item;
    }
    private static void createSicknessPerkBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Księga Słabości");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Użycie tej księgi zwiększy");
        lore.add(ChatColor.GRAY + "twoją szanse na nałożenie");
        lore.add(ChatColor.GRAY + "efektu słabości na wroga od 1 do 3%");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Kliknij PPM aby użyć");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        sicknessPerkBook = item;
    }
//    private static void createAdditionalHeartsPerkBook() {
//
//        ItemStack item = new ItemStack(Material.BOOK, 1);
//
//        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Księga Dodatkowych Serc");
//        ArrayList<String> lore = new ArrayList<>();
//
//        lore.add(" ");
//        lore.add(ChatColor.GRAY + "Użycie tej księgi zwiększy");
//        lore.add(ChatColor.GRAY + "ilość twoich serc od 0 do 2");
//        lore.add(ChatColor.GRAY + "serc");
//        lore.add(ChatColor.GRAY + " ");
//        lore.add(ChatColor.GRAY + "Kliknij PPM aby użyć");
//        lore.add(ChatColor.GRAY + " ");

//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//
//        additionalHeartsPerkBook = item;
//    }
    private static void createSlownessPerkBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Księga Spowolnienia");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Użycie tej księgi zwiększy");
        lore.add(ChatColor.GRAY + "twoją szanse na nałożenie");
        lore.add(ChatColor.GRAY + "efektu spowolnienia na wroga");
        lore.add(ChatColor.GRAY + "od 1 do 3%");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Kliknij PPM aby użyć");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        slownessPerkBook = item;
    }
    private static void createPerkFragment() {
        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.BOLD + "" + convertColorText("&#6de1fbF&#78d8f9r&#83cff8a&#8fc6f6g&#9abcf5m&#a5b3f3e&#b0aaf2n&#bca1f0t &#c798efK&#d28feds&#dd85eci&#e97ceaę&#f473e9g&#ff6ae7i"));
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Użyj aby wymienić na magiczne księgi");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        perkFragment = item;
    }
    private static String convertColorText(String text) {
        StringBuilder convertedText = new StringBuilder();
        String[] parts = text.split("&#");
        for (String part : parts) {
            if (!part.isEmpty()) {
                String colorCode = part.substring(0, 6);
                String letter = part.substring(6);
                net.md_5.bungee.api.ChatColor color = net.md_5.bungee.api.ChatColor.of("#" + colorCode);
                convertedText.append(color).append(letter);
            }
        }
        return convertedText.toString();
    }

    public static ItemStack getPerkBook(PerkType perkType) {
        switch (perkType) {
            case STRENGTH:
                return strengthPerkBook;
            case POISON:
                return poisonPerkBook;
            case RESISTANCE:
                return resistancePerkBook;
            case LIFE_STEAL:
                return lifeStealPerkBook;
            case SICKNESS:
                return sicknessPerkBook;
            default:
                throw new IllegalStateException("Unexpected value: " + perkType);
        }
    }
}
