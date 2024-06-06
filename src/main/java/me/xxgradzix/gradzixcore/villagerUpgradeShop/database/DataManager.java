package me.xxgradzix.gradzixcore.villagerUpgradeShop.database;

import com.j256.ormlite.dao.BaseForeignCollection;
import com.j256.ormlite.dao.ForeignCollection;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.VPLNOrderDTO;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.entities.VPLNAccountEntity;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.entities.VPLNOrderEntity;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.managers.VPLNAccountsManager;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.managers.VPLNItemShopOrdersManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.VillagerUpgradeShop;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopEntity;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.entities.VillagerUpgradeShopProductEntity;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.managers.VillagerUpgradeShopEntityManager;
import me.xxgradzix.gradzixcore.villagerUpgradeShop.database.managers.VillagerUpgradeShopProductEntityManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataManager {

    private static final boolean useDB = Gradzix_Core.USE_DB;

    private VillagerUpgradeShopEntityManager villagerUpgradeShopEntityManager;
    private VillagerUpgradeShopProductEntityManager villagerUpgradeShopProductEntityManager;

    public DataManager(VillagerUpgradeShopEntityManager villagerUpgradeShopEntityManager, VillagerUpgradeShopProductEntityManager villagerUpgradeShopProductEntityManager) {
        this.villagerUpgradeShopEntityManager = villagerUpgradeShopEntityManager;
        this.villagerUpgradeShopProductEntityManager = villagerUpgradeShopProductEntityManager;
    }


    public void createUpgradeShop(String name) {
        if(useDB) {
            try {
                VillagerUpgradeShopEntity villagerUpgradeShop = VillagerUpgradeShopEntity.builder()
                        .name(name)
                        .build();

                villagerUpgradeShopEntityManager.createOrUpdateEntity(villagerUpgradeShop);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public List<VillagerUpgradeShopEntity> getAllShopEntities() {
        if(useDB) {
            try {
                return villagerUpgradeShopEntityManager.getAllEntities();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }
    public void deleteVillagerUpgradeShopProductEntity(Long id) {
        if(useDB) {
            try {
                villagerUpgradeShopProductEntityManager.deleteEntityById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createOrUpdateVillagerUpgradeShopProductEntity(VillagerUpgradeShopProductEntity product) {
        if(useDB) {
            try {
                villagerUpgradeShopProductEntityManager.createOrUpdateEntity(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Optional<VillagerUpgradeShopEntity> getShopEntityByName(String shopName) {
        if(useDB) {
            try {
                return villagerUpgradeShopEntityManager.getAllEntitiesByField("name",shopName).stream().findAny();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
}
