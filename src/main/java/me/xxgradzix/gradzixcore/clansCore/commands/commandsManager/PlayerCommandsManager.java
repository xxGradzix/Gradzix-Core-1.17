package me.xxgradzix.gradzixcore.clansCore.commands.commandsManager;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.messages.Messages;
import org.bukkit.entity.Player;

import java.util.Optional;

public class PlayerCommandsManager {

    public static void clanInfoCommand(Player player, ClanEntity clan) {
        player.sendMessage(Messages.CLAN_INFO(clan));
    }

}
