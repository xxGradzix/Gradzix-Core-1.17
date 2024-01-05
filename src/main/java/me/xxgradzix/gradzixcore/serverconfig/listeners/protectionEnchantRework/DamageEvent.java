package me.xxgradzix.gradzixcore.serverconfig.listeners.protectionEnchantRework;

import me.xxgradzix.gradzixcore.serverconfig.data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageEvent implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player) || !(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        ItemStack[] armor = player.getInventory().getArmorContents();

        int protLevel = 0;

        for(ItemStack itemStack : armor) {
            if(itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasEnchant(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                protLevel += itemStack.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
            }
        }

        double baseDamage = event.getDamage();

        double damageReduction = protLevel * 0.17;

        double newDamage = baseDamage - damageReduction;

        if(newDamage < 1) newDamage = 1;

        // TODO there can be a problem with memory usage beacuse database is called every time when player is damaged

        double serverDamageMultiplier = DataManager.getServerDamageMultiplier();

        event.setDamage(newDamage * serverDamageMultiplier);

    }


}
