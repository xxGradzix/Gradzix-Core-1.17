package me.xxgradzix.gradzixcore.chatOptions.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack deathButtonOff;
    public static ItemStack deathButtonOn;

    public static ItemStack scratchCardButtonOff;
    public static ItemStack scratchCardButtonOn;

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

        createScratchCardButtonOn();
        createScratchCardButtonOff();

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

        item.setItemMeta(meta);

        deathButtonOn = item;
    }

    private static void createScratchCardButtonOn() {
        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości o dropie ze zdrapki " + ChatColor.RED.toString() + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        scratchCardButtonOn = item;
    }

    private static void createScratchCardButtonOff() {
        ItemStack item = new ItemStack(Material.PAPER, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości o dropie ze zdrapki " + ChatColor.GREEN.toString() + "ON");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby wyłączyć");
        meta.setLore(lore);

        item.setItemMeta(meta);

        scratchCardButtonOff = item;
    }

    private static void createChatButtonOn() {
        ItemStack item = new ItemStack(Material.BOOK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Wiadomości z chatu " + ChatColor.RED.toString() + "OFF");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Kliknij aby włączyć");
        meta.setLore(lore);

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

        item.setItemMeta(meta);

        shopButtonOff = item;
    }

    // glass

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
