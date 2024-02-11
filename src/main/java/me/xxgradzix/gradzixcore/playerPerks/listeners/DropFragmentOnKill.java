package me.xxgradzix.gradzixcore.playerPerks.listeners;

import me.xxgradzix.gradzixcore.itemPickupPriorities.listeners.GiveItemsBackWithPriorities;
import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import me.xxgradzix.gradzixcore.playerPerks.items.ItemManager;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class DropFragmentOnKill implements Listener {

    private final PlayerPerkEntityManager playerPerksEntityManager;

    public DropFragmentOnKill(PlayerPerkEntityManager playerPerksEntityManager) {
        this.playerPerksEntityManager = playerPerksEntityManager;
    }
    @EventHandler
    public void onKill(PlayerDeathEvent event) {

        Player dead = event.getEntity();
        Player killer = dead.getKiller();

        if(killer == null) return;
        PlayerPerksEntity playerPerksEntityById = playerPerksEntityManager.getPlayerPerksEntityById(killer.getUniqueId());
        double chance = 0.05 + playerPerksEntityById.getPerkTypeLevel(PerkType.PERK_FRAGMENT_DROP);

        if(!(shouldDrop(chance))) return;

        if (killer.getInventory().firstEmpty() != -1) {
            killer.getInventory().addItem(ItemManager.perkFragment);
        } else {
            killer.getWorld().dropItemNaturally(killer.getLocation(), ItemManager.perkFragment);
        }
    }
    public static boolean shouldDrop(double chance) {

        Random random = new Random();
        double result = random.nextDouble();

        return result <= chance;
    }

}
