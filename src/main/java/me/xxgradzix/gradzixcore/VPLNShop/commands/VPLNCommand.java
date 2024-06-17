package me.xxgradzix.gradzixcore.VPLNShop.commands;

import me.xxgradzix.gradzixcore.VPLNShop.data.DataManager;
import me.xxgradzix.gradzixcore.VPLNShop.data.database.VPLNOrderDTO;
import me.xxgradzix.gradzixcore.VPLNShop.messages.Messages;
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

        boolean isDiscord = false;

        if(args.length == 3) {
            String reason = args[2];
            if(reason.equalsIgnoreCase("discord")) {
                isDiscord = true;
            }
        }

        double amountDouble;
        try {
            amountDouble = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            sender.sendMessage("§cPodana kwota nie jest liczba!");
            return true;
        }

        String orderCreator = sender.getName();
        if(isDiscord) {
            orderCreator = "DISCORD";
        }

        VPLNOrderDTO dto = VPLNOrderDTO.builder()
                .orderCreator(orderCreator)
                .VPLNAmount(amountDouble)
                .orderStatus("PENDING")
                .playerName(playerName)
                .build();

        dataManager.createNewVPLNOrder(dto);

        if(isDiscord) {
            Bukkit.broadcastMessage(Messages.discordRewardVpln(playerName, amountDouble));
        } else {
            Bukkit.broadcastMessage(Messages.playerBoughtVpln(playerName, amountDouble));

        }


        return true;
    }
}
