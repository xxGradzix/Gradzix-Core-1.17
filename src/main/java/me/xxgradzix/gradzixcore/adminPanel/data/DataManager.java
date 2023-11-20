package me.xxgradzix.gradzixcore.adminPanel.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.adminPanel.Panel;
import me.xxgradzix.gradzixcore.adminPanel.data.database.entities.PanelOptionsEntity;

public class DataManager {

    private static boolean useDB = Gradzix_Core.USEDB;

    public static void setChatStatus(boolean value) {

        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            panelOptionsEntity.setChatEnabled(value);
            Panel.getPanelOptionsEntityManager().updatePanelOptionsEntity(panelOptionsEntity);
        }
    }
    public static void setScratchCardStatus(boolean value) {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            panelOptionsEntity.setScratchCardEnabled(value);
            Panel.getPanelOptionsEntityManager().updatePanelOptionsEntity(panelOptionsEntity);
        }
    }
    public static void setKitStatus(boolean value) {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            panelOptionsEntity.setKitsEnabled(value);
            Panel.getPanelOptionsEntityManager().updatePanelOptionsEntity(panelOptionsEntity);
        }
    }
    public static void setAchievementStatus(boolean value) {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            panelOptionsEntity.setAchievementsEnabled(value);
            Panel.getPanelOptionsEntityManager().updatePanelOptionsEntity(panelOptionsEntity);
        }
    }

    //// GET
    public static boolean getChatStatus() {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            return panelOptionsEntity.isChatEnabled();
        } else {
            return false;
        }
    }
    public static boolean getScratchCardStatus() {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            return panelOptionsEntity.isScratchCardEnabled();
        } else {
            return false;
        }
    }
    public static boolean getKitStatus() {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            return panelOptionsEntity.isKitsEnabled();
        } else {
            return false;
        }
    }
    public static boolean getAchievementStatus() {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            return panelOptionsEntity.isAchievementsEnabled();
        } else {
            return false;
        }
    }

}
