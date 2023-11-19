package me.xxgradzix.gradzixcore.binds.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {


    public static void init() {
    }

    public static ItemStack createBindButton(int id, String name, String description) {
        ItemStack item = new ItemStack(Material.OAK_SIGN, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a" + id + "§8. §a" +name);

        ArrayList<String> lore = new ArrayList<>();

        lore.add("§a" + description);

        lore.add(" ");
        lore.add("§7Kliknij aby zagrać dźwięk");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}
