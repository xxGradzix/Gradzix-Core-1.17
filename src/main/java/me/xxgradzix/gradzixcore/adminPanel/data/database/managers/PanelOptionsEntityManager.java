package me.xxgradzix.gradzixcore.adminPanel.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.adminPanel.data.database.entities.PanelOptionsEntity;

import java.sql.SQLException;

public class PanelOptionsEntityManager {
    private Dao<PanelOptionsEntity, Integer> panelOptionsEntityDao;

    public PanelOptionsEntityManager(ConnectionSource connectionSource) {
        try {
            panelOptionsEntityDao = DaoManager.createDao(connectionSource, PanelOptionsEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdatePanelOptionsEntity(PanelOptionsEntity panelOptionsEntity) {
        try {
            panelOptionsEntity.setId(1);
            panelOptionsEntityDao.createOrUpdate(panelOptionsEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PanelOptionsEntity getPanelOptionsEntity() {
        try {
            PanelOptionsEntity panelOptionsEntity = panelOptionsEntityDao.queryForId(1);
            if(panelOptionsEntity == null) {
                panelOptionsEntity =  new PanelOptionsEntity(true, true, true, true);
                createOrUpdatePanelOptionsEntity(panelOptionsEntity);
            }
            return panelOptionsEntity;
        } catch (SQLException e) {
            e.printStackTrace();
            PanelOptionsEntity panelOptionsEntity = new PanelOptionsEntity(true, true, true, true);
            createOrUpdatePanelOptionsEntity(panelOptionsEntity);
            return panelOptionsEntity;
        }
    }

    public void updatePanelOptionsEntity(PanelOptionsEntity panelOptionsEntity) {
        try {
            panelOptionsEntity.setId(1);
            panelOptionsEntityDao.update(panelOptionsEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePanelOptionsEntity(PanelOptionsEntity panelOptionsEntity) {
        try {
            panelOptionsEntityDao.delete(panelOptionsEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}