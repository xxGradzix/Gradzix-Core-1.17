package me.xxgradzix.gradzixcore.VPLNShop.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.VPLNOrderDTO;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.entities.VPLNAccountEntity;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.entities.VPLNOrderEntity;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.managers.VPLNAccountsManager;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.managers.VPLNItemShopOrdersManager;
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
import java.util.Collections;
import java.util.List;

public class DataManager {

    private static final boolean useDB = Gradzix_Core.USE_DB;

    private VPLNItemShopOrdersManager vplnItemShopOrdersManager;
    private VPLNAccountsManager vplnAccountsManager;

    public DataManager(VPLNItemShopOrdersManager vplnItemShopOrdersManager, VPLNAccountsManager vplnAccountsManager) {
        this.vplnItemShopOrdersManager = vplnItemShopOrdersManager;
        this.vplnAccountsManager = vplnAccountsManager;
    }


    public void createNewVPLNOrder(VPLNOrderDTO dto) {

        if(useDB) {
            VPLNOrderEntity entity = VPLNOrderEntity.builder()
                    .orderCreator(dto.getOrderCreator())
                    .playerName(dto.getPlayerName())
                    .orderDateTime(LocalDateTime.now())
                    .VPLNAmount(dto.getVPLNAmount())
                    .orderStatus("PENDING")
                    .build();

            try {
                vplnItemShopOrdersManager.createOrUpdateEntity(entity);
            } catch (SQLException e) {

                String fileName = dto.getPlayerName() + "_" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ".txt";
                Path errorFilePath = Paths.get(Gradzix_Core.getInstance().getDataFolder().getAbsolutePath(), fileName);
                String errorMessage = e.getMessage();

                try {
                    Files.write(errorFilePath, errorMessage.getBytes());
                    Files.write(errorFilePath, dto.toString().getBytes());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            Bukkit.getScheduler().runTaskLater(Gradzix_Core.getInstance(), () -> {
                Player player = Bukkit.getPlayer(dto.getPlayerName());
                if(player != null) {
                    player.sendMessage("§aOtrzymales nowe zamowienie VPLN! Sprawdz dostepne zamowienia w sklepie!");
                }
                collectAllPendingOrders(player);
            }, 20L * 3);

        }

    }

    public Double getPlayerVPLNAmount(OfflinePlayer player) {
        if(useDB) {
            try {
                VPLNAccountEntity vplnAccount = getVPLNAccount(player);
                if(vplnAccount == null) {
                    return 0.0;
                }
                return vplnAccount.getVPLNAmount();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0.0;
            }
        }
        return 0.0;
    }

//    public void addVPLNAmount(OfflinePlayer player, double amount) {
//        if(useDB) {
//            try {
//                VPLNAccounts vplnAccount = getVPLNAccount(player);
//                if(vplnAccount == null) {
//                    return;
//                }
//                vplnAccount.setVPLNAmount(vplnAccount.getVPLNAmount() + amount);
//                vplnAccountsManager.createOrUpdateEntity(vplnAccount);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public void subtractVPLNAmount(OfflinePlayer player, double amount) {
        if(useDB) {
            try {
                VPLNAccountEntity vplnAccount = getVPLNAccount(player);
                if(vplnAccount == null) {
                    return;
                }
                vplnAccount.setVPLNAmount(vplnAccount.getVPLNAmount() - amount);
                vplnAccountsManager.createOrUpdateEntity(vplnAccount);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private VPLNAccountEntity getVPLNAccount(OfflinePlayer player) throws SQLException {
        if(useDB) {
            VPLNAccountEntity entityById = vplnAccountsManager.getEntityById(player.getUniqueId());
            if(entityById == null) {
                VPLNAccountEntity newEntity = VPLNAccountEntity.builder()
                        .uuid(player.getUniqueId())
                        .playerName(player.getName())
                        .VPLNAmount(0.0)
                        .build();
                vplnAccountsManager.createOrUpdateEntity(newEntity);
                return newEntity;
            }
            return entityById;
        }
        return null;
    }

    public boolean collectAllPendingOrders(OfflinePlayer player) {
        if(useDB) {
            try {
                List<VPLNOrderEntity> allOrders;
                try {
                    allOrders = getPlayerVPLNOrders(player);
                } catch (SQLException e) {
                    return false;
                }


//                if(allOrders.stream().noneMatch(order -> order.getOrderStatus().equalsIgnoreCase("PENDING"))) {
//                    return false;
//                }

                VPLNAccountEntity vplnAccount;
                try {
                    vplnAccount = getVPLNAccount(player);
                } catch (SQLException e) {
                    return false;
                }

                if(vplnAccount == null) {
                    return false;
                }

                double totalAmount = 0.0;

                for (VPLNOrderEntity order : allOrders) {
                    if (order.getOrderStatus().equalsIgnoreCase("PENDING")) {
                        totalAmount += order.getVPLNAmount();
                        order.setOrderStatus("COLLECTED");
                        vplnItemShopOrdersManager.createOrUpdateEntity(order);
                    }
                }

                vplnAccount.setVPLNAmount(vplnAccount.getVPLNAmount() + totalAmount);
                vplnAccountsManager.createOrUpdateEntity(vplnAccount);

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }


    public List<VPLNOrderEntity> getPlayerVPLNOrders(OfflinePlayer player) throws SQLException {
        if(useDB) {

            return vplnItemShopOrdersManager.getAllEntitiesByField("player_name", player.getName());

        }
        return Collections.emptyList();
    }
    public List<VPLNOrderEntity> getAllVPLNOrders() throws SQLException {
        if(useDB) {

            return vplnItemShopOrdersManager.getAllEntities();

        }
        return Collections.emptyList();
    }

}
