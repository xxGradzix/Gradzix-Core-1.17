package me.xxgradzix.gradzixcore.playerPerks.listeners;

import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.DataManager;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import me.xxgradzix.gradzixcore.playerPerks.items.ItemManager;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class DropFragmentOnKill implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent event) {

        Player dead = event.getEntity();
        Player killer = dead.getKiller();

        if(killer == null) return;


        double chance = 0.1;

        if(LuckPermsProvider.get().getUserManager().getUser(killer.getUniqueId()) != null) {
            User user = LuckPermsProvider.get().getUserManager().getUser(killer.getUniqueId());
            chance = getPlayerRewardChance(user);
        }

        chance += (DataManager.getPerkEntity(killer).getPerkTypeLevel(PerkType.PERK_FRAGMENT_DROP) / 100);

        if(!(shouldDrop(chance))) return;

        if (killer.getInventory().firstEmpty() != -1) {
            killer.getInventory().addItem(ItemManager.perkFragment);
        } else {
            killer.getWorld().dropItemNaturally(killer.getLocation(), ItemManager.perkFragment);
        }
    }
    private static double getPlayerRewardChance(User player) {
        switch (player.getPrimaryGroup()) {
            case "age":
                return 0.5;
            case "svip":
                return 0.5;
            case "vip":
                return 0.2;
            default:
                return 0.1;
        }
    }
    public static boolean shouldDrop(double chance) {

        Random random = new Random();
        double result = random.nextDouble();

        return result <= chance;
    }

}
