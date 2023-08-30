package me.xxgradzix.gradzixcore.chatopcje.data.database.managers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.chatopcje.data.database.entities.ChatOptionsEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ChatOptionsEntityManager {
    private Dao<ChatOptionsEntity, UUID> chatOptionsEntityDao;

    public ChatOptionsEntityManager(ConnectionSource connectionSource) {
        try {
            chatOptionsEntityDao = DaoManager.createDao(connectionSource, ChatOptionsEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createChatOptionsEntity(ChatOptionsEntity player) {
        try {
            chatOptionsEntityDao.create(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createOrUpdateChatOptionsEntity(ChatOptionsEntity chatOptionsEntity) {
        try {
            chatOptionsEntityDao.createOrUpdate(chatOptionsEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChatOptionsEntity getChatOptionsEntityById(UUID playerUUID) {
        try {
            return chatOptionsEntityDao.queryForId(playerUUID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<ChatOptionsEntity> getChatOptionsEntitiesWhereShowDeathMessageIs(boolean expectedValue) {
        try {
            return chatOptionsEntityDao.queryForEq("showDeathMessages", expectedValue);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<ChatOptionsEntity> getChatOptionsEntitiesWhereShowScratchCardMessageIs(boolean expectedValue) {
        try {
            return chatOptionsEntityDao.queryForEq("showScratchCardMessages", expectedValue);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<ChatOptionsEntity> getChatOptionsEntitiesWhereShowChatMessageIs(boolean expectedValue) {
        try {
            return chatOptionsEntityDao.queryForEq("showChatMessages", expectedValue);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    public void updatePlayerInventoryEntity(ChatOptionsEntity chatOptionsEntity) {
        try {
            chatOptionsEntityDao.update(chatOptionsEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChatOptionsEntity(ChatOptionsEntity chatOptionsEntity) {
        try {
            chatOptionsEntityDao.delete(chatOptionsEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}