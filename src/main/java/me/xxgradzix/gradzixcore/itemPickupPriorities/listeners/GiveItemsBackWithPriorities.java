package me.xxgradzix.gradzixcore.itemPickupPriorities.listeners;

import me.xxgradzix.gradzixcore.itemPickupPriorities.data.DataManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.GroupManager;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.*;

public class GiveItemsBackWithPriorities implements Listener {

    private static HashMap<UUID, ArrayList<ItemStack>> returnItems = new HashMap<>();


    private enum AgePlayGroups {
        GRACZ, VIP, SVIP, AGE
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player victim = event.getEntity();

        LuckPerms luckPerms = LuckPermsProvider.get();

        UserManager userManager = luckPerms.getUserManager();

        User user = userManager.getUser(victim.getUniqueId());


        if(user == null) return;

        double returnPercent = getPlayerReturnPercent(user);

        List<ItemStack> inventoryItems = Arrays.asList(victim.getInventory().getContents());
//        List<ItemStack> armorItems = Arrays.asList(victim.getInventory().getArmorContents());

        ArrayList<ItemStack> itemsToReturn = new ArrayList<>();
        itemsToReturn.addAll(inventoryItems);


        itemsToReturn.removeAll(Collections.singleton(null));

        int amountOfItemsToReturn = (int) Math.round(itemsToReturn.size() * returnPercent);

        ArrayList<ItemStack> itemsForVictim = new ArrayList<>();

        for(int i = 0; i < amountOfItemsToReturn; i++) {
            if(itemsToReturn.isEmpty()) break;
            int randomIndex = new Random().nextInt(itemsToReturn.size());
            itemsForVictim.add(itemsToReturn.get(randomIndex));
            itemsToReturn.remove(randomIndex);
        }


        returnItems.put(victim.getUniqueId(), itemsForVictim);
        event.getDrops().clear();

        Player killer = victim.getKiller();

        if(killer == null) return;
        Bukkit.broadcastMessage("death test final");

        addItemsWithPriorities(killer, itemsToReturn);

    }
    private AgePlayGroups getPlayerGroup(User player) {
        switch (player.getPrimaryGroup()) {
            case "age":
                return AgePlayGroups.AGE;
            case "svip":
                return AgePlayGroups.SVIP;
            case "vip":
                return AgePlayGroups.VIP;
            default:
                return AgePlayGroups.GRACZ;
        }
    }
    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e){

        if(returnItems == null) {
            return;
        }

        ArrayList<ItemStack> itemsToGive = returnItems.getOrDefault(e.getPlayer().getUniqueId(), new ArrayList<>());

        Player player = e.getPlayer();

        addItemsWithPriorities(player, itemsToGive);
    }
    private double getPlayerReturnPercent(User player) {
        switch (getPlayerGroup(player)) {
            case VIP:
                return 0.3;
            case SVIP:
                return 0.4;
            case AGE:
                return 0.5;
            default:
                return 0.2;

        }
    }
    private void addItemsWithPriorities(Player player, ArrayList<ItemStack> items) {
        PlayerInventory inventory = player.getInventory();

        ArrayList<ItemStack> priorities = (ArrayList<ItemStack>) DataManager.getItemPriorities();

        ArrayList<ItemStack> itemsAfterPriorities = new ArrayList<>();

        for(ItemStack itemFromPriorities : priorities) {

            for (ItemStack item : items) {
                if(itemFromPriorities.isSimilar(item)) {
                    itemsAfterPriorities.add(item);
                    items.remove(item);
                }
            }
        }
        itemsAfterPriorities.addAll(items);
        for (ItemStack itemStack : itemsAfterPriorities) {
            if(itemStack.containsEnchantment(Enchantment.VANISHING_CURSE)) continue;
            if (inventory.firstEmpty() != -1) {
                inventory.addItem(itemStack);
            } else {
                player.getWorld().dropItem(player.getLocation(), itemStack);
            }
        }

    }

}