package me.xxgradzix.gradzixcore.incognito.commands;

import me.xxgradzix.gradzixcore.incognito.data.database.entities.IncognitoAdminEntity;
import me.xxgradzix.gradzixcore.incognito.data.database.managers.IncognitoAdminEntityManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AddIncognitoAdminCommand implements CommandExecutor, TabCompleter {

    private final IncognitoAdminEntityManager incognitoAdminEntityManager;

    public AddIncognitoAdminCommand(IncognitoAdminEntityManager incognitoAdminEntityManager) {
        this.incognitoAdminEntityManager = incognitoAdminEntityManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length < 1) {
            sender.sendMessage("§cPoprawne uzycie komendy to: /incognitoAdmin [ADD/REMOVE/LIST]");
            return false;
        }
        String action = args[0];

        IncognitoAdminEntity incognitoAdminEntity;

        if(action.equalsIgnoreCase("list")) {
            List<IncognitoAdminEntity> incognitoAdminEntities = incognitoAdminEntityManager.getAllIncognitoAdminEntities();

            sender.sendMessage("§aLista widzących nicki graczy z incognito: ");
            for (IncognitoAdminEntity admin : incognitoAdminEntities) {
                sender.sendMessage("§a- " + Bukkit.getOfflinePlayer(admin.getUuid()).getName());
            }
            return true;
        }

        String nick = args[1];

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(nick);

        if(offlinePlayer == null) {
            sender.sendMessage("§cNie znaleziono gracza o nicku: " + nick);
            return false;
        }

        if(args.length < 2) {
            sender.sendMessage("§cPoprawne uzycie komendy to: /incognitoAdmin [ADD/REMOVE/LIST]");
            return false;
        }

        if(action.equalsIgnoreCase("add")) {

            incognitoAdminEntity = new IncognitoAdminEntity(offlinePlayer.getUniqueId(), offlinePlayer.getName());

            incognitoAdminEntityManager.createIncognitoAdminEntity(incognitoAdminEntity);

            sender.sendMessage("§aDodano widzenie nicku gracza dla: " + offlinePlayer.getName());

        } else if(action.equalsIgnoreCase("remove")) {

            incognitoAdminEntity = incognitoAdminEntityManager.getIncognitoAdminEntityById(offlinePlayer.getUniqueId());

            if(incognitoAdminEntity == null) return false;
            incognitoAdminEntityManager.deleteIncognitoModeEntity(incognitoAdminEntity);

            sender.sendMessage("§aUsunieto widzenie nicku gracza dla: " + offlinePlayer.getName());

        } else {
            sender.sendMessage("§cPoprawne uzycie komendy to: /incognitoAdmin [ADD/REMOVE] <nick>");
            return false;
        }


        return false;
    }


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> list = new ArrayList<>();
        if(command.getName().equalsIgnoreCase("incognitoAdmin")) {
            if(args.length == 1) {

                list.add("add");
                list.add("remove");
                list.add("list");
                return list;
            }
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("remove")) {

                    for (IncognitoAdminEntity offlinePlayer : incognitoAdminEntityManager.getAllIncognitoAdminEntities()) {
                        list.add(offlinePlayer.getNick());
                    }
                }
                if(args[0].equalsIgnoreCase("add")) {

                    for(OfflinePlayer player : Bukkit.getOperators()) {
                        list.add(player.getName());
                    }
                }
            }
        }
        return list;
    }
}
