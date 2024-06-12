package me.xxgradzix.gradzixcore.kits.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.abstraction.BasicEntityManager;
import me.xxgradzix.gradzixcore.kits.data.database.entities.KitEntity;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.List;

public class KitEntityManager extends BasicEntityManager<KitEntity, String> {

    public KitEntityManager(ConnectionSource connectionSource) {
        super(connectionSource, KitEntity.class);
    }
}