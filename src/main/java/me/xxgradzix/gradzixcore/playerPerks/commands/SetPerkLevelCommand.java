package me.xxgradzix.gradzixcore.playerPerks.commands;

import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static me.xxgradzix.gradzixcore.playerPerks.PerkType.ADDITIONAL_HEARTS;

public class SetPerkLevelCommand implements CommandExecutor, TabCompleter {

    private final PlayerPerkEntityManager playerPerkEntityManager;

    public SetPerkLevelCommand(PlayerPerkEntityManager playerPerkEntityManager) {
        this.playerPerkEntityManager = playerPerkEntityManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length < 3) {
            sender.sendMessage("§cUzycie: /ustawPerk <gracz> <perk> <poziom>");
            return true;
        }

        Player player;
        int perkLevel;
        PerkType perkType;

        try {
            player = Bukkit.getPlayer(args[0]);
        } catch (ClassCastException e) {
            sender.sendMessage("§cNie ma takiego gracza jak " + args[0] + "!");
            return true;
        }

        try {
            perkType = PerkType.valueOf(args[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            sender.sendMessage("§cNie ma takiego perku!");
            return true;
        }

        try {
            perkLevel = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            sender.sendMessage("§cPoziom musi byc liczbą!");
            return true;
        }

        if(perkLevel < 0) {
            sender.sendMessage("§cPoziom musi byc wiekszy lub rowny 0!");
            return true;
        }

        PlayerPerksEntity playerPerksEntity = playerPerkEntityManager.getPlayerPerksEntityById(player.getUniqueId());

        playerPerksEntity.setPerkLevel(perkType, perkLevel);

        playerPerkEntityManager.createOrUpdatePlayerPerksEntity(playerPerksEntity);

        if(ADDITIONAL_HEARTS.equals(perkType)) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20 + perkLevel);
        }
        return true;

    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        ArrayList<String> list = new ArrayList<>();

        if(args.length == 1) {
            return null;
        }
        if(args.length == 2) {
            for(PerkType perkType : PerkType.values()) {
                list.add(perkType.toString().toLowerCase());
            }
            return list;
        }
        if(args.length == 3) {
            list.add("0");
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            list.add("5");
            return list;
        }

        return list;
    }
}
