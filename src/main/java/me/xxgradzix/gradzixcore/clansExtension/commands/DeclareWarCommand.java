package me.xxgradzix.gradzixcore.clansExtension.commands;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import me.xxgradzix.gradzixcore.clansExtension.ClansExtension;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.CantDeclareWarDuringWarTimeException;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.YouAlreadyHaveMaxAmountOfWarsException;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.YouAlreadyHaveWarWithThisGuildException;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeclareWarCommand implements CommandExecutor, TabCompleter {

    private final WarManager warManager;

    public DeclareWarCommand(WarManager warManager) {
        this.warManager = warManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(ClansExtension.ARE_WARS_ACTIVE) {
            player.sendMessage(Messages.YOU_CANNOT_DECLARE_WAR_WHILE_WARS_ARE_ACTIVE);
            return false;
        }


        UserEntity user = UserManager.getOrCreateUserEntity(player);

        Optional<ClanEntity> clanEntityOptional = ClanManager.getClanEntityOfMember(user);

        if(!clanEntityOptional.isPresent()) {
            player.sendMessage(Messages.YOU_DONT_HAVE_CLAN);
            return false;
        }

        ClanEntity clanEntity = clanEntityOptional.get();

        if(args.length != 1) {
            player.sendMessage(Messages.YOU_MUST_SPECIFY_CLAN_TAG);
            return false;
        }
        String guildTag = args[0];

        Optional<ClanEntity> invadedGuildOption = ClanManager.getClanEntityByTag(guildTag);

        if(!invadedGuildOption.isPresent()) {
            player.sendMessage(Messages.CLAN_DOES_NOT_EXISTS);
            return false;
        }
        if (clanEntity.equals(invadedGuildOption.get())) {
            player.sendMessage(Messages.YOU_CANT_DECLARE_WAR_TO_YOUR_OWN_GUILD);
            return false;
        }
        ClanEntity invadedClan = invadedGuildOption.get();

        try {
            warManager.declareWar(clanEntity, invadedClan);
        } catch (YouAlreadyHaveMaxAmountOfWarsException e) {
            player.sendMessage(Messages.YOU_ALREADY_HAVE_MAX_AMOUNT_OF_WARS);
            return false;
        } catch (CantDeclareWarDuringWarTimeException e) {
            player.sendMessage(Messages.YOU_CANNOT_DECLARE_WAR_WHILE_WARS_ARE_ACTIVE);
            return false;
        } catch (YouAlreadyHaveWarWithThisGuildException e) {
            player.sendMessage(Messages.You_ARE_ALREADY_IN_WAR_WITH_THIS_CLAN);
            return false;
        }


        player.sendMessage(Messages.YOU_DECLARED_WAR_TO_CLAN_XXXX + invadedClan.getTag());

        invadedClan.getMembersUUIDs().forEach(
                uuid -> {
                    Player p = Bukkit.getPlayer(uuid);
                    if(p != null) p.sendMessage(Messages.CLAN_XXXX_DECLARED_WAR_TO_YOU(clanEntity.getTag()));
                }
        );

        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ArrayList<String> completions = new ArrayList<>();

        if(command.getName().equalsIgnoreCase("wypowiedzwojne")) {
            if(strings.length == 1) {
                ClanManager.getAllClans().forEach(guild -> completions.add(guild.getTag()));
                return completions;
            }
        }
        return null;
    }
}
