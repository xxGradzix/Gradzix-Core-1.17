package me.xxgradzix.gradzixcore.autodropsell.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.xxgradzix.gradzixcore.events.Events;
import me.xxgradzix.gradzixcore.generators.Generators;
import me.xxgradzix.gradzixcore.globalStatic.EconomyManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import me.xxgradzix.gradzixcore.playerSettings.data.database.managers.AutoSellEntityManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class BlockBreakAutoSellEvent implements Listener {

    private static HashMap<Material, Double> blockPrices = new HashMap<>();

    private static AutoSellEntityManager autoSellEntityManager;

    public BlockBreakAutoSellEvent(AutoSellEntityManager autoSellEntityManager) {
        this.autoSellEntityManager = autoSellEntityManager;
        refreshBlockPrices();
    }

    public static void refreshBlockPrices() {
        blockPrices = autoSellEntityManager.getAutoSellEntity().getItemsToSell();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {

        event.setDropItems(false);

        if(blockPrices.containsKey(event.getBlock())) {
            blockPrices.remove(event.getBlock());
        }

        Player p  = event.getPlayer();

        if(p.getGameMode().equals(GameMode.CREATIVE)) return;

        Block block = event.getBlock();

        if(!isLocationInGeneratorRegion(block.getLocation())) return;

        if(block.getType().equals(Material.OAK_LOG) || block.getType().equals(Material.OAK_PLANKS)) {
            p.getInventory().addItem(new ItemStack(block.getType(), 1));
            return;
        }

        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();

        int fortuneLevel = itemInHand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);

        if(block.getType().equals(Material.LIGHT_GRAY_WOOL) || block.getType().equals(Material.PINK_WOOL) || block.getType().equals(Material.GRAY_WOOL)) {
            if(!itemInHand.getType().equals(Material.SHEARS)) {
                fortuneLevel = 0;
            }
        }

        int afterFortuneAmount = fortuneLevel > 0 ? 1 + (int) (Math.random() * (fortuneLevel + 2)) : 1;

        double playerAbilityLevelModifier = DataManager.getDropAbilityModifier(p);

        double eventMultiplier = Events.isGeneratorEventEnabled() ? Events.getGeneratorEventMultiplier() : 1;

        int finalDropAmount = (int) (afterFortuneAmount * playerAbilityLevelModifier * eventMultiplier);

        double itemPrice = blockPrices.getOrDefault(block.getType(), 0.0);

        double totalPrice = finalDropAmount * itemPrice;

        EconomyManager.depositMoney(p, totalPrice);

    }
    public boolean isLocationInGeneratorRegion(Location location) {
        World world = location.getWorld();
        if (world == null) {
            return false;
        }

        ApplicableRegionSet regionSet = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().getApplicableRegions(BukkitAdapter.adapt(location));

        for (ProtectedRegion region : regionSet) {
            if (region.getId().startsWith(Generators.GENERATOR_REGION_PREFIX)) {
                return true;
            }
        }

        return false;
    }

}
