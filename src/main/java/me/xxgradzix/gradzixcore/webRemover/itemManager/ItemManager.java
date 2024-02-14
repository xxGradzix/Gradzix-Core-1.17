package me.xxgradzix.gradzixcore.webRemover.itemManager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    public static ItemStack webRemover;
    public static void init() {
        createWebRemover();
    }

    private static void createWebRemover() {

        ItemStack item = new ItemStack(Material.BONE, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§c§lUsuwacz pajęczyn");
        ArrayList<String> lore = new ArrayList<>();

        lore.add("§8» §7Kliknij prawym, aby użyć");

        meta.setLore(lore);

        item.setItemMeta(meta);
        webRemover = item;

    }
}
