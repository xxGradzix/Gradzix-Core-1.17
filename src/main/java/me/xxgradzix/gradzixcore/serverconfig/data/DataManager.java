package me.xxgradzix.gradzixcore.serverconfig.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.serverconfig.ServerConfig;
import me.xxgradzix.gradzixcore.serverconfig.data.configfiles.ConfigServera;
import me.xxgradzix.gradzixcore.serverconfig.data.database.entities.ServerConfigEntity;
import me.xxgradzix.gradzixcore.serverconfig.data.database.managers.ServerConfigEntityManager;

public class DataManager {

    private static final boolean useDB = Gradzix_Core.USE_DB;

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
}
