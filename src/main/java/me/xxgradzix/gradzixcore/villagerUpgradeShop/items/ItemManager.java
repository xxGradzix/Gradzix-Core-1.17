package me.xxgradzix.gradzixcore.villagerUpgradeShop.items;

import dev.triumphteam.gui.guis.GuiItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

//    public static GuiItem addItemButton;



    public static void init() {
//        createAddItemButton();


    }

    public static GuiItem createShowItem(ItemStack item, double price, ItemStack neededItem, int shopSlot, String shopName) {

        ItemStack itemStack = new ItemStack(item);

        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("§7Cena: §b" + price);
        lore.add("§7Prezedmiot potrzebny do ulepszenia: §b" + neededItem.getItemMeta().getDisplayName());
        lore.add("§7Slot sklepu: §b" + shopSlot);
        lore.add("§7Nazwa sklepu: §b" + shopName);



        meta.setLore(lore);
        item.setItemMeta(meta);

        return new GuiItem(item);
    }

    public static GuiItem createAddItemButton() {

        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§bDodaj przedmiot");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("Trzymaj przedmiot w ręce i kliknij aby dodać go do sklepu");
        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        return new GuiItem(item);
    }


}
