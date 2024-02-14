package me.xxgradzix.gradzixcore.playerPerks.commands;

import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.items.ItemManager;
import me.xxgradzix.gradzixcore.playerPerks.messages.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GivePerkBooksCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
//            sender.sendMessage();
            return true;
        }
        Player player = (Player) sender;

        if(args.length < 1) {
            player.sendMessage(Messages.YOU_MUST_SPECIFY_PERK_TYPE);
            return true;
        }
        String perkBookTypeString = args[0];
        PerkType perkType;
        try {
            perkType = PerkType.valueOf(perkBookTypeString);
        } catch (IllegalArgumentException e) {
            player.sendMessage(Messages.THERE_IS_NO_SUCH_PERK);
            return true;
        }

        Player targetPlayer = player;
        if(args.length >= 2) {
            String playerNick = args[1];
            targetPlayer = player.getServer().getPlayer(playerNick);
            if(targetPlayer == null) {
                player.sendMessage(Messages.PLAYER_IS_NOT_ONLINE);
                return true;
            }
        }
        int amount = 1;
        if(args.length >= 3) {
            String amountString = args[2];
            try {
                amount = Integer.parseInt(amountString);
            } catch (NumberFormatException e) {
                player.sendMessage(Messages.AMOUNT_MUST_BE_A_NUMBER);
                return true;
            }
        }

        for(int i = 0; i < amount; i++)
            targetPlayer.getInventory().addItem(ItemManager.getPerkBook(perkType));


        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ArrayList<String> tabComplete = new ArrayList<>();
        if(command.getName().equalsIgnoreCase("givePerkBook")) {
            if(strings.length == 1) {
                for(PerkType perkType : PerkType.values()) {
                    tabComplete.add(perkType.name());
                }
                return tabComplete;
            }
            if(strings.length == 2) {
                for(Player player : commandSender.getServer().getOnlinePlayers()) {
                    tabComplete.add(player.getName());
                }
                return tabComplete;
            }
//            if(strings.length == 3) {
//                tabComplete.add("1");
//                tabComplete.add("2");
//                tabComplete.add("3");
//                tabComplete.add("4");
//                tabComplete.add("5");
//                tabComplete.add("6");
//                tabComplete.add("7");
//                tabComplete.add("8");
//                tabComplete.add("9");
//                tabComplete.add("10");
//                return tabComplete;
//            }
        }
        return null;
    }
}
