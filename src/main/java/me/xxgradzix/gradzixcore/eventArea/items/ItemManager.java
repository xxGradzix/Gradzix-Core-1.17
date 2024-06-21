package me.xxgradzix.gradzixcore.eventArea.items;

import me.xxgradzix.gradzixcore.dailyQuests.DailyQuests;
import me.xxgradzix.gradzixcore.dailyQuests.QuestType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.xxgradzix.gradzixcore.magicFirework.items.ItemManager.convertColorText;

public class ItemManager {

    public static ItemStack fragmentOfRing;
    public static ItemStack elfFragment;
    public static ItemStack dwarfFragment;
    public static ItemStack onlyRing;
    public static ItemStack pickaxeOfMoria;

    public static void init() {
        createFragmentOfRing();
        createElfFragment();
        createDwarfFragment();
        createOnlyRing();
        createPickaxeOfMoria();
    }

    private static void createPickaxeOfMoria() {
        ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "Kilof z Morii");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Krasnoludzki kilof z opuszczonych kopalni Morii.");
        lore.add(ChatColor.GRAY + "Przedmiot służy do wydobycia bloków eventowych.");

        itemMeta.setLore(lore);
//        itemMeta.addEnchant(Enchantment.DIG_SPEED, 3, false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        pickaxeOfMoria = item;
    }

    public static void createFragmentOfRing() {

        ItemStack item = new ItemStack(Material.BUDDING_AMETHYST, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(convertColorText("&#5A5A5AF&#5F5F5Fr&#656565a&#6A6A6Ag&#6F6F6Fm&#747474e&#7A7A7An&#7F7F7Ft &#898989P&#8F8F8Fi&#949494e&#999999r&#9E9E9Eś&#A4A4A4c&#A9A9A9i&#AEAEAEe&#B3B3B3n&#B9B9B9i&#BEBEBEa"));
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("§f➡§7Przedmiot służy do ulepszenia na §3§nFragment Elfów§7.");
        lore.add("§7Wymagana ilość §a32x Fragment Pierścienia§7.");

        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        fragmentOfRing = item;
    }
    public static void createElfFragment() {

        ItemStack item = new ItemStack(Material.SMALL_AMETHYST_BUD, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(convertColorText("&#246900§lF&#277300§lr&#2B7E00§la&#2E8800§lg&#329300§lm&#359D00§le&#39A800§ln&#3CB200§lt &#43C700§lE&#47D200§ll&#4ADC00§lf&#4EE700§ló&#51F100§lw"));
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(("§f➡§7 Przedmiot służy do ulepszenia na §3§nFragment Krasnoludów§7."));
        lore.add(("§7Wymagana ilość §a16x Fragment Elfów§7."));

        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        elfFragment = item;
    }
    public static void createDwarfFragment() {

        ItemStack item = new ItemStack(Material.MEDIUM_AMETHYST_BUD, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(convertColorText("&#393C2F§lF&#3A3D30§lr&#3A3D32§la&#3B3E33§lg&#3C3E34§lm&#3D3F35§le&#3D3F37§ln&#3E4038§lt &#40413A§lK&#40423C§lr&#41423D§la&#42433E§ls&#43443F§ln&#434441§lo&#444542§ll&#454543§lu&#464644§ld&#464646§ló&#474747§lw"));
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(("§f➡§7 Przedmiot służy do ulepszenia na §3§nJedyny Pierścień§7."));
        lore.add(("§7Wymagana ilość §a8x Fragment Krasnoludów§7."));

        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        dwarfFragment = item;
    }
    public static void createOnlyRing() {

        ItemStack item = new ItemStack(Material.LARGE_AMETHYST_BUD, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(convertColorText("&#9D8500§lJ&#A48C07§le&#AA940D§ld&#B19B14§ly&#B7A31B§ln&#BEAA21§ly &#CBB92F§lP&#D1C135§li&#D8C83C§le&#DED043§lr&#E5D749§lś&#EBDF50§lc&#F2E657§li&#F8EE5D§le&#FFF564§lń"));
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(("§f➡ §7Wymień przedmiot na §3§nKlucz do EVENTOWEJ SKRZYNI§7."));
        lore.add(("§7Wymianę przedmiotów eventowych dokonasz na §aspawnie (PORTAL PVP)§7."));

        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        onlyRing = item;
    }

}
