package me.xxgradzix.gradzixcore.playerSettings.commands;

import me.xxgradzix.gradzixcore.playerSettings.PlayerSettings;
import me.xxgradzix.gradzixcore.playerSettings.data.database.entities.AutoSellEntity;
import me.xxgradzix.gradzixcore.playerSettings.listeners.BlockBreakAutoSellEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AutoSellCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(args.length != 2) {
            player.sendMessage("§cUżycie: /autosell add <cena>");
            player.sendMessage("§cUżycie: /autosell remove <block>");
            return false;
        }
        if(args[0].equalsIgnoreCase("add")) {
            String price = args[1];
            Double priceDouble;
            try {
                priceDouble = Double.parseDouble(price);
            } catch (NumberFormatException e) {
                player.sendMessage("§cPodana cena jest nieprawidłowa!");
                return false;
            }
            if(player.getInventory().getItemInMainHand().getType().isAir()) {
                player.sendMessage("§cMusisz trzymać przedmiot w ręce!");
                return false;
            }
            ItemStack item = player.getInventory().getItemInMainHand();

            if(!item.getType().isBlock()) {
                player.sendMessage("§cMusisz trzymać blok w ręce!");
                return false;
            }
            AutoSellEntity autoSellEntity = PlayerSettings.getAutoSellEntityManager().getAutoSellEntity();

            HashMap<Material, Double> itemsToSell = autoSellEntity.getItemsToSell();
            itemsToSell.put(item.getType(), priceDouble);
            autoSellEntity.setItemsToSell(itemsToSell);

            PlayerSettings.getAutoSellEntityManager().updateAutoSellEntity(autoSellEntity);
            player.sendMessage("§aDodano blok do automatycznego sprzedawania!");
            return true;
        }
        if (args[0].equalsIgnoreCase("remove")) {
            AutoSellEntity autoSellEntity = PlayerSettings.getAutoSellEntityManager().getAutoSellEntity();
            HashMap<Material, Double> itemsToSell = autoSellEntity.getItemsToSell();

            String blockName = args[1];
            Material material = Material.matchMaterial(blockName.toUpperCase());

            if (material == null) {
                player.sendMessage("§cPodany blok nie istnieje!");
                return false;
            }
            itemsToSell.remove(material);
            autoSellEntity.setItemsToSell(itemsToSell);

            PlayerSettings.getAutoSellEntityManager().updateAutoSellEntity(autoSellEntity);
            player.sendMessage("§aUsunięto blok z automatycznego sprzedawania!");
            return true;
        }
        BlockBreakAutoSellEvent.refreshBlockPrices();
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length == 1) {
            return new ArrayList<>(Arrays.asList("add", "remove"));
        }
        if(args.length == 2) {
            if(args[0].equalsIgnoreCase("remove")) {
                AutoSellEntity autoSellEntity = PlayerSettings.getAutoSellEntityManager().getAutoSellEntity();
                HashMap<Material, Double> itemsToSell = autoSellEntity.getItemsToSell();
                List<String> items = new ArrayList<>();
                for(Material item : itemsToSell.keySet()) {
                    items.add(item.name());
                }
                return items;
            }
        }
        return null;
    }
}
