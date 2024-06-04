package me.xxgradzix.gradzixcore.VPLNShop.commands;

import me.xxgradzix.gradzixcore.VPLNShop.data.DataManager;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.VPLNOrderDTO;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VPLNCommand implements CommandExecutor {

    private final DataManager dataManager;

    public VPLNCommand(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] args) {

        if(args.length < 2) {
            sender.sendMessage("§cPoprawne uzycie: /vpln <nick> <kwota>");
            return true;
        }

        String playerName = args[0];
        String amount = args[1];

        int amountInt;
        try {
            amountInt = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            sender.sendMessage("§cPodana kwota nie jest liczba!");
            return true;
        }


        VPLNOrderDTO dto = VPLNOrderDTO.builder()
                .orderCreator(sender.getName())
                .VPLNAmount(amountInt)
                .orderStatus("PENDING")
                .playerName(playerName)
                .build();

        dataManager.createNewVPLNOrder(dto);

        return true;
    }
}
