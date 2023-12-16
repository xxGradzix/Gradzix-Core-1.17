package me.xxgradzix.gradzixcore.clansExtension.items;

import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WAR_STATE;
import net.dzikoysk.funnyguilds.guild.Guild;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    public static void init() {

    }


    public static ItemStack warResult(Long tempId, Guild userGuild, Guild enemyGuild, WAR_STATE warState, int userPoints, int enemyPoints) {

        ItemStack item = new ItemStack(Material.END_CRYSTAL);

        ItemMeta itemMeta = item.getItemMeta();

        String warResult;

        if(userPoints > enemyPoints) {
            warResult = ChatColor.BOLD + "" + ChatColor.GOLD + "ZWYCIĘSTWO";
        } else if (userPoints < enemyPoints) {
            warResult = ChatColor.BOLD + "" + ChatColor.DARK_RED + "PRZEGRANA";
        } else {
            warResult = ChatColor.BOLD + "" + ChatColor.DARK_GRAY + "REMIS";
        }
        String warStatus = "";
        if(warState.equals(WAR_STATE.FUTURE)) {
            warStatus = ChatColor.BOLD + "" + ChatColor.YELLOW + "WOJNA JESZCZE SIĘ NIE ROZPOCZĘŁA";
        }
        if(warState.equals(WAR_STATE.CURRENT)) {
            warStatus = ChatColor.BOLD + "" + ChatColor.RED + "WOJNA TRWA";
        }
        if(warState.equals(WAR_STATE.FINISHED)) {
            warStatus = ChatColor.BOLD + "" + ChatColor.RED + "WOJNA ZAKOŃCZONA";
        }
        warResult += (" " + tempId);

        itemMeta.setDisplayName(warStatus);

        ArrayList<String> lore = new ArrayList<>();

        lore.add("");

        lore.add(ChatColor.GRAY + "Wojna toczona z " + ChatColor.BOLD + "" + ChatColor.YELLOW + enemyGuild.getTag());
        lore.add("");
        lore.add(ChatColor.GRAY + "Status wojny: " + warResult);
        lore.add("");
        lore.add(ChatColor.GRAY + "Liczba punktów zdobytych przez Twoją gildię: " + ChatColor.DARK_GRAY + userPoints);
        lore.add(ChatColor.GRAY + "Liczba punktów zdobytych przez gildię wroga: " + ChatColor.DARK_GRAY + enemyPoints);
        lore.add("");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;


    }

}
