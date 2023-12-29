package me.xxgradzix.gradzixcore.serverconfig.listeners.protectionEnchantRework;

import me.xxgradzix.gradzixcore.serverconfig.data.DataManager;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageEvent implements Listener {


    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();

            ItemStack[] armor = player.getInventory().getArmorContents();

            int protLevel = 0;

            for(ItemStack itemStack : armor) {
                if(itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasEnchant(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                    protLevel += itemStack.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
                }
            }

            double oldDamage = event.getDamage();

            double damageReduction = protLevel * 0.17;


            double newDamage = oldDamage - damageReduction;

            if(newDamage < 1) newDamage = 1;


            double serverDamageMultiplier = DataManager.getServerDamageMultiplier();

            event.setDamage(newDamage * serverDamageMultiplier);
        }
    }

}
