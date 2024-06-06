package me.xxgradzix.gradzixcore.VPLNShop.items;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static GuiItem cancelButtonGuiItem;
    public static GuiItem buyButtonGuiItem;

    public static ItemStack vipShowcaseItem;
    public static ItemStack svipShowcaseItem;
    public static ItemStack uniShowcaseItem;
    public static ItemStack magicKeyShowcaseItem;
    public static ItemStack uniKeyShowcaseItem;


    public static void init() {
        createCancelButton();
        createBuyButton();

//        createVipShowcaseItem();
//        createSvipShowcaseItem();
//        createUniShowcaseItem();
//        createMagicKeyShowcaseItem();
//        createUniKeyShowcaseItem();


    }

    public static GuiItem createVipShowcaseGuiItem(double balance) {
        ItemStack vipItem = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta vipItemMeta = vipItem.getItemMeta();
        vipItemMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Ranga " + ChatColor.YELLOW + "VIP");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
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
        vipItemMeta.setLore(lore);
        vipItem.setItemMeta(vipItemMeta);
        vipItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        vipItem.setItemMeta(vipItemMeta);
        return new GuiItem(vipItem);
    }
    public static GuiItem createSvipShowcaseItem(double balance) {
        ItemStack svipItem = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta svipItemMeta = svipItem.getItemMeta();
        svipItemMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Ranga " + ChatColor.GOLD + "SVIP");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "Przywileje rangi " + ChatColor.GOLD + "SVIP" + ChatColor.GRAY + ":");
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
        svipItemMeta.setLore(lore);
        svipItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        svipItem.setItemMeta(svipItemMeta);
        return  new GuiItem(svipItem);
    }
    public static GuiItem createUniShowcaseItem(double balance) {
        ItemStack uniItem = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta uniItemMeta = uniItem.getItemMeta();
        uniItemMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Ranga " + ChatColor.AQUA + "UNI");

        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
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
        uniItemMeta.setLore(lore);
        uniItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        uniItem.setItemMeta(uniItemMeta);
        return new GuiItem(uniItem);
    }
    public static GuiItem createMagicKeyShowcaseItem(double balance) {
        ItemStack magicKeyItem = new ItemStack(Material.NAME_TAG);
        ItemMeta magicKeyItemMeta = magicKeyItem.getItemMeta();
        magicKeyItemMeta.setDisplayName("§7§lKlucz do §5§lMagiczna skrzynia§7§l!");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Dzięki niemu możesz otworzyć §5§lMagiczna skrzynia§7§l!");
        lore.add("§7Skrzynie znajdziesz na §f/warp skrzynia§7!");
        magicKeyItemMeta.setLore(lore);
        magicKeyItem.setItemMeta(magicKeyItemMeta);

        return new GuiItem(magicKeyItem);
    }
    public static GuiItem createUniKeyShowcaseItem(double price) {
        ItemStack uniKeyItem = new ItemStack(Material.NAME_TAG);
        ItemMeta uniKeyItemMeta = uniKeyItem.getItemMeta();
        uniKeyItemMeta.setDisplayName("§7§lKlucz do §b§lUNIBOX§7§l!");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Dzięki niemu możesz otworzyć §b§lUNIBOX§7§l!");
        lore.add("§7Skrzynie znajdziesz na §f/warp skrzynia§7!");
        uniKeyItemMeta.setLore(lore);
        uniKeyItem.setItemMeta(uniKeyItemMeta);
        return new GuiItem(uniKeyItem);
    }


    public static GuiItem getShowcaseItem(ItemStack item, int amount, double price) {


        ItemStack showItem = new ItemStack(item);

        item.setAmount(amount);

        ItemMeta itemMeta = showItem.getItemMeta();

        List<String> lore = itemMeta.getLore();

        if (lore == null) {
            lore = new ArrayList<>();
        }

        lore.add(" ");
        lore.add("§7Ilość: §3" + amount);
        lore.add("§7Cena: §6" + price + " VPLN");

        itemMeta.setLore(lore);

        showItem.setItemMeta(itemMeta);

        return new GuiItem(showItem);

    }

    private static void createCancelButton() {
        ItemStack cancelButton = new ItemStack(Material.RED_DYE);
        ItemMeta cancelButtonMeta = cancelButton.getItemMeta();
        cancelButtonMeta.setDisplayName("§cAnuluj zakup");
        cancelButton.setItemMeta(cancelButtonMeta);
        cancelButtonGuiItem = new GuiItem(cancelButton);
    }
    private static void createBuyButton() {
        ItemStack addOneButton = new ItemStack(Material.LIME_DYE);
        ItemMeta addOneButtonMeta = addOneButton.getItemMeta();
        addOneButtonMeta.setDisplayName("§aKup");
        addOneButton.setItemMeta(addOneButtonMeta);
        buyButtonGuiItem = new GuiItem(addOneButton);
    }

    public static GuiItem createConfirmButton(double price) {
        ItemStack confirmButton = new ItemStack(Material.LIME_DYE);
        ItemMeta confirmButtonMeta = confirmButton.getItemMeta();
        confirmButtonMeta.setDisplayName("§aPotwierdź zakup");

        List<String> lore = new ArrayList<>();
        lore.add("§7Cena: §6" + price + " VPLN");
        lore.add("§7Kliknij aby potwierdzić zakup");
        confirmButtonMeta.setLore(lore);
        confirmButton.setItemMeta(confirmButtonMeta);
        return new GuiItem(confirmButton);
    }

}
