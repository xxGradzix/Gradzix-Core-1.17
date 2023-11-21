package me.xxgradzix.gradzixcore.magicPond.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.magicPond.data.database.entities.MagicPondEntity;
import me.xxgradzix.gradzixcore.magicPond.data.database.managers.MagicPondEntityManager;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class DataManager {

    private static final boolean useDB = Gradzix_Core.USEDB;

    private final MagicPondEntityManager magicPondEntityManager;

    public DataManager(MagicPondEntityManager magicPondEntityManager) {
        this.magicPondEntityManager = magicPondEntityManager;
    }

    public void setMagicPondEntityRewards(HashMap<ItemStack, Integer> rewards) {

        if(useDB) {
            MagicPondEntity magicPondEntity = magicPondEntityManager.getMagicPondEntity();
            if(magicPondEntity == null) throw new RuntimeException("Nie ma obiektu magic pond");
            magicPondEntity.setRewards(rewards);
            magicPondEntityManager.createOrUpdateMagicPondEntity(magicPondEntity);
        }
    }

    public HashMap<ItemStack, Integer> getMagicPondEntityRewards() {

        if(useDB) {
            MagicPondEntity magicPondEntity = magicPondEntityManager.getMagicPondEntity();
            return magicPondEntity.getRewards();
        } else {
            throw new RuntimeException("nie ma obsługi pliku konfiguracyjnego");
        }
    }


}
