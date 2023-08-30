package me.xxgradzix.gradzixcore.magicznafajerwerka.items;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemMenager {

    public static ItemStack firework;

    public static void init() {
        createWand();
    }

    private static void createWand() {
        ItemStack item = new ItemStack(Material.FIREWORK_ROCKET, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(convertColorText("&#c82b2bM&#cb2f2fa&#ce3333g&#d23636i&#d53a3ac&#d83e3ez&#db4242n&#df4646a &#e24a4aF&#e54d4da&#e85151j&#ec5555e&#ef5959r&#f25d5dw&#f56161e&#f96464r&#fc6868k&#ff6c6ca"));

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Fajerwerka która jest nieskończona");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        firework = item;
    }



    public static String convertColorText(String text) {
        StringBuilder convertedText = new StringBuilder();
        String[] parts = text.split("&#");

        for (String part : parts) {
            if (!part.isEmpty()) {
                String colorCode = part.substring(0, 6);
                String letter = part.substring(6);

                ChatColor color = ChatColor.of("#" + colorCode);

                convertedText.append(color).append(letter);
            }
        }

        return convertedText.toString();
    }
}
