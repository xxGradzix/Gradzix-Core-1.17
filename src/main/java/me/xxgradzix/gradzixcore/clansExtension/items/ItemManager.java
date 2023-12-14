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

    public static ItemStack currentWarsButton;
    public static void init() {
        createCurrentWarsButton();
    }

    private static void createCurrentWarsButton() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(ChatColor.GRAY + "Aktualne wojny");

        ArrayList<String> lore = new ArrayList<>();

        lore.add("");
        lore.add(ChatColor.GRAY + "Kliknij aby zobaczyć aktualne wojny");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        currentWarsButton = item;

    }
    private static void createFinishedWarsButton() {
        ItemStack item = new ItemStack(Material.SHIELD);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(ChatColor.GRAY + "Zakończone wojny");

        ArrayList<String> lore = new ArrayList<>();

        lore.add("");
        lore.add(ChatColor.GRAY + "Kliknij aby zobaczyć zakończone wojny");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        currentWarsButton = item;

    }

    public static ItemStack currentWarItem(Long tempId, String userGuildTag, String enemyGuildTag, int hostPoints, int enemyPoints) {

        ItemStack item = new ItemStack(Material.END_CRYSTAL);

        ItemMeta itemMeta = item.getItemMeta();

        String warResult;

        if(hostPoints > enemyPoints) {
            warResult = ChatColor.BOLD + "" + ChatColor.GOLD + "Wygrywacie";
        } else if (hostPoints < enemyPoints) {
            warResult = ChatColor.BOLD + "" + ChatColor.DARK_RED + "Przegrywacie";
        } else {
            warResult = ChatColor.BOLD + "" + ChatColor.BLUE + "Remis";
        }

        itemMeta.setDisplayName(warResult);

        ArrayList<String> lore = new ArrayList<>();

        lore.add("");

        lore.add(ChatColor.GRAY + "Wojna toczona z " + ChatColor.BOLD + "" + ChatColor.YELLOW + enemyGuildTag);
        lore.add("");
//        lore.add(ChatColor.GRAY + "Status wojny: " + warResult + ChatColor.GRAY + "!");
//        lore.add("");
        lore.add(ChatColor.GRAY + "Liczba punktów zdobytych przez Twoją gildię: " + ChatColor.DARK_GRAY + hostPoints);
        lore.add(ChatColor.GRAY + "Liczba punktów zdobytych przez gildię wroga: " + ChatColor.DARK_GRAY + enemyPoints);
        lore.add("");
//        lore.add(ChatColor.GRAY + "Wojna zakończy się ");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }
    public static ItemStack endedWarResult(Long tempId, String userGuildTag, String enemyGuildTag, int hostPoints, int enemyPoints, boolean isRewardTaken) {

        ItemStack item = new ItemStack(Material.END_CRYSTAL);

        ItemMeta itemMeta = item.getItemMeta();

        String warResult;
        String rewardStatus;

        if(hostPoints > enemyPoints) {
            warResult = ChatColor.BOLD + "" + ChatColor.GOLD + "Zwycięstwo";
            if(!isRewardTaken) {
                rewardStatus = ChatColor.GRAY + "Kliknij aby odebrać nagrodę";
            } else {
                rewardStatus = ChatColor.GRAY + "Nagroda została już odebrana";
            }
        } else if (hostPoints < enemyPoints) {
            warResult = ChatColor.BOLD + "" + ChatColor.DARK_RED + "Przegrana";
            rewardStatus = ChatColor.GRAY + "Nie otrzymujesz nagrody za przegraną wojnę";
        } else {
            warResult = ChatColor.BOLD + "" + ChatColor.BLUE + "Remis";
            rewardStatus = ChatColor.GRAY + "Nie otrzymujesz nagrody za remis w wojnie";
        }

        itemMeta.setDisplayName(warResult);

        ArrayList<String> lore = new ArrayList<>();

        lore.add("");

        lore.add(ChatColor.GRAY + "Wojna toczona z " + ChatColor.BOLD + "" + ChatColor.YELLOW + enemyGuildTag);
        lore.add("");
//        lore.add(ChatColor.GRAY + "Status wojny: " + warResult + ChatColor.GRAY + "!");
//        lore.add("");
        lore.add(ChatColor.GRAY + "Liczba punktów zdobytych przez Twoją gildię: " + ChatColor.DARK_GRAY + hostPoints);
        lore.add(ChatColor.GRAY + "Liczba punktów zdobytych przez gildię wroga: " + ChatColor.DARK_GRAY + enemyPoints);
        lore.add("");

        lore.add(rewardStatus);
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
        PerkModifierEntity perkModifierEntityByID = PerkModifierEntityManager.getPerkModifierEntityByID(clanPerk);

        if(perkModifierEntityByID == null) throw new IllegalArgumentException("Unknown perk: " + clanPerk);

        int perkPricePerLevel;
        try {
            perkPricePerLevel = perkModifierEntityByID.getPerkPricePerLevel(clanPerks.getClanPerkLevel(clanPerk) + 1);
        } catch (IllegalArgumentException e) {
            lore.add(ChatColor.GRAY + "Perk jest już na maksymalnym poziomie");
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            return item;
        }


        lore.add("Cena: " + ChatColor.DARK_GRAY + perkPricePerLevel + " " + ChatColor.GRAY + "waluty gildyjnej");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

}
