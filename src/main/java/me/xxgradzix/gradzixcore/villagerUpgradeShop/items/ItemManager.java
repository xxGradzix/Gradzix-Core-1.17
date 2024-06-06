package me.xxgradzix.gradzixcore.villagerUpgradeShop.items;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.globalStatic.TextInputFromChat;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.commands.UpgradeShopEditorCommand;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.DataManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopEntity;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopProductEntity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemManager {

//    public static GuiItem addItemButton;

    private static DataManager dataManager;

    public static void init() {

    }

    public ItemManager(DataManager dataManager) {
        ItemManager.dataManager = dataManager;
        init();
    }

    public static GuiItem createUpgradeShopItem(String name) {

        ItemStack item = new ItemStack(Material.VILLAGER_SPAWN_EGG);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§b" + name);

        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("§7LPM: edycja produktów sklepu");
        lore.add("§7SHIFT + PPM: §b" + "Usuń sklep");

        item.setItemMeta(itemMeta);
        return new GuiItem(item);
    }

    public static GuiItem createProductGuiItem(VillagerUpgradeShopProductEntity product, String shopName) {
        ItemStack item = product.getItem().clone();

        ItemMeta meta = item.getItemMeta();
        List<String> lore;

        if (meta.hasLore()) {
            lore = meta.getLore();
        } else {
            lore = new ArrayList<>();
        }

        lore.add(" ");
        if (product.getNeededItem() != null) {
            lore.add("§7Prezedmiot potrzebny do ulepszenia: §b" + product.getNeededItem().getItemMeta().getDisplayName());
        }
        lore.add("§7Cena ulepszenia: §b" + product.getPrice());

        meta.setLore(lore);
        item.setItemMeta(meta);

        return new GuiItem(item);

    }

    public static GuiItem createProductEditor(Player player, Gui gui, VillagerUpgradeShopProductEntity product, String shopName) {
        ItemStack item = product.getItem().clone();

        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("§7Cena: §b" + product.getPrice());
        if (product.getNeededItem() != null) {
            lore.add("§7Prezedmiot potrzebny do ulepszenia: §b" + product.getNeededItem().getItemMeta().getDisplayName());
        }
        lore.add("§7Slot sklepu: §b" + product.getShopSlot());
        lore.add("§7Nazwa sklepu: §b" + shopName);
        lore.add(" ");

        lore.add("§7UPUSC PRZEDMIOT: §bUstaw przedmiot potrzebny do ulepszenia");
        lore.add("§7PRAWY: §bUstaw cene");
        lore.add("§7LEWY: §bUstaw slot");
        lore.add("§7SHIFT-PRAWY: §bUsuń produkt");

        meta.setLore(lore);
        item.setItemMeta(meta);

        GuiItem guiItem = new GuiItem(item);

        guiItem.setAction(event -> {

            event.setCancelled(true);
            boolean itemInCursor = event.getCursor() != null && event.getCursor().getType() != Material.AIR;
            boolean shiftClick = event.isShiftClick();
            boolean rightClick = event.isRightClick();
            boolean leftClick = event.isLeftClick();


            if (itemInCursor) {
                Bukkit.broadcastMessage(ChatColor.AQUA + "Dodano przedmiot potrzebny do ulepszenia");
                product.setNeededItem(event.getCursor());
                dataManager.createOrUpdateVillagerUpgradeShopProductEntity(product);
                Bukkit.getScheduler().runTaskLater(Gradzix_Core.getInstance(), () -> {
                    UpgradeShopEditorCommand.openEditor(player, product.getShopEntity());
                }, 1);
            }

            if (shiftClick && rightClick && !itemInCursor) {
                Bukkit.broadcastMessage(ChatColor.RED + "Usuwanie produktu...");
                dataManager.deleteVillagerUpgradeShopProductEntity(product.getId());
                Bukkit.getScheduler().runTaskLater(Gradzix_Core.getInstance(), () -> {
                    UpgradeShopEditorCommand.openEditor(player, product.getShopEntity());
                }, 1);
            }

            if (!shiftClick && leftClick && !itemInCursor) {
                Bukkit.broadcastMessage(ChatColor.AQUA + "Podaj slot: ");
                gui.close(event.getWhoClicked());
                TextInputFromChat.getPlayerInput(event.getWhoClicked().getUniqueId()).thenAccept(message -> {
                    product.setShopSlot(Integer.parseInt(message));
                    dataManager.createOrUpdateVillagerUpgradeShopProductEntity(product);
                    Bukkit.broadcastMessage("Slot ustawiony na: " + message);
                    Bukkit.getScheduler().runTaskLater(Gradzix_Core.getInstance(), () -> {
                        UpgradeShopEditorCommand.openEditor(player, product.getShopEntity());
                    }, 1);
                });
            }
            if (rightClick && !shiftClick && !itemInCursor) {
                Bukkit.broadcastMessage(ChatColor.AQUA + "Podaj cene: ");
                gui.close(event.getWhoClicked());
                TextInputFromChat.getPlayerInput(event.getWhoClicked().getUniqueId()).thenAccept(message -> {
                    product.setPrice(Double.parseDouble(message));
                    dataManager.createOrUpdateVillagerUpgradeShopProductEntity(product);
                    Bukkit.broadcastMessage("Cena ustawiona na: " + message);

                    Bukkit.getScheduler().runTaskLater(Gradzix_Core.getInstance(), () -> {
                        UpgradeShopEditorCommand.openEditor(player, product.getShopEntity());
                    }, 1);
                });
            }
            Optional<VillagerUpgradeShopEntity> shopEntityByName = dataManager.getShopEntityByName(shopName);

        });
        return guiItem;

    }

    public static GuiItem createAddItemButton() {

        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§bDodaj przedmiot");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("Trzymaj przedmiot w ręce i kliknij aby dodać go do sklepu");
        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        return new GuiItem(item);
    }


}
