package me.xxgradzix.gradzixcore.clansExtension.items;

import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerk;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerksEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.PerkModifierEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.PerkModifierEntityManager;
import net.dzikoysk.funnyguilds.guild.Guild;
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

        itemMeta.setDisplayName(ChatColor.GRAY + "Wojna z " + ChatColor.YELLOW + enemyGuildTag + "!");

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

    public static ItemStack createPerkUpgradeButtonForGuild(ClanPerksEntity clanPerks, ClanPerk clanPerk) {

        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(ChatColor.GRAY + "Perk " + ChatColor.YELLOW + clanPerk.name());


        ArrayList<String> lore = new ArrayList<>();

        lore.add("");

        lore.add(ChatColor.GRAY + "Poziom: " + ChatColor.DARK_GRAY + clanPerks.getClanPerkLevel(clanPerk));
        lore.add("");
        lore.add(ChatColor.GRAY + "Kliknij aby ulepszyć!");
        lore.add("");
        lore.add("Cena: " + ChatColor.DARK_GRAY + PerkModifierEntityManager.getPerkModifierEntityByID(clanPerk).getPerkPricePerLevel(clanPerks.getClanPerkLevel(clanPerk) + 1) + " " + ChatColor.GRAY + "waluty gildyjnej");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

}
