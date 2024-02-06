package me.xxgradzix.gradzixcore.playerPerks.listeners;

import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PotionEffectAfflictionOnDamage implements Listener {

    private final PlayerPerkEntityManager playerPerksEntityManager;

    public PotionEffectAfflictionOnDamage(PlayerPerkEntityManager playerPerksEntityManager) {
        this.playerPerksEntityManager = playerPerksEntityManager;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        if(!(event.getDamager() instanceof Player)) return;

        Player damaged = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        PlayerPerksEntity damagerPerks = playerPerksEntityManager.getPlayerPerksEntityById(damager.getUniqueId());

        if(shouldApplyEffect(damagerPerks.getPerkTypeLevel(PerkType.POISON))) {
            damaged.addPotionEffect(PotionEffectType.POISON.createEffect(20*3, 1));
            damager.sendMessage(ChatColor.GREEN + "Udało Ci się nałożyć efekt trucizny na przeciwnika!");
        }
        if(shouldApplyEffect(damagerPerks.getPerkTypeLevel(PerkType.SICKNESS))) {
            damaged.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(20*3, 1));
            damager.sendMessage(ChatColor.GREEN + "Udało Ci się nałożyć efekt słabości na przeciwnika!");
        }
        if(shouldApplyEffect(damagerPerks.getPerkTypeLevel(PerkType.SLOWNESS))) {
            damaged.addPotionEffect(PotionEffectType.SLOW.createEffect(20*3, 1));
            damager.sendMessage(ChatColor.GREEN + "Udało Ci się nałożyć efekt spowolnienia na przeciwnika!");
        }
        if(shouldApplyEffect(damagerPerks.getPerkTypeLevel(PerkType.LIFE_STEAL))) {
            double damage = event.getDamage();
            damager.setHealth(damager.getHealth() + (damage / 2));
        }
        if(shouldApplyEffect(damagerPerks.getPerkTypeLevel(PerkType.RESISTANCE))) {
            damager.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(20*3, 1));
        }

    }
    private static boolean shouldApplyEffect(int chance) {
        Random random = new Random();
        double result = random.nextDouble();

        return result <= ((double) chance / 100);
    }

}
