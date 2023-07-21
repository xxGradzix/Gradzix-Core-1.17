package me.xxgradzix.gradzixcore.ulepsz.commands;

import me.xxgradzix.gradzixcore.ulepsz.files.UlepszConfigFile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

public class UlepszCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(sender instanceof Player) {

            Player p = (Player) sender;

            ItemStack currentItem = p.getInventory().getItemInMainHand();



            ItemStack nextItem = UlepszConfigFile.findNextItem(currentItem);
            ItemStack requiredItem = UlepszConfigFile.findRequiredItem(currentItem);

            if(nextItem == null || requiredItem == null) {
                p.sendMessage(ChatColor.RED + "Tego przedmiotu nie mozesz ulepszyc");
                return false;
            }

            if(p.getInventory().containsAtLeast(requiredItem, requiredItem.getAmount())) {

                if(p.getInventory().getItemInMainHand().equals(currentItem)) {


                    removeItems(p, requiredItem, requiredItem.getAmount());
                    p.getInventory().setItemInMainHand(nextItem);
                    if(nextItem.getItemMeta().hasDisplayName()){
                        p.sendMessage(ChatColor.GREEN + "Przedmiot został ulepszony na " + nextItem.getItemMeta().getDisplayName());
                    } else {
                        p.sendMessage(ChatColor.GREEN + "Przedmiot został ulepszony na " + nextItem.getType().toString());
                    }

                }

            } else {
//                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości wymaganego przedmiotu aby to ulepszyć");
                p.sendMessage(ChatColor.RED + "Aby to ulepszyć potrzebujesz " + requiredItem.getAmount() + " " + requiredItem.getItemMeta().getDisplayName());
            }

        }

        return true;
    }

    public void removeItems(Player player, ItemStack itemStack, int amount) {
        PlayerInventory inventory = player.getInventory();
        int remainingAmount = amount;

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);

            if (item != null && item.isSimilar(itemStack)) {
                int itemAmount = item.getAmount();

                if (itemAmount <= remainingAmount) {
                    remainingAmount -= itemAmount;
                    inventory.setItem(i, null);
                } else {
                    item.setAmount(itemAmount - remainingAmount);
                    break;
                }
            }
        }
    }
}
