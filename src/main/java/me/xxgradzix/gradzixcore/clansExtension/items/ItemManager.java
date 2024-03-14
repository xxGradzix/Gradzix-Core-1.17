package me.xxgradzix.gradzixcore.clansExtension.items;

import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerk;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerksEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.PerkModifierEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.PerkModifierEntityManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    public static ItemStack currentWarsButton;
    public static ItemStack finishedWarsButton;

    public static void init() {
        createActiveWarsButton();
        createFinishedWarsButton();
    }

    private static void createActiveWarsButton() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§dAktywne wojny");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§8» §7Kliknij aby zobaczyć status wojny");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        currentWarsButton = item;
    }

    private static void createFinishedWarsButton() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§5Zakończone wojny");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§8» §7Kliknij aby zobaczyć zakończone wojny");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        finishedWarsButton = item;
    }

    public static ItemStack currentWarItem(Long tempId, String userGuildTag, String enemyGuildTag, int hostPoints, int enemyPoints) {

        ItemStack item = new ItemStack(Material.END_CRYSTAL);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName("§4Trwa wojna");

        ArrayList<String> lore = new ArrayList<>();

        lore.add("");
        lore.add("§8» §7Liczba zabitych osób przez twój klan§8: §2" + hostPoints);
        lore.add("§8» §7Liczba zabitych osób przez wrogi klan§8: §2" + enemyPoints);

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }
    public static ItemStack endedWarResult(int hostPoints, int enemyPoints, boolean isRewardTaken) {

        ItemStack item = new ItemStack(Material.END_CRYSTAL);

        ItemMeta itemMeta = item.getItemMeta();

        String warResult;
        String rewardStatus;

        if(hostPoints > enemyPoints) {
            warResult = "&aWygrana wojna";
            if(!isRewardTaken) {
                rewardStatus = "&8» &aKliknij aby odebrać nagrodę za wygraną wojnę";
            } else {
                rewardStatus = "&8» &eNagroda została już odebrana";
            }
        } else if (hostPoints < enemyPoints) {
            warResult = "&cPrzegrana wojna";
            rewardStatus = "&8» &cNie otrzymujesz nagrody za przegraną w wojnie";
        } else {
            warResult = "&9Remis";
            rewardStatus = "&8» &9Nie otrzymujesz nagrody za remis w wojnie";
        }

        itemMeta.setDisplayName(warResult);

        ArrayList<String> lore = new ArrayList<>();

        lore.add("");

        lore.add("&8» &7Liczba zabitych osób przez twój klan&8: &2(liczba)" + hostPoints);
        lore.add("&8» &7Liczba zabitych osób przez wrogi klan&8: &2(liczba)" + enemyPoints);
        lore.add("&8» &7Osoba która zabiła najwięcej osób&8: &e" + "WKRÓTCE");
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
