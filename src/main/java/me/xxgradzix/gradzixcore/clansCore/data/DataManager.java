package me.xxgradzix.gradzixcore.clansCore.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;

public class DataManager {

    private static final boolean useDB = Gradzix_Core.USE_DB;

//    public static void setServerDamageMultiplier(double serverDamageMultiplier) {
//
//        if(useDB) {
//
//            ServerConfigEntityManager manager = ServerConfig.getServerConfigEntityManager();
//            ServerConfigEntity entity = manager.getServerConfigEntity();
//            entity.setServerDamageModifier(serverDamageMultiplier);
//            manager.createOrUpdateServerConfigEntity(entity);
//
//        } else {
//            ConfigServera.setDamageMultiplier(serverDamageMultiplier);
//        }
//
//    }
//    public static double getServerDamageMultiplier() {
//
//        if(useDB) {
//
//            ServerConfigEntityManager manager = ServerConfig.getServerConfigEntityManager();
//            ServerConfigEntity entity = manager.getServerConfigEntity();
//            return entity.getServerDamageModifier();
//        } else {
//            return ConfigServera.getDamageMultiplier();
//        }
//
//    }
//
//    public static void setItemPriorities(List<ItemStack> itemPriorities) {
//
//        if(useDB) {
//
//            GeneratorManager manager = ItemPickupPriorities.getPickupPrioritiesEntityManager();
//            Generator entity = manager.getPickupPrioritiesEntity();
//
//            entity.setItemPriorities(itemPriorities);
//            manager.createOrUpdatePickupPrioritiesEntity(entity);
//
//        }
//
//    }
//    public static List<ItemStack> getItemPriorities() {
//
//        if(useDB) {
//            GeneratorManager manager = ItemPickupPriorities.getPickupPrioritiesEntityManager();
//            Generator entity = manager.getPickupPrioritiesEntity();
//
//            return entity.getItemPriorities();
//        } else {
//            throw new RuntimeException("nie ma obsługi pliku konfiguracyjnego");
//        }
//
//    }
//


}
