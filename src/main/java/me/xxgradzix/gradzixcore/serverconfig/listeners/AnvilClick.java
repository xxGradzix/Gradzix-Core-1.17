package me.xxgradzix.gradzixcore.serverconfig.listeners;

import com.sk89q.worldedit.event.platform.BlockInteractEvent;
import me.xxgradzix.gradzixcore.GlobalMessagesManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class AnvilClick implements Listener {

    @EventHandler
    public void onAnvilClick(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();

        if(clickedBlock == null) return;
        if (clickedBlock.getType() != Material.ANVIL) return;

        event.setCancelled(true);
        Player player = event.getPlayer();

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack == null) return;
        if (itemStack.getType() == Material.AIR) return;

        itemStack.setDurability((short) 0);

        player.sendMessage(GlobalMessagesManager.PREFIX + "Naprawiono przedmiot!");

        // some code
    }



}
