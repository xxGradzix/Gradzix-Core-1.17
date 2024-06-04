package me.xxgradzix.gradzixcore.VPLNShop.data.database.managers;


import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.entities.VPLNOrderEntity;
import me.xxgradzix.gradzixcore.abstraction.BasicEntityManager;

import java.sql.SQLException;
import java.util.List;

public class VPLNItemShopOrdersManager extends BasicEntityManager<VPLNOrderEntity, Long> {


    public VPLNItemShopOrdersManager(ConnectionSource connectionSource) {
        super(connectionSource, VPLNOrderEntity.class);
    }


}