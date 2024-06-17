package me.xxgradzix.gradzixcore.playerPerks.listeners;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.DataManager;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import me.xxgradzix.gradzixcore.playerPerks.items.ItemManager;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.*;

public class DropFragmentOnKill implements Listener {

    private static HashMap<UUID, Set<UUID>> killedPlayers = new HashMap<>();

    @EventHandler
    public void onKill(PlayerDeathEvent event) {

        Player dead = event.getEntity();
        Player killer = dead.getKiller();

        if(killer == null) return;

        Set<UUID> killedPlayersSet = killedPlayers.getOrDefault(killer.getUniqueId(), new HashSet<>());

        if(killedPlayersSet.contains(dead.getUniqueId())) return;

        double chance = 0.1;

        if(LuckPermsProvider.get().getUserManager().getUser(killer.getUniqueId()) != null) {
            User user = LuckPermsProvider.get().getUserManager().getUser(killer.getUniqueId());
            chance = getPlayerRewardChance(user);
        }

        chance += ((double) DataManager.getPerkEntity(killer).getPerkTypeLevel(PerkType.PERK_FRAGMENT_DROP) / 100);

        if(!(shouldDrop(chance))) return;

        if (killer.getInventory().firstEmpty() != -1) {
            killer.getInventory().addItem(ItemManager.perkFragment);
        } else {
            killer.getWorld().dropItemNaturally(killer.getLocation(), ItemManager.perkFragment);
        }

        killedPlayersSet.add(dead.getUniqueId());
        killedPlayers.put(killer.getUniqueId(), killedPlayersSet);
        Bukkit.getScheduler().runTaskLaterAsynchronously(Gradzix_Core.getInstance(), () -> {
            killedPlayersSet.remove(dead.getUniqueId());
            killedPlayers.put(killer.getUniqueId(), killedPlayersSet);
        }, 20 * 60 * 5);

    }
    private static double getPlayerRewardChance(User player) {
        switch (player.getPrimaryGroup()) {
            case "uni":
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
