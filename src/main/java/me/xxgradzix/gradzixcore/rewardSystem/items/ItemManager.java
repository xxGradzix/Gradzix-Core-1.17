package me.xxgradzix.gradzixcore.rewardSystem.items;

import me.xxgradzix.gradzixcore.rewardSystem.database.entities.PlayerRewardsEntity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private static ItemStack caveManKeyBaseItem;
    private static ItemStack magicKeyBaseItem;
    private static ItemStack scratchCardKeyBaseItem;


    public static void init() {
        createCaveManKeyBaseItem();
        createMagicKeyBaseItem();
        createScratchCardBaseItem();
    }

    private static void createCaveManKeyBaseItem() {
        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GREEN + "Klucz Jaskiniowca");
        item.setItemMeta(meta);
        caveManKeyBaseItem = item;
    }
    private static void createMagicKeyBaseItem() {
        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "Klucz Magicznej Skrzyni");
        item.setItemMeta(meta);
        magicKeyBaseItem = item;
    }
    private static void createScratchCardBaseItem() {
        ItemStack item = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Zdrapka");
        item.setItemMeta(meta);
        scratchCardKeyBaseItem = item;
    }

    public static ItemStack createRewardCollectButton(PlayerRewardsEntity.Reward reward, int rewardAmount) {

        ItemStack item = getBaseItem(reward);

        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + " ");
        lore.add(ChatColor.GRAY + "Kliknij aby odebrać nagrodę");
        lore.add(ChatColor.GRAY + "Ilość nagród do odebrania: " + ChatColor.GREEN + rewardAmount);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;

    }

    private static ItemStack getBaseItem(PlayerRewardsEntity.Reward reward) {
        switch (reward) {
            case caveman_key:
                return caveManKeyBaseItem;
            case magic_key:
                return magicKeyBaseItem;
            case scratchcard:
                return scratchCardKeyBaseItem;
            default:
                throw new IllegalStateException("Unexpected value: " + reward);
        }
    }





}
