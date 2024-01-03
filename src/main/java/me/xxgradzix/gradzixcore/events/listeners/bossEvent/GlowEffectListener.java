package me.xxgradzix.gradzixcore.events.listeners.bossEvent;

import me.xxgradzix.gradzixcore.events.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GlowEffectListener implements Listener {

    @EventHandler
    public void playerKillBossKillerEvent(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if(killer == null) return;
        Player victim = event.getEntity();

        if(!victim.hasPotionEffect(PotionEffectType.GLOWING)) return;
        if(!victim.getInventory().containsAtLeast(ItemManager.mainReward, 1)) return;

        PotionEffect potionEffect = victim.getPotionEffect(PotionEffectType.GLOWING);

        if(potionEffect == null) return;

        killer.addPotionEffect(potionEffect);

    }

}
