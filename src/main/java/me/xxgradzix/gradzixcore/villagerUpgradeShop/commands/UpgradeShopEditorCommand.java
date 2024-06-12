package me.xxgradzix.gradzixcore.villagerUpgradeShop.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.GlobalMessagesManager;
import me.xxgradzix.gradzixcore.Gradzix_Core;
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

public class UpgradeShopEditorCommand implements CommandExecutor {
    
    private static DataManager dataManager;

    public UpgradeShopEditorCommand(DataManager dataManager) {
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
        gui.disableAllInteractions();
        dataManager.getAllShopEntities().forEach((shop) -> {
            GuiItem shopItem = ItemManager.createUpgradeShopItem(shop.getName());

            shopItem.setAction(event -> {
                openEditor(player, shop);
            });

            gui.addItem(shopItem);
        });


        gui.open(player);
    }

    public static void openEditor(Player player, VillagerUpgradeShopEntity shop) {

        Gui gui = new Gui(6, "Edytor sklepu: " + shop.getName());

        gui.setDragAction(event -> {
            gui.update();
        });

        shop.getProducts().forEach((product) -> {
            GuiItem productItem = ItemManager.createProductEditor(player, gui, product, shop.getName());
            gui.addItem(productItem);
        });

        GuiItem addProductItemButton = ItemManager.createAddItemButton();

        addProductItemButton.setAction(event -> {
            event.setCancelled(true);
            ItemStack item = event.getCursor();

            if(item.getType() == Material.AIR) {
                player.sendMessage(GlobalMessagesManager.PREFIX + "§cTrzymaj przedmiot w ręce aby dodać go do sklepu");
                return;
            }

            String numberFromName = shop.getName();

            numberFromName = numberFromName.replace("v", "");

            int number = Integer.parseInt(numberFromName);

            int slot = 0;

            double defaultPrice = 0;

            if(number < 6) {
                defaultPrice = Math.pow(2, (number-1));
            } else {
                defaultPrice = Math.pow(2, (number - 1)) * 1.5;
            }


            double price = 0;
            switch (item.getType()) {
                case NETHERITE_SWORD:
                    slot = 31;
                    price = defaultPrice * 4;
                    break;
                case NETHERITE_CHESTPLATE:
                    slot = 22;
                    price = defaultPrice;
                    break;
                case PLAYER_HEAD:
                    slot = 21;
                    price = defaultPrice;
                    break;
                case NETHERITE_BOOTS:
                    slot = 25;
                    price = defaultPrice;
                    break;
                case NETHERITE_LEGGINGS:
                    slot = 24;
                    price = defaultPrice;
                    break;
                case NETHERITE_PICKAXE:
                    slot = 33;
                    price = defaultPrice * 4;
                    break;
                case ELYTRA:
                    slot = 34;
                    price = defaultPrice;
                    break;
                case SHEARS:
                    slot = 30;
                    price = 13820 * Math.pow(2, (number - 13));
                    break;
            }



            VillagerUpgradeShopProductEntity product = VillagerUpgradeShopProductEntity.builder()
                    .item(item)
                    .shopEntity(shop)
                    .shopSlot(slot)
                    .price(price)
                    .build();

            dataManager.createOrUpdateVillagerUpgradeShopProductEntity(product);

            Bukkit.getScheduler().runTaskLater(Gradzix_Core.getInstance(), () -> {
                UpgradeShopEditorCommand.openEditor(player, product.getShopEntity());
            }, 1);
        });

        gui.setItem(5, 5, addProductItemButton);

        gui.open(player);
    }


}
