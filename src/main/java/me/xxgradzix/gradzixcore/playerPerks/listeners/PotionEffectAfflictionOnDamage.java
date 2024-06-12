package me.xxgradzix.gradzixcore.playerPerks.listeners;

import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PotionEffectAfflictionOnDamage implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        if(!(event.getDamager() instanceof Player)) return;

        Player damaged = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        if(shouldApplyEffect(DataManager.getPerkEntity(damager).getPerkTypeLevel(PerkType.POISON))) {
            damaged.addPotionEffect(PotionEffectType.POISON.createEffect(20*3, 1));
            damager.sendMessage(ChatColor.GREEN + "Udało Ci się nałożyć efekt trucizny na przeciwnika!");
        }
        if(shouldApplyEffect(DataManager.getPerkEntity(damager).getPerkTypeLevel(PerkType.SLOWNESS))) {
            damaged.addPotionEffect(PotionEffectType.SLOW.createEffect(20*3, 1));
            damager.sendMessage(ChatColor.GREEN + "Udało Ci się nałożyć efekt spowolnienia na przeciwnika!");
        }
        if(shouldApplyEffect(DataManager.getPerkEntity(damager).getPerkTypeLevel(PerkType.LIFE_STEAL))) {
            double damage = event.getDamage();
            double healthToSEt = (damager.getHealth() + (damage / 2));
            if(healthToSEt > 20) {
                healthToSEt = 20;
            }
            damager.setHealth(healthToSEt);
        }
        if(shouldApplyEffect(DataManager.getPerkEntity(damager).getPerkTypeLevel(PerkType.RESISTANCE))) {
            damager.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(20*3, 1));
        }

    }
    private static boolean shouldApplyEffect(int chance) {
        Random random = new Random();
        double result = random.nextDouble();

        return result <= ((double) chance / 100);
    }

}
