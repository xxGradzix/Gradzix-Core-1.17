package me.xxgradzix.gradzixcore.scratchCard.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.scratchCard.Zdrapka;
import me.xxgradzix.gradzixcore.scratchCard.data.database.entities.ScratchCardEntity;
import org.bukkit.inventory.ItemStack;

public class DataManager {

    private static final boolean useDB = Gradzix_Core.USE_DB;

    public static void setScratchCardItems(ItemStack[] items) {

        if(useDB) {
            ScratchCardEntity scratchCardEntity = Zdrapka.getScratchCardEntityManager().getScratchCardEntity();
            scratchCardEntity.setItems(items);
            Zdrapka.getScratchCardEntityManager().updateScratchCardEntity(scratchCardEntity);
        }
    }


    //// GET

    public static ItemStack[] getScratchCardItems() {

        if(useDB) {
            ScratchCardEntity scratchCardEntity = Zdrapka.getScratchCardEntityManager().getScratchCardEntity();
            return scratchCardEntity.getItems();
        } else {
            throw new RuntimeException("nie ma obsługi pliku konfiguracyjnego");
        }
    }


}
