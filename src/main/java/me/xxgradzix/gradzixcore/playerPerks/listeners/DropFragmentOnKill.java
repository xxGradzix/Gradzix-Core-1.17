package me.xxgradzix.gradzixcore.playerPerks.listeners;

import me.xxgradzix.gradzixcore.itemPickupPriorities.listeners.GiveItemsBackWithPriorities;
import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import me.xxgradzix.gradzixcore.playerPerks.items.ItemManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.nio.Buffer;
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
        double chance = 0.1;
        if(LuckPermsProvider.get().getUserManager().getUser(killer.getUniqueId()) != null) {
            User user = LuckPermsProvider.get().getUserManager().getUser(killer.getUniqueId());
            chance = getPlayerRewardChance(user);
        }
        chance += (playerPerksEntityById.getPerkTypeLevel(PerkType.PERK_FRAGMENT_DROP) / 100);

//        Bukkit.broadcastMessage("Chance: " + chance);

        if(!(shouldDrop(chance))) return;

//        Bukkit.broadcastMessage("Dropped");

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

//        Bukkit.broadcastMessage("____________");
//        Bukkit.broadcastMessage("Result: " + result);
//        Bukkit.broadcastMessage("Chance: " + chance);
//        Bukkit.broadcastMessage("____________");

        return result <= chance;
    }

}