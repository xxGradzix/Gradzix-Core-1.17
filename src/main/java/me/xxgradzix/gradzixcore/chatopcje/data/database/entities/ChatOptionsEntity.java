package me.xxgradzix.gradzixcore.chatopcje.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_player_chat_options")
@Getter
@Setter
public class ChatOptionsEntity {

    public ChatOptionsEntity(UUID uuid, String playerName, boolean showDeathMessages, boolean showScratchCardMessages, boolean showChatMessages, boolean showShopMessages) {
        this.uuid = uuid;
        this.playerName = playerName;
        this.showDeathMessages = showDeathMessages;
        this.showScratchCardMessages = showScratchCardMessages;
        this.showChatMessages = showChatMessages;
        this.showShopMessages = showShopMessages;
    }

    public ChatOptionsEntity() {
    }

    @DatabaseField(unique = true, id = true)
    private UUID uuid;

    @DatabaseField
    private String playerName;

    @DatabaseField
    private boolean showDeathMessages;

    @DatabaseField
    private boolean showScratchCardMessages;
    @DatabaseField
    private boolean showChatMessages;

    @DatabaseField
    private boolean showShopMessages;

    public UUID getUuid() {
        return uuid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isShowDeathMessages() {
        return showDeathMessages;
    }

    public void setShowDeathMessages(boolean showDeathMessages) {
        this.showDeathMessages = showDeathMessages;
    }

    public boolean isShowScratchCardMessages() {
        return showScratchCardMessages;
    }

    public void setShowScratchCardMessages(boolean showScratchCardMessages) {
        this.showScratchCardMessages = showScratchCardMessages;
    }

    public boolean isShowChatMessages() {
        return showChatMessages;
    }

    public void setShowChatMessages(boolean showChatMessages) {
        this.showChatMessages = showChatMessages;
    }

    public boolean isShowShopMessages() {
        return showShopMessages;
    }

    public void setShowShopMessages(boolean showShopMessages) {
        this.showShopMessages = showShopMessages;
    }
}
