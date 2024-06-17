package me.xxgradzix.gradzixcore.autodropsell.items;

import dev.triumphteam.gui.guis.GuiItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static GuiItem createAutoSellItem(Material material, Double price) {

        ItemStack autoSellItem = new ItemStack(material, 1);

        ItemMeta meta = autoSellItem.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("§7Cena sprzedaży: §b" + price + "$");

        meta.setLore(lore);
        autoSellItem.setItemMeta(meta);

        return new GuiItem(autoSellItem);
    }

}
