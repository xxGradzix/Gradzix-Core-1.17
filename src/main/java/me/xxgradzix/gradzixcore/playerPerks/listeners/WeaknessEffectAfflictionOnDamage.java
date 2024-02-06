package me.xxgradzix.gradzixcore.playerPerks.listeners;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.*;

public class WeaknessEffectAfflictionOnDamage {

    private static Map<UUID, Integer> weakenedPlayers = new HashMap<>();
    private final PlayerPerkEntityManager playerPerksEntityManager;

    public WeaknessEffectAfflictionOnDamage(PlayerPerkEntityManager playerPerksEntityManager) {
        this.playerPerksEntityManager = playerPerksEntityManager;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {

        if(!(event.getEntity() instanceof Player)) return;
        if(!(event.getDamager() instanceof Player)) return;

        Player damaged = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        if(weakenedPlayers.containsKey(damaged.getUniqueId())) {
            event.setDamage(event.getDamage() * 1.5);
        }

        PlayerPerksEntity damagerPerks = playerPerksEntityManager.getPlayerPerksEntityById(damager.getUniqueId());

        if(!shouldApplyEffect(damagerPerks.getPerkTypeLevel(PerkType.POISON))) {
            return;
        }
        BukkitScheduler scheduler = Bukkit.getScheduler();

        if(weakenedPlayers.containsKey(damaged.getUniqueId())) {
            scheduler.cancelTask(weakenedPlayers.get(damaged.getUniqueId()));
        }

        damaged.addPotionEffect(PotionEffectType.GLOWING.createEffect(20*10, 1));

        int taskId = scheduler.runTaskLater(Gradzix_Core.getInstance(), () -> {
            weakenedPlayers.remove(damaged.getUniqueId());
        }, 20 * 10).getTaskId();

        weakenedPlayers.put(damaged.getUniqueId(), taskId);
        damager.sendMessage(ChatColor.GREEN + "Udało Ci się nałożyć efekt osłabienia na przeciwnika!");

    }
    private static boolean shouldApplyEffect(int chance) {
        Random random = new Random();
        double result = random.nextDouble();

        return result <= ((double) chance / 100);
    }
}
