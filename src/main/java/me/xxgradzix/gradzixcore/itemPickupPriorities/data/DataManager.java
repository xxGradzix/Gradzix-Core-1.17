package me.xxgradzix.gradzixcore.itemPickupPriorities.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.itemPickupPriorities.ItemPickupPriorities;
import me.xxgradzix.gradzixcore.itemPickupPriorities.data.database.entities.PickupPrioritiesEntity;
import me.xxgradzix.gradzixcore.itemPickupPriorities.data.database.managers.PickupPrioritiesEntityManager;
import me.xxgradzix.gradzixcore.serverconfig.ServerConfig;
import me.xxgradzix.gradzixcore.serverconfig.data.configfiles.ConfigServera;
import me.xxgradzix.gradzixcore.serverconfig.data.database.entities.ServerConfigEntity;
import me.xxgradzix.gradzixcore.serverconfig.data.database.managers.ServerConfigEntityManager;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DataManager {

    private static final boolean useDB = Gradzix_Core.USEDB;

    public static void setServerDamageMultiplier(double serverDamageMultiplier) {

        if(useDB) {

            ServerConfigEntityManager manager = ServerConfig.getServerConfigEntityManager();
            ServerConfigEntity entity = manager.getServerConfigEntity();
            entity.setServerDamageModifier(serverDamageMultiplier);
            manager.createOrUpdateServerConfigEntity(entity);

        } else {
            ConfigServera.setDamageMultiplier(serverDamageMultiplier);
        }

    }
    public static double getServerDamageMultiplier() {

        if(useDB) {

            ServerConfigEntityManager manager = ServerConfig.getServerConfigEntityManager();
            ServerConfigEntity entity = manager.getServerConfigEntity();
            return entity.getServerDamageModifier();
        } else {
            return ConfigServera.getDamageMultiplier();
        }

    }

    public static void setItemPriorities(List<ItemStack> itemPriorities) {

        if(useDB) {

            PickupPrioritiesEntityManager manager = ItemPickupPriorities.getPickupPrioritiesEntityManager();
            PickupPrioritiesEntity entity = manager.getPickupPrioritiesEntity();

            entity.setItemPriorities(itemPriorities);
            manager.createOrUpdatePickupPrioritiesEntity(entity);

        } else {
//            ConfigServera.setItemPriorities((ArrayList<ItemStack>) itemPriorities);
        }

    }
    public static List<ItemStack> getItemPriorities() {

        if(useDB) {
            PickupPrioritiesEntityManager manager = ItemPickupPriorities.getPickupPrioritiesEntityManager();
            PickupPrioritiesEntity entity = manager.getPickupPrioritiesEntity();

            return entity.getItemPriorities();
        } else {
            throw new RuntimeException("nie ma obsulgi pliku konfiguracyjnego");
        }

    }



}
