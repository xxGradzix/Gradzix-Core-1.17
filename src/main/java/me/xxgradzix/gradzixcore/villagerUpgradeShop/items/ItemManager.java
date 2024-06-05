package me.xxgradzix.gradzixcore.villagerUpgradeShop.items;

import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.globalStatic.TextInputFromChat;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.DataManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopProductEntity;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ItemManager {

//    public static GuiItem addItemButton;

    private static DataManager dataManager;

    public ItemManager(DataManager dataManager) {
        ItemManager.dataManager = dataManager;
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
//    public static GuiItem createShowItem(ItemStack item, double price, ItemStack neededItem, int shopSlot, String shopName) {
    public static GuiItem createShowItem(VillagerUpgradeShopProductEntity product, String shopName) {
        ItemStack item = product.getItem();

        ItemStack itemStack = new ItemStack(item);

        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("§7Cena: §b" + product.getPrice();
        lore.add("§7Prezedmiot potrzebny do ulepszenia: §b" + product.getNeededItem().getItemMeta().getDisplayName());
        lore.add("§7Slot sklepu: §b" + product.getShopSlot();
        lore.add("§7Nazwa sklepu: §b" + shopName);

        meta.setLore(lore);
        item.setItemMeta(meta);

        GuiItem guiItem = new GuiItem(item);

        guiItem.setAction(event -> {

            boolean itemInCursor = event.getCursor() != null && event.getCursor().getType() != Material.AIR;
            boolean shiftClick = event.isShiftClick();
            boolean rightClick = event.isRightClick();
            boolean leftClick = event.isLeftClick();


            if (itemInCursor) {
                Bukkit.broadcastMessage("dodaj item potrzebny do ulepszenia");
                product.setNeededItem(event.getCursor());
                dataManager.createOrUpdateVillagerUpgradeShopProductEntity(product);
                return;
            }

            if (shiftClick && rightClick) {
                Bukkit.broadcastMessage("USUN");
                dataManager.deleteVillagerUpgradeShopProductEntity(product.getId());
                return;
            }

            if (!shiftClick && leftClick) {
                Bukkit.broadcastMessage("ustaw slot");
                return;
            }
            if (rightClick && !shiftClick) {
                Bukkit.broadcastMessage("ustaw cene");
//                CompletableFuture<String> future = new CompletableFuture<>();
//                playerInputMap.put(player, future);
//                future.thenAccept(message -> {
//                    // This is where you handle the player's input
//                    Bukkit.broadcastMessage("Player " + player.getName() + " set the price to " + message);
//                });;
                TextInputFromChat.getPlayerInput(event.getWhoClicked().getUniqueId()).thenAccept(message -> {
                    product.setPrice(Double.parseDouble(message));
                    dataManager.createOrUpdateVillagerUpgradeShopProductEntity(product);
                    Bukkit.broadcastMessage("Cena ustawiona na: " + message);
                });
            }
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
