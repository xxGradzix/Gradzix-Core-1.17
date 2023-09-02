package me.xxgradzix.gradzixcore.zdrapka.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.zdrapka.Zdrapka;
import me.xxgradzix.gradzixcore.zdrapka.data.database.entities.ScratchCardEntity;
import org.bukkit.inventory.ItemStack;

public class DataManager {

    private static boolean useDB = Gradzix_Core.USEDB;

    public static void setScratchCardItems(ItemStack[] items) {

        if(useDB) {
            ScratchCardEntity scratchCardEntity = Zdrapka.getScratchCardEntityManager().getScratchCardEntity();
            scratchCardEntity.setItems(items);
            Zdrapka.getScratchCardEntityManager().updateScratchCardEntity(scratchCardEntity);
        } else {
//            PanelAdminConfigFile.setChatStatus(value);
        }
    }


    //// GET

    public static ItemStack[] getScratchCardItems() {

        if(useDB) {
            ScratchCardEntity scratchCardEntity = Zdrapka.getScratchCardEntityManager().getScratchCardEntity();
            return scratchCardEntity.getItems();
        } else {
            throw new RuntimeException("nie ma obslugi pliku konfiguracyjnego");
        }
    }


}
