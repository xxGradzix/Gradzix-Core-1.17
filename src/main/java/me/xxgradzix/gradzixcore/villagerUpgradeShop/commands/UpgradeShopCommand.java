package me.xxgradzix.gradzixcore.villagerUpgradeShop.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.GlobalItemManager;
import me.xxgradzix.gradzixcore.GlobalMessagesManager;
import me.xxgradzix.gradzixcore.globalStatic.EconomyManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.DataManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopEntity;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopProductEntity;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UpgradeShopCommand implements CommandExecutor, TabCompleter {

    private DataManager dataManager;

    public UpgradeShopCommand(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalMessagesManager.PLAYER_ONLY);
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 1) {
            String shopName = args[0];


            Optional<VillagerUpgradeShopEntity> shopEntityByName = dataManager.getShopEntityByName(shopName);
            if(!shopEntityByName.isPresent()) {
                player.sendMessage("§cSklep o podanej nazwie nie istnieje!");
                return true;
            }

            VillagerUpgradeShopEntity shop = shopEntityByName.get();

            Gui gui = new Gui(6, "§3§lUlepszenie uzbrojenia");

            gui.disableAllInteractions();

            gui.getFiller().fillBetweenPoints(1, 3, 1, 4, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
            gui.getFiller().fillBetweenPoints(1, 6, 1, 7, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
            gui.getFiller().fillBetweenPoints(6, 3, 6, 4, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
            gui.getFiller().fillBetweenPoints(6, 6, 6, 7, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);


            gui.setItem(3, 1, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
            gui.setItem(3, 9, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
            gui.setItem(4, 1, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
            gui.setItem(4, 9, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);

            gui.setItem(1, 2, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);
            gui.setItem(6, 2, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);

            gui.setItem(1, 8, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);
            gui.setItem(6, 8, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);

            gui.setItem(2, 1, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);
            gui.setItem(2, 9, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);

            gui.setItem(5, 1, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);
            gui.setItem(5, 9, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);

            gui.setItem(1, 1, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
            gui.setItem(6, 1, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);

            gui.setItem(1, 9, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
            gui.setItem(6, 9, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);

            for(VillagerUpgradeShopProductEntity product : shop.getProducts()) {
                GuiItem item = ItemManager.createProductGuiItem(product, shop.getName());
                item.setAction((action) -> {
                    action.setCancelled(true);

                    if (player.getInventory().firstEmpty() == -1) {
                        player.sendMessage("§cNie masz wystarczająco miejsca w ekwipunku!");
                        return;
                    }

                    if(product.getNeededItem() != null && !player.getInventory().containsAtLeast(product.getNeededItem(), 1)) {
                        player.sendMessage("§cNie masz wymaganego przedmiotu!");
                        return;
                    }
                    if(EconomyManager.getBalance(player) < product.getPrice()) {
                        player.sendMessage("§cNie masz wystarczająco pieniędzy!");
                        return;
                    }
                    if(product.getNeededItem() != null) player.getInventory().removeItem(product.getNeededItem());

                    EconomyManager.withdrawMoney(player, product.getPrice());
                    player.getInventory().addItem(product.getItem());
                    player.sendMessage("§aZakupiono produkt!");

                });

                gui.setItem(product.getShopSlot() - 1, item);
            }

            gui.open(player);


            return true;
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length == 1) {
            return dataManager.getAllShopEntities().stream().map(VillagerUpgradeShopEntity::getName).collect(Collectors.toList());
        }
        return null;
    }
}
