package me.xxgradzix.gradzixcore.itemShop.command;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import lombok.RequiredArgsConstructor;
import me.xxgradzix.gradzixcore.itemShop.ItemShop;
import me.xxgradzix.gradzixcore.itemShop.data.DataManager;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopCategoryEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopProductEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.enums.ShopType;
import me.xxgradzix.gradzixcore.itemShop.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.enginehub.piston.exception.ConversionFailedException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.management.InstanceAlreadyExistsException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ItemShopConfigCommand implements CommandExecutor, TabCompleter {



    private final DataManager dataManager;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(args.length < 3) {
            player.sendMessage("Musisz podac co chcesz zrobic");
            return false;
        }

        String shopTypeString = args[0].toLowerCase();
        ShopType shopType;
        try {
            shopType = ShopType.valueOf(shopTypeString.toUpperCase());
        } catch (EnumConstantNotPresentException e) {
            player.sendMessage("ShopType " + shopTypeString + " does not exists");
            return false;
        }

        String action = args[1].toLowerCase();

        if(!action.equalsIgnoreCase("create") && !action.equalsIgnoreCase("delete")) {
            player.sendMessage("Mozesz wykonac tylko akcje create albo delete");
            return false;
        }
        String entityType = args[2].toLowerCase();

        if(!"product".equalsIgnoreCase(entityType)) {
            player.sendMessage("Mozesz wykonac tylko tworzyc i usuwac produkty");
            return false;
        }

        if("create".equalsIgnoreCase(action)) {

            if("product".equalsIgnoreCase(entityType)) {
                if(args.length < 4) {
                    player.sendMessage("Musisz podac cene produktu");
                    return false;
                }
                String stringPrice = args[3].toLowerCase();
                int price;
                try {
                    price = Integer.parseInt(stringPrice);
                } catch (Exception e) {
                    player.sendMessage("Cena musi byc liczbą");
                    return false;
                }
//                if(args.length < 5) {
//                    player.sendMessage("Musisz podac cene produktu");
//                    return false;
//                }
//                String stringSlot = args[3].toLowerCase();
//                int slot;
//                try {
//                    slot = Integer.parseInt(stringPrice);
//                } catch (Exception e) {
//                    player.sendMessage("Slot musi byc liczbą");
//                    return false;
//                }
//                ItemStack item = player.getInventory().getItemInMainHand();
                selectSlotAndCreateProduct(player, shopType, price);
//                dataManager.createProduct(item, price, shopType, slot);
            }
        }
        if("delete".equalsIgnoreCase(action)) {
//            if("category".equalsIgnoreCase(entityType)) {
//
//                chooseCategoryToRemove(player, shopType);
//            }
            if("product".equalsIgnoreCase(entityType)) {
                chooseProductToRemoveFromShopType(player, shopType);
            }
        }


        return false;
    }

    private void selectSlotAndCreateProduct(Player player, ShopType shopType, int price) {
        Gui selectSlotGui = Gui.gui()
                .title(Component.text("Wybierz slot w ktorym chcesz umiescic produkt!"))
                .rows(ItemShop.SHOP_SIZE)
                .disableAllInteractions()
                .create();

        dataManager.getItemShopProductsByShopType(shopType).forEach(product -> {
            selectSlotGui.setItem(product.getSlot(), new GuiItem(product.getProduct().clone()));
        });

        selectSlotGui.setDefaultClickAction(event -> {
            ItemStack item = event.getCurrentItem();
            if(item != null && !item.getType().equals(Material.AIR)) {
                player.sendMessage("Slot nie moze byc zajety");
                return;
            }
            dataManager.createProduct(player.getInventory().getItemInMainHand(), price, shopType, event.getSlot());
            selectSlotGui.close(player);
        });
        selectSlotGui.open(player);

    }

//    private void chooseCategoryToRemove(Player player, ShopType shopType) {
//        Gui chooseCategoryGui = Gui.gui()
//                .title(Component.text("Wybierz kategorie do ktora chcesz usunac!"))
//                .rows(3)
//                .disableAllInteractions()
//                .create();
//        List<ItemShopCategoryEntity> categories = dataManager.getItemShopCategoriesByShopType(shopType);
//        player.sendMessage(categories.toString());
//        for(ItemShopCategoryEntity category : categories) {
//            GuiItem guiItem = new GuiItem(ItemManager.createCategoryButton(category.getName()));
//            guiItem.setAction((a) -> {
//                dataManager.deleteCategory(category);
//                chooseCategoryGui.close(player);
//                player.sendMessage("usunales kategorie " + category.getName());
//            });
//            chooseCategoryGui.addItem(guiItem);
//        }
//        chooseCategoryGui.open(player);
//    }

//    private void chooseCategoryToPutProductIn(Player player, ShopType shopType, ItemStack item, int price) {
//        Gui chooseCategoryGui = Gui.gui()
//                .title(Component.text("Wybierz kategorie do ktorej chcesz umiescic produkt!"))
//                .rows(3)
//                .disableAllInteractions()
//                .create();
//        List<ItemShopCategoryEntity> categories = dataManager.getItemShopCategoriesByShopType(shopType);
//
//        for(ItemShopCategoryEntity categoryEntity : categories){
//
//            GuiItem categoryGuiItem = new GuiItem(ItemManager.createCategoryButton(categoryEntity.getName()));
//            categoryGuiItem.setAction((a) -> {
//                try {
//                    dataManager.createProduct();
//                } catch (NoSuchObjectException e) {
//                    player.sendMessage("Ta kategoria nie istnieje w tym sklepie");
//                } catch (IllegalArgumentException e) {
//                    player.sendMessage("Musisz trzymac przedmiot w rece");
//                }
//                chooseCategoryGui.close(player);
//            });
//            chooseCategoryGui.addItem(categoryGuiItem);
//        }
//        chooseCategoryGui.open(player);
//    }
//
//    public void chooseShopTypeToRemove(Player player) {
//        Gui chooseShopType = Gui.gui()
//                .title(Component.text("Wybierz typ sklepu z ktorego chcesz usunac produkty!"))
//                .rows(3)
//                .disableAllInteractions()
//                .create();
//        GuiItem timeShop = new GuiItem(ItemManager.timeCoinShopButton);
//        GuiItem killsShop = new GuiItem(ItemManager.killCoinShopButton);
//        GuiItem moneyShop = new GuiItem(ItemManager.moneyShopButton);
//
//        timeShop.setAction((a) -> {
//            chooseCategoryToRemoveProducts(player, ShopType.TIME);
//        });
//        killsShop.setAction((a) -> {
//            chooseCategoryToRemoveProducts(player, ShopType.KILLS);
//        });
//        moneyShop.setAction((a) -> {
//            chooseCategoryToRemoveProducts(player, ShopType.MONEY);
//        });
//        chooseShopType.setItem(2, 3, timeShop);
//        chooseShopType.setItem(2, 5, killsShop);
//        chooseShopType.setItem(2, 7, timeShop);
//        chooseShopType.open(player);
//    }
//    public void chooseCategoryToRemoveProducts(Player player, ShopType shopType) {
//        Gui chooseCategoryGui = Gui.gui()
//                .title(Component.text("Wybierz kategorie z ktorej chcesz usunac produkty!"))
//                .rows(3)
//                .disableAllInteractions()
//                .create();
//
//        HashMap<ItemShopCategoryEntity, List<ItemShopProductEntity>> productsInCategories = dataManager.getItemShopProductsDividedByCategories(shopType);
//
//        for(ItemShopCategoryEntity categoryEntity : productsInCategories.keySet()){
//
//            GuiItem categoryGuiItem = new GuiItem(ItemManager.createCategoryButton(categoryEntity.getName()));
//
//            categoryGuiItem.setAction((categoryAction) -> {
//                chooseProductToRemoveFromCategory(player, productsInCategories.getOrDefault(categoryEntity, new ArrayList<>()));
//            });
//            chooseCategoryGui.addItem(categoryGuiItem);
//        }
//        chooseCategoryGui.open(player);
//    }
    private void chooseProductToRemoveFromShopType(Player player, ShopType shopType) {
        Gui chooseProductGui = Gui.gui()
                .title(Component.text("Wybierz produkt do usuniecia!"))
                .rows(3)
                .disableAllInteractions()
                .create();

        for(ItemShopProductEntity product : dataManager.getItemShopProductsByShopType(shopType)) {

            GuiItem productGuiItem = new GuiItem(product.getProduct().clone());

            productGuiItem.setAction((productAction) -> {
                chooseProductGui.removeItem(productGuiItem);
                dataManager.deleteProduct(product);
            });

            if(chooseProductGui.getGuiItem(product.getSlot()) == null)
                chooseProductGui.setItem(product.getSlot(), productGuiItem);
            else {
                chooseProductGui.addItem(productGuiItem);
            }
        }
        chooseProductGui.open(player);
    }

//    private void deleteCategory(ItemShopCategoryEntity itemShopCategoryEntity) {
//        dataManager.deleteCategory(itemShopCategoryEntity);
//    }


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if(cmd.getName().equalsIgnoreCase("itemshopconfig")) {
            List<String> completions = new ArrayList<>();
            if(args.length == 1) {
                for(ShopType shopType : ShopType.values()) {
                    completions.add(shopType.name());
                }
            }
            if(args.length == 2) {
                completions.add("create");
                completions.add("delete");
            }
            if(args.length == 3) {
                completions.add("category");
                completions.add("product");
            }
            if(args.length == 4) {
//                if("create".equalsIgnoreCase(args[1]) && "product".equalsIgnoreCase(args[2])) {
//                    String shopTypeString = args[0];
//                    ShopType shopType;
//                    try {
//                        shopType = ShopType.valueOf(shopTypeString);
//
//                    } catch (Exception ignored) {
//                        return new ArrayList<>();
//                    }
//
//                    for(ItemShopCategoryEntity category : dataManager.getItemShopCategoriesByShopType(shopType)) {
//                        completions.add(category.getName());
//                    }
//                }

            }
//            if(args.length == 3) {
//                if(args[0].equalsIgnoreCase("create") && args[1].equalsIgnoreCase("product")) {
//                    for(ItemShopCategoryEntity categoryEntity : dataManager.getAllItemShopCategories()) {
//                        completions.add(categoryEntity.getName());
//                    }
//                }
//            }
//            if(args.length == 3) {
//                if(args[0].equalsIgnoreCase("delete") && args[1].equalsIgnoreCase("category")) {
//                    for(ItemShopCategoryEntity categoryEntity : dataManager.getAllItemShopCategories()) {
//                        completions.add(categoryEntity.getName());
//                    }
//                }
//            }
            return completions;
        }
        return null;
    }
}
