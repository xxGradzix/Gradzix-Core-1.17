package me.xxgradzix.gradzixcore.itemPickupPriorities.listeners;


import me.xxgradzix.gradzixcore.itemPickupPriorities.data.DataManager;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

;


public class GiveArmorBackEvent implements Listener {

    public static HashMap<UUID, ArrayList<ItemStack>> returnItems = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeathEvent(PlayerDeathEvent event){
        if(!event.getEntity().getType().equals(EntityType.PLAYER)) return;


        ArrayList<ItemStack> armor = new ArrayList<>();
        for (ItemStack itemStack : event.getEntity().getInventory().getArmorContents()){

            if(itemStack != null) {
                if(shouldExecute()) armor.add(itemStack);
            }
        }
        returnItems.put(event.getEntity().getPlayer().getUniqueId(), armor);

        ArrayList<ItemStack> priorities = (ArrayList<ItemStack>) DataManager.getItemPriorities();

        ArrayList<ItemStack> drops = (ArrayList<ItemStack>) event.getDrops();
        if(!(event.getEntity().getKiller() instanceof Player)) {
            return;
        }

        Player killer = event.getEntity().getKiller();

        drops.removeAll(armor);

        addItemsWithPriorities(killer, drops, priorities);

        event.getDrops().clear();
    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e){

        if(returnItems == null) {
            return;
        }
        ArrayList<ItemStack> armorToGive = returnItems.getOrDefault(e.getPlayer().getUniqueId(), new ArrayList<>());

        Player player = e.getPlayer();
        if(shouldExecute()) {
            player.getInventory().setItemInOffHand(null);

        }
    }
    public static boolean shouldExecute() {

        Random random = new Random();


        double randomValue = random.nextDouble();

        return randomValue < 0.5;
    }

    public void addItemsWithPriorities(Player player, ArrayList<ItemStack> itemsToGive, ArrayList<ItemStack> priorities) {
        PlayerInventory inventory = player.getInventory();

        ArrayList<ItemStack> prioritiesCopy = new ArrayList<>(priorities);

        ArrayList<ItemStack> itemsAfterPriorities = new ArrayList<>(itemsToGive);



        for (ItemStack itemStack : itemsToGive) {
            if(itemStack.containsEnchantment(Enchantment.VANISHING_CURSE)) continue;

            if (prioritiesCopy.contains(itemStack)) {
                prioritiesCopy.remove(itemStack);
                itemsAfterPriorities.remove(itemStack);
                if (inventory.firstEmpty() != -1) {
                    inventory.addItem(itemStack);
                } else {
                    player.getWorld().dropItem(player.getLocation(), itemStack);
                }
            }

        }


        for (ItemStack itemStack : itemsAfterPriorities) {

            if(itemStack.containsEnchantment(Enchantment.VANISHING_CURSE)) continue;

            if (inventory.firstEmpty() != -1) {
                inventory.addItem(itemStack);
            } else {
                player.getWorld().dropItem(player.getLocation(), itemStack);
            }
        }
    }

}
