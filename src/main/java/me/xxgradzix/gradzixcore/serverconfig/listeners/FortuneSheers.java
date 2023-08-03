package me.xxgradzix.gradzixcore.serverconfig.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class FortuneSheers implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        ArrayList<Material> blockTypes = new ArrayList<>();

        blockTypes.add(Material.LIME_WOOL);
        blockTypes.add(Material.PINK_WOOL);

        if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.SHEARS)) {

            if(event.getBlock().getType().equals(Material.COBWEB)) {
                event.setCancelled(true);
            }

            if(event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)){

                if(event.getBlock() != null && blockTypes.contains(event.getBlock().getType())) {
                    ArrayList<ItemStack> drops = (ArrayList<ItemStack>) event.getBlock().getDrops();
                    event.setDropItems(false);
                    for(ItemStack drop : drops) {
                        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
                        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);

                    }

                }

            }


        }

    }

}
