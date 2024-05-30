package me.xxgradzix.gradzixcore.itemPickupPriorities.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.itemPickupPriorities.ItemPickupPriorities;
import me.xxgradzix.gradzixcore.itemPickupPriorities.data.database.entities.PickupPrioritiesEntity;
import me.xxgradzix.gradzixcore.itemPickupPriorities.data.database.managers.PickupPrioritiesEntityManager;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DataManager {

    private static final boolean useDB = Gradzix_Core.USE_DB;

    public static void setItemPriorities(List<ItemStack> itemPriorities) {

        if(useDB) {

            PickupPrioritiesEntityManager manager = ItemPickupPriorities.getPickupPrioritiesEntityManager();
            PickupPrioritiesEntity entity = manager.getPickupPrioritiesEntity();

            entity.setItemPriorities(itemPriorities);
            manager.createOrUpdatePickupPrioritiesEntity(entity);
            ItemPickupPriorities.updateItemPriorities();
        }
    }

    public static List<ItemStack> getItemPriorities() {

        if(useDB) {
            PickupPrioritiesEntityManager manager = ItemPickupPriorities.getPickupPrioritiesEntityManager();
            PickupPrioritiesEntity entity = manager.getPickupPrioritiesEntity();

            return entity.getItemPriorities();
        } else {
            throw new RuntimeException("nie ma obsługi pliku konfiguracyjnego");
        }

    }



}
