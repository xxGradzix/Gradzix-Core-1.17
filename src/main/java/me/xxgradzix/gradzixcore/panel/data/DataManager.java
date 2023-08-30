package me.xxgradzix.gradzixcore.panel.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.panel.Panel;
import me.xxgradzix.gradzixcore.panel.data.configfiles.PanelAdminConfigFile;
import me.xxgradzix.gradzixcore.panel.data.database.entities.PanelOptionsEntity;

public class DataManager {

    private static boolean useDB = Gradzix_Core.USEDB;

//    private static PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();


//    if(panelOptionsEntity == null) {
//        Panel.getPanelOptionsEntityManager().createOrUpdatePanelOptionsEntity(new PanelOptionsEntity(true, true, true, true));
//    }

    public static void setChatStatus(boolean value) {

        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            panelOptionsEntity.setChatEnabled(value);
            Panel.getPanelOptionsEntityManager().updatePanelOptionsEntity(panelOptionsEntity);
        } else {
            PanelAdminConfigFile.setChatStatus(value);
        }
    }
    public static void setZdrapkaStatus(boolean value) {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            panelOptionsEntity.setZdrapkaEnabled(value);
            Panel.getPanelOptionsEntityManager().updatePanelOptionsEntity(panelOptionsEntity);
        } else {
            PanelAdminConfigFile.setZdrapkaStatus(value);
        }
    }
    public static void setKitStatus(boolean value) {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            panelOptionsEntity.setKityEnabled(value);
            Panel.getPanelOptionsEntityManager().updatePanelOptionsEntity(panelOptionsEntity);
        } else {
            PanelAdminConfigFile.setKityStatus(value);
        }
    }
    public static void setOsiagnieciaStatus(boolean value) {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            panelOptionsEntity.setOsiagnieciaEnabled(value);
            Panel.getPanelOptionsEntityManager().updatePanelOptionsEntity(panelOptionsEntity);
        } else {
            PanelAdminConfigFile.setOsiagnieciaStatus(value);
        }
    }

    //// GET

    public static boolean getChatStatus() {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
//            panelOptionsEntity.setChatEnabled(value);
            return panelOptionsEntity.isChatEnabled();
        } else {
            return PanelAdminConfigFile.getChatStatus();
        }
    }
    public static boolean getZdrapkaStatus() {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            return panelOptionsEntity.isZdrapkaEnabled();
        } else {
            return PanelAdminConfigFile.getZdrapkaStatus();
        }
    }
    public static boolean getKitStatus() {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            return panelOptionsEntity.isKityEnabled();
        } else {
            return PanelAdminConfigFile.getKityStatus();
        }
    }
    public static boolean getOsiagnieciaStatus() {
        if(useDB) {
            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();
            return panelOptionsEntity.isOsiagnieciaEnabled();
        } else {
            return PanelAdminConfigFile.getOsiagnieciaStatus();
        }
    }

}
