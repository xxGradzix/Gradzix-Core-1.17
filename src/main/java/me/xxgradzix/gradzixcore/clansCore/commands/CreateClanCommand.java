package me.xxgradzix.gradzixcore.clansCore.commands;

import me.xxgradzix.gradzixcore.clansCore.exceptions.ClanWithThisNameAlreadyExists;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ClanWithThisTagAlreadyExists;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ThisUserAlreadyBelongsToAnotherClan;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ThisUserAlreadyIsALeaderOfAnotherClan;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateClanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length != 2) {
            player.sendMessage("Użycie: /zaloz <nazwa> <tag>");
            return false;
        }

        String clanName = args[0];
        String clanTag = args[1];

        if (!assertName(clanName)) {
            player.sendMessage("Nazwa klanu musi zawierać od 3 do 12 znaków i składać się z liter i cyfr oraz nie może zawierać spacji");
            return false;
        }
        if (!assertTag(clanTag)) {
            player.sendMessage("Tag klanu musi zawierać od 3 do 5 znaków i składać się z liter i cyfr oraz nie może zawierać spacji");
            return false;
        }

        try {
            ClanManager.createClan(clanName, clanTag, player);
        } catch (ClanWithThisTagAlreadyExists clanWithThisTagAlreadyExists) {
            player.sendMessage("Klan z takim tagiem już istnieje");
            return false;
        } catch (ThisUserAlreadyBelongsToAnotherClan e) {
            player.sendMessage("Należysz już do jakiejś gildi");
            return false;
        } catch (ThisUserAlreadyIsALeaderOfAnotherClan e) {
            player.sendMessage("Masz już gildie");
            return false;
        } catch (ClanWithThisNameAlreadyExists e) {
            player.sendMessage("Klan z taką nazwą już istnieje");
            return false;
        }

        player.sendMessage("Utworzyłeś klan z tagiem " + clanTag + " i nazwą " + clanName);

        return true;
    }

    private boolean assertName(String name) {
        if(name.length() > 12 || name.length() < 3) return false;
        if(!name.matches("^[a-zA-Z0-9]*$")) return false;
        if(name.contains(" ")) return false;
        return true;
    }
    private boolean assertTag(String name) {
        if(name.length() > 5 || name.length() < 3) return false;
        if(!name.matches("^[a-zA-Z0-9]*$")) return false;
        if(name.contains(" ")) return false;
        return true;
    }



}
