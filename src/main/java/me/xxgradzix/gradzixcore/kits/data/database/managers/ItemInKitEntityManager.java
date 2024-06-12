package me.xxgradzix.gradzixcore.kits.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.abstraction.BasicEntityManager;
import me.xxgradzix.gradzixcore.kits.data.database.entities.ItemInKitEntity;
import me.xxgradzix.gradzixcore.kits.data.database.entities.KitEntity;

public class ItemInKitEntityManager extends BasicEntityManager<ItemInKitEntity, Long> {

    public ItemInKitEntityManager(ConnectionSource connectionSource) {
        super(connectionSource, ItemInKitEntity.class);
    }
}