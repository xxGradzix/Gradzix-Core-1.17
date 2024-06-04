package me.xxgradzix.gradzixcore.VPLNShop.items;

import dev.triumphteam.gui.guis.GuiItem;
import org.bukkit.Material;
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

        createVipShowcaseItem();
        createSvipShowcaseItem();
        createUniShowcaseItem();
        createMagicKeyShowcaseItem();
        createUniKeyShowcaseItem();


    }

    private static void createVipShowcaseItem() {
        ItemStack vipItem = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta vipItemMeta = vipItem.getItemMeta();
        vipItemMeta.setDisplayName("§bVIP");
        vipItem.setItemMeta(vipItemMeta);
        vipShowcaseItem = vipItem;
    }
    private static void createSvipShowcaseItem() {
        ItemStack svipItem = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta svipItemMeta = svipItem.getItemMeta();
        svipItemMeta.setDisplayName("§bSVIP");
        svipItem.setItemMeta(svipItemMeta);
        svipShowcaseItem = svipItem;
    }
    private static void createUniShowcaseItem() {
        ItemStack uniItem = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta uniItemMeta = uniItem.getItemMeta();
        uniItemMeta.setDisplayName("§bUNI");
        uniItem.setItemMeta(uniItemMeta);
        uniShowcaseItem = uniItem;
    }
    private static void createMagicKeyShowcaseItem() {
        ItemStack magicKeyItem = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta magicKeyItemMeta = magicKeyItem.getItemMeta();
        magicKeyItemMeta.setDisplayName("§bKlucz Magiczny");
        magicKeyItem.setItemMeta(magicKeyItemMeta);
        magicKeyShowcaseItem = magicKeyItem;
    }
    private static void createUniKeyShowcaseItem() {
        ItemStack uniKeyItem = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta uniKeyItemMeta = uniKeyItem.getItemMeta();
        uniKeyItemMeta.setDisplayName("§bKlucz UNI");
        uniKeyItem.setItemMeta(uniKeyItemMeta);
        uniKeyShowcaseItem = uniKeyItem;
    }


    public static GuiItem getShowcaseItem(ItemStack item, int amount, double price) {

        ItemStack showItem = new ItemStack(item);

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
        addOneButtonMeta.setDisplayName("§aDodaj 1");
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
