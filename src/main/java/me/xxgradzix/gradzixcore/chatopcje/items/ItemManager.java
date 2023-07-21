package me.xxgradzix.gradzixcore.chatopcje.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack deathButtonOff;
    public static ItemStack deathButtonOn;

    public static ItemStack zdrapkaButtonOff;
    public static ItemStack zdrapkaButtonOn;

    public static ItemStack chatButtonOff;
    public static ItemStack chatButtonOn;

    public static ItemStack shopButtonOff;
    public static ItemStack shopButtonOn;


    public static ItemStack blackGlass;
    public static ItemStack greenGlass;
    public static ItemStack limeGlass;



    public static void init() {

        createDeathButtonOn();
        createDeathButtonOff();

        createZdrapkaButtonOn();
        createZdrapkaButtonOff();

        createChatButtonOn();
        createChatButtonOff();

        createShopButtonOn();
        createShopButtonOff();


        createLimeGlass();
        createBlackGlass();
        createGreenGlass();

    }

    private static void createDeathButtonOff() {
        ItemStack item = new ItemStack(Material.SKELETON_SKULL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości o śmierci " + ChatColor.GREEN + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        deathButtonOff = item;
    }
    private static void createDeathButtonOn() {
        ItemStack item = new ItemStack(Material.SKELETON_SKULL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości o śmierci " + ChatColor.RED.toString() + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        deathButtonOn = item;
    }

    private static void createZdrapkaButtonOn() {
        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości o dropie ze zdrapki " + ChatColor.RED.toString() + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        zdrapkaButtonOn = item;
    }

    private static void createZdrapkaButtonOff() {
        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości o dropie ze zdrapki " + ChatColor.GREEN.toString() + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        zdrapkaButtonOff = item;
    }

    private static void createChatButtonOn() {
        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości z chatu " + ChatColor.RED.toString() + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        chatButtonOn = item;
    }

    private static void createChatButtonOff() {
        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości z chatu " + ChatColor.GREEN.toString() + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        chatButtonOff = item;
    }

    private static void createShopButtonOn() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości o kupionych rangach " + ChatColor.RED.toString() + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        shopButtonOn = item;
    }

    private static void createShopButtonOff() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości o kupionych rangach " + ChatColor.GREEN.toString() + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);
//        meta.addEnchant(Enchantment.LUCK, 1, false);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        shopButtonOff = item;
    }

    // szklo

    private static void createBlackGlass() {

        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        blackGlass = item;
    }
    private static void createGreenGlass() {

        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        greenGlass = item;
    }

    private static void createLimeGlass() {

        ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        limeGlass = item;
    }



}
