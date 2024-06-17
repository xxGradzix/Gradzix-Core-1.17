package me.xxgradzix.gradzixcore.playerPerks.items;

import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.PlayerPerks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    // strength
    public static ItemStack strengthPerkBook;
    public static ItemStack poisonPerkBook;
    public static ItemStack resistancePerkBook;
    public static ItemStack lifeStealPerkBook;
    public static ItemStack additionalHeartsPerkBook;
    public static ItemStack weaknessPerkBook;
    public static ItemStack slownessPerkBook;
    public static ItemStack perkFragmentDrop;


//    public static ItemStack sicknessPerkBook;
//    public static ItemStack additionalHeartsPerkBook;

    public static ItemStack perkFragment;
    public static ItemStack perksItemButton;
    public static ItemStack abilityItemButton;
    public static ItemStack yourPerksItemButton;

    public static void init() {

        createStrengthPerkBook();
        createPoisonPerkBook();
        createResistancePerkBook();
        createLifeStealPerkBook();
        createAdditionalHeartsPerkBook();
        createSlownessPerkBook();
        createWeaknessPerkBook();
        createPerkFragmentDropBook();

        createPerkFragment();
        createPerksItemButton();
        createAbilitiesItemButton();
        createYourPerksItemButton();

    }

    // strength
    private static void createStrengthPerkBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e§lKsięga Siły");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("§8» §7Użycie księgi zwiększa");
        lore.add("§7twoje obrażenia §3od 1 do 3%");
        lore.add(" ");
        lore.add("§8» §3§lKliknij PPM aby użyć");
        lore.add(" ");

        meta.setLore(lore);

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);

        strengthPerkBook = item;
    }
    private static void createPoisonPerkBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "§2§lKsięga Trucizny");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "§8» §7Użycie księgi zwiększa");
        lore.add(ChatColor.GRAY + "§7szanse na otrucie");
        lore.add(ChatColor.GRAY + "§7przeciwnika §3od 1 do 3%");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "§8» §3§lKliknij PPM aby użyć");
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
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "§6§lKsięga Odporności");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "§8» §7Użycie księgi zwiększa");
        lore.add(ChatColor.GRAY + "§7twoją szanse na odporności §3od 1 do 3%");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "§8» §3§lKliknij PPM aby użyć");
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
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "§c§lKsięga Kradzieży Zdrowia");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "§8» §7Użycie księgi zwiększa");
        lore.add(ChatColor.GRAY + "§7szanse na odzyskanie część");
        lore.add(ChatColor.GRAY + "§7zdrowia po ataku §3od 1 do 3%");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "§8» §3§lKliknij PPM aby użyć");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        lifeStealPerkBook = item;
    }
//    private static void createSicknessPerkBook() {
//
//        ItemStack item = new ItemStack(Material.BOOK, 1);
//
//        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Księga Słabości");
//        ArrayList<String> lore = new ArrayList<>();
//
//        lore.add(" ");
//        lore.add(ChatColor.GRAY + "Użycie tej księgi zwiększy");
//        lore.add(ChatColor.GRAY + "twoją szanse na nałożenie");
//        lore.add(ChatColor.GRAY + "efektu słabości na wroga od 1 do 3%");
//        lore.add(ChatColor.GRAY + " ");
//        lore.add(ChatColor.GRAY + "Kliknij PPM aby użyć");
//        lore.add(ChatColor.GRAY + " ");
//
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//
//        sicknessPerkBook = item;
//    }
    private static void createAdditionalHeartsPerkBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "§4§lKsięga Życia");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "§8» §7Użycie księgi zwiększa");
        lore.add(ChatColor.GRAY + "§7ilość serc na pasku §3od 1 do 3%");
        lore.add(ChatColor.GRAY + "serc");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "§8» §3§lKliknij PPM aby użyć");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        additionalHeartsPerkBook = item;
    }
    private static void createWeaknessPerkBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "§d§lKsięga Osłabienia");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "§8» §7Użycie księgi zwiększa");
        lore.add(ChatColor.GRAY + "§7szanse na osłabienie");
        lore.add(ChatColor.GRAY + "§7przeciwnika §3od 1 do 3%");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "§8» §3§lKliknij PPM aby użyć");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        weaknessPerkBook = item;
    }
    private static void createPerkFragmentDropBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "§b§lKsięga Fragmentów");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "§8» §7Użycie księgi zwiększa");
        lore.add(ChatColor.GRAY + "§7zdobycia ksiąg fragmentów");
        lore.add(ChatColor.GRAY + "§7po przez zabójstwa §3od 1 do 3%");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "§8» §3§lKliknij PPM aby użyć");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        perkFragmentDrop = item;
    }
    private static void createSlownessPerkBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "§5§lKsięga Spowolnienia");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "§8» §7Użycie księgi zwiększa");
        lore.add(ChatColor.GRAY + "§7szanse na spowolnienie");
        lore.add(ChatColor.GRAY + "§7przeciwnika §3od 1 do 3%");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "§8» §3§lKliknij PPM aby użyć");
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

        meta.setDisplayName(ChatColor.BOLD + "" + convertColorText("&#642599§lF&#5E2496§lr&#582392§la&#52228F§lg&#4C218C§lm&#462089§le&#401F85§ln&#3A1E82§lt &#2D1B7B§lK&#271A78§ls&#211975§li&#1B1872§lę&#15176E§lg&#0F166B§li"));
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "§8» §7Służy do ulepszania postaci");
//        lore.add(ChatColor.AQUA + "ᴡᴀʟᴜᴛᴀ ᴘʀᴇᴍɪᴜᴍ, ᴋᴛóʀᴀ sᴌóżʏ ᴅᴏ ᴜʟᴇᴘsᴢᴀɴɪᴀ sᴡᴏᴊᴇᴊ ᴘᴏsᴛᴀᴄɪ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        perkFragment = item;
    }

    private static void createPerksItemButton() {
        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "§3Losowanie Ksiąg");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("§8» §7Można wylosować jedną z §38 ksiąg");
        lore.add(ChatColor.GRAY + "§8» §7Koszt§8: §3" + PlayerPerks.FRAGMENT_COST +"x Fragment Księgi");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        perksItemButton = item;
    }
    private static void createAbilitiesItemButton() {
        ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "§dUmiejętności");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "§8» §7Wybierz aby otworzyć menu umiejętności");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        abilityItemButton = item;
    }

    private static void createYourPerksItemButton() {
        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Twoje Księgi");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "§8» §7Twoje ulepszenia możesz sprawdzić pod §3/perki");
//        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        yourPerksItemButton = item;
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
            case SLOWNESS:
                return slownessPerkBook;
            case PERK_FRAGMENT_DROP:
                return perkFragmentDrop;
            case WEAKNESS:
                return weaknessPerkBook;
            case ADDITIONAL_HEARTS:
                return additionalHeartsPerkBook;
            default:
                return perkFragment;
        }
    }
}
