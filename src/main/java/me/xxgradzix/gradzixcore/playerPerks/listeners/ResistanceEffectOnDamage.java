package me.xxgradzix.gradzixcore.playerPerks.listeners;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class ResistanceEffectOnDamage implements Listener {

    private static Map<UUID, Integer> resistanePlayers = new HashMap<>();

    private final BukkitScheduler scheduler = Bukkit.getScheduler();


    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {

        if(!(event.getEntity() instanceof Player)) return;
        if(!(event.getDamager() instanceof Player)) return;

        Player damaged = (Player) event.getEntity();

        if(resistanePlayers.containsKey(damaged.getUniqueId())) {
            event.setDamage(event.getDamage() * 0.8);
        }

        if(!shouldApplyEffect(DataManager.getPerkEntity(damaged).getPerkTypeLevel(PerkType.RESISTANCE))) return;

        if(resistanePlayers.containsKey(damaged.getUniqueId())) {
            scheduler.cancelTask(resistanePlayers.get(damaged.getUniqueId()));
        }

//        damaged.addPotionEffect(PotionEffectType.GLOWING.createEffect(20 * Gradzix_Core.WEAKNESS_EFFECT_DURATION_TIME_SECONDS, 1));

        int taskId = scheduler.runTaskLater(Gradzix_Core.getInstance(), () -> {
            resistanePlayers.remove(damaged.getUniqueId());
        }, 20 * 3).getTaskId();

        resistanePlayers.put(damaged.getUniqueId(), taskId);
        damaged.sendMessage(ChatColor.GREEN + "Otrzymałeś efekt odporności!");

    }
    private static boolean shouldApplyEffect(int chance) {
        Random random = new Random();
        double result = random.nextDouble();

        return result <= ((double) chance / 100);
    }

}
