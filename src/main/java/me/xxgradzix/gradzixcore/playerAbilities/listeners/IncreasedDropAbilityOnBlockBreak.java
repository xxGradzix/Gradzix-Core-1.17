package me.xxgradzix.gradzixcore.playerAbilities.listeners;


import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.xxgradzix.gradzixcore.events.Events;
import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class IncreasedDropAbilityOnBlockBreak implements Listener {


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        event.setDropItems(false);
        Player p  = event.getPlayer();

        if(p.getGameMode().equals(GameMode.CREATIVE)) return;



        Block block = event.getBlock();

        if(!isLocationInGeneratorRegion(block.getLocation())) return;

        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();

        int fortuneLevel = itemInHand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);


        int afterFortuneAmount = fortuneLevel > 0 ? 1 + (int) (Math.random() * (fortuneLevel + 2)) : 1;

        double playerAbilityLevelModifier = DataManager.getAbilityModifier(Ability.DROP, DataManager.getPlayerAbilityLevel(Ability.DROP, p));

        double eventMultiplier = 1;


        if(Events.isGeneratorEventEnabled()) eventMultiplier = Events.getGeneratorEventMultiplier();


        for(ItemStack drop : block.getDrops()) {

            int finalDropAmount = (int) (afterFortuneAmount * playerAbilityLevelModifier * eventMultiplier);

            drop.setAmount(finalDropAmount);

            if (p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(drop);
            } else {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
            }
        }
    }
    public boolean isLocationInGeneratorRegion(Location location) {
        World world = location.getWorld();
        if (world == null) {
            return false;
        }

        ApplicableRegionSet regionSet = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().getApplicableRegions(BukkitAdapter.adapt(location));

        for (ProtectedRegion region : regionSet) {
            if (region.getId().equalsIgnoreCase("generator")) {
                return true;
            }
        }

        return false;
    }
}
