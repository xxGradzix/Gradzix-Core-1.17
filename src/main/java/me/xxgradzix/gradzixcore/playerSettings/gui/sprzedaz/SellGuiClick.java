package me.xxgradzix.gradzixcore.playerSettings.gui.sprzedaz;

import me.xxgradzix.gradzixcore.playerSettings.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SellGuiClick implements Listener {


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof SellGui)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        int clickedSlot = event.getSlot();

        if (clickedSlot > 8 && clickedSlot < 54 && event.getClickedInventory().getHolder() instanceof SellGui) {
            event.setCancelled(true);
        }

        SellGui gui = (SellGui) event.getInventory().getHolder();

        if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.previousPage)) {
            gui.previousPage(player);
        } else if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.nextPage)) {
            gui.nextPage(player);
        }

        if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.SUNFLOWER)) {
            gui.getInventory().setItem(event.getSlot(), ItemManager.price);
        }

        if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.addOne)) {

            ItemStack item =  gui.getInventory().getItem(event.getSlot() - 9);

            ItemMeta meta = item.getItemMeta();

            String price = meta.getDisplayName().substring(6, meta.getDisplayName().length() - 1).substring(4);

            int priceInt = Integer.parseInt(price);

            priceInt += 1;

            meta.setDisplayName(ChatColor.GRAY + "Cena: " + ChatColor.GREEN + priceInt + "$");

            item.setItemMeta(meta);

            gui.getInventory().setItem(event.getSlot() - 9, item);

        }
        if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.addTen)) {

            ItemStack item =  gui.getInventory().getItem(event.getSlot() - 18);

            ItemMeta meta = item.getItemMeta();

            String price = meta.getDisplayName().substring(6, meta.getDisplayName().length() - 1).substring(4);

            int priceInt = Integer.parseInt(price);

            priceInt += 10;

            meta.setDisplayName(ChatColor.GRAY + "Cena: " + ChatColor.GREEN + priceInt + "$");

            item.setItemMeta(meta);

            gui.getInventory().setItem(event.getSlot() - 18, item);

        }
        if (event.getCurrentItem() != null && event.getCurrentItem().equals(ItemManager.addHundred)) {

            ItemStack item =  gui.getInventory().getItem(event.getSlot() - 27);

            ItemMeta meta = item.getItemMeta();

            String price = meta.getDisplayName().substring(6, meta.getDisplayName().length() - 1).substring(4);

            int priceInt = Integer.parseInt(price);

            priceInt += 100;

            meta.setDisplayName(ChatColor.GRAY + "Cena: " + ChatColor.GREEN + priceInt + "$");

            item.setItemMeta(meta);

            gui.getInventory().setItem(event.getSlot() - 27, item);

        }
    }
}
