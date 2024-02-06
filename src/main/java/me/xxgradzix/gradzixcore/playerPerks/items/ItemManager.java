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
    public static ItemStack additionalHeartsPerkBook;
    public static ItemStack weaknessPerkBook;
    public static ItemStack slownessPerkBook;

    public static ItemStack perkFragment;
    public static ItemStack perksItemButton;
    public static ItemStack abilityItemButton;

    public static void init() {

        createStrengthPerkBook();
        createPoisonPerkBook();
        createResistancePerkBook();
        createLifeStealPerkBook();
        createSicknessPerkBook();
        createAdditionalHeartsPerkBook();
        createSlownessPerkBook();
        createPerkFragment();
        createWeaknessPerkBook();
        createPerksItemButton();
        createAbilitiesItemButton();
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
    private static void createAdditionalHeartsPerkBook() {

        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Księga Dodatkowych Serc");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Użycie tej księgi zwiększy");
        lore.add(ChatColor.GRAY + "ilość twoich serc od 0 do 2");
        lore.add(ChatColor.GRAY + "serc");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Kliknij PPM aby użyć");
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
        meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Księga Osłabienia");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Użycie tej księgi osłabi");
        lore.add(ChatColor.GRAY + "gracza zwiększając otrzymane");
        lore.add(ChatColor.GRAY + "przez niego obrażenia");
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Kliknij PPM aby użyć");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        weaknessPerkBook = item;
    }
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

        meta.setDisplayName(ChatColor.BOLD + "" + convertColorText("&#04aafbF&#0dadfbR&#16b0fbA&#1fb3fbG&#28b6fcM&#31b9fcE&#3abcfcN&#43bffcT &#4cc2fcK&#55c5fcS&#5ec8fdI&#67cbfdĘ&#70cefdG&#79d1fdI"));
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Użyj aby wymienić na magiczne księgi");
        lore.add(ChatColor.AQUA + "ᴡᴀʟᴜᴛᴀ ᴘʀᴇᴍɪᴜᴍ, ᴋᴛóʀᴀ sᴌóżʏ ᴅᴏ ᴜʟᴇᴘsᴢᴀɴɪᴀ sᴡᴏᴊᴇᴊ ᴘᴏsᴛᴀᴄɪ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        perkFragment = item;
    }

    private static void createPerksItemButton() {
        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Perki");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("Wybierz aby wylosować księgę perku");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: 50 Fragmentów Perku");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        perksItemButton = item;
    }
    private static void createAbilitiesItemButton() {
        ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Umiejętności");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Wybierz aby otworzyć menu umiejętności");
        lore.add(ChatColor.GRAY + " ");

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);
        item.setItemMeta(meta);

        abilityItemButton = item;
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
            case SLOWNESS:
                return slownessPerkBook;
            case WEAKNESS:
                return weaknessPerkBook;
            case ADDITIONAL_HEARTS:
                return additionalHeartsPerkBook;
            default:
                return perkFragment;
        }
    }
}