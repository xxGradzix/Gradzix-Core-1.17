package me.xxgradzix.gradzixcore.VPLNShop.data.database.managers;


import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.entities.VPLNAccountEntity;
import me.xxgradzix.gradzixcore.abstraction.BasicEntityManager;

import java.util.UUID;

public class VPLNAccountsManager extends BasicEntityManager<VPLNAccountEntity, UUID> {


    public VPLNAccountsManager(ConnectionSource connectionSource) {
        super(connectionSource, VPLNAccountEntity.class);
    }


}