package me.xxgradzix.gradzixcore.generators.items;

import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorEntity;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorLocationEntity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    public static ItemStack generatorTypeButton;
    public static ItemStack generatorLocationsButton;

    public static void init() {
        createGeneratorTypeButton();
        createGeneratorLocationsButton();
    }

    private static void createGeneratorTypeButton() {

        ItemStack item = new ItemStack(Material.EMERALD_BLOCK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Typy generatorów");

        ArrayList<String> lore = new ArrayList<>();

        lore.add("§7Kliknij lewy przycisk, aby otworzyć menu zarządzania typami generatorów");

        meta.setLore(lore);

        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        generatorTypeButton = item;
    }
    private static void createGeneratorLocationsButton() {

        ItemStack item = new ItemStack(Material.FILLED_MAP, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Pozycje generatorów");

        ArrayList<String> lore = new ArrayList<>();

        lore.add("§7Kliknij lewy przycisk, aby otworzyć menu zarządzania pozycjami generatorów");

        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        generatorLocationsButton = item;
    }
    public static ItemStack createGeneratorTypeButton(GeneratorEntity generator) {
        ItemStack item = new ItemStack(generator.getMaterials().stream().findAny().orElse(Material.BARRIER), 1);

        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(generator.getId() + ": " + generator.getName());
        meta.setDisplayName(generator.getName());
        ArrayList<String> lore = new ArrayList<>();

        lore.add("§7CoolDown: " + generator.getCoolDownSeconds());
        lore.add("§7Kliknij prawy przycisk, aby usunąć typ generatora");
        lore.add("§cUWAGA USUNIECIE TEGO GENERATORA SPOWODUJE USUNIECIE");
        lore.add("§cWSZYSTKICH GENERATOROW UZYWAJACYCH TEGO TYPU");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
    public static ItemStack createGeneratorLocationButton(GeneratorLocationEntity generator) {
        ItemStack item = new ItemStack(Material.MAP, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(String.valueOf(generator.getId()));

        ArrayList<String> lore = new ArrayList<>();

        lore.add("§7Rodzaj generatora: (" + generator.getGenerator().getName() + ")");
        lore.add("§7Pierwszy naroznik generatora znajduje sie na kordach:");
        lore.add("§Dx: " + generator.getMinLocation().getBlockX() +
                "y: " + generator.getMinLocation().getBlockY() +
                "z: " + generator.getMinLocation().getBlockZ());

        lore.add("§7Drugi naroznik generatora znajduje sie na kordach:");
        lore.add("§Dx: " + generator.getMaxLocation().getBlockX() +
                "y: " + generator.getMaxLocation().getBlockY() +
                "z: " + generator.getMaxLocation().getBlockZ());

        lore.add(" ");
        lore.add("§7Kliknij prawy przycisk, aby usunąć typ generatora");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}
