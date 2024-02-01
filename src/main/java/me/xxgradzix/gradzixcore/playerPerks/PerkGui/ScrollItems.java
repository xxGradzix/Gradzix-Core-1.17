package me.xxgradzix.gradzixcore.playerPerks.PerkGui;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.chatOptions.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class ScrollItems extends BukkitRunnable {


    private ArrayList<ItemStack> itemStackList;
    Player player;
    Gui gui;
    ItemStack reward;

    int row;


    public ScrollItems(List<ItemStack> items, Player player, Gui gui, int row) {

        if (items.size() < 9) {
            throw new RuntimeException("liczba nagród nie moze byc mniejsza niz 9");
        }

        this.player = player;
        this.gui = gui;
        this.row = row;
        itemStackList = (ArrayList<ItemStack>) items;

        reward = chooseRandomReward(items);
    }

    int rep = 0;
    int endRep = -1;

    boolean shouldContinue = true;
    ItemStack winningSlotItem = null;

    @Override
    public void run() {

        if(shouldContinue) {

            boolean shouldPerform = true;
            if (rep > (itemStackList.size() * 2) && rep%2 == 0) shouldPerform = false;

            if(shouldPerform){ // to perform

                int newSlot = row;
                if(row == 2) newSlot = 9;

                int slotIncrement = 1;

                for (int i = 0; i < 9; i++) {
                    if (newSlot == (9 + 4)) winningSlotItem = itemStackList.get(i).clone();
                    gui.updateItem(newSlot, new GuiItem(itemStackList.get(i).clone()));
                    newSlot += slotIncrement;
                    gui.update();
                }

                    player.getWorld().playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 0.3F, 2F);

                if (rep < (itemStackList.size() * 4)) {
                    shiftRewardsList(itemStackList);
                } else {
                    if (!winningSlotItem.equals(reward)) {

                        shiftRewardsList(itemStackList);
                    } else {
                        shouldContinue = false;
                        endRep = rep + 10;
                    }
                }
            }
        }

        rep++;

        if(rep > (itemStackList.size()*5.5)) {
            gui.updateItem(9, ItemManager.greenGlass);
            gui.updateItem(10, ItemManager.greenGlass);
            gui.updateItem(11, ItemManager.greenGlass);
            gui.updateItem(12, ItemManager.greenGlass);
            gui.updateItem(14, ItemManager.greenGlass);
            gui.updateItem(15, ItemManager.greenGlass);
            gui.updateItem(16, ItemManager.greenGlass);
            gui.updateItem(17, ItemManager.greenGlass);
            if(winningSlotItem.equals(reward)) {
                cancel();
                player.getInventory().addItem(reward);
                if(reward.hasItemMeta() && reward.getItemMeta().hasDisplayName()) {
                    player.sendMessage(ChatColor.GRAY + "Wygrałeś "+ reward.getAmount() + " " + reward.getItemMeta().getDisplayName());
                } else {
                    player.sendMessage(ChatColor.GRAY + "Wygrałeś "+ reward.getAmount() + " " + reward.getType().toString());
                }
            }
        }
    }

    private static void shiftRewardsList(List<ItemStack> list) {
        Collections.rotate(list, 1);
    }
    private static ItemStack chooseRandomReward(List<ItemStack> rewards) {
        int totalPercentage = rewards.size();

        int randomPercentage = new Random().nextInt(totalPercentage);

        return rewards.get(randomPercentage);
    }
}
