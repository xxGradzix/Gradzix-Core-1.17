package me.xxgradzix.gradzixcore.ustawienia.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.ustawienia.Ustawienia;
import me.xxgradzix.gradzixcore.ustawienia.data.database.entities.SettingsEntity;
import me.xxgradzix.gradzixcore.ustawienia.data.database.entities.SettingsItemsEntity;
import me.xxgradzix.gradzixcore.ustawienia.data.database.managers.SettingItemsEntityManager;
import me.xxgradzix.gradzixcore.ustawienia.data.database.managers.SettingOptionsEntityManager;
import me.xxgradzix.gradzixcore.ustawienia.data.configfiles.UstawieniaOpcjeConfigFile;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class DataManager {

    private static boolean useDB = Gradzix_Core.USEDB;

//    private static PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();


//    if(panelOptionsEntity == null) {
//        Panel.getPanelOptionsEntityManager().createOrUpdatePanelOptionsEntity(new PanelOptionsEntity(true, true, true, true));
//    }

    public static void setAutoExchangeStatus(Player player, boolean value) {

        if(useDB) {
            SettingOptionsEntityManager manager = Ustawienia.getSettingOptionsEntityManager();
            SettingsEntity entity = manager.getSettingsEntityByUUID(player.getUniqueId());
            entity.setAutoExchange(value);
            manager.createOrUpdateSettingsEntity(entity);
        } else {
            UstawieniaOpcjeConfigFile.setAutoWymianaStatus(player, true);
        }
    }
    public static void setAutoSellStatus(Player player, boolean value) {

        if(useDB) {
            SettingOptionsEntityManager manager = Ustawienia.getSettingOptionsEntityManager();
            SettingsEntity entity = manager.getSettingsEntityByUUID(player.getUniqueId());
            entity.setAutoSell(value);
            manager.createOrUpdateSettingsEntity(entity);
        } else {
            UstawieniaOpcjeConfigFile.setAutoSprzedazStatus(player, true);
        }
    }
    public static void setAutoExchangeItems(HashMap<ItemStack, ItemStack> itemMap) {

        if(useDB) {
            SettingItemsEntityManager manager = Ustawienia.getSettingItemsEntityManager();
            SettingsItemsEntity entity = manager.getSettingsItemsEntity();
            entity.setItemsToExchange(itemMap);
            manager.createOrUpdateSettingsItemsEntity(entity);
        } else {
//            UstawieniaOpcjeConfigFile.setAutoSprzedazStatus(player, true);
        }
    }
    public static void setAutoSellItems(HashMap<ItemStack, Integer> itemMap) {

        if(useDB) {
            SettingItemsEntityManager manager = Ustawienia.getSettingItemsEntityManager();
            SettingsItemsEntity entity = manager.getSettingsItemsEntity();
            entity.setItemsToSell(itemMap);
            manager.createOrUpdateSettingsItemsEntity(entity);
        } else {
//            UstawieniaOpcjeConfigFile.setAutoSprzedazStatus(player, true);
        }
    }
    public static Map<ItemStack, ItemStack> getAutoExchangeItems() {

        if(useDB) {
            SettingItemsEntityManager manager = Ustawienia.getSettingItemsEntityManager();
            SettingsItemsEntity entity = manager.getSettingsItemsEntity();
            return entity.getItemsToExchange();
        } else {
            throw new RuntimeException("Nie ma obsługi pliku konfiguracyjnego");
        }
    }
    public static Map<ItemStack, Integer> getAutoSellItems() {

        if(useDB) {
            SettingItemsEntityManager manager = Ustawienia.getSettingItemsEntityManager();
            SettingsItemsEntity entity = manager.getSettingsItemsEntity();
            return entity.getItemsToSell();
        } else {
            throw new RuntimeException("Nie ma obsługi pliku konfiguracyjnego");
        }
    }

    //// GET
    public static boolean getAutoExchangeStatus(Player player) {

        if(useDB) {
            SettingOptionsEntityManager manager = Ustawienia.getSettingOptionsEntityManager();
            SettingsEntity entity = manager.getSettingsEntityByUUID(player.getUniqueId());
            return entity.isAutoExchange();
        } else {
            return UstawieniaOpcjeConfigFile.getAutoWymianaStatus(player);
        }
    }
    public static boolean getAutoSellStatus(Player player) {

        if(useDB) {
            SettingOptionsEntityManager manager = Ustawienia.getSettingOptionsEntityManager();
            SettingsEntity entity = manager.getSettingsEntityByUUID(player.getUniqueId());
            return entity.isAutoSell();
        } else {
            return UstawieniaOpcjeConfigFile.getAutoSprzedazStatus(player);
        }
    }



}
