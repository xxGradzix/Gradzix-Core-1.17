package me.xxgradzix.gradzixcore.serverconfig.listeners;

import me.xxgradzix.gradzixcore.serverconfig.files.ConfigServera;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

public class PickUpPriority implements Listener {

    @EventHandler
    public void onPlayerPickup(PlayerDeathEvent event) {

        ArrayList<ItemStack> priorities = ConfigServera.getItemPriorities();

        if(!(event.getEntity().getKiller() instanceof Player)) return;
        Player killer = event.getEntity().getKiller();

        ArrayList<ItemStack> drops = (ArrayList<ItemStack>) event.getDrops();

        addItemsWithPriorities(killer, drops, priorities);

        event.getDrops().clear();


    }
    public void addItemsWithPriorities(Player player, ArrayList<ItemStack> itemsToGive, ArrayList<ItemStack> priorities) {
        PlayerInventory inventory = player.getInventory();

        ArrayList<ItemStack> priorytetyKopia = new ArrayList<>(priorities);

        ArrayList<ItemStack> itemsAfterPriorities = new ArrayList<>(itemsToGive);



        for (ItemStack itemStack : itemsToGive) {

            if (priorytetyKopia.contains(itemStack)) {
                priorytetyKopia.remove(itemStack);
                itemsAfterPriorities.remove(itemStack);
                if (inventory.firstEmpty() != -1) {
                    inventory.addItem(itemStack);
                } else {
                    player.getWorld().dropItem(player.getLocation(), itemStack);
                }
            }

        }


        for (ItemStack itemStack : itemsAfterPriorities) {
            if (inventory.firstEmpty() != -1) {
                inventory.addItem(itemStack);
            } else {
                player.getWorld().dropItem(player.getLocation(), itemStack);
            }
        }
    }


    private int getFreeSlots(Player player) {
        Inventory inventory = player.getInventory();

        ItemStack[] armor = player.getInventory().getArmorContents();



        int freeSlots = 0;
        for(ItemStack itemStack : inventory.getContents()) {

            if(itemStack == null || itemStack.getType() == Material.AIR) {
                freeSlots++;
            }

        }
        for (ItemStack itemStack : armor) {
            if (itemStack == null || itemStack.getType().isAir()) {
                freeSlots--;
            }
        }
        return freeSlots;
    }

}
