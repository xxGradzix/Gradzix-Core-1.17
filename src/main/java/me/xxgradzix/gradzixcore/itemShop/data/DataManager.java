package me.xxgradzix.gradzixcore.itemShop.data;

import lombok.RequiredArgsConstructor;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopCategoryEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopPlayerBalanceEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopProductEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.enums.ShopType;
import me.xxgradzix.gradzixcore.itemShop.data.database.managers.ItemShopCategoryEntityManager;
import me.xxgradzix.gradzixcore.itemShop.data.database.managers.ItemShopPlayerBalanceEntityManager;
import me.xxgradzix.gradzixcore.itemShop.data.database.managers.ItemShopProductEntityManager;
import me.xxgradzix.gradzixcore.itemShop.managers.EconomyManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.management.InstanceAlreadyExistsException;
import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DataManager {

    private final ItemShopCategoryEntityManager categoryEntityManager;
    private final ItemShopProductEntityManager productEntityManager;
    private final ItemShopPlayerBalanceEntityManager playerBalanceEntityManager;
    private final EconomyManager economyManager;

    private static final boolean useDB = Gradzix_Core.USEDB;

    public HashMap<ItemShopCategoryEntity, List<ItemShopProductEntity>> getItemShopProductsDividedByCategories(ShopType shopType) {

        if(useDB) {
            HashMap<ItemShopCategoryEntity, List<ItemShopProductEntity>> itemShopProducts = new HashMap<>();

            for(ItemShopCategoryEntity entity : categoryEntityManager.getItemShopCategoriesByShopType(shopType)) {
                itemShopProducts.put(entity, productEntityManager.getItemShopProductEntitiesByCategory(entity));
            }

            return itemShopProducts;

        } else {
            return new HashMap<>();
        }
    }
    public List<ItemShopCategoryEntity> getItemShopCategoriesByShopType(ShopType shopType) {
        return categoryEntityManager.getItemShopCategoriesByShopType(shopType);
    }
    public void createCategory(ItemShopCategoryEntity entity) throws InstanceAlreadyExistsException {
        if(useDB) {
            categoryEntityManager.createOrUpdateAbilityModifierEntity(entity);
        }
    }
    public void createProduct(ItemShopProductEntity entity) {
        if(useDB) {
            productEntityManager.createOrUpdateItemShopProductEntity(entity);
        }
    }
    public void deleteCategory(ItemShopCategoryEntity entity) {
        if(useDB) {
            categoryEntityManager.deleteItemShopCategoryEntity(entity);
            productEntityManager.deleteItemShopProductEntitiesByCategory(entity);
        }
    }
    public void deleteProduct(ItemShopProductEntity entity) {
        if(useDB) {
            productEntityManager.deleteItemShopProductEntity(entity);
        }
    }
    public void createProduct(ShopType shopType, String categoryName, ItemStack item, int price) throws IllegalArgumentException, NoSuchObjectException {
        if(useDB) {
            Optional<ItemShopCategoryEntity> optionalCategory = categoryEntityManager.getItemShopCategoryEntityByNameAndShopType(categoryName, shopType);

            if(!optionalCategory.isPresent()) throw new NoSuchObjectException("Category with name " + categoryName + " does not exists");

            ItemShopCategoryEntity category = optionalCategory.get();

            if(item == null || Material.AIR.equals(item.getType())) throw new IllegalArgumentException("ItemStack cant be null or air");

            ItemShopProductEntity product = new ItemShopProductEntity(item, price,  category, shopType);

            productEntityManager.createOrUpdateItemShopProductEntity(product);
        }
    }
    public void createCategory(String name, ShopType shopType) throws InstanceAlreadyExistsException {
        if(useDB) {
            ItemShopCategoryEntity entity = new ItemShopCategoryEntity(name, shopType);
            categoryEntityManager.createOrUpdateAbilityModifierEntity(entity);
        }
    }

    public boolean subtractMoneyFromPlayer(Player player, ShopType shopType, int price) {

        ItemShopPlayerBalanceEntity entity = getPlayerBalanceEntity(player);

        switch (shopType) {
            case TIME:
            {
                int currentCoins = entity.getTimeCoins();
                if(currentCoins - price >= 0) {
                    entity.setTimeCoins((currentCoins-price));
                    playerBalanceEntityManager.createOrUpdateAItemShopPlayerBalanceEntity(entity);
                    return true;
                }
                return false;
            }
            case KILLS:
            {
                int currentCoins = entity.getKillCoins();
                if(currentCoins - price >= 0) {
                    entity.setKillCoins((currentCoins-price));
                    playerBalanceEntityManager.createOrUpdateAItemShopPlayerBalanceEntity(entity);
                    return true;
                }
                return false;
            }
            case MONEY:
            {
                return economyManager.withdrawMoney(player, price);
            }
        }
        return false;
    }
    public ItemShopPlayerBalanceEntity getPlayerBalanceEntity(Player player) {
        Optional<ItemShopPlayerBalanceEntity> optional = playerBalanceEntityManager.getItemShopCategoryEntityById(player.getUniqueId());
        ItemShopPlayerBalanceEntity entity;
        if(optional.isPresent()) {
            entity = optional.get();
        } else {
            entity = new ItemShopPlayerBalanceEntity(player.getUniqueId(), 0, 0);
            playerBalanceEntityManager.createOrUpdateAItemShopPlayerBalanceEntity(entity);
        }
        return entity;
    }
    public void createPlayerBalanceEntityIfNotExists(Player player) {
        Optional<ItemShopPlayerBalanceEntity> optional = playerBalanceEntityManager.getItemShopCategoryEntityById(player.getUniqueId());
        if(!optional.isPresent()) {
            ItemShopPlayerBalanceEntity entity = new ItemShopPlayerBalanceEntity(player.getUniqueId(), 0, 0);
            playerBalanceEntityManager.createOrUpdateAItemShopPlayerBalanceEntity(entity);
        }
    }
    public boolean addMoneyToPlayer(Player player, ShopType shopType, int price) {
        ItemShopPlayerBalanceEntity entity = getPlayerBalanceEntity(player);

        switch (shopType) {
            case TIME:
            {
                int currentCoins = entity.getTimeCoins();
                entity.setTimeCoins(currentCoins + price);
                playerBalanceEntityManager.createOrUpdateAItemShopPlayerBalanceEntity(entity);
                return true;
            }
            case KILLS:
            {
                int currentCoins = entity.getKillCoins();
                entity.setKillCoins(currentCoins + price);
                playerBalanceEntityManager.createOrUpdateAItemShopPlayerBalanceEntity(entity);
                return true;
            }
            case MONEY:
            {
                return economyManager.depositMoney(player, price);
            }
        }
        return false;
    }
    public int getPlayerBalance(Player player, ShopType shopType) {
        ItemShopPlayerBalanceEntity entity = getPlayerBalanceEntity(player);
        switch (shopType) {
            case TIME:
            {
                return entity.getTimeCoins();
            }
            case KILLS:
            {
                return entity.getKillCoins();
            }
            case MONEY:
            {
                return (int) economyManager.getBalance(player);
            }
        }
        return -1;
    }

}
