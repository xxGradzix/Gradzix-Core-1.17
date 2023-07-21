package me.xxgradzix.gradzixcore.ustawienia.listeners;

import me.xxgradzix.gradzixcore.ustawienia.files.UstawieniaOpcjeConfigFile;
import me.xxgradzix.gradzixcore.ustawienia.files.WymianaUstawieniaItemsConfigFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class BlockBreakWymiana implements Listener {


    @EventHandler
    public void onItemHeldChange(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (!UstawieniaOpcjeConfigFile.getAutoWymianaStatus(player)) return;

        HashMap<ItemStack, ItemStack> mapa = (HashMap<ItemStack, ItemStack>) WymianaUstawieniaItemsConfigFile.getAllItems();


        // Sprawdź, czy nowy przedmiot jest kluczem do wymiany

        for (ItemStack keyItem : mapa.keySet()) {
            while (player.getInventory().containsAtLeast(keyItem, keyItem.getAmount())) {
                int freeSlots = 0;

                for (ItemStack item : player.getInventory().getContents()) {
                    if (item == null) {
                        freeSlots++;
                    }
                }

                if (freeSlots >= 1) {
                    // Usuń przedmiot klucza
                    player.getInventory().removeItem(keyItem);
                    // Dodaj przedmiot wartości
                    player.getInventory().addItem(mapa.get(keyItem));
                    // Zaktualizuj ekwipunek gracza
                    player.updateInventory();
                }
            }
        }

    }
}
