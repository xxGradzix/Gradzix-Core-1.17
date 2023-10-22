package me.xxgradzix.gradzixcore.scratchCard.listeners;


import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.StorageGui;
import me.xxgradzix.gradzixcore.scratchCard.data.DataManager;
import me.xxgradzix.gradzixcore.scratchCard.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnLeftClick implements Listener {

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {

        Player p = e.getPlayer();


        Action action = e.getAction();

        if(!p.hasPermission("zdrapka.zdrapka-lewy")) return;
        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {


            ItemStack itemInMainHand = p.getInventory().getItemInMainHand();
            ItemStack itemInOffHand = p.getInventory().getItemInOffHand();



            if (((itemInMainHand != null && itemInMainHand.isSimilar(ItemManager.zdrapka)) || (itemInOffHand != null && itemInOffHand.isSimilar(ItemManager.zdrapka)))) {


                StorageGui gui = Gui.storage()
                        .title(Component.text("GUI Title!"))
                        .rows(6)
                        .create();

//                List<ItemStack> items = (List<ItemStack>) ZdrapkaConfigFile.getCustomFile().get("items");

                ItemStack[] itemStacks = Arrays.asList(me.xxgradzix.gradzixcore.scratchCard.data.DataManager.getScratchCardItems()).toArray(new ItemStack[0]);
                List<ItemStack> items = new ArrayList<>();
                for (ItemStack item : itemStacks) {
                    if(item == null) continue;
                    items.add(item);
                }

                gui.addItem(items);

                gui.open(p);

                gui.setCloseGuiAction((player) -> {
                    List<ItemStack> list = new ArrayList<>();

                    Inventory inv = gui.getInventory();
//                    for(ItemStack itemStack : inv.getContents()) {
//                        if(itemStack != null) {
//                            list.add(itemStack);
//
//                        }
//                    }
                    DataManager.setScratchCardItems(inv.getContents());
//                    ZdrapkaConfigFile.getCustomFile().set("items", list);
//                    ZdrapkaConfigFile.save();
                });
            }
        }
    }
}
