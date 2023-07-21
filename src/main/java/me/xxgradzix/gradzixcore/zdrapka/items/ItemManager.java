package me.xxgradzix.gradzixcore.zdrapka.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    public static ItemStack zdrapka;

    public static void init() {

        createZdrapka();




    }

    private static void createZdrapka() {

        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Z" +
                ChatColor.RED + ChatColor.BOLD.toString() + "d" +
                ChatColor.AQUA + ChatColor.BOLD.toString() + "r" +
                ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "a" +
                ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "p" +
                ChatColor.GOLD + ChatColor.BOLD.toString() + "k" +
                ChatColor.BLUE + ChatColor.BOLD.toString() + "a");

        ArrayList<String> lore = new ArrayList<>();

        lore.add("§7Kliknij lewy przycisk, aby otworzyć zdrapkę");

        meta.setLore(lore);

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        zdrapka = item;
    }


}
