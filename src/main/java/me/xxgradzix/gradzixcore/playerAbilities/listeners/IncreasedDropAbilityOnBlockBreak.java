package me.xxgradzix.gradzixcore.playerAbilities.listeners;


import me.xxgradzix.gradzixcore.events.Events;
import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;
import org.bukkit.GameMode;
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

        Player p  = event.getPlayer();

        if(p.getGameMode().equals(GameMode.CREATIVE)) return;



        Block block = event.getBlock();

//        event.setDropItems(false);
//        ArrayList<Material> blockTypes = new ArrayList<>();
//        blockTypes.add(Material.DIAMOND_BLOCK);
//        blockTypes.add(Material.DIAMOND_ORE);
//        blockTypes.add(Material.EMERALD_BLOCK);
//        blockTypes.add(Material.EMERALD_ORE);
//        blockTypes.add(Material.GOLD_ORE);
//        blockTypes.add(Material.GOLD_BLOCK);
//        blockTypes.add(Material.REDSTONE_BLOCK);
//        blockTypes.add(Material.REDSTONE_ORE);
//        blockTypes.add(Material.IRON_BLOCK);
//        blockTypes.add(Material.IRON_ORE);
//        blockTypes.add(Material.NETHERITE_BLOCK);
//        blockTypes.add(Material.ANCIENT_DEBRIS);
//        blockTypes.add(Material.COAL_BLOCK);
//        blockTypes.add(Material.COAL_ORE);
//        blockTypes.add(Material.LAPIS_BLOCK);
//        blockTypes.add(Material.LAPIS_ORE);
//        blockTypes.add(Material.ACACIA_LOG);
//        blockTypes.add(Material.ACACIA_PLANKS);
//
//        blockTypes.add(Material.LIME_WOOL);
//        blockTypes.add(Material.PINK_WOOL);
//        blockTypes.add(Material.COBWEB);
//        blockTypes.add(Material.COBWEB);
//
//        if(!blockTypes.contains(block.getType())) {
//            event.setCancelled(true);
//            p.sendMessage("Nie możesz niszczyć tego bloku w tym regionie.");
//            return;
//        }


//        if(!p.getGameMode().equals(GameMode.SURVIVAL)) return;
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
}
