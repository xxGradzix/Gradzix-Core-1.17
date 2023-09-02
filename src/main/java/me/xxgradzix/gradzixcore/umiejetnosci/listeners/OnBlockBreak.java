package me.xxgradzix.gradzixcore.umiejetnosci.listeners;


import me.xxgradzix.gradzixcore.umiejetnosci.data.DataManager;
import me.xxgradzix.gradzixcore.umiejetnosci.data.database.entities.enums.Ability;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class OnBlockBreak implements Listener {


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Block block = event.getBlock();

        Player p  = event.getPlayer();

        if(p.getGameMode().equals(GameMode.CREATIVE)) return;

        

        ArrayList<Material> blockTypes = new ArrayList<>();



        blockTypes.add(Material.DIAMOND_BLOCK);
        blockTypes.add(Material.DIAMOND_ORE);
        blockTypes.add(Material.EMERALD_BLOCK);
        blockTypes.add(Material.EMERALD_ORE);
        blockTypes.add(Material.GOLD_ORE);
        blockTypes.add(Material.GOLD_BLOCK);
        blockTypes.add(Material.REDSTONE_BLOCK);
        blockTypes.add(Material.REDSTONE_ORE);
        blockTypes.add(Material.IRON_BLOCK);
        blockTypes.add(Material.IRON_ORE);
        blockTypes.add(Material.NETHERITE_BLOCK);
        blockTypes.add(Material.ANCIENT_DEBRIS);
        blockTypes.add(Material.COAL_BLOCK);
        blockTypes.add(Material.COAL_ORE);
        blockTypes.add(Material.LAPIS_BLOCK);
        blockTypes.add(Material.LAPIS_ORE);
        blockTypes.add(Material.ACACIA_LOG);
        blockTypes.add(Material.ACACIA_PLANKS);

        blockTypes.add(Material.LIME_WOOL);
        blockTypes.add(Material.PINK_WOOL);
        blockTypes.add(Material.COBWEB);
        blockTypes.add(Material.COBWEB);


//        com.sk89q.worldedit.util.Location location = BukkitAdapter.adapt(block.getLocation());
//        ApplicableRegionSet regions = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().getApplicableRegions(location);
//        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
//
//
//
//
//        StateFlag.State flagState = regions.queryValue(localPlayer, Flags.BLOCK_BREAK);
//
//        if (flagState != null && (flagState == StateFlag.State.DENY || flagState.equals(StateFlag.State.DENY))) {
            if(!blockTypes.contains(block.getType())) {

                event.setCancelled(true);
                p.sendMessage("Nie możesz niszczyć tego bloku w tym regionie.");
                return;
            }

//        }


        if(event.isCancelled()) return;


        if(!p.getGameMode().equals(GameMode.SURVIVAL)) return;

//        int playerLevel = UmiejetnosciConfigFile.getDropLevel(p);
        int playerLevel = DataManager.getPlayerAbilityLevel(Ability.DROP, p);


        if(playerLevel == 0) return;

        double multiplier = DataManager.getAbilityModifier(Ability.DROP, playerLevel);

//        Block block = event.getBlock();
        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        int fortuneLevel = itemInHand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);

        ArrayList<ItemStack> drops = (ArrayList<ItemStack>) block.getDrops();
        event.setDropItems(false);


        for(ItemStack drop : drops) {
            int baseAmount = 1;
            int maxAmount = baseAmount + (int) (Math.random() * (fortuneLevel + 2));

            if(fortuneLevel > 0) {
                drop.setAmount((int) (maxAmount * multiplier));
            } else {
                drop.setAmount((int) (baseAmount * multiplier));
            }
            if (p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(drop);
            } else {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
            }

        }

    }



}
