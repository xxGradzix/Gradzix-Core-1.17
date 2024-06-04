package me.xxgradzix.gradzixcore.warps.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    public static ItemStack playerSafeRegion;
    public static ItemStack vipSVipSafeRegion;
    public static ItemStack ageSafeRegion;
    public static ItemStack pvpArena;
    public static ItemStack afkArea;
    public static ItemStack chestArea;
    public static ItemStack armorArea;

    public static void init() {
        createPlayerSafeRegion();
        createVipSvipSafeRegion();
        createAgeSafeRegion();
        createPvpArena();
        createAfkArea();
        createChestArea();
        createArmorArea();
    }

    private static void createPlayerSafeRegion() {
        ItemStack item = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "Strefa bezpieczna Gracza");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "Kliknij, aby przeteleportować się");
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        playerSafeRegion = item;
    }
    private static void createVipSvipSafeRegion() {
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "Strefa bezpieczna VIP/SVIP");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "Kliknij, aby przeteleportować się");
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        vipSVipSafeRegion = item;
    }
    private static void createAgeSafeRegion() {
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "Strefa bezpieczna UNI");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "Kliknij, aby przeteleportować się");
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        ageSafeRegion = item;
    }

    private static void createPvpArena() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_RED + "Arena PVP");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "Kliknij, aby przeteleportować się");
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        pvpArena = item;
    }

    private static void createAfkArea() {
        ItemStack item = new ItemStack(Material.CLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_AQUA + "Strefa AFK");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "Kliknij, aby przeteleportować się");
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        afkArea = item;
    }

    private static void createChestArea() {
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Skrzynki");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "Kliknij, aby przeteleportować się");
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        chestArea = item;
    }

    private static void createArmorArea() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_BLUE + "Zbroje");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.DARK_GRAY + "» " + ChatColor.GRAY + "Kliknij, aby przeteleportować się");
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        armorArea = item;
    }


}
