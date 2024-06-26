//package me.xxgradzix.gradzixcore.itemPickupPriorities.listeners;
//
//
//import me.xxgradzix.gradzixcore.itemPickupPriorities.ItemPickupPriorities;
//import org.bukkit.enchantments.Enchantment;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.entity.PlayerDeathEvent;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.PlayerInventory;
//
//import java.util.ArrayList;
//
//public class PickUpPriority implements Listener {
//
//    @EventHandler
//    public void onPlayerPickup(PlayerDeathEvent event) {
//
//        if(!(event.getEntity().getKiller() instanceof Player)) return;
//        Player killer = event.getEntity().getKiller();
//
//        ArrayList<ItemStack> drops = (ArrayList<ItemStack>) event.getDrops();
//
//        addItemsWithPriorities(killer, drops, ItemPickupPriorities.getItemPriorities());
//
//        event.getDrops().clear();
//
//
//    }
//    public void addItemsWithPriorities(Player player, ArrayList<ItemStack> itemsToGive, ArrayList<ItemStack> priorities) {
//        PlayerInventory inventory = player.getInventory();
//
//        ArrayList<ItemStack> prioritiesCopy = new ArrayList<>(priorities);
//
//        ArrayList<ItemStack> itemsAfterPriorities = new ArrayList<>(itemsToGive);
//
//        for (ItemStack itemStack : itemsToGive) {
//            if(itemStack.containsEnchantment(Enchantment.VANISHING_CURSE)) continue;
//
//            if (prioritiesCopy.contains(itemStack)) {
//                prioritiesCopy.remove(itemStack);
//                itemsAfterPriorities.remove(itemStack);
//                if (inventory.firstEmpty() != -1) {
//                    inventory.addItem(itemStack);
//                } else {
//                    player.getWorld().dropItem(player.getLocation(), itemStack);
//                }
//            }
//
//        }
//
//
//        for (ItemStack itemStack : itemsAfterPriorities) {
//            if(itemStack.containsEnchantment(Enchantment.VANISHING_CURSE)) continue;
//            if (inventory.firstEmpty() != -1) {
//                inventory.addItem(itemStack);
//            } else {
//                player.getWorld().dropItem(player.getLocation(), itemStack);
//            }
//        }
//    }
//
//}
