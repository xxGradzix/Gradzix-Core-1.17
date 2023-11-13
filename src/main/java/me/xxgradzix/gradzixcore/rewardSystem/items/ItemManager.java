package me.xxgradzix.gradzixcore.rewardSystem.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static void init() {

    }

    public static ItemStack createVipCollectButton(int rewardAmount) {
        ItemStack item = new ItemStack(Material.GOLD_INGOT, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "VIP");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby odebrać nagrodę");
        lore.add(ChatColor.GRAY + "Ilość nagród do odebrania: " + ChatColor.DARK_GRAY + rewardAmount);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
    public static ItemStack createSvipCollectButton(int rewardAmount) {
        ItemStack item = new ItemStack(Material.GOLD_INGOT, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "SVIP");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby odebrać nagrodę");
        lore.add(ChatColor.GRAY + "Ilość nagród do odebrania: " + ChatColor.DARK_GRAY + rewardAmount);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
    public static ItemStack createAgeCollectButton(int rewardAmount) {
        ItemStack item = new ItemStack(Material.EMERALD, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "AGE");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby odebrać nagrodę");
        lore.add(ChatColor.GRAY + "Ilość nagród do odebrania: " + ChatColor.DARK_GRAY + rewardAmount);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
    public static ItemStack createRewardCollectButton(Material rewardMaterial, String rewardName, int rewardAmount) {
        ItemStack item = new ItemStack(rewardMaterial, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(rewardName);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby odebrać nagrodę");
        lore.add(ChatColor.GRAY + "Ilość nagród do odebrania: " + ChatColor.DARK_GRAY + rewardAmount);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }




}
