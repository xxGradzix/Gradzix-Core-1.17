package me.xxgradzix.gradzixcore.playerPerks.commands;

import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import me.xxgradzix.gradzixcore.playerPerks.messages.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PerksCommand implements CommandExecutor {

    private final PlayerPerkEntityManager playerPerkEntityManager;

    public PerksCommand(PlayerPerkEntityManager playerPerkEntityManager) {
        this.playerPerkEntityManager = playerPerkEntityManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        PlayerPerksEntity playerPerksEntity = playerPerkEntityManager.getPlayerPerksEntityById(player.getUniqueId());

        Messages.sendPerksLevelsToPlayer(player, playerPerksEntity);
        return true;


    }
}
