package me.xxgradzix.gradzixcore.clansExtension.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    public static void init() {

    }


    public static ItemStack endedWarResult(Long tempId, String userGuildTag, String enemyGuildTag, int hostPoints, int enemyPoints) {

        ItemStack item = new ItemStack(Material.END_CRYSTAL);

        ItemMeta itemMeta = item.getItemMeta();

        String warResult;

        if(hostPoints > enemyPoints) {
            warResult = ChatColor.BOLD + "" + ChatColor.GOLD + "ZWYCIĘSTWO";
        } else if (hostPoints < enemyPoints) {
            warResult = ChatColor.BOLD + "" + ChatColor.DARK_RED + "PRZEGRANA";
        } else {
            warResult = ChatColor.BOLD + "" + ChatColor.BLUE + "REMIS";
        }

        warResult += (" " + tempId);

        itemMeta.setDisplayName(warResult);

        ArrayList<String> lore = new ArrayList<>();

        lore.add("");

        lore.add(ChatColor.GRAY + "Wojna toczona z " + ChatColor.BOLD + "" + ChatColor.YELLOW + enemyGuildTag);
        lore.add("");
        lore.add(ChatColor.GRAY + "Status wojny: " + warResult);
        lore.add("");
        lore.add(ChatColor.GRAY + "Liczba punktów zdobytych przez Twoją gildię: " + ChatColor.DARK_GRAY + hostPoints);
        lore.add(ChatColor.GRAY + "Liczba punktów zdobytych przez gildię wroga: " + ChatColor.DARK_GRAY + enemyPoints);
        lore.add("");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

}
