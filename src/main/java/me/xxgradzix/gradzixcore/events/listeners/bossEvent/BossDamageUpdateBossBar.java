package me.xxgradzix.gradzixcore.events.listeners.bossEvent;

import me.xxgradzix.gradzixcore.events.Events;
import me.xxgradzix.gradzixcore.events.commands.StartEvent;
import me.xxgradzix.gradzixcore.events.managers.BossManager;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BossDamageUpdateBossBar implements Listener {

    @EventHandler
    public void onBossClick(EntityInteractEvent event) {
        if(!Events.isBossSpawned()) return;

        if(BossManager.isEntityBoss(event.getEntity())) {
            Bukkit.broadcastMessage("Boss health: " + ((int) BossManager.getBoss().getHealth()));
        }
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(!Events.isBossSpawned()) return;

        if(BossManager.isEntityBoss(event.getEntity())) {
            // broadcast boss health before damage and after damage
            PiglinBrute boss = BossManager.getBoss();

            double healthBefore = boss.getHealth();
            double health = boss.getHealth() - event.getFinalDamage();
            double maxHealth = boss.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getBaseValue();

            int healthMarker = (int) (maxHealth / 10.0);

            int healthPart = (int) (healthBefore / healthMarker);

            if(healthBefore > healthMarker * healthPart && health <= healthMarker * healthPart) {
//                Bukkit.broadcastMessage(ChatColor.RED + "Utracono co najmniej 10% maksymalnego zdrowia!");
                BossManager.dropSmallReward();
            }

            BossManager.updateBossBar();
        }

    }
    private boolean isHealthLossGreaterThanOrEqual(double healthBefore, double healthAfter, double maxHealth, double percentage) {
        double healthLoss = healthBefore - healthAfter;
        double lossPercentage = (healthLoss / maxHealth) * 100.0;
        return lossPercentage >= percentage;
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if(!Events.isBossSpawned()) return;
        if(BossManager.isEntityBoss(event.getEntity())) {
            Player killer = event.getEntity().getKiller();

            if(killer != null) BossManager.giveFullReward(killer);

            BossManager.removeBossBar();
            StartEvent.cancelTask(StartEvent.EVENT_NAME.BOSS);
        }

    }

}
