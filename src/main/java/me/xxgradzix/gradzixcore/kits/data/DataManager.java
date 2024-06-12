package me.xxgradzix.gradzixcore.kits.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.kits.data.database.entities.ItemInKitEntity;
import me.xxgradzix.gradzixcore.kits.data.database.entities.KitEntity;
import me.xxgradzix.gradzixcore.kits.data.database.managers.ItemInKitEntityManager;
import me.xxgradzix.gradzixcore.kits.data.database.managers.KitEntityManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DataManager {

    private static final boolean useDB = Gradzix_Core.USE_DB;

    private KitEntityManager kitEntityManager;
    private ItemInKitEntityManager itemInKitEntityManager;

    public DataManager(KitEntityManager kitEntityManager, ItemInKitEntityManager itemInKitEntity) {
        this.kitEntityManager = kitEntityManager;
        this.itemInKitEntityManager = itemInKitEntity;
    }



    public void createKit(String kitName, List<ItemStack> itemStack) {

        if(!useDB) return;

        // concate two strings
        // String permission = "gradzixcore.kit." + kitName;

        KitEntity kitEntity = new KitEntity(kitName, ("gradzixcore.kit." + kitName), -1, 0, new ItemStack(Material.COAL), new ItemStack(Material.DIAMOND));

        try {
            kitEntityManager.createOrUpdateEntity(kitEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        kitEntity = getKit(kitName);

        for (ItemStack item : itemStack) {

            if(item == null || item.getType().isAir()) continue;

            ItemInKitEntity itemInKitEntity = new ItemInKitEntity();
            itemInKitEntity.setItemStack(item);
            itemInKitEntity.setSlot(-1);
            itemInKitEntity.setKitEntity(kitEntity);

            kitEntity.getKitItems().add(itemInKitEntity);

            try {
                itemInKitEntityManager.createOrUpdateEntity(itemInKitEntity);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            kitEntityManager.createOrUpdateEntity(kitEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateKit(KitEntity kitEntity) {
        if(!useDB) return;

        try {
            kitEntityManager.createOrUpdateEntity(kitEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public KitEntity getKit(String kitName) {
        if(!useDB) return null;

        try {
            KitEntity kitEntity = kitEntityManager.getEntityById(kitName);

            if(kitEntity == null) return kitEntity;

            return kitEntity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<KitEntity> getAllKits() {
        if(!useDB) return Collections.emptyList();

        try {
            return kitEntityManager.getAllEntities();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteKit(String kitName) {
        if(!useDB) return;

        try {
            kitEntityManager.deleteEntity(getKit(kitName));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateItemInKitEntity(ItemInKitEntity itemInKitEntity) {
        if(!useDB) return;

        try {
            itemInKitEntityManager.createOrUpdateEntity(itemInKitEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
