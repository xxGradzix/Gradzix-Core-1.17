package me.xxgradzix.gradzixcore.playerPerks.listeners;

import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import me.xxgradzix.gradzixcore.playerPerks.items.ItemManager;
import me.xxgradzix.gradzixcore.playerPerks.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class BookClickIncreaseLevel implements Listener {

    private final PlayerPerkEntityManager playerPerksEntityManager;

    public BookClickIncreaseLevel(PlayerPerkEntityManager playerPerksEntityManager) {
        this.playerPerksEntityManager = playerPerksEntityManager;
    }

    @EventHandler
    public void onBookClick(PlayerInteractEvent event) {

        if(event.getAction().equals(Action.LEFT_CLICK_AIR) ||
                event.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;

        ItemStack item = event.getItem();
        if(item == null) return;

        PerkType perkType = getPerkTypeByPerkBook(item);
//        PerkType perkType = PerkType.STRENGTH;

        if (perkType == null) return;

        Player player = event.getPlayer();

        try {
            upgradePerk(player, perkType);
        } catch (RuntimeException e) {
            player.sendMessage(Messages.PERK_MAX_LEVEL);
        }
    }
    private PerkType getPerkTypeByPerkBook(ItemStack item) {

        if (item.isSimilar(ItemManager.strengthPerkBook)) return PerkType.STRENGTH;
        if (item.isSimilar(ItemManager.poisonPerkBook)) return PerkType.POISON;
        if (item.isSimilar(ItemManager.resistancePerkBook)) return PerkType.RESISTANCE;
        if (item.isSimilar(ItemManager.lifeStealPerkBook)) return PerkType.LIFE_STEAL;
        if (item.isSimilar(ItemManager.sicknessPerkBook)) return PerkType.SICKNESS;
        if (item.isSimilar(ItemManager.additionalHeartsPerkBook)) return PerkType.ADDITIONAL_HEARTS;
        if (item.isSimilar(ItemManager.weaknessPerkBook)) return PerkType.WEAKNESS;
        if (item.isSimilar(ItemManager.slownessPerkBook)) return PerkType.SLOWNESS;
        return null;
    }
    private void upgradePerk(Player player, PerkType perkType) throws RuntimeException {
        PlayerPerksEntity playerPerksEntity = playerPerksEntityManager.getPlayerPerksEntityById(player.getUniqueId());
        switch (perkType) {
            case STRENGTH: {
                playerPerksEntity.increasePerkLevelRandomly(PerkType.STRENGTH);
                removeItems(player, ItemManager.strengthPerkBook, 1);
                player.sendMessage(Messages.UPGRADED_STRENGTH_PERK + ChatColor.DARK_RED + " +" + playerPerksEntity.getPerkTypeLevel(PerkType.STRENGTH) + "%");
            }
            break;
            case POISON: {
                playerPerksEntity.increasePerkLevelRandomly(PerkType.POISON);
                removeItems(player, ItemManager.poisonPerkBook, 1);
                player.sendMessage(Messages.UPGRADED_POISON_PERK + ChatColor.GREEN + " +" + playerPerksEntity.getPerkTypeLevel(PerkType.POISON) + "%");
            }
            break;
            case RESISTANCE: {
                playerPerksEntity.increasePerkLevelRandomly(PerkType.RESISTANCE);
                removeItems(player, ItemManager.resistancePerkBook, 1);
                player.sendMessage(Messages.UPGRADED_RESISTANCE_PERK + ChatColor.GOLD + " +" + playerPerksEntity.getPerkTypeLevel(PerkType.RESISTANCE) + "%");
            }
            break;
            case LIFE_STEAL: {
                playerPerksEntity.increasePerkLevelRandomly(PerkType.LIFE_STEAL);
                removeItems(player, ItemManager.lifeStealPerkBook, 1);
                player.sendMessage(Messages.UPGRADED_LIFE_STEAL_PERK + ChatColor.DARK_PURPLE + " +" + playerPerksEntity.getPerkTypeLevel(PerkType.LIFE_STEAL) + "%");
            }
            break;
            case SICKNESS: {
                playerPerksEntity.increasePerkLevelRandomly(PerkType.SICKNESS);
                removeItems(player, ItemManager.sicknessPerkBook, 1);
                player.sendMessage(Messages.UPGRADED_SICKNESS_PERK + ChatColor.DARK_GRAY + " +" + playerPerksEntity.getPerkTypeLevel(PerkType.SICKNESS) + "%");
            }
            break;
            case ADDITIONAL_HEARTS: {
                playerPerksEntity.increasePerkLevelRandomly(PerkType.ADDITIONAL_HEARTS);
                removeItems(player, ItemManager.additionalHeartsPerkBook, 1);
                player.sendMessage(Messages.UPGRADED_ADDITIONAL_HEARTS + ChatColor.DARK_RED + " +" + playerPerksEntity.getPerkTypeLevel(PerkType.ADDITIONAL_HEARTS) + "<3");

                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerPerksEntity.getPerkTypeLevel(PerkType.ADDITIONAL_HEARTS) + 20);
            }
            break;
            case WEAKNESS: {
                playerPerksEntity.increasePerkLevelRandomly(PerkType.WEAKNESS);
                removeItems(player, ItemManager.weaknessPerkBook, 1);
                player.sendMessage(Messages.UPGRADED_WEAKNESS_PERK + ChatColor.YELLOW + " +" + playerPerksEntity.getPerkTypeLevel(PerkType.WEAKNESS) + "%");
            }
            break;
            case SLOWNESS: {
                playerPerksEntity.increasePerkLevelRandomly(PerkType.SLOWNESS);
                removeItems(player, ItemManager.slownessPerkBook, 1);
                player.sendMessage(Messages.UPGRADED_SLOWNESS_PERK + ChatColor.BLUE + " +" + playerPerksEntity.getPerkTypeLevel(PerkType.SLOWNESS) + "%");
            }
            break;

        }
        playerPerksEntityManager.createOrUpdatePlayerPerksEntity(playerPerksEntity);
    }
    private void removeItems(Player player, ItemStack itemStack, int amount) {
        for(int i = 0; i < amount; i++) {
            player.getInventory().removeItem(itemStack);
        }
    }

}
