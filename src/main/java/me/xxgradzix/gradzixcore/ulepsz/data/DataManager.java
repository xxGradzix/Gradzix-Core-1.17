package me.xxgradzix.gradzixcore.ulepsz.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.ulepsz.Ulepsz;
import me.xxgradzix.gradzixcore.ulepsz.data.database.entities.UpgradeEntity;
import me.xxgradzix.gradzixcore.ulepsz.data.database.managers.UpgradeEntityManager;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

public class DataManager {

    private static final boolean useDB = Gradzix_Core.USEDB;

//    private static final ServerConfigEntityManager manager = Ulepsz.getUpgradeEntityManager();

//    public static void addNewItem(ItemStack currentItem, ItemStack requiredItem, ItemStack nextItem) {
//
//        if(useDB) {
//            ServerConfigEntityManager manager = Ulepsz.getUpgradeEntityManager();
//
//            UpgradeEntity entity = new UpgradeEntity(currentItem, requiredItem, nextItem);
//
//            manager.createOrUpdateUpgradeEntity(entity);
//        } else {
////            UstawieniaOpcjeConfigFile.setAutoWymianaStatus(player, true);
//        }
//    }
//    public static void addNewItem(UpgradeEntity entity) {
//
//        if(useDB) {
//            ServerConfigEntityManager manager = Ulepsz.getUpgradeEntityManager();
//            manager.createOrUpdateUpgradeEntity(entity);
////            manager.createUpgradeEntity(entity);
//        } else {
//
//        }
//    }

    public static void setUpgradeItems(List<UpgradeEntity> upgradeEntities) {

        if(useDB) {
            UpgradeEntityManager manager = Ulepsz.getUpgradeEntityManager();
            manager.deleteAllUpgradeEntities();
            for(UpgradeEntity upgradeEntity : upgradeEntities) {
                List<ItemStack> itemStacks = getAllUpgradeEntities().stream()
                        .map(UpgradeEntity::getCurrentItem)
                        .collect(Collectors.toList());

                if(itemStacks.contains(upgradeEntity.getCurrentItem())) continue;

                manager.createOrUpdateUpgradeEntity(upgradeEntity);
            }

        } else {

        }

    }

    public static ItemStack getRequiredItem(ItemStack currentItem) {

        if(useDB) {
            UpgradeEntityManager manager = Ulepsz.getUpgradeEntityManager();
            UpgradeEntity entity = manager.getUpgradeEntityByItemStackKey(currentItem);

            if(entity == null) return null;

            return entity.getItemNeeded();

        } else {
            throw new RuntimeException("Nie ma obslugi pliku konfiguracyjnego");
        }
    }
    public static ItemStack getNextItem(ItemStack currentItem) {

        if(useDB) {
            UpgradeEntityManager manager = Ulepsz.getUpgradeEntityManager();
            UpgradeEntity entity = manager.getUpgradeEntityByItemStackKey(currentItem);

            if(entity == null) return null;

            return entity.getNextItem();

        } else {
            throw new RuntimeException("Nie ma obslugi pliku konfiguracyjnego");
        }
    }
    public static List<UpgradeEntity> getAllUpgradeEntities() {

        if(useDB) {
            UpgradeEntityManager manager = Ulepsz.getUpgradeEntityManager();
            return manager.getUpgradeEntities();

        } else {
            throw new RuntimeException("Nie ma obslugi pliku konfiguracyjnego");
        }
    }




}
