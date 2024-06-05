package me.xxgradzix.gradzixcore.villagerUpgradeShop.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.GlobalMessagesManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.VillagerUpgradeShop;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.DataManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopEntity;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopProductEntity;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UpgradeShopCommand implements CommandExecutor {
    
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
            String arg1 = args[0];
            if(arg1.equalsIgnoreCase("editor")) {
                openShopsGui(player);

            }
            return true;
        }
        if(args.length == 2) {
            String arg1 = args[0];
            String arg2 = args[1];

            if(arg1.equalsIgnoreCase("create")) {

                dataManager.createUpgradeShop(arg2);

            }
            return true;
        }



        return true;

    }
    private void openShopsGui(Player player) {
        Gui gui = new Gui(6, "Sklepy");

        dataManager.getAllShopEntities().forEach((shop) -> {
            GuiItem shopItem = ItemManager.createUpgradeShopItem(shop.getName());

            shopItem.setAction(event -> {
                openEditor(player, shop);
            });

            gui.addItem(shopItem);
        });


        gui.open(player);
    }

    private void openEditor(Player player, VillagerUpgradeShopEntity shop) {

        Gui gui = new Gui(6, "Edytor sklepu: " + shop.getName());

        shop.getProducts().forEach((product) -> {
            GuiItem productItem = ItemManager.createShowItem(product.getItem(), product.getPrice(), product.getNeededItem(), product.getShopSlot(), shop.getName());

            productItem.setAction(event -> {
                Bukkit.broadcastMessage("Edytuj produkt");
            });

            gui.addItem(productItem);
        });



        GuiItem addProductItemButton = ItemManager.createAddItemButton();

        addProductItemButton.setAction(event -> {
            ItemStack item = event.getCursor();

            if(item.getType() == Material.AIR) {
                player.sendMessage(GlobalMessagesManager.PREFIX + "§cTrzymaj przedmiot w ręce aby dodać go do sklepu");
                return;
            }



            // TODO add item to shop

        });

        gui.setItem(5, 5, addProductItemButton);

        gui.open(player);
    }

    private GuiItem createButtonWithOptionsEditor() {
        GuiItem shop = ItemManager.createShowItem(new ItemStack(Material.DIAMOND), 100.0, new ItemStack(Material.GOLD_INGOT), 1, "shop");

        shop.setAction(event -> {

            boolean itemInCursor = event.getCursor() != null && event.getCursor().getType() != Material.AIR ;
            boolean shiftClick = event.isShiftClick();
            boolean rightClick = event.isRightClick();
            boolean leftClick = event.isLeftClick();


            if(itemInCursor) {
                Bukkit.broadcastMessage("dodaj item potrzebny do ulepszenia");
                return;
            }

            if (shiftClick && rightClick) {
                Bukkit.broadcastMessage("USUN");
                return;
            }

            if (!shiftClick && leftClick) {
                Bukkit.broadcastMessage("ustaw slot");
                return;
            }
            if (rightClick && !shiftClick) {
                Bukkit.broadcastMessage("ustaw cene");
                return;
            }



        });


        return shop;
    }

}
