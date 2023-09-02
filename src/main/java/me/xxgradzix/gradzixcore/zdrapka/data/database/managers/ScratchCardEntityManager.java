package me.xxgradzix.gradzixcore.zdrapka.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.zdrapka.data.database.entities.ScratchCardEntity;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class ScratchCardEntityManager {
    private Dao<ScratchCardEntity, Integer> scratchCardEntityDao;

    public ScratchCardEntityManager(ConnectionSource connectionSource) {
        try {
            scratchCardEntityDao = DaoManager.createDao(connectionSource, ScratchCardEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void createPanelOptionsEntity(PanelOptionsEntity panelOptionsEntity) {
//
//        try {
//            if (panelOptionsEntityDao.queryForAll().size() < 1) {
//                panelOptionsEntityDao.create(panelOptionsEntity);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    public void createOrUpdateScratchCardEntity(ScratchCardEntity scratchCardEntity) {
        try {
            scratchCardEntity.setId(1);
            scratchCardEntityDao.createOrUpdate(scratchCardEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ScratchCardEntity getScratchCardEntity() {
        try {
            ScratchCardEntity scratchCardEntity = scratchCardEntityDao.queryForId(1);
            if(scratchCardEntity == null) {

//                List<ItemStack> itemStackList = new ArrayList<>();
                ItemStack[] items = {};

                scratchCardEntity =  new ScratchCardEntity(items);
                createOrUpdateScratchCardEntity(scratchCardEntity);
            }
            return scratchCardEntity;
        } catch (SQLException e) {
            e.printStackTrace();

//            List<ItemStack> itemStackList = new ArrayList<>();
            ItemStack[] items = {};
            ScratchCardEntity scratchCardEntity = new ScratchCardEntity(items);
            createOrUpdateScratchCardEntity(scratchCardEntity);
            return scratchCardEntity;
        }
    }
//    public List<PanelOptionsEntity> getPanelOptionsEntityWhereShowDeathMessageIs(boolean expectedValue) {
//        try {
//            return chatOptionsEntityDao.queryForEq("showDeathMessages", expectedValue);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

//        try {
//            return chatOptionsEntityDao.queryForEq("showScratchCardMessages", expectedValue);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

//        try {
//            return chatOptionsEntityDao.queryForEq("showChatMessages", expectedValue);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public void updateScratchCardEntity(ScratchCardEntity scratchCardEntity) {
        try {
            scratchCardEntity.setId(1);
            scratchCardEntityDao.update(scratchCardEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteScratchCardEntity(ScratchCardEntity scratchCardEntity) {
        try {
            scratchCardEntityDao.delete(new ScratchCardEntity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}